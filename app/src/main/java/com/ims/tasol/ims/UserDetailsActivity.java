package com.ims.tasol.ims;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ims.tasol.ims.model.CustomerDetails;
import com.wang.avi.AVLoadingIndicatorView;

import utils.database.IMSDatabaseHandler;

/**
 * Created by tasol on 24/2/17.
 */

public class UserDetailsActivity extends AppCompatActivity {
    Toolbar toolbar;
    IMSDatabaseHandler helperDB;
    String tableName="";
    String custId="";
    CustomerDetails customerDetails=null;
    AVLoadingIndicatorView avi;

    private TextView titleCustName;
//    private TextView tvCustName;
    private TextView tvCustAge;
    private TextView tvCustGender;
    private TextView tvCustReason;
    private TextView tvCustDate;
    private TextView tvCustTime;
    private TextView tvCustRelativeName;
    private TextView tvCustRelativeRelation;
    private TextView tvCustMobile;
    private TextView tvCustAddress;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_details_activity);

        titleCustName = (TextView)findViewById( R.id.titleCustName );
//        tvCustName = (TextView)findViewById( R.id.tvCustName );
        tvCustAge = (TextView)findViewById( R.id.tvCustAge );
        tvCustGender = (TextView)findViewById( R.id.tvCustGender );
        tvCustReason = (TextView)findViewById( R.id.tvCustReason );
        tvCustDate = (TextView)findViewById( R.id.tvCustDate );
        tvCustTime = (TextView)findViewById( R.id.tvCustTime );
        tvCustRelativeName = (TextView)findViewById( R.id.tvCustRelativeName );
        tvCustRelativeRelation = (TextView)findViewById( R.id.tvCustRelativeRelation );
        tvCustMobile = (TextView)findViewById( R.id.tvCustMobile );
        tvCustAddress = (TextView)findViewById( R.id.tvCustAddress );

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        helperDB= new IMSDatabaseHandler(UserDetailsActivity.this);
        avi=(AVLoadingIndicatorView)findViewById(R.id.avi);

        avi.setVisibility(View.VISIBLE);

        custId=getIntent().getStringExtra("custID");
        tableName=getIntent().getStringExtra("tableName");

        customerDetails= new CustomerDetails();

        getCustomerDetail();

        titleCustName.setText(customerDetails.getCustName());
//        tvCustName.setText(customerDetails.getCustName());
        tvCustAge.setText(customerDetails.getCustAge());
        tvCustGender.setText(customerDetails.getCustGender());
        tvCustReason.setText(customerDetails.getCustReason());
        tvCustDate.setText(customerDetails.getCustDateOfBill());
        tvCustTime.setText(customerDetails.getCustTimeOfBill());
        tvCustRelativeName.setText(customerDetails.getCustRelativeName());
        tvCustRelativeRelation.setText(customerDetails.getCustRelativeRelation());
        tvCustMobile.setText(customerDetails.getCustRelativeMobileNumber());
        tvCustAddress.setText(customerDetails.getCustRelativeAddress());

        toolbar.setTitle("User Details");
        toolbar.setTitleTextColor(getResources().getColor(R.color.cardview_light_background));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void getCustomerDetail(){
        customerDetails=helperDB.getSingleCustomerRows(tableName,custId);
        avi.setVisibility(View.GONE);
    }
}

