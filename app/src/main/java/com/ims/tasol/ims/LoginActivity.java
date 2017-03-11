package com.ims.tasol.ims;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.wang.avi.AVLoadingIndicatorView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_login);
        final EditText userPassword=(EditText)findViewById(R.id.userPassword);
        final EditText userName=(EditText)findViewById(R.id.userName);
        final Button btn_submit=(Button)findViewById(R.id.btn_submit);
        final AVLoadingIndicatorView avi=(AVLoadingIndicatorView)findViewById(R.id.avi);

        final ImageView login_icon=(ImageView)findViewById(R.id.login_icon);

        Animation animation= AnimationUtils.loadAnimation(LoginActivity.this,R.anim.clockwise);
        login_icon.startAnimation(animation);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if((userName.getText().toString()!=null&&userName.getText().toString().length()>0)&&(userPassword.getText().toString()!=null&&userPassword.getText().toString().length()>0)){

                    avi.setVisibility(View.VISIBLE);
                    userPassword.setVisibility(View.GONE);
                    userName.setVisibility(View.GONE);
                    btn_submit.setVisibility(View.GONE);
                    login_icon.setVisibility(View.GONE);
                    Handler handler= new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(userName.getText().toString().equals("admin")&&userPassword.getText().toString().equals("123")){
                                Intent adminIntent=new Intent(LoginActivity.this,IMSAdminActivity.class);
                                startActivity(adminIntent);
                            }else{
                                Intent homeIntent=new Intent(LoginActivity.this,IMSHomeActivity.class);
                                startActivity(homeIntent);
                            }
                        }
                    },8000);

                }else{
                }
            }
        });

    }
}
