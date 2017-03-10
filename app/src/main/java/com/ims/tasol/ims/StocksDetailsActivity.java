package com.ims.tasol.ims;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tasol on 24/2/17.
 */

public class StocksDetailsActivity extends AppCompatActivity {

    boolean isEditable;
    Toolbar toolbar;
    LinearLayout btnLayout;
    Button btnEditStock,btnCancel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stocks_details_activity);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        btnLayout=(LinearLayout)findViewById(R.id.btnLayout);
        isEditable=getIntent().getBooleanExtra("isEditable",false);
        btnCancel=(Button)findViewById(R.id.btnCancel);
        btnEditStock=(Button)findViewById(R.id.btnEditStock);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnEditStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addStocksIntent = new Intent(StocksDetailsActivity.this,AddStockActivity.class);
                addStocksIntent.putExtra("isEditStock",true);
                startActivity(addStocksIntent);
            }
        });

        if(isEditable){
            btnLayout.setVisibility(View.VISIBLE);
        }else{
            btnLayout.setVisibility(View.GONE);
        }
        toolbar.setTitle("Stock Details");
        toolbar.setTitleTextColor(getResources().getColor(R.color.tab_text_color));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
