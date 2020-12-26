package com.scanner.demo.loginPage.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.scanner.demo.R;
import com.scanner.demo.databinding.ActivityLoginBinding;
import com.scanner.demo.loginPage.viewmodel.LoginVM;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding loginBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        LoginVM loginVM = new LoginVM();
        loginBinding.setLogin(loginVM);
    }
}