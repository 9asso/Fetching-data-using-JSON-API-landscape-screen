package com.gtek.testtaskhssoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class HSSOFTAdapter extends RecyclerView.Adapter<HSSOFTAdapter.ViewHolder> {

    private final Activity activity;
    private final List<HSSOFTData> listData;
    private final LayoutInflater inflater;

    public HSSOFTAdapter(Activity activity, List<HSSOFTData> listData) {
        this.listData = listData;
        inflater = LayoutInflater.from(activity);
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.model_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final HSSOFTData storeData = listData.get(position);

        if (position == listData.size() - 1){
            holder.divider.setVisibility(View.GONE);
        }

        holder.name.setText(listData.get(position).getName());
        holder.contact.setText(listData.get(position).getContact());
        holder.location.setText(listData.get(position).getLocation());
        Glide.with(activity).load(storeData.getPicture())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.picture);
        holder.content.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("name", listData.get(position).getName());
            bundle.putString("picture", listData.get(position).getPicture());
            bundle.putString("location", listData.get(position).getLocation());
            bundle.putString("street", listData.get(position).getStreet());
            bundle.putString("dob", listData.get(position).getDOB());
            bundle.putString("email", listData.get(position).getEmail());
            bundle.putString("phone", listData.get(position).getPhone());
            bundle.putString("cell", listData.get(position).getCell());
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            Fragment myFragment = new HSSOFTFragment();
            myFragment.setArguments(bundle);
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment, myFragment).addToBackStack(null).commit();
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, location, contact;
        public ImageView picture;
        public LinearLayout content;
        public View divider;
        public ViewHolder(View itemView) {
            super(itemView);
            this.divider = itemView.findViewById(R.id.divider);
            this.content = itemView.findViewById(R.id.content);
            this.name = itemView.findViewById(R.id.name);
            this.contact = itemView.findViewById(R.id.contact);
            this.location = itemView.findViewById(R.id.location);
            this.picture = itemView.findViewById(R.id.picture);
        }
    }
}
