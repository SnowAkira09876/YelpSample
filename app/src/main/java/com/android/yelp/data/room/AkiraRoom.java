package com.android.laneclosures.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.android.yelp.data.room.ListTypeConverter;
import com.android.yelp.data.room.PojoTypeConverter;
import com.android.yelp.pojos.RestaurantModel;

@Database(
    entities = {RestaurantModel.class},
    exportSchema = false,
    version = 1)
@TypeConverters({ListTypeConverter.class, PojoTypeConverter.class})
public abstract class AkiraRoom extends RoomDatabase {

  public abstract YelpDAO getYelpDAO();
}
