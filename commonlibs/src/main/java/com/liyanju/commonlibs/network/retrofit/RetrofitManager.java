package com.liyanju.commonlibs.network.retrofit;

import com.liyanju.commonlibs.util.StringUtils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by liyanju on 2017/3/24.
 */

public class RetrofitManager {

    private static String SERVER_URL;

    private static RetrofitManager sRetrofitManager;

    private Retrofit mRetrofit;

    private RetrofitManager() {
        if (StringUtils.isEmpty(SERVER_URL)) {
            throw new NullPointerException("SERVER_URL is null !!!");
        }
        mRetrofit = new Retrofit.Builder().baseUrl(SERVER_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static void init(String serverUrl) {
        SERVER_URL = serverUrl;
    }

    public static RetrofitManager getInstance() {
        if (sRetrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (sRetrofitManager == null) {
                    sRetrofitManager = new RetrofitManager();
                }
            }
        }
        return sRetrofitManager;
    }
}
