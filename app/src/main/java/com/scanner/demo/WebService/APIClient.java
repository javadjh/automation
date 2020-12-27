package com.scanner.demo.WebService;

import com.scanner.demo.loginPage.model.LoginModelBody;
import com.scanner.demo.loginPage.model.LoginModelResponseRoot;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient  {
    public static final String BASE_URL = "http://192.168.1.33:5000";
    Retrofit retrofit = null;
    APIInterface apiInterface;
    public APIClient(){

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()

                        .addHeader("Authorization", " Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJJZGVudGlmaWVyIjoiZGZkNmE1ZGYtYmIxZC00ZWE2LTk2NmMtNDllMWY0YjY4MTgyIiwiVXNlck5hbWUiOiJkZXYiLCJGdWxsTmFtZSI6ItmI2K3bjNivINmG2KzZgduMIiwiUmFuayI6Itio2LHZhtin2YXZhyDZhtmI24zYsyIsIkNsYWltcyI6Ild5ZE9iM1JGWkdsMFlXSnNaU2NzSjBaMWJHeEJZMk5sYzNNblhRPT0iLCJSb2xlcyI6IiIsImV4cCI6MTYwOTI1ODMzMH0.hRNJWY7u-0tE7cV93nSWAglOWhinODnXqFL9R4qMJ2Y")
                        .build();
                return chain.proceed(newRequest);
            }
        })
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(APIInterface.class);

    }
    public Single<LoginModelResponseRoot> LOGIN(LoginModelBody loginModelBody){
        return apiInterface.login(loginModelBody);
    }

}
