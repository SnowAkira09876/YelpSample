package com.android.yelp.recyclerviews.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.ListAdapter;
import com.android.yelp.databinding.ItemRestaurantBinding;
import com.android.yelp.pojos.RestaurantModel;
import com.android.yelp.recyclerviews.diffs.RestaurantDiff;
import com.android.yelp.recyclerviews.holders.RestaurantViewHolder;

public class RestaurantAdapter extends ListAdapter<RestaurantModel, RestaurantViewHolder> {
  private ItemClickListener listener;

  public RestaurantAdapter(RestaurantDiff diff, ItemClickListener listener) {
    super(diff);
    this.listener = listener;
  }

  @Override
  public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new RestaurantViewHolder(
        ItemRestaurantBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
  }

  @Override
  public void onBindViewHolder(RestaurantViewHolder holder, int position) {
    holder.bind(getItem(position), listener);
  }

  public interface ItemClickListener {
    void onItemClick(RestaurantModel model);
  }
}
