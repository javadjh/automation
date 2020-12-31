package com.scanner.demo.WebService;

import com.scanner.demo.mainApp.homePage.model.ReceiveLetterRoot;
import com.scanner.demo.loginPage.model.LoginModelBody;
import com.scanner.demo.loginPage.model.LoginModelResponseRoot;
import com.scanner.demo.mainApp.letterSingle.model.LetterSingleRoot;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @POST("/api/v2/account/login")
    Single<LoginModelResponseRoot> login(@Body LoginModelBody loginModelBody);

    @GET("/api/v2/letter/received")
    Single<ReceiveLetterRoot> getReceive(@Query("Title") String Title, @Query("SenderName") String SenderName, @Query("Urgency") String Urgency, @Query("From") String From, @Query("To") String To, @Query("NotObserved") Boolean NotObserved, @Query("PageNumber") Integer PageNumber, @Query("PageSize") Integer PageSize);

    @GET("/api/v2/letter/{id}")
    Single<LetterSingleRoot> getLetterSingle(@Path("id") String id);
}