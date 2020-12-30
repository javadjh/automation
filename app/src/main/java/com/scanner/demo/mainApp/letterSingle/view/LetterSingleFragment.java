package com.scanner.demo.mainApp.letterSingle.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scanner.demo.R;
import com.scanner.demo.databinding.FragmentLetterSingleBinding;

public class LetterSingleFragment extends Fragment {
    FragmentLetterSingleBinding fragmentLetterSingleBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentLetterSingleBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_letter_single,container,false);
        return fragmentLetterSingleBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}