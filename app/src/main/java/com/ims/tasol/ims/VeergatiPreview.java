package com.ims.tasol.ims;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ims.tasol.ims.model.CustomerDetails;

import java.util.ArrayList;
import java.util.List;

import utils.database.IMSDatabaseHandler;

/**
 * Created by tasol on 24/2/17.
 */

public class VeergatiPreview extends Fragment {
    private RecyclerView rv_user;
    IMSDatabaseHandler helperDB;
    String tableName="";
    String custId="";
    CustomerDetails customerDetails=null;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.veergati_preview_screen,container,false);
        helperDB= new IMSDatabaseHandler(getActivity());

        tableName=PreviewActivity.tableName;
        custId=PreviewActivity.custID;
        customerDetails= new CustomerDetails();
        getCustomerDetail();

        return view;

    }

    public void getCustomerDetail(){
        Log.v("@@@Preview"," RedcordCount "+helperDB.countAllRows(tableName));
        customerDetails=helperDB.getSingleCustomerRows(tableName,custId);
    }

}
