package com.android.yelp.dialogs.searchoption;

import android.os.Bundle;
import android.app.Dialog;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import com.android.yelp.MainViewModel;
import com.android.yelp.data.preference.PreferenceData;
import com.android.yelp.databinding.DialogSearchOptionBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;

@AndroidEntryPoint
public class SearchOptionDialog extends DialogFragment {
  private DialogSearchOptionBinding binding;
  private TextInputLayout tl_location, tl_limit;
  private TextInputEditText te_location, te_limit;
  private SearchOptionViewModel viewModel;
  private MainViewModel mainViewModel;
  private SearchOptionFactory factory;

  @Inject PreferenceData preference;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    factory = new SearchOptionFactory(preference);
    viewModel = new ViewModelProvider(requireActivity(), factory).get(SearchOptionViewModel.class);
    mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
  }

  @Override
  public Dialog onCreateDialog(Bundle bundle) {
    binding = DialogSearchOptionBinding.inflate(getLayoutInflater());

    tl_location = binding.tlLocation;
    tl_limit = binding.tlLimit;

    te_location = binding.teLocation;
    te_limit = binding.teLimit;

    te_location.setText(viewModel.getLocation());
    te_limit.setText(String.valueOf(viewModel.getLimit()));

    AlertDialog dialog =
        new MaterialAlertDialogBuilder(requireActivity())
            .setTitle("Search option")
            .setView(binding.getRoot())
            .setPositiveButton("Okay", null)
            .setNegativeButton("Cancel", (d, which) -> {})
            .create();

    dialog.setOnShowListener(
        d -> {
          Button button = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
          button.setOnClickListener(
              view -> {
                String location = te_location.getText().toString();
                String limit = te_limit.getText().toString();

                if (location.isEmpty()) tl_location.setError("Location is empty");
                if (limit.isEmpty()) tl_limit.setError("Limit is empty");

                if (!location.isEmpty() && !limit.isEmpty()) {
                  viewModel.setLocation(location);
                  viewModel.setLimit(Integer.parseInt(limit));
                  mainViewModel.setSnackBar("Settings saved");
                  dialog.dismiss();
                }
              });
        });

    return dialog;
  }
}
