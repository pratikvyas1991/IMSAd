package com.ims.tasol.ims;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ims.tasol.ims.model.CustomerDetails;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import utils.database.IMSDatabaseHandler;

/**
 * Created by tasol on 24/2/17.
 */

public class PreviewActivity extends AppCompatActivity {
    Toolbar toolPreview;
    ViewPager viewPagerPreview;
    TabLayout tabLayoutPreview;
    LinearLayout parentLayout;
    Button btnPreview;
    TextView tvDate,tvTime;
    public static String custID="";
    public static String tableName="";
    CustomerDetails details=null;
    CustomerDetails customerDetails=null;
    IMSDatabaseHandler helperDB;
    public String CUST_TABLE_NAME="CUST_DETAILS";
    AVLoadingIndicatorView avi;
    LinearLayout frameLayout;

    ArrayList<String> custColumnsNames= new ArrayList<>();
    ArrayList<String> custColumnsTypes= new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_activity);
        toolPreview = (Toolbar) findViewById(R.id.toolPreview);
        toolPreview.setTitle("Preview");
        setSupportActionBar(toolPreview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolPreview.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tabLayoutPreview=(TabLayout)findViewById(R.id.tabLayoutPreview);
        viewPagerPreview=(ViewPager)findViewById(R.id.viewPagerPreview);

        avi=(AVLoadingIndicatorView)findViewById(R.id.avi);
        helperDB= new IMSDatabaseHandler(PreviewActivity.this);

        frameLayout=(LinearLayout)findViewById(R.id.frameLayout);
        setupViewPager(viewPagerPreview);
        tabLayoutPreview.setupWithViewPager(viewPagerPreview);
        setUpTabIcons();
        custID=getIntent().getStringExtra("custID");
        tableName=getIntent().getStringExtra("tableName");
        details= new CustomerDetails();
//        details=(CustomerDetails) getIntent().getSerializableExtra("custClass");
    }

    private void setUpTabIcons(){
        LayoutInflater inflater =LayoutInflater.from(PreviewActivity.this);

        tabLayoutPreview.setSelectedTabIndicatorColor(getResources().getColor(R.color.tab_text_color));

        View userDet = inflater.inflate(R.layout.user_preview_tab_item, null);
        tabLayoutPreview.getTabAt(0).setCustomView(userDet);

        View veergati = inflater.inflate(R.layout.veergati_tab_item, null);
        tabLayoutPreview.getTabAt(1).setCustomView(veergati);

        View gauravT = inflater.inflate(R.layout.gaurav_trading_tab_item, null);
        tabLayoutPreview.getTabAt(2).setCustomView(gauravT);
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new UserDetailPreview(), "First");
        adapter.addFragment(new VeergatiPreview(), "Second");
        adapter.addFragment(new GauravTradingPreview(), "Third");
//        adapter.addFragment(new HistoryTab(), "Third");
        viewPager.setAdapter(adapter);
    }
    public class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList= new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentTitleList.size();
        }
        public void addFragment(Fragment fragment, String title){
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.preview_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_print) {

            avi.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            createUserTable();
            insertCustFromPreview();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    avi.setVisibility(View.GONE);
                    Snackbar snackbar=Snackbar.make(frameLayout,"User Saved Sucessfully",Snackbar.LENGTH_LONG);
                    snackbar.show();
                    Intent intent= new Intent(PreviewActivity.this,IMSHomeActivity.class);
                    startActivity(intent);
                }
            },7000);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void createUserTable(){
        customerDetails = new CustomerDetails();

        helperDB.setTABLE_NAME(CUST_TABLE_NAME);

        if(customerDetails.getTableColumnsName().size()>0){
            custColumnsNames=customerDetails.getTableColumnsName();
            custColumnsTypes=customerDetails.getTableColumnsType();
        }

        helperDB.setColumnNameList(custColumnsNames);
        helperDB.setColumnTypeList(custColumnsTypes);
        helperDB.addTable();
    }
    public void insertCustFromPreview(){
        helperDB.copyTableValue("CUST_DETAILS_TEMP","CUST_DETAILS");
        helperDB.deletValuesFromTable("CUST_DETAILS_TEMP");

    }
}
