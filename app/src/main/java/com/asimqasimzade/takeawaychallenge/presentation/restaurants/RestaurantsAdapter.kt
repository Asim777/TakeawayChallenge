package com.asimqasimzade.takeawaychallenge.presentation.restaurants

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asimqasimzade.takeawaychallenge.R
import com.asimqasimzade.takeawaychallenge.data.model.ui.RestaurantUiModel
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.restaurant_item.view.*

class RestaurantsAdapter : RecyclerView.Adapter<RestaurantsAdapter.RestaurantHolder>() {
    var restaurants = mutableListOf<RestaurantUiModel>()
    private var favoriteSelectedDrawable: Drawable? = null
    private var favoriteNotSelectedDrawable: Drawable? = null

    val favoriteClicked = PublishSubject.create<RestaurantUiModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantHolder {
        val inflater = LayoutInflater.from(parent.context)
        favoriteSelectedDrawable = parent.context.getDrawable(R.drawable.ic_favorite_selected)
        favoriteNotSelectedDrawable =
            parent.context.getDrawable(R.drawable.ic_favorite_not_selected)

        return RestaurantHolder(
            inflater.inflate(R.layout.restaurant_item, parent, false)
        )
    }

    override fun getItemCount() = restaurants.size

    override fun onBindViewHolder(holder: RestaurantHolder, position: Int) {
        holder.bind(restaurants[position])
    }

    fun setData(data: List<RestaurantUiModel>) {
        restaurants = data.toMutableList()
        notifyDataSetChanged()
    }

    inner class RestaurantHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(restaurant: RestaurantUiModel) {
            itemView.restaurant_name.text = restaurant.name
            itemView.restaurant_rating.numStars = restaurant.rating
            itemView.restaurant_status.text = restaurant.status
            itemView.restaurant_best_match_label.visibility =
                if (restaurant.bestMatch) View.VISIBLE else View.GONE
            itemView.restaurant_new_label.visibility =
                if (restaurant.newest) View.VISIBLE else View.GONE
            itemView.restaurant_distance_text.text = restaurant.distance
            itemView.restaurant_delivery_cost_text.text = restaurant.deliveryCosts
            itemView.restaurant_minimal_cost_text.text = restaurant.minCost

            if (favoriteSelectedDrawable != null) {
                itemView.restaurant_favorite_button.setImageDrawable(
                    if (restaurant.isFavorite) favoriteSelectedDrawable else favoriteNotSelectedDrawable
                )
            }

            itemView.restaurant_favorite_button.setOnClickListener {
                favoriteClicked.onNext(restaurant)
            }
        }
    }
}