package com.android.yelp.dialogs.searchoption;

import com.android.yelp.data.preference.PreferenceData;

public class SearchOptionRepo {
  private PreferenceData preference;

  public SearchOptionRepo(PreferenceData preference) {
    this.preference = preference;
  }

  public void setLocation(String value) {
    preference.setLocation(value);
  }

  public void setLimit(int value) {
    preference.setLimit(value);
  }
  
  public String getLocation() {
    return preference.getLocation();
  }
  
  public int getLimit() {
    return preference.getLimit();
  }
}
