package com.scanner.demo.WebService;

import com.scanner.demo.loginPage.model.LoginModelBody;
import com.scanner.demo.loginPage.model.LoginModelResponseRoot;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIInterface {

    @POST("/api/v2/account/login")
    Single<LoginModelResponseRoot> login(@Body LoginModelBody loginModelBody);

}