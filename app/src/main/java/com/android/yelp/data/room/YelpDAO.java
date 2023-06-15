package com.android.laneclosures.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.android.yelp.pojos.RestaurantModel;
import io.reactivex.rxjava3.core.Observable;
import java.util.List;

@Dao
public interface YelpDAO {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  void insert(RestaurantModel list);

  @Delete
  void delete(RestaurantModel model);

  @Update
  void update(RestaurantModel model);

  @Query("SELECT * FROM RestaurantTable")
  Observable<List<RestaurantModel>> getRestaurantTable();
}
