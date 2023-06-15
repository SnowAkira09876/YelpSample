package com.android.yelp.fragments.search;

import androidx.lifecycle.ViewModel;
import com.android.laneclosures.data.AkiraRoom;
import com.android.yelp.data.preference.PreferenceData;
import com.android.yelp.data.retrofit.YelpAPI;
import com.android.yelp.pojos.RestaurantModel;
import com.android.yelp.pojos.SearchResponse;
import io.reactivex.rxjava3.core.Single;

public class SearchViewModel extends ViewModel {
  private SearchRepo repo;

  public SearchViewModel(YelpAPI yelp, AkiraRoom room, PreferenceData preference) {
    repo = new SearchRepo(yelp, room, preference);
  }
  
  public Single<SearchResponse> getRestaurants(String location, String term, String sortBy, int limit) {
    return repo.getRestaurants(location, term, sortBy, limit);
  }
  
  public void insert(RestaurantModel model) {
    repo.insert(model);
  }
  
  public String getLocation() {
    return repo.getLocation();
  }
  
  public int getLimit() {
    return repo.getLimit();
  }
}
