package com.ims.tasol.ims;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by tasol on 24/2/17.
 */

public class UserDetailsActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_details_activity);
        toolbar=(Toolbar)findViewById(R.id.toolbar);

        toolbar.setTitle("Stock Details");
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
}
