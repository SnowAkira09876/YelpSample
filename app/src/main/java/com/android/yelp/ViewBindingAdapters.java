package com.android.yelp;

import androidx.databinding.BindingAdapter;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;
import java.util.List;
import com.android.yelp.pojos.CategoryModel;

public class ViewBindingAdapters {
  @BindingAdapter("imageUrl")
  public static void loadImage(ShapeableImageView imageView, String url) {
    if (!url.isEmpty()) {
      Picasso.get().load(url).resize(500, 500).into(imageView);
    }
  }

  public static String categoryListToString(List<CategoryModel> categories) {
    StringBuilder stringBuilder = new StringBuilder();
    for (CategoryModel category : categories) {
      stringBuilder.append(category.getTitle());
      stringBuilder.append(", ");
    }
    if (stringBuilder.length() > 2) {
      stringBuilder.setLength(stringBuilder.length() - 2);
    }
    return stringBuilder.toString();
  }

  public static String locationListToString(List<String> addresses) {
    StringBuilder stringBuilder = new StringBuilder();
    for (String address : addresses) {
      stringBuilder.append(address);
      stringBuilder.append(", ");
    }
    if (stringBuilder.length() > 2) {
      stringBuilder.setLength(stringBuilder.length() - 2);
    }
    return stringBuilder.toString();
  }
}
