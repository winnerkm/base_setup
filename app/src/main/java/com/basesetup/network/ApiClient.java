package com.basesetup.network;

import android.content.Context;
import android.util.Log;

import com.basesetup.model.ResponseModel;
import com.basesetup.utilities.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.greenrobot.event.EventBus;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kuliza-234 on 05/09/17.
 */

public class ApiClient {

    public static final String TAG = ApiClient.class.getSimpleName();
    public static final String BASE_URL = "https://hn.algolia.com/api/v1/";

    private APIService mApiService;

    private static ApiClient instance;
    private static Retrofit retrofit;
    private Context mContext;

    private ApiClient(Context context) {
        mContext = context.getApplicationContext();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        mApiService = getAPIServiceEndPoint();
    }

    public APIService getAPIServiceEndPoint() {
        if (mApiService == null) {
            mApiService = retrofit.create(APIService.class);
        }
        return mApiService;
    }

    public static ApiClient getInstance(Context context) {
        if (instance == null) {
            instance = new ApiClient(context);
        }
        return instance;
    }

    public interface APIService {

        @GET("search")
        Call<ResponseModel> getHackerNews(@Query("query") String userQuery, @Query("page") int page);

    }

    public void apiCall(String query, int page) {
        Call<ResponseModel> call = mApiService.getHackerNews(query, page);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful()) {
                    EventBus.getDefault().post(response.body());
                } else {
                    onApiFailure(response.errorBody(),Constants.SomethingWentWrong);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                onApiFailure(null,t.getMessage());
            }
        });
    }

    private void onApiFailure(ResponseBody response,String message) {
        if (response != null) {
            Log.d(TAG,response.toString());
        }
        EventBus.getDefault().post(new ErrorModel(message));
    }

}
