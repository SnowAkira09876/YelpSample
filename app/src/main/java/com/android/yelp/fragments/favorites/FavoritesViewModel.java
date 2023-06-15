package com.android.yelp.fragments.favorites;

import androidx.lifecycle.ViewModel;
import com.android.laneclosures.data.AkiraRoom;
import io.reactivex.rxjava3.core.Observable;
import java.util.List;
import com.android.yelp.pojos.RestaurantModel;

public class FavoritesViewModel extends ViewModel{
  private FavoritesRepo repo;

  public FavoritesViewModel(AkiraRoom room) {
    repo = new FavoritesRepo(room);
  }

  public Observable<List<RestaurantModel>> getRestaurantTable() {
    return repo.getRestaurantTable();
  }
  
  public void delete(RestaurantModel model) {
    repo.delete(model);
  }
}
