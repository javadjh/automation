package com.scanner.demo.mainApp.kartable.viewmodel;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;

import com.scanner.demo.BR;
import com.scanner.demo.WebService.Letter.letterService;
import com.scanner.demo.mainApp.homePage.model.ReceiveLetterRoot;
import com.scanner.demo.mainApp.homePage.model.data;

public class SendKartableVM extends BaseObservable {
    private com.scanner.demo.mainApp.homePage.model.data data;
    private boolean status;
    private String message;
    private Context context;
    private MutableLiveData<ReceiveLetterRoot> sendLetterRootMutableLiveData = new MutableLiveData<>();
    private ReceiveLetterRoot sendLetterRoot;

    public SendKartableVM(Context context) {
        this.context = context;
        getSendLetter();
    }

    private void getSendLetter() {
        letterService letterService = new letterService(context);
        sendLetterRootMutableLiveData = letterService.getSendLetter(null,null,null,null,null,1,50);
        notifyPropertyChanged(BR.sendLetterRootMutableLiveData);
    }


    //getter and setter
    @Bindable
    public com.scanner.demo.mainApp.homePage.model.data getData() {
        return data;
    }

    public void setData(com.scanner.demo.mainApp.homePage.model.data data) {
        this.data = data;
        notifyPropertyChanged(com.scanner.demo.BR.data);
    }
    @Bindable
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
        notifyPropertyChanged(com.scanner.demo.BR.status);
    }
    @Bindable
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        notifyPropertyChanged(com.scanner.demo.BR.message);
    }
    @Bindable
    public MutableLiveData<ReceiveLetterRoot> getSendLetterRootMutableLiveData() {
        return sendLetterRootMutableLiveData;
    }

    public void setSendLetterRootMutableLiveData(MutableLiveData<ReceiveLetterRoot> sendLetterRootMutableLiveData) {
        this.sendLetterRootMutableLiveData = sendLetterRootMutableLiveData;
        notifyPropertyChanged(com.scanner.demo.BR.sendLetterRootMutableLiveData);
    }
    @Bindable
    public ReceiveLetterRoot getSendLetterRoot() {
        return sendLetterRoot;
    }

    public void setSendLetterRoot(ReceiveLetterRoot sendLetterRoot) {
        this.sendLetterRoot = sendLetterRoot;
        notifyPropertyChanged(BR.sendLetterRoot);
    }
}
