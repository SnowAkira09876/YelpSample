package com.android.yelp.fragments.favorites;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.android.laneclosures.data.AkiraRoom;
import com.android.yelp.MainViewModel;
import com.android.yelp.databinding.FragmentFavoritesBinding;
import com.android.yelp.recyclerviews.adapters.RestaurantAdapter;
import com.android.yelp.recyclerviews.diffs.RestaurantDiff;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javax.inject.Inject;

@AndroidEntryPoint
public class FavoritesFragment extends Fragment {
  private FragmentFavoritesBinding binding;
  private FavoritesFactory factory;
  private FavoritesViewModel viewModel;
  private RestaurantAdapter adapter;
  private Disposable dispRestaurant, dispDeleteFavorites;
  private CompositeDisposable disposables;
  private RecyclerView rv;
  private MainViewModel mainViewModel;

  @Inject AkiraRoom room;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    disposables = new CompositeDisposable();
    factory = new FavoritesFactory(room);
    viewModel = new ViewModelProvider(requireActivity(), factory).get(FavoritesViewModel.class);
    mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    adapter =
        new RestaurantAdapter(
            new RestaurantDiff(),
            model -> {
              new MaterialAlertDialogBuilder(requireActivity())
                  .setTitle("Delete Item")
                  .setMessage(
                      String.format("Do you want to delete %s from Favorites?", model.getName()))
                  .setPositiveButton(
                      "Delete",
                      (dialog, which) -> {
                        dispDeleteFavorites =
                            Completable.fromRunnable(() -> viewModel.delete(model))
                                .subscribeOn(Schedulers.io())
                                .subscribe();

                        disposables.add(dispDeleteFavorites);
						
                        mainViewModel.setSnackBar(
                            String.format("%s removed from favorites", model.getName()));
                      })
                  .setNegativeButton("Cancel", (dialog, which) -> {})
                  .create()
                  .show();
            });
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle bundle) {
    binding = FragmentFavoritesBinding.inflate(inflater, parent, false);
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
    rv.setAdapter(adapter);

    dispRestaurant =
        viewModel
            .getRestaurantTable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(list -> adapter.submitList(list));

    disposables.add(dispRestaurant);
  }
}
