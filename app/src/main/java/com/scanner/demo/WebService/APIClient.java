package com.scanner.demo.WebService;

import com.scanner.demo.mainApp.homePage.model.ReceiveLetterRoot;
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

                        .addHeader("Authorization", " Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqYXZhZCIsImp0aSI6Ijk1OTBjODNiLWJkMTEtNDRlMi1hMWJkLWFkOGUyZTgxOTYzOCIsInVuaXF1ZV9uYW1lIjoiamF2YWQiLCJpZCI6IjJiMzI0NWMxLWViNjktNGFkZS04NDZiLTg3OTNjYWIwMTEwZiIsIm5iZiI6MTYwOTE3MTExOSwiZXhwIjoxNjExMjQ0NzE5LCJpYXQiOjE2MDkxNzExMTl9.fYEMTAfQGiflYZ67Ua535aqU8nvwYNIo9RC-Ne3IBdQ")
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

    public Single<ReceiveLetterRoot> RECEIVE_LETTER(String searchValueTitle,
                                                    String searchSenderName,
                                                    String urgent,
                                                    String fromDate,
                                                    String toDate){
        return apiInterface.getReceive(searchValueTitle,searchSenderName,urgent,fromDate,toDate,null,1,20);
    }

}
