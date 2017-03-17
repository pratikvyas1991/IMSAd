package com.ims.tasol.ims;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ims.tasol.ims.model.CustomerDetails;

import utils.database.IMSDatabaseHandler;

/**
 * Created by tasol on 24/2/17.
 */

public class UserDetailPreview extends Fragment {
    private RecyclerView rv_user;
    IMSDatabaseHandler helperDB;
    String tableName="";
    String custId="";
    CustomerDetails customerDetails=null;

    private TextView tvCustName;
    private TextView tvCustAge;
    private TextView tvCustGender;
    private TextView tvCustReason;
    private TextView tvCustRelativeName;
    private TextView tvCustRelativeRelation;
    private TextView tvCustRelativeMobile;
    private TextView tvCustRelativeAddress;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_preview_screen,container,false);
        helperDB= new IMSDatabaseHandler(getActivity());

        tvCustName = (TextView)view.findViewById( R.id.tvCustName );
        tvCustAge = (TextView)view.findViewById( R.id.tvCustAge );
        tvCustGender = (TextView)view.findViewById( R.id.tvCustGender );
        tvCustReason = (TextView)view.findViewById( R.id.tvCustReason );
        tvCustRelativeName = (TextView)view.findViewById( R.id.tvCustRelativeName );
        tvCustRelativeRelation = (TextView)view.findViewById( R.id.tvCustRelativeRelation );
        tvCustRelativeMobile = (TextView)view.findViewById( R.id.tvCustRelativeMobile );
        tvCustRelativeAddress = (TextView)view.findViewById( R.id.tvCustRelativeAddress );

        tableName=PreviewActivity.tableName;
        custId=PreviewActivity.custID;
        customerDetails= new CustomerDetails();
        getCustomerDetail();


        tvCustName.setText(customerDetails.getCustName());
        tvCustAge.setText(customerDetails.getCustAge());
        tvCustGender.setText(customerDetails.getCustGender());
        tvCustReason.setText(customerDetails.getCustReason());
        tvCustRelativeName.setText(customerDetails.getCustRelativeName());
        tvCustRelativeRelation.setText(customerDetails.getCustRelativeRelation());
        tvCustRelativeMobile.setText(customerDetails.getCustRelativeMobileNumber());
        tvCustRelativeAddress.setText(customerDetails.getCustRelativeAddress());

        return view;

    }

    public void getCustomerDetail(){
        Log.v("@@@Preview"," RedcordCount "+helperDB.countAllRows(tableName));
        customerDetails=helperDB.getSingleCustomerRows(tableName,custId);
    }

}
