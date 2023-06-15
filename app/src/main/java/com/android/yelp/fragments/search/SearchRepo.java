package com.android.yelp.fragments.search;

import com.android.laneclosures.data.AkiraRoom;
import com.android.yelp.data.preference.PreferenceData;
import com.android.yelp.data.retrofit.YelpAPI;
import com.android.yelp.pojos.RestaurantModel;
import com.android.yelp.pojos.SearchResponse;
import io.reactivex.rxjava3.core.Single;

public class SearchRepo {
  private YelpAPI yelp;
  private AkiraRoom room;
  private PreferenceData preference;

  public SearchRepo(YelpAPI yelp, AkiraRoom room, PreferenceData preference) {
    this.yelp = yelp;
    this.room = room;
    this.preference = preference;
  }

  public Single<SearchResponse> getRestaurants(
      String location, String term, String sortBy, int limit) {
    return yelp.getRestaurants(location, term, sortBy, limit);
  }

  public void insert(RestaurantModel model) {
    room.getYelpDAO().insert(model);
  }

  public String getLocation() {
    return preference.getLocation();
  }
  
  public int getLimit() {
    return preference.getLimit();
  }
}
