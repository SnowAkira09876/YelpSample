package com.android.yelp;

import android.widget.ListView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.util.Pair;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.NavController;
import androidx.navigation.ui.NavigationUI;
import com.android.yelp.databinding.ActivityMainBinding;
import com.android.yelp.dialogs.searchoption.SearchOptionDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.search.SearchBar;
import com.google.android.material.search.SearchView;
import com.google.android.material.snackbar.Snackbar;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
  private ActivityMainBinding binding;
  private DrawerLayout drawerLayout;
  private SearchBar searchbar;
  private NavigationView navView;
  private SearchBar searchBar;
  private SearchView searchView;
  private MainViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    onsetViewBinding();
    onsetViews();
  }

  private void onsetViewBinding() {
    searchbar = binding.searchBar;
    drawerLayout = binding.drawerLayout;
    navView = binding.navigationView;
    searchBar = binding.searchBar;
    searchView = binding.searchView;
  }

  private void onsetViews() {
    setContentView(binding.getRoot());
    searchView.setupWithSearchBar(searchBar);

    NavHostFragment navHostFragment =
        (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
    NavController navController = navHostFragment.getNavController();

    NavigationUI.setupWithNavController(navView, navController);
    NavigationUI.setupWithNavController(searchbar, navController, drawerLayout);

    searchView.addTransitionListener((searchView, previousState, newState) -> {});

    navController.addOnDestinationChangedListener(
        (controller, destination, arguments) -> {
          if (destination.getId() == R.id.nav_search)
            searchBar.getMenu().findItem(R.id.menu_sort).setVisible(true);
          else searchBar.getMenu().findItem(R.id.menu_sort).setVisible(false);
        });

    searchView
        .getEditText()
        .setOnEditorActionListener(
            (v, actionId, event) -> {
              String sText = searchView.getText().toString();

              if (!sText.isEmpty()) {
                viewModel.setSearch(Pair.create(sText, "best_match"));

                searchBar.setTag(sText);
                searchBar.setText(searchView.getText());

              } else searchBar.setText("");

              searchView.hide();

              return true;
            });

    searchBar.setOnMenuItemClickListener(
        menuItem -> {
          if (menuItem.getItemId() == R.id.menu_sort) {
            new MaterialAlertDialogBuilder(this)
                .setTitle("Sort by")
                .setSingleChoiceItems(
                    new String[] {"None", "Best Match", "Rating", "Reviews", "Distance"},
                    1,
                    (dialog, which) -> {
                      ListView lv = ((AlertDialog) dialog).getListView();
                      lv.setTag(Integer.valueOf(which));
                    })
                .setPositiveButton(
                    "Sort",
                    (dialog, which) -> {
                      ListView lv = ((AlertDialog) dialog).getListView();
                      String searchTag = (String) searchBar.getTag();

                      if (lv.getTag() != null && searchTag != null) {
                        Integer selected = (Integer) lv.getTag();
                        switch (selected) {
                          case 0:
                            viewModel.setSearch(Pair.create(searchTag, ""));
                            break;

                          case 1:
                            viewModel.setSearch(Pair.create(searchTag, "best_match"));
                            break;

                          case 2:
                            viewModel.setSearch(Pair.create(searchTag, "rating"));
                            break;

                          case 3:
                            viewModel.setSearch(Pair.create(searchTag, "review_count"));
                            break;

                          case 4:
                            viewModel.setSearch(Pair.create(searchTag, "distance"));
                            break;
                        }
                      }
                    })
                .setNegativeButton("Cancel", (dialog, which) -> {})
                .create()
                .show();
            return true;
          } else if (menuItem.getItemId() == R.id.menu_search_option) {
            new SearchOptionDialog().show(getSupportFragmentManager(), "SearchOptionDialog");
            return true;
          }

          return false;
        });

    // First Launch
    viewModel.setSearch(Pair.create("Restaurant", "best_match"));
    searchBar.setTag("Restaurant");
    searchBar.setText("Restaurant");

    // SnackBar
    viewModel
        .getSnackBar()
        .observe(this, msg -> Snackbar.make(binding.getRoot(), msg, Snackbar.LENGTH_SHORT).show());
  }
}
