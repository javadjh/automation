package com.scanner.demo.WebService.AccountService;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.scanner.demo.CustomClass.AlertDialog;
import com.scanner.demo.CustomClass.MyApplication;
import com.scanner.demo.WebService.APIClient;
import com.scanner.demo.loginPage.model.LoginModelBody;
import com.scanner.demo.loginPage.model.LoginModelResponseRoot;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class account {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<LoginModelResponseRoot> loginModelResponseRootMutableLiveData = new MutableLiveData<>();
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
}
