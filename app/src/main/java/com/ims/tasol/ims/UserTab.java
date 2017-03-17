package com.ims.tasol.ims;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ims.tasol.ims.model.CustomerDetails;

import java.util.ArrayList;
import java.util.List;

import utils.database.IMSDatabaseHandler;

/**
 * Created by tasol on 24/2/17.
 */

public class UserTab extends Fragment {
    private RecyclerView rv_user;
    List<String> userNames= new ArrayList<>();
    private UserAdapter userAdapter;
    LinearLayoutManager linearLayoutManager;
    IMSDatabaseHandler helperDB;
    CustomerDetails customerDetails;
    ArrayList<String> tableColumnsNames= new ArrayList<>();
    ArrayList<String> tableColumnsTypes= new ArrayList<>();
    ArrayList<CustomerDetails> customerList= new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_tab_layout,container,false);
        rv_user=(RecyclerView)view.findViewById(R.id.rv_user);

        helperDB= new IMSDatabaseHandler(getActivity());

        linearLayoutManager = new LinearLayoutManager(getActivity());
        rv_user.setLayoutManager(linearLayoutManager);
        userAdapter = new UserAdapter();
        rv_user.setAdapter(userAdapter);
        Log.v("@@@WWE"," All users");
        customerDetails= new CustomerDetails();

        helperDB.setTABLE_NAME("CUST_DETAILS");
        if(customerDetails.getTableColumnsName().size()>0){
            tableColumnsNames=customerDetails.getTableColumnsName();
            tableColumnsTypes=customerDetails.getTableColumnsType();
        }
        helperDB.setColumnNameList(tableColumnsNames);
        helperDB.setColumnTypeList(tableColumnsTypes);
        helperDB.addTable();
        userDetails ();

        return view;

    }

    private class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View parentView =LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item,parent,false);
            RecyclerView.ViewHolder holder = new ViewHolder(parentView);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ViewHolder viewHolder= (ViewHolder)holder;
            final CustomerDetails customer=customerList.get(position);

            viewHolder.userName.setText(customer.getCustName());
            viewHolder.userDate.setText(customer.getCustDateOfBill());
            viewHolder.userTime.setText(customer.getCustTimeOfBill());

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent userDetailsintent = new Intent(getActivity(),UserDetailsActivity.class);
                    userDetailsintent.putExtra("custID",customer.getCustID());
                    userDetailsintent.putExtra("tableName","CUST_DETAILS");
                    startActivity(userDetailsintent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return customerList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            public TextView userName,userDate,userTime;

            public ViewHolder(View itemView) {
                super(itemView);
                userName=(TextView)itemView.findViewById(R.id.userName);
                userDate=(TextView)itemView.findViewById(R.id.userDate);
                userTime=(TextView)itemView.findViewById(R.id.userTime);
            }
        }
    }

    public void userDetails (){
        Log.v("@@@WE"," total row count "+helperDB.countAllRows("CUST_DETAILS"));
        customerList= helperDB.getAllCustomerRows("CUST_DETAILS");


        Log.v("@@WE"," Final Values ");

        for (int p = 0; p < customerList.size(); p++) {

            Log.v("@@CUST"," Cust "+customerList.toString());
        }
    }
}
