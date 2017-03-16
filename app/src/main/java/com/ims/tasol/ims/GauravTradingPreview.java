package com.ims.tasol.ims;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tasol on 24/2/17.
 */

public class GauravTradingPreview extends Fragment {
    private RecyclerView rv_user;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gaurav_trading_preview_screen,container,false);
        return view;

    }

}
