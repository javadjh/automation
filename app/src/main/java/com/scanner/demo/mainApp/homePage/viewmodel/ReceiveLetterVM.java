package com.scanner.demo.mainApp.homePage.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.scanner.demo.BR;
import com.scanner.demo.mainApp.homePage.adapter.ReceiveLetterCustomAdapter;
import com.scanner.demo.mainApp.homePage.model.ListLetter;
import com.scanner.demo.mainApp.homePage.model.ReceiveLetterRoot;
import com.scanner.demo.WebService.Letter.receivedService;

import java.util.List;

public class ReceiveLetterVM extends BaseObservable {
    private com.scanner.demo.mainApp.homePage.model.data data;
    private boolean status;
    private String message;
    private Context context;
    private MutableLiveData<ReceiveLetterRoot> receiveLetterRootMutableLiveData = new MutableLiveData<>();
    private ReceiveLetterRoot receiveLetterRoot;

    //init VM
    public ReceiveLetterVM(Context context) {
        this.context = context;
        getReceivedLetter();
    }

    //logic

    //setPageData
    public void getReceivedLetter(){
        receivedService receivedService = new receivedService(context);
        if(receiveLetterRootMutableLiveData==null) {
            receiveLetterRootMutableLiveData = receivedService.getReceivedLetter(null, null, null, null, null, null, 1, 100);
        }
        notifyPropertyChanged(BR.data);
    }

    //getter and setter

    public MutableLiveData<ReceiveLetterRoot> getReceiveLetterRootMutableLiveData() {
        return receiveLetterRootMutableLiveData;
    }

    public void setReceiveLetterRootMutableLiveData(MutableLiveData<ReceiveLetterRoot> receiveLetterRootMutableLiveData) {
        this.receiveLetterRootMutableLiveData = receiveLetterRootMutableLiveData;
    }
    @Bindable
    public com.scanner.demo.mainApp.homePage.model.data getData() {
        return data;
    }

    public void setData(com.scanner.demo.mainApp.homePage.model.data data) {
        this.data = data;
        notifyPropertyChanged(BR.data);
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
