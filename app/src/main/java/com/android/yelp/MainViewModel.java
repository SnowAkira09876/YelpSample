package com.android.yelp;

import androidx.core.util.Pair;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
  private final MutableLiveData<Pair<String, String>> search = new MutableLiveData<>();
  private final MutableLiveData<String> snackBar = new MutableLiveData<>();

  public MutableLiveData<Pair<String, String>> getSearch() {
    return this.search;
  }

  public void setSearch(Pair<String, String> search) {
    this.search.setValue(search);
  }

  public MutableLiveData<String> getSnackBar() {
    return this.snackBar;
  }

  public void setSnackBar(String msg) {
    this.snackBar.setValue(msg);
  }
}
