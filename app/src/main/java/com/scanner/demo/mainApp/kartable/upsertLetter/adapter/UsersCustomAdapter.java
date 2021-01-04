package com.scanner.demo.mainApp.kartable.upsertLetter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.scanner.demo.R;
import com.scanner.demo.databinding.ItemUsersBinding;
import com.scanner.demo.mainApp.kartable.upsertLetter.model.DataUsersList;

import java.util.List;

public class UsersCustomAdapter extends RecyclerView.Adapter<UsersCustomAdapter.viewHolderUsers> {
    Context context;
    List<DataUsersList> dataUsersList;

    public UsersCustomAdapter(Context context, List<DataUsersList> dataUsersList) {
        this.context = context;
        this.dataUsersList = dataUsersList;
    }

    @NonNull
    @Override
    public viewHolderUsers onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemUsersBinding itemUsersBinding = DataBindingUtil.inflate(inflater, R.layout.item_users,parent,false);
        return new viewHolderUsers(itemUsersBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderUsers holder, int position) {
        holder.itemUsersBinding.setDataUsersList(dataUsersList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataUsersList.size();
    }

    public static class viewHolderUsers extends RecyclerView.ViewHolder {
        ItemUsersBinding itemUsersBinding;
        public viewHolderUsers(@NonNull ItemUsersBinding itemView) {
            super(itemView.getRoot());
            this.itemUsersBinding = itemView;
        }
    }
}
