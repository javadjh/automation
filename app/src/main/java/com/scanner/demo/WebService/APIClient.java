package com.scanner.demo.WebService;

import com.scanner.demo.mainApp.homePage.model.ReceiveLetterRoot;
import com.scanner.demo.loginPage.model.LoginModelBody;
import com.scanner.demo.loginPage.model.LoginModelResponseRoot;
import com.scanner.demo.mainApp.letterSingle.model.LetterSingleRoot;

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
    public static final String BASE_URL = "http://192.168.1.34:5000";
    Retrofit retrofit = null;
    APIInterface apiInterface;
    public APIClient(){

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()

                        .addHeader("Authorization", " Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJtb2hhbW1hZCIsImp0aSI6IjlkZTFjYjEzLWIyMDctNDg1ZS1hN2Y3LTAyYTZlYzA2ODdkNSIsInVuaXF1ZV9uYW1lIjoibW9oYW1tYWQiLCJpZCI6IjgwMzIzMGNlLWNlOTUtNDgwOC1iNGQ4LWJlMDAzMThjZmNjNiIsIm5iZiI6MTYwOTQxNzU1NywiZXhwIjoxNjExNDkxMTU3LCJpYXQiOjE2MDk0MTc1NTd9.pWlCbrSWOSZNmFQ709v-GyHV9YrjrzIlewVENvoLehI")
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
    public Single<ReceiveLetterRoot> RECEIVE_LETTER(String searchValueTitle, String searchSenderName, String urgent, String fromDate, String toDate){
        return apiInterface.getReceive(searchValueTitle,searchSenderName,urgent,fromDate,toDate,null,1,50);
    }
    public Single<LetterSingleRoot> LETTER_SINGLE(String id){
        return apiInterface.getLetterSingle(id);
    }
    public Single<ReceiveLetterRoot> SEND_LETTER(String searchValueTitle, String searchReceiverName, String urgent, String fromDate, String toDate){
        return apiInterface.getSend(searchValueTitle,searchReceiverName,urgent,fromDate,toDate,1,50);
    }

}
