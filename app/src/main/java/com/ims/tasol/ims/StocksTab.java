package com.ims.tasol.ims;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tasol on 24/2/17.
 */

public class StocksTab extends Fragment {
    private RecyclerView rv_stocks;
    List<String> userNames= new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    StocksAdapter stocksAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.stocks_tab_layout,container,false);
        rv_stocks=(RecyclerView)view.findViewById(R.id.rv_stocks);
        linearLayoutManager =new LinearLayoutManager(getActivity());
        rv_stocks.setLayoutManager(linearLayoutManager);
        stocksAdapter = new StocksAdapter();
        userNames.add(null);
        userNames.add("pratik");
        userNames.add("dharmesh");
        userNames.add("neeraj");
        userNames.add("jamal");
        userNames.add("arvind");
        userNames.add("mrugen");
        rv_stocks.setAdapter(stocksAdapter);
        return view;
    }

    private class StocksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


                View parentView=LayoutInflater.from(parent.getContext()).inflate(R.layout.stocks_item_footer,parent,false);
                RecyclerView.ViewHolder holder=new StocksViewHolder(parentView);
                return holder;
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                final StocksViewHolder holderFooter =(StocksViewHolder)holder;
//                holderFooter.user_name.setText(userNames.get(position));
            if(position==3){
                holderFooter.stockItemLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
        }

        @Override
        public int getItemCount() {
            return userNames.size();
        }



        public class StocksViewHolder extends RecyclerView.ViewHolder{
//            public TextView user_name;
            public LinearLayout stockItemLayout;

            public StocksViewHolder(View itemView) {
                super(itemView);
//                user_name=(TextView)itemView.findViewById(R.id.user_name);
                stockItemLayout=(LinearLayout)itemView.findViewById(R.id.stockItemLayout);
            }
        }
    }
}
