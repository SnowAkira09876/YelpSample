package com.android.yelp.data.preference;

import android.content.SharedPreferences;
import javax.inject.Inject;

public class PreferenceData {
  @Inject SharedPreferences preferences;
  private SharedPreferences.Editor editor;

  @Inject
  public PreferenceData(SharedPreferences preferences) {
    this.preferences = preferences;
    this.editor = preferences.edit();
  }

  public void setLocation(String value) {
    editor.putString("location", value);
    editor.apply();
  }

  public String getLocation() {
    return preferences.getString("location", "Montréal (Canada)");
  }
  
  public void setLimit(int value) {
    editor.putInt("limit", value);
    editor.apply();
  }

  public int getLimit() {
    return preferences.getInt("limit", 20);
  }
}
