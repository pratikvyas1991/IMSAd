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

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_tab_layout,container,false);
        rv_user=(RecyclerView)view.findViewById(R.id.rv_user);
        helperDB= new IMSDatabaseHandler(getActivity());
        userNames.add("XXXXX");
        userNames.add("XXXXX");
        userNames.add("AAAAAAA AAAAAAAAa");
        userNames.add("XXXXX");
        userNames.add("XXXXX");
        userNames.add("AAAAAAA AAAAAAAAa");
        userNames.add("XXXXX");
        userNames.add("XXXXX");
        userNames.add("AAAAAAA AAAAAAAAa");
        userNames.add("XXXXX");
        userNames.add("XXXXX");
        userNames.add("AAAAAAA AAAAAAAAa");
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
            viewHolder.userName.setText(userNames.get(position));
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent userDetailsintent = new Intent(getActivity(),UserDetailsActivity.class);
                    startActivity(userDetailsintent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return userNames.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            public TextView userName;

            public ViewHolder(View itemView) {
                super(itemView);
                userName=(TextView)itemView.findViewById(R.id.userName);
            }
        }
    }

    public void userDetails (){
//        Log.v("@@@WE"," total row count "+helperDB.countAllRows("CUST_DETAILS"));
        helperDB.getAllCustomerRows("CUST_DETAILS");
    }
}
