package com.android.yelp.pojos;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SearchResponse {

  @SerializedName("businesses")
  private List<RestaurantModel> restaurants;

  public SearchResponse(List<RestaurantModel> restaurants) {
    this.restaurants = restaurants;
  }

  public List<RestaurantModel> getRestaurants() {
    return this.restaurants;
  }

  public void setRestaurants(List<RestaurantModel> restaurants) {
    this.restaurants = restaurants;
  }
}
