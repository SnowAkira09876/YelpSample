package com.android.yelp.data.room;

import java.util.List;
import com.android.yelp.pojos.CategoryModel;
import androidx.room.TypeConverter;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

public class ListTypeConverter {
  @TypeConverter
  public String fromCategoryList(List<CategoryModel> categoryList) {
    Gson gson = new Gson();
    return gson.toJson(categoryList);
  }

  @TypeConverter
  public List<CategoryModel> toCategoryList(String categoryListString) {
    Gson gson = new Gson();
    Type listType = new TypeToken<List<CategoryModel>>() {}.getType();
    return gson.fromJson(categoryListString, listType);
  }
}
