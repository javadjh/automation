package com.scanner.demo.WebService.AccountService;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.scanner.demo.CustomClass.AlertDialog;
import com.scanner.demo.CustomClass.MyApplication;
import com.scanner.demo.WebService.APIClient;
import com.scanner.demo.loginPage.model.LoginModelBody;
import com.scanner.demo.loginPage.model.LoginModelResponseRoot;
import com.scanner.demo.mainApp.kartable.upsertLetter.model.usersListRoot;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class account {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    //login
    private MutableLiveData<LoginModelResponseRoot> loginModelResponseRootMutableLiveData = new MutableLiveData<>();
    //users
    private MutableLiveData<usersListRoot> usersListRootMutableLiveData = new MutableLiveData<>();
    APIClient apiClient;
    Context context;

    public account(Context context) {
        this.context = context;
    }

    public MutableLiveData<LoginModelResponseRoot> login(String username, String password){
        apiClient = new APIClient();
        compositeDisposable.add(apiClient.LOGIN(new LoginModelBody(username,password))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<LoginModelResponseRoot>() {
                    @Override
                    public void onSuccess(@NonNull LoginModelResponseRoot loginModelResponseRoot) {
                        if(loginModelResponseRoot.isStatus()) {
                            //todo Change_thisCodePlace
                            SharedPreferences sharedPreferences = context.getSharedPreferences("information",Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("rolse",loginModelResponseRoot.getData().getRoles().toString());
                            editor.putString("userId",loginModelResponseRoot.getData().getUserId());
                            editor.putString("username",loginModelResponseRoot.getData().getUsername());
                            editor.putString("fullName",loginModelResponseRoot.getData().getFullName());
                            editor.apply();
                            loginModelResponseRootMutableLiveData.setValue(loginModelResponseRoot);
                        }else{
                            AlertDialog.showAlertDialog(MyApplication.getAppContext(),"خطا در ورود","نام کاربری یا رمز عبور خود را درست وارد کنید");
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        AlertDialog.showAlertDialog(context,"خطا در ورود","نام کاربری یا رمز عبور خود را درست وارد کنید");
                    }
                }));
        return loginModelResponseRootMutableLiveData;
    }

    public MutableLiveData<usersListRoot> users(){
        if(usersListRootMutableLiveData==null){
            apiClient = new APIClient();
            compositeDisposable.add(apiClient.GET_USERS()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableSingleObserver<usersListRoot>() {
                @Override
                public void onSuccess(@NonNull usersListRoot usersListRoot) {
                    usersListRootMutableLiveData.setValue(usersListRoot);
                }

                @Override
                public void onError(@NonNull Throwable e) {

                }
            }));
        }
        return usersListRootMutableLiveData;
    }
}
