package com.asimqasimzade.takeawaychallenge.presentation.restaurants

import android.annotation.SuppressLint
import android.app.Application
import com.asimqasimzade.takeawaychallenge.data.RestaurantsPresentationToUiModelMapper
import com.asimqasimzade.takeawaychallenge.data.model.presentation.RestaurantPresentationModel
import com.asimqasimzade.takeawaychallenge.data.model.ui.RestaurantUiModel
import com.asimqasimzade.takeawaychallenge.domain.*
import com.asimqasimzade.takeawaychallenge.presentation.base.BaseViewModelInputs
import com.asimqasimzade.takeawaychallenge.presentation.base.BaseViewModelOutputs
import com.asimqasimzade.takeawaychallenge.presentation.base.LifeCycleAwareViewModel
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

interface RestaurantsViewModelInputs :
    BaseViewModelInputs {
    fun onLoadRestaurants()
    fun onFavoriteClicked(restaurant: RestaurantUiModel)
    fun onFilter(query: String)
    fun onSortSelected(sortParameter: String)
}

interface RestaurantsViewModelOutputs :
    BaseViewModelOutputs {
    fun showRestaurants(): Observable<List<RestaurantUiModel>>
    fun showFavoriteStatuses(): Observable<List<RestaurantUiModel>>
    fun showSortedRestaurants(): Observable<List<RestaurantPresentationModel>>
    fun showFilteredRestaurants(): Observable<List<RestaurantPresentationModel>>
    fun showFavoriteAddedMessage(): Observable<Unit>
    fun showNoInternetAvailableMessage(): Observable<Unit>
}

class RestaurantsViewModel @Inject constructor(
    application: Application,
    schedulerProvider: SchedulerProvider,
    private val getRestaurantsUseCase: GetRestaurantsUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoritesUseCase: RemoveFavoritesUseCase,
    private val sortRestaurantsUseCase: SortRestaurantsUseCase,
    private val restaurantsPresentationToUiModelMapper: RestaurantsPresentationToUiModelMapper
) : LifeCycleAwareViewModel(application, schedulerProvider),
    RestaurantsViewModelInputs,
    RestaurantsViewModelOutputs {

    override val inputs: RestaurantsViewModelInputs
        get() = this

    override val outputs: RestaurantsViewModelOutputs
        get() = this

    private val showRestaurants = PublishSubject.create<List<RestaurantUiModel>>()
    private val showFavoriteStatuses = PublishSubject.create<List<RestaurantUiModel>>()
    private val showSortedRestaurants = PublishSubject.create<List<RestaurantPresentationModel>>()
    private val filterRestaurants = PublishSubject.create<List<RestaurantPresentationModel>>()
    private val showFavoriteAddedMessage = PublishSubject.create<Unit>()
    private val showNoInternetAvailableMessage = PublishSubject.create<Unit>()

    private var listOfRestaurants = mutableListOf<RestaurantPresentationModel>()
    private var filteredRestaurants = mutableListOf<RestaurantPresentationModel>()

    override fun onLoadRestaurants() {
        getRestaurantsUseCase.execute()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({
                listOfRestaurants = it.toMutableList()
                showRestaurants.onNext(getRestaurantUiModels(listOfRestaurants))
                updateFavorites()
            }, {
                showError.onNext(it.message.toString())
            }).addTo(subscriptions)
    }

    private fun updateFavorites() {
        getFavoritesUseCase.execute()
            .subscribeOn(schedulerProvider.computation())
            .observeOn(schedulerProvider.ui())
            .subscribe({
                updateFavoriteStatuses(it)
                sortRestaurants()
            }, {
                showError.onNext(it.message.toString())
            }).addTo(subscriptions)
    }

    private fun updateFavoriteStatuses(it: List<RestaurantPresentationModel>?) {
        listOfRestaurants.forEach { it.isFavorite = false }
        it?.forEach { favoriteRestaurant ->
            listOfRestaurants.forEachIndexed { index, restaurant ->
                if (favoriteRestaurant.name == restaurant.name) {
                    listOfRestaurants[index].isFavorite = true
                }
            }
        }
        showFavoriteStatuses.onNext(
            getRestaurantUiModels(listOfRestaurants)
        )
    }

    override fun onFavoriteClicked(restaurant: RestaurantUiModel) {
        if (restaurant.isFavorite) {
            getPresentationModel(restaurant)?.let {
                removeFavoritesUseCase.execute(it)
                    .subscribeOn(schedulerProvider.computation())
                    .observeOn(schedulerProvider.ui())
                    .subscribe {
                        updateFavorites()
                    }
            }
        } else {
            getPresentationModel(restaurant)?.let {
                addFavoriteUseCase.execute(it)
                    .subscribeOn(schedulerProvider.computation())
                    .observeOn(schedulerProvider.ui())
                    .subscribe {
                        updateFavorites()
                    }
            }
        }
    }

    private fun getPresentationModel(restaurant: RestaurantUiModel) =
        listOfRestaurants.find {
            it.name == restaurant.name
        }

    @SuppressLint("DefaultLocale")
    override fun onFilter(query: String) {
        filteredRestaurants = listOfRestaurants.filter {
            it.name.trim().toLowerCase().contains(query.toLowerCase())
        }.toMutableList()

        showRestaurants.onNext(getRestaurantUiModels(filteredRestaurants))
    }

    override fun onSortSelected(sortParameter: String) {
        sortRestaurants(sortParameter)
    }

    private fun sortRestaurants(sortParameter: String? = null) {
        sortRestaurantsUseCase.execute(listOfRestaurants, sortParameter)
            .subscribeOn(schedulerProvider.computation())
            .observeOn(schedulerProvider.ui())
            .subscribe({
                listOfRestaurants = it.toMutableList()
                showRestaurants.onNext(getRestaurantUiModels(listOfRestaurants))
            }, {
                showError.onNext(it.message.toString())
            }).addTo(subscriptions)
    }

    private fun getRestaurantUiModels(presentationList: List<RestaurantPresentationModel>): List<RestaurantUiModel> {
        return presentationList.map {
            restaurantsPresentationToUiModelMapper.map(it)
        }
    }

    override fun showRestaurants(): Observable<List<RestaurantUiModel>> =
        showRestaurants.observeOnUiAndHide()

    override fun showFavoriteStatuses(): Observable<List<RestaurantUiModel>> =
        showFavoriteStatuses.observeOnUiAndHide()

    override fun showSortedRestaurants(): Observable<List<RestaurantPresentationModel>> =
        showSortedRestaurants.observeOnUiAndHide()

    override fun showFilteredRestaurants(): Observable<List<RestaurantPresentationModel>> =
        filterRestaurants.observeOnUiAndHide()

    override fun showFavoriteAddedMessage(): Observable<Unit> =
        showFavoriteAddedMessage.observeOnUiAndHide()

    override fun showNoInternetAvailableMessage(): Observable<Unit> =
        showNoInternetAvailableMessage.observeOnUiAndHide()
}

