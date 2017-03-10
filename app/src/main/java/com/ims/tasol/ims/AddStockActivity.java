package com.ims.tasol.ims;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.Calendar;

public class AddStockActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText etProductName,etQuantity,etPrice;
    Spinner spnOrganization;
    String organization[]={"veergati","gaurav trading"};
    Button btnCancel,btnSave;
    boolean isEditStock;
    Button mButton;
    Button mBu;
    ArrayAdapter<String> orgAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        etProductName=(EditText)findViewById(R.id.etProductName);
        etQuantity=(EditText)findViewById(R.id.etQuantity);
        etPrice=(EditText)findViewById(R.id.etPrice);
        spnOrganization=(Spinner)findViewById(R.id.spnOrganization);
        btnSave=(Button)findViewById(R.id.btnSave);
        btnCancel=(Button)findViewById(R.id.btnCancel);
        orgAdapter= new ArrayAdapter<String>(AddStockActivity.this,R.layout.support_simple_spinner_dropdown_item,organization);
        spnOrganization.setAdapter(orgAdapter);
        isEditStock=getIntent().getBooleanExtra("isEditStock",false);

        toolbar.setTitle("Add Stocks");
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.icons));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(isEditStock){
            btnSave.setText("Edit");
            toolbar.setTitle("Edit Stocks");
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEditStock){
                    Snackbar snackbar=Snackbar.make(view,"Stock Edited",Snackbar.LENGTH_LONG);
                    View sbView=snackbar.getView();
                    sbView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    snackbar.show();
                }else{
                    Snackbar snackbar=Snackbar.make(view,"Stock Added",Snackbar.LENGTH_LONG);
                    View sbView=snackbar.getView();
                    sbView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    snackbar.show();
                }
            }
        });
    }
}
