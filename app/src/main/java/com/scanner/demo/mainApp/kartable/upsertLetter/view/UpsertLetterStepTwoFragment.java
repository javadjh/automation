package com.scanner.demo.mainApp.kartable.upsertLetter.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.scanner.demo.R;
import com.scanner.demo.databinding.FragmentUpsertLetterStepTwoBinding;
import com.scanner.demo.mainApp.kartable.upsertLetter.eventInterface.onClicks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class UpsertLetterStepTwoFragment extends Fragment {
    FragmentUpsertLetterStepTwoBinding fragmentUpsertLetterStepTwoBinding;
    List<String> roles = new ArrayList<>();
    SharedPreferences sharedPreferences;
    MaterialSpinner postSpinnerSender;
    Typeface typeface;
    Button choiseReceiverBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentUpsertLetterStepTwoBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_upsert_letter_step_two,container,false);
        setView();
        return fragmentUpsertLetterStepTwoBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferences = requireActivity().getSharedPreferences("information",Context.MODE_PRIVATE);
        roles = Collections.singletonList(sharedPreferences.getString("rolse", null));
        typeface = Typeface.createFromAsset(getActivity().getAssets(),"vazir.ttf");
        postSpinnerSender.setItems(roles);
        postSpinnerSender.setTypeface(typeface);
        choiseReceiverBtn.setOnClickListener(View ->{
            ShowUserBottomSheet showUserBottomSheet = new ShowUserBottomSheet(new onClicks() {
                @Override
                public void listUsers(List<String> usersList) {
                    Toast.makeText(getContext(), usersList.get(0), Toast.LENGTH_SHORT).show();
                }
            });
            showUserBottomSheet.show(requireActivity().getSupportFragmentManager(),"choiseUser");
        });
    }

    private void setView() {
        postSpinnerSender = fragmentUpsertLetterStepTwoBinding.postSpinnerSender;
        choiseReceiverBtn = fragmentUpsertLetterStepTwoBinding.choiseReceiverBtn;
    }
}