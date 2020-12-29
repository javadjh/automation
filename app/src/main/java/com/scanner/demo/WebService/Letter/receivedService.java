package com.scanner.demo.WebService.Letter;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.scanner.demo.mainApp.homePage.model.ReceiveLetterRoot;
import com.scanner.demo.WebService.APIClient;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class receivedService  {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<ReceiveLetterRoot> receiveLetterRootMutableLiveData = new MutableLiveData<>();
    APIClient apiClient;
    public receivedService() {
    }
    public MutableLiveData<ReceiveLetterRoot> getReceivedLetter(String Title,
                                                                String SenderName,
                                                                String Urgency,
                                                                String From,
                                                                String To,
                                                                Boolean NotObserved,
                                                                Integer PageNumber,
                                                                Integer PageSize){
        apiClient = new APIClient();
        compositeDisposable = new CompositeDisposable(apiClient.RECEIVE_LETTER(Title,SenderName,Urgency,From,To)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableSingleObserver<ReceiveLetterRoot>() {
            @Override
            public void onSuccess(@NonNull ReceiveLetterRoot receiveLetterRoot) {
                if(receiveLetterRootMutableLiveData.getValue()==null){
                    receiveLetterRootMutableLiveData.setValue(receiveLetterRoot);
                    Log.i("rrrr",receiveLetterRoot.getData().getList().size() + "");
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i("rrrr",e.toString());
            }
        }));
        return receiveLetterRootMutableLiveData;

    }
}
