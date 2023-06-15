package com.android.yelp.pojos;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;
import java.util.List;

@Entity(
    tableName = "RestaurantTable",
    indices = {@Index(value = "yelpId", unique = true)})
public class RestaurantModel {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  private int roomId;

  private String name, price, rating;
  private List<CategoryModel> categories;
  private LocationModel location;

  @SerializedName("id")
  private String yelpId;

  @SerializedName("image_url")
  private String imageUrl;

  @SerializedName("display_phone")
  private String phone;

  @Override
  public boolean equals(Object object) {
    return super.equals(object);
  }

  public int getRoomId() {
    return this.roomId;
  }

  public void setRoomId(int roomId) {
    this.roomId = roomId;
  }

  public String getYelpId() {
    return this.yelpId;
  }

  public void setYelpId(String yelpId) {
    this.yelpId = yelpId;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPrice() {
    return this.price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getRating() {
    return this.rating;
  }

  public void setRating(String rating) {
    this.rating = rating;
  }

  public List<CategoryModel> getCategories() {
    return this.categories;
  }

  public void setCategories(List<CategoryModel> categories) {
    this.categories = categories;
  }

  public String getImageUrl() {
    return this.imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public LocationModel getLocation() {
    return this.location;
  }

  public void setLocation(LocationModel location) {
    this.location = location;
  }

  public String getPhone() {
    return this.phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
}
