package com.android.yelp.fragments.favorites;

import com.android.laneclosures.data.AkiraRoom;
import io.reactivex.rxjava3.core.Observable;
import java.util.List;
import com.android.yelp.pojos.RestaurantModel;

public class FavoritesRepo {
  private AkiraRoom room;

  public FavoritesRepo(AkiraRoom room) {
    this.room = room;
  }

  public Observable<List<RestaurantModel>> getRestaurantTable() {
    return room.getYelpDAO().getRestaurantTable();
  }

  public void delete(RestaurantModel model) {
    room.getYelpDAO().delete(model);
  }
}
