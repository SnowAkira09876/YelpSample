package com.android.yelp.data.retrofit;

import com.android.yelp.pojos.SearchResponse;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YelpAPI {

  @GET("businesses/search")
  Single<SearchResponse> getRestaurants(
      @Query("location") String location,
      @Query("term") String term,
      @Query("sort_by") String sortBy,
	  @Query("limit") int limit);
}
