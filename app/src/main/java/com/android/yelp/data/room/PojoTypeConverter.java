package com.android.yelp.data.room;

import androidx.room.TypeConverter;
import com.android.yelp.pojos.LocationModel;
import com.google.gson.Gson;

public class PojoTypeConverter {
  @TypeConverter
  public String fromLocationModel(LocationModel locationModel) {
    Gson gson = new Gson();
    return gson.toJson(locationModel);
  }

  @TypeConverter
  public LocationModel toLocationModel(String locationString) {
    Gson gson = new Gson();
    return gson.fromJson(locationString, LocationModel.class);
  }
}
