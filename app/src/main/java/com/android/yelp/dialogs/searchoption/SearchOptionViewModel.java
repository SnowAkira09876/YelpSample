package com.android.yelp.dialogs.searchoption;

import androidx.lifecycle.ViewModel;
import com.android.yelp.data.preference.PreferenceData;

public class SearchOptionViewModel extends ViewModel{
  private SearchOptionRepo repo;

  public SearchOptionViewModel(PreferenceData preference) {
    repo = new SearchOptionRepo(preference);
  }

  public void setLocation(String value) {
    repo.setLocation(value);
  }

  public void setLimit(int value) {
    repo.setLimit(value);
  }
  
  public String getLocation() {
    return repo.getLocation();
  }
  
  public int getLimit() {
    return repo.getLimit();
  }
}
