package com.android.yelp.fragments.favorites;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.android.laneclosures.data.AkiraRoom;

public class FavoritesFactory implements ViewModelProvider.Factory {

  private AkiraRoom room;

  public FavoritesFactory(AkiraRoom room) {
    this.room = room;
  }

  @NonNull
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    if (modelClass.isAssignableFrom(FavoritesViewModel.class)) {
      return modelClass.cast(new FavoritesViewModel(room));
    }
    throw new IllegalArgumentException("Unknown ViewModel class");
  }
}
