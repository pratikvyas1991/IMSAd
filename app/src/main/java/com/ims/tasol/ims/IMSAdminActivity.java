package com.ims.tasol.ims;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class IMSAdminActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    FloatingActionButton fabAddStocks;
    String sortOrder[]={"Select Order","ascending","descending"};
    ArrayAdapter<String> sortAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imsadmin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new AdminHome()).commit();

        sortAdapter= new ArrayAdapter<String>(IMSAdminActivity.this,R.layout.support_simple_spinner_dropdown_item,sortOrder);

        fabAddStocks = (FloatingActionButton) findViewById(R.id.fabAddStocks);


        fabAddStocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addStocksIntent = new Intent(IMSAdminActivity.this,AddStockActivity.class);
                addStocksIntent.putExtra("isEditStock",false);
                startActivity(addStocksIntent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_tab_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filter) {
            getFilterDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
            if (id == R.id.nav_add_stocks) {
                Intent addStocksIntent = new Intent(IMSAdminActivity.this,AddStockActivity.class);
                addStocksIntent.putExtra("isEditStock",false);
                startActivity(addStocksIntent);
        } else if (id == R.id.nav_logout) {
                getLogoutDialog();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void getLogoutDialog() {
        final Dialog dialog = new Dialog(IMSAdminActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.logout_layout);
        Button btnFilterCancel=(Button)dialog.findViewById(R.id.btnFilterCancel);
        Button btnFilterApply=(Button)dialog.findViewById(R.id.btnFilterApply);


        btnFilterCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnFilterApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logout= new Intent(IMSAdminActivity.this,LoginActivity.class);
                startActivity(logout);
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    private void getFilterDialog() {
        final Dialog dialog = new Dialog(IMSAdminActivity.this);
        int IN_ATTENDEE_INDEX = 0;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.filter_layout);
        String sortOrder = null;
        final TextView tvStartDate=(TextView)dialog.findViewById(R.id.tvStartDate);
        final TextView tvEndDate=(TextView)dialog.findViewById(R.id.tvEndDate);
        Spinner spnFilterName=(Spinner)dialog.findViewById(R.id.spnFilterName);
        Button btnFilterCancel=(Button)dialog.findViewById(R.id.btnFilterCancel);
        Button btnFilterApply=(Button)dialog.findViewById(R.id.btnFilterApply);

        tvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mCurrentDate=Calendar.getInstance();
                final int mYear=mCurrentDate.get(Calendar.YEAR);
                final int mMonth=mCurrentDate.get(Calendar.MONTH);
                final int mDay=mCurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(IMSAdminActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        tvStartDate.setText(" "+i2+"-"+(i1+1)+"-"+i);
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.setTitle("select date");
                datePickerDialog.show();
            }
        });

        tvEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mCurrentDate=Calendar.getInstance();
                final int mYear=mCurrentDate.get(Calendar.YEAR);
                final int mMonth=mCurrentDate.get(Calendar.MONTH);
                final int mDay=mCurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(IMSAdminActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        tvEndDate.setText(" "+i2+"-"+(i1+1)+"-"+i);
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.setTitle("select date");
                datePickerDialog.show();
            }
        });

        btnFilterCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        final String finalSortOrder = sortOrder;
        btnFilterApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Start Date: "+tvStartDate.getText().toString()+" End Date: "+tvEndDate.getText().toString()+" Order: "+ finalSortOrder, Snackbar.LENGTH_LONG).show();
            }
        });

        spnFilterName.setAdapter(sortAdapter);
        sortOrder=spnFilterName.getSelectedItem().toString();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

}
