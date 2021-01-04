package com.scanner.demo.mainApp.kartable.upsertLetter.viewmodel;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;

import com.scanner.demo.WebService.AccountService.account;
import com.scanner.demo.mainApp.kartable.upsertLetter.model.DataUsersList;
import com.scanner.demo.mainApp.kartable.upsertLetter.model.usersListRoot;

import java.util.List;

public class UsersListVM extends BaseObservable {
    //model Variable
    private List<DataUsersList> data;
    private boolean status;
    private String message;
    //ViewModel Variable
    private Context context;
    private MutableLiveData<usersListRoot> usersListRootMutableLiveData = new MutableLiveData<>();

    //cons
    public UsersListVM(Context context) {
        this.context = context;
        getUsers();
    }

    private void getUsers() {
        account account = new account(context);
        usersListRootMutableLiveData = account.users();

    }

    //getter and setter
    public List<DataUsersList> getData() {
        return data;
    }

    public void setData(List<DataUsersList> data) {
        this.data = data;
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
    public MutableLiveData<usersListRoot> getUsersListRootMutableLiveData() {
        return usersListRootMutableLiveData;
    }

    public void setUsersListRootMutableLiveData(MutableLiveData<usersListRoot> usersListRootMutableLiveData) {
        this.usersListRootMutableLiveData = usersListRootMutableLiveData;
    }
}
