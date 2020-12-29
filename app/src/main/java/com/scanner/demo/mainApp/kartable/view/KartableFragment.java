package com.scanner.demo.mainApp.kartable.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scanner.demo.R;
import com.scanner.demo.databinding.FragmentKartableBinding;
import com.scanner.demo.mainApp.homePage.view.HomePageFragmentDirections;

public class KartableFragment extends Fragment {
    FragmentKartableBinding fragmentKartableBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentKartableBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_kartable,container,false);
        getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        return fragmentKartableBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getFragmentManager().popBackStack();
        fragmentKartableBinding.archiveIcon.setOnClickListener(View ->{
            NavDirections navDirections = KartableFragmentDirections.actionKartableFragmentToArchiveFragment();
            Navigation.findNavController(fragmentKartableBinding.archiveIcon).navigate(navDirections);
        });
        fragmentKartableBinding.fileManagerIcon.setOnClickListener(View->{
            NavDirections navDirections = KartableFragmentDirections.actionKartableFragmentToFileManegerFragment();
            Navigation.findNavController(fragmentKartableBinding.fileManagerIcon).navigate(navDirections);
        });
        fragmentKartableBinding.homePageIcon.setOnClickListener(View->{
            NavDirections navDirections = KartableFragmentDirections.actionKartableFragmentToHomePageFragment();
            Navigation.findNavController(fragmentKartableBinding.kartableIcon).navigate(navDirections);
        });
    }
}