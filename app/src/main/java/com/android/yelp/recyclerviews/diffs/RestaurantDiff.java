package com.android.yelp.recyclerviews.diffs;

import androidx.recyclerview.widget.DiffUtil;
import com.android.yelp.pojos.RestaurantModel;

public class RestaurantDiff extends DiffUtil.ItemCallback<RestaurantModel> {

  @Override
  public boolean areItemsTheSame(RestaurantModel oldItem, RestaurantModel newItem) {
    return oldItem.getYelpId().equals(newItem.getYelpId());
  }

  @Override
  public boolean areContentsTheSame(RestaurantModel oldItem, RestaurantModel newItem) {
    return oldItem.equals(newItem);
  }
}
