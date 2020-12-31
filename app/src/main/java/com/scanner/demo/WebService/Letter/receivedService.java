package com.scanner.demo.WebService.Letter;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.scanner.demo.CustomClass.AlertDialog;
import com.scanner.demo.mainApp.homePage.model.ReceiveLetterRoot;
import com.scanner.demo.WebService.APIClient;
import com.scanner.demo.mainApp.letterSingle.model.LetterSingleRoot;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class receivedService extends ViewModel {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    //getReceive
    private MutableLiveData<ReceiveLetterRoot> receiveLetterRootMutableLiveData = new MutableLiveData<>();
    //getSingle
    private MutableLiveData<LetterSingleRoot> singleRootMutableLiveData  = new MutableLiveData<>();
    APIClient apiClient;
    Context context;

    public receivedService(Context context) {
        this.context = context;
    }

    public MutableLiveData<ReceiveLetterRoot> getReceivedLetter(String Title, String SenderName, String Urgency, String From, String To, Boolean NotObserved, Integer PageNumber, Integer PageSize){
        apiClient = new APIClient();
        compositeDisposable.add(apiClient.RECEIVE_LETTER(Title, SenderName, Urgency, From, To)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ReceiveLetterRoot>() {
                    @Override
                    public void onSuccess(@NonNull ReceiveLetterRoot receiveLetterRoot) {
                        if (receiveLetterRootMutableLiveData.getValue() == null) {
                            receiveLetterRootMutableLiveData.postValue(receiveLetterRoot);
                            Log.i("rrrr", receiveLetterRoot.getData().getList().size() + "");
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        AlertDialog.showAlertDialog(context,"خطا در دریافت اطلاعات","خطا در دریافت اطلاعات از سرور رخ داده است لطفا اتصالات خود را بررسی نمایید");
                    }
                }));
        return receiveLetterRootMutableLiveData;
    }

    public MutableLiveData<LetterSingleRoot> getSingleLetter(String id){
        apiClient = new APIClient();
        compositeDisposable.add(apiClient.LETTER_SINGLE(id)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableSingleObserver<LetterSingleRoot>() {
            @Override
            public void onSuccess(@NonNull LetterSingleRoot letterSingleRoot) {
                if (singleRootMutableLiveData.getValue()==null){
                    singleRootMutableLiveData.setValue(letterSingleRoot);
                    Log.i("rrrr",letterSingleRoot.getData().getActionId());
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("rrrrr",e.toString());
            }
        }));
        return singleRootMutableLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
