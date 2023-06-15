package com.android.yelp.dialogs.searchoption;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.android.laneclosures.data.AkiraRoom;
import com.android.yelp.data.preference.PreferenceData;

public class SearchOptionFactory implements ViewModelProvider.Factory {
  private PreferenceData preference;

  public SearchOptionFactory(PreferenceData preference) {
    this.preference = preference;
  }

  @NonNull
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    if (modelClass.isAssignableFrom(SearchOptionViewModel.class)) {
      return modelClass.cast(new SearchOptionViewModel(preference));
    }
    throw new IllegalArgumentException("Unknown ViewModel class");
  }
}
