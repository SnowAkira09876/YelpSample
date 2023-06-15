package com.android.yelp.fragments.search;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.android.laneclosures.data.AkiraRoom;
import com.android.yelp.MainViewModel;
import com.android.yelp.data.preference.PreferenceData;
import com.android.yelp.data.retrofit.YelpAPI;
import com.android.yelp.databinding.FragmentSearchBinding;
import com.android.yelp.recyclerviews.adapters.RestaurantAdapter;
import com.android.yelp.recyclerviews.diffs.RestaurantDiff;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import javax.inject.Inject;

@AndroidEntryPoint
public class SearchFragment extends Fragment {
  private FragmentSearchBinding binding;
  private SearchViewModel viewModel;
  private SearchFactory factory;
  private RestaurantAdapter adapter;
  private RecyclerView rv;
  private Disposable dispRestaurant, dispInsertFavorites;
  private CompositeDisposable disposables;
  private MainViewModel mainViewModel;
  private Subject<String> searchSubject = PublishSubject.create();

  @Inject YelpAPI yelp;
  @Inject AkiraRoom room;
  @Inject PreferenceData preference;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    disposables = new CompositeDisposable();
    factory = new SearchFactory(yelp, room, preference);
    viewModel = new ViewModelProvider(requireActivity(), factory).get(SearchViewModel.class);
    mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    adapter =
        new RestaurantAdapter(
            new RestaurantDiff(),
            model -> {
              new MaterialAlertDialogBuilder(requireActivity())
                  .setTitle("Add item")
                  .setMessage(String.format("Do you want to add %s to Favorites?", model.getName()))
                  .setPositiveButton(
                      "Add",
                      (dialog, which) -> {
                        dispInsertFavorites =
                            Completable.fromRunnable(() -> viewModel.insert(model))
                                .subscribeOn(Schedulers.io())
                                .subscribe();

                        disposables.add(dispInsertFavorites);

                        mainViewModel.setSnackBar(
                            String.format("%s added to favorites", model.getName()));
                      })
                  .setNegativeButton("Cancel", (dialog, which) -> {})
                  .create()
                  .show();
            });
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle bundle) {
    binding = FragmentSearchBinding.inflate(inflater, parent, false);
    onsetViewBinding();
    onsetViews();
    return binding.getRoot();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    disposables.clear();
  }

  private void onsetViewBinding() {
    rv = binding.rv;
  }

  private void onsetViews() {
    mainViewModel
        .getSearch()
        .observe(
            getViewLifecycleOwner(),
            pair -> {
              dispRestaurant =
                  viewModel
                      .getRestaurants(viewModel.getLocation(), pair.first, pair.second, viewModel.getLimit())
                      .subscribeOn(Schedulers.io())
                      .observeOn(AndroidSchedulers.mainThread())
                      .subscribe(
                          response -> adapter.submitList(response.getRestaurants()),
                          System.out::print);

              disposables.add(dispRestaurant);
            });

    rv.setAdapter(adapter);
  }
}
