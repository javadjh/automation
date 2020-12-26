package com.scanner.demo.loginPage.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.scanner.demo.BR;

public class LoginVM extends BaseObservable {
    private String username = "";
    private String password = "";
    private Context context;


    //Logic
    //click Event Login
    public void getUserInformationForLogin (String username , String password){
        if (username.length() > 10 && password.length() > 10) {
            Log.i("tttttt",password + " + "+ username);
        }
    }


    //getter And Setter
    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }
    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }
}
