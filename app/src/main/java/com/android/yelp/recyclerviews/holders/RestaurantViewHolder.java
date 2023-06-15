package com.android.yelp.recyclerviews.holders;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import com.android.yelp.databinding.ItemRestaurantBinding;
import com.android.yelp.pojos.RestaurantModel;
import com.android.yelp.recyclerviews.adapters.RestaurantAdapter;

public class RestaurantViewHolder extends RecyclerView.ViewHolder {
  private ItemRestaurantBinding binding;
  private Context context;

  public RestaurantViewHolder(ItemRestaurantBinding binding) {
    super(binding.getRoot());
    this.binding = binding;
    this.context = binding.getRoot().getContext();
  }

  public void bind(RestaurantModel model, RestaurantAdapter.ItemClickListener listener) {
    binding.setModel(model);
    itemView.setOnClickListener(v -> listener.onItemClick(model));
  }
}
