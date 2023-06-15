package com.android.yelp.fragments.search;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.android.laneclosures.data.AkiraRoom;
import com.android.yelp.data.preference.PreferenceData;
import com.android.yelp.data.retrofit.YelpAPI;

public class SearchFactory implements ViewModelProvider.Factory {

  private YelpAPI yelp;
  private AkiraRoom room;
  private PreferenceData preference;

  public SearchFactory(YelpAPI yelp, AkiraRoom room, PreferenceData preference) {
    this.yelp = yelp;
    this.room = room;
    this.preference = preference;
  }

  @NonNull
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    if (modelClass.isAssignableFrom(SearchViewModel.class)) {
      return modelClass.cast(new SearchViewModel(yelp, room, preference));
    }
    throw new IllegalArgumentException("Unknown ViewModel class");
  }
}
