package com.android.yelp.pojos;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class LocationModel {
  private String address1;
  private String address2;
  private String address3;
  private String city;
  
  @SerializedName("zip_code")
  private String zipCode;
  private String country;
  private String state;
  
  @SerializedName("display_address")
  private List<String> displayAddress;

  public String getAddress1() {
    return this.address1;
  }

  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  public String getAddress2() {
    return this.address2;
  }

  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  public String getAddress3() {
    return this.address3;
  }

  public void setAddress3(String address3) {
    this.address3 = address3;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getZipCode() {
    return this.zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getCountry() {
    return this.country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getState() {
    return this.state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public List<String> getDisplayAddress() {
    return this.displayAddress;
  }

  public void setDisplayAddress(List<String> displayAddress) {
    this.displayAddress = displayAddress;
  }
}
