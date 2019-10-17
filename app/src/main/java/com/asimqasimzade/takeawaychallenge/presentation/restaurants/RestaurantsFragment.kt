package com.asimqasimzade.takeawaychallenge.presentation.restaurants

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProviders
import com.asimqasimzade.takeawaychallenge.R
import com.asimqasimzade.takeawaychallenge.databinding.FragmentRestaurantsBinding
import com.asimqasimzade.takeawaychallenge.presentation.base.BaseFragment
import io.reactivex.rxkotlin.addTo


class RestaurantsFragment : BaseFragment<RestaurantsViewModel, FragmentRestaurantsBinding>() {

    override val bindingLayout = R.layout.fragment_restaurants
    override val viewModel: RestaurantsViewModel by lazy {
        ViewModelProviders.of(this, vmFactory).get(RestaurantsViewModel::class.java)
    }

    private lateinit var restaurantsAdapter: RestaurantsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()

        setupUi()
        setupInputListeners()
        setupOutputListeners()
        getEntities()
    }

    private fun setupUi() {
        restaurantsAdapter = RestaurantsAdapter()
        binding.restaurantsRecyclerView.adapter = restaurantsAdapter
    }

    private fun setupInputListeners() {
        restaurantsAdapter.favoriteClicked
            .subscribe { clickedRestaurant ->
                viewModel.inputs.onFavoriteClicked(clickedRestaurant)
            }.addTo(subscriptions)

        binding.filterBar.addTextChangedListener {
            if (it.toString().isNotEmpty()) {
                viewModel.inputs.onFilter(it.toString())
            } else {
                viewModel.inputs.onLoadRestaurants()
            }
        }
    }

    private fun setupOutputListeners() {
        viewModel.outputs.showRestaurants().subscribe {
            restaurantsAdapter.setData(it)
        }.addTo(subscriptions)

        viewModel.outputs.showFavoriteStatuses().subscribe {
            restaurantsAdapter.setData(it)
        }.addTo(subscriptions)
    }

    private fun getEntities() {
        viewModel.inputs.onLoadRestaurants()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.restaurants_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_sort -> {
                showSortingDialog()
                true
            }
            R.id.action_filter -> {
                if (binding.filterBar.visibility == View.VISIBLE) {
                    binding.filterBar.visibility = View.GONE
                    viewModel.inputs.onLoadRestaurants()
                    hideKeyboard()
                } else {
                    binding.filterBar.visibility = View.VISIBLE
                    binding.filterBar.requestFocus()
                    showKeyboard()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun hideKeyboard() {
        val imm = activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view!!.windowToken, 0)
    }

    private fun showKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
    }

    private fun showSortingDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val sortingOptions = resources.getStringArray(R.array.sorting_options)
        builder.setTitle(getString(R.string.sorting_dialog_title))
        builder.setItems(sortingOptions) { _, which ->
            viewModel.inputs.onSortSelected(sortingOptions[which])
        }
        builder.show()
    }
}