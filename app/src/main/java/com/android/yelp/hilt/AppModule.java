package com.android.yelp.hilt;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.laneclosures.data.AkiraRoom;
import dagger.hilt.android.qualifiers.ApplicationContext;
import androidx.room.Room;
import com.android.yelp.data.retrofit.YelpAPI;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

  @Singleton
  @Provides
  public static OkHttpClient provideOkHttpClient() {
    return new OkHttpClient.Builder()
        .addInterceptor(
            chain -> {
              Request originalRequest = chain.request();
              Request.Builder builder =
                  originalRequest
                      .newBuilder()
                      .header("Accept", "application/json")
                      .header(
                          "Authorization",
                          "Bearer ArNDadhApG2scmJ3Azn-D_u7Sin2JMw4o93dCRGssB6F_hrNYVOsQBfnUW6q5Blb8nblPO3ebDc70X3rk8caVaUFuryV--yOS4UUjdAeOBAIYfOm_rCfnNP4LvWDZHYx");
              Request newRequest = builder.build();
              return chain.proceed(newRequest);
            })
        .build();
  }

  @Singleton
  @Provides
  public static Retrofit provideRetrofit(OkHttpClient okHttpClient) {
    return new Retrofit.Builder()
        .baseUrl("https://api.yelp.com/v3/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .client(okHttpClient)
        .build();
  }

  @Singleton
  @Provides
  public static YelpAPI provideYelpAPI(Retrofit retrofit) {
    return retrofit.create(YelpAPI.class);
  }

  @Provides
  @Singleton
  public static AkiraRoom provideAkiraRoom(@ApplicationContext Context context) {
    return Room.databaseBuilder(context, AkiraRoom.class, "AkiraRoom").build();
  }

  @Singleton
  @Provides
  public static SharedPreferences provideSharedPreferences(@ApplicationContext Context context) {
    return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
  }
}
