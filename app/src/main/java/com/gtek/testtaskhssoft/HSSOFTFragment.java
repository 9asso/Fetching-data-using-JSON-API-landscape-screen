package com.gtek.testtaskhssoft;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class HSSOFTFragment extends Fragment {

    View view;
    public ImageView picture;
    public TextView name, location, street, email, phone, cell, dob;

    public HSSOFTFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.hssoft_fragment, container, false);

        String _name = this.getArguments().getString("name");
        String _picture = this.getArguments().getString("picture");
        String _dob = this.getArguments().getString("dob");
        String _email = this.getArguments().getString("email");
        String _phone = this.getArguments().getString("phone");
        String _cell = this.getArguments().getString("cell");
        String _location = this.getArguments().getString("location");
        String _street = this.getArguments().getString("street");

        name = view.findViewById(R.id.name);
        dob = view.findViewById(R.id.dob);
        email = view.findViewById(R.id.email);
        phone = view.findViewById(R.id.phone);
        cell = view.findViewById(R.id.cell);
        location = view.findViewById(R.id.location);
        street = view.findViewById(R.id.street);
        picture = view.findViewById(R.id.picture);

        name.setText(_name);
        dob.setText(_dob);
        email.setText(_email);
        phone.setText(_phone);
        cell.setText(_cell);
        location.setText(_location);
        street.setText(String.format("Street : %s", _street));
        Glide.with(this).load(_picture)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(picture);

        return view;
    }
}