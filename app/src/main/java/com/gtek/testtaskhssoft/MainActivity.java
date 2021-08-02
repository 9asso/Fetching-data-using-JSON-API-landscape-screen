package com.gtek.testtaskhssoft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private JSONObject jsonObject_p;
    private JSONArray jsonArray_p;
    private int i = 0;
    private final int start_p = 0;
    private List<HSSOFTData> rowsRNASAT;
    private HSSOFTAdapter adapterRNASAT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        _init_ids();
        _init_data();
    }

    private void _init_ids() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void _init_data() {
        rowsRNASAT = new ArrayList<>();
        adapterRNASAT = new HSSOFTAdapter(this, rowsRNASAT);

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterRNASAT);

        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonreq = new JsonObjectRequest(Request.Method.GET,
                "https://randomuser.me/api/?results=25", null,
                response -> {
                    try {
                        jsonArray_p = response.getJSONArray("results");
                        for (i = start_p; i < jsonArray_p.length(); i++) {
                            jsonObject_p = jsonArray_p.getJSONObject(i);

                            JSONObject name  = jsonObject_p.getJSONObject("name");
                            JSONObject picture  = jsonObject_p.getJSONObject("picture");
                            JSONObject location  = jsonObject_p.getJSONObject("location");
                            JSONObject street  = location.getJSONObject("street");
                            JSONObject dob  = jsonObject_p.getJSONObject("dob");

                            HSSOFTData item = new HSSOFTData();

                            item.setEmail(jsonObject_p.optString("email"));
                            item.setPhone(jsonObject_p.optString("phone"));
                            item.setCell(jsonObject_p.optString("cell"));
                            item.setContact(jsonObject_p.optString("email") + " | " +
                                    jsonObject_p.getString("phone"));
                            item.setContact(jsonObject_p.optString("email") + " | " +
                                    jsonObject_p.getString("phone"));
                            item.setLocation(location.optString("city") + ", " +
                                    location.getString("country"));
                            item.setName(name.optString("title") + " " +
                                    name.getString("first") + " " +
                                    name.getString("last"));
                            item.setPicture(picture.optString("large"));
                            item.setStreet(street.optString("name") + "/" + street.optString("number"));
                            item.setDOB(dob.optString("date").substring(0,10) + " (" + dob.optString("age") + " YO)");

                            rowsRNASAT.add(item);
                        }
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                    adapterRNASAT.notifyDataSetChanged();
                }, error -> Toast.makeText(this, "error", Toast.LENGTH_SHORT).show());
        rq.add(jsonreq);
    }
}