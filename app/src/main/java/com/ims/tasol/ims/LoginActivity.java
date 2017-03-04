package com.ims.tasol.ims;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_login);
        Button btn_submit=(Button)findViewById(R.id.btn_submit);
        ImageView login_icon=(ImageView)findViewById(R.id.login_icon);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent= new Intent(LoginActivity.this,SubMainActivity.class);
                startActivity(homeIntent);
            }
        });

    }
}
