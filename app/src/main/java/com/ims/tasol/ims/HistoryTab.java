package com.ims.tasol.ims;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tasol on 24/2/17.
 */

public class HistoryTab extends Fragment {
    private RecyclerView rv_history;
    List<String> userNames= new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    HistoryAdapter historyAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.history_tab_layout,container,false);
        rv_history=(RecyclerView)view.findViewById(R.id.rv_history);
        linearLayoutManager =new LinearLayoutManager(getActivity());
        rv_history.setLayoutManager(linearLayoutManager);
        historyAdapter = new HistoryAdapter();
        userNames.add(null);
        userNames.add("pratik");
        userNames.add("dharmesh");
        userNames.add("neeraj");
        userNames.add("jamal");
        userNames.add("arvind");
        userNames.add("mrugen");
        rv_history.setAdapter(historyAdapter);
        return view;
    }

    private class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View parentView=LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item,parent,false);
                RecyclerView.ViewHolder holder=new ViewHolder(parentView);
                return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                final ViewHolder viewHolder =(ViewHolder)holder;
//                holderFooter.user_name.setText(userNames.get(position));
        }

        @Override
        public int getItemCount() {
            return userNames.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            public ViewHolder(View itemView) {
                super(itemView);
            }
        }

    }

}
