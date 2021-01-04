package com.scanner.demo.mainApp.kartable.upsertLetter.view;

import android.gesture.GestureOverlayView;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.scanner.demo.R;
import com.scanner.demo.databinding.FragmentShowUserBottomSheetBinding;
import com.scanner.demo.mainApp.kartable.adapter.DraftLetterCustomAdapter;
import com.scanner.demo.mainApp.kartable.model.DraftResponseRoot;
import com.scanner.demo.mainApp.kartable.upsertLetter.adapter.UsersCustomAdapter;
import com.scanner.demo.mainApp.kartable.upsertLetter.eventInterface.onClicks;
import com.scanner.demo.mainApp.kartable.upsertLetter.model.usersListRoot;
import com.scanner.demo.mainApp.kartable.upsertLetter.viewmodel.UsersListVM;
import com.scanner.demo.mainApp.kartable.viewmodel.DraftKartableVM;

import java.util.ArrayList;
import java.util.List;

public class ShowUserBottomSheet extends BottomSheetDialogFragment {
    public ShowUserBottomSheet(com.scanner.demo.mainApp.kartable.upsertLetter.eventInterface.onClicks onClicks) {
        this.onClicks = onClicks;
    }
    List<String> UsersList = new ArrayList<>();
    onClicks onClicks;
    FragmentShowUserBottomSheetBinding fragmentShowUserBottomSheetBinding;
    UsersListVM usersListVM;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentShowUserBottomSheetBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_show_user_bottom_sheet,container,false);
        return fragmentShowUserBottomSheetBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentShowUserBottomSheetBinding.recyUsers.setAdapter(new UsersCustomAdapter(getContext(),null));
        usersListVM = new UsersListVM(getContext());
        MutableLiveData<usersListRoot> usersListRootMutableLiveData = usersListVM.getUsersListRootMutableLiveData();
        usersListRootMutableLiveData.observe(getActivity(), new Observer<usersListRoot>() {
            @Override
            public void onChanged(usersListRoot usersListRoot) {
                usersListVM.setData(usersListRoot.getData());
                fragmentShowUserBottomSheetBinding.setUsersListVM(usersListVM);
                fragmentShowUserBottomSheetBinding.recyUsers.setAdapter(new
                        UsersCustomAdapter(getContext(),usersListRoot.getData()));
            }
        });
    }
}