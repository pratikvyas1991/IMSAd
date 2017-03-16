package com.ims.tasol.ims;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.ims.tasol.ims.model.Users;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import utils.database.IMSDatabaseHandler;

public class SplashActivity extends AppCompatActivity {
    IMSDatabaseHandler handlerDB= new IMSDatabaseHandler(SplashActivity.this);
    AVLoadingIndicatorView avi = null;
    final Handler handler = new Handler();
    ArrayList<String> tableColumnsNames= new ArrayList<>();
    ArrayList<String> tableColumnsTypes= new ArrayList<>();
    Users users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        avi= (AVLoadingIndicatorView)findViewById(R.id.avi);
        avi.setVisibility(View.VISIBLE);
        avi.smoothToShow();

        users = new Users();

        Log.v("@@@We"," Checking Started");

        handlerDB.setTABLE_NAME("USERS");
        if(users.getTableColumnsName().size()>0){
            tableColumnsNames=users.getTableColumnsName();
            tableColumnsTypes=users.getTableColumnsType();
        }

        handlerDB.setColumnNameList(tableColumnsNames);
        handlerDB.setColumnTypeList(tableColumnsTypes);
        handlerDB.addTable();

        int rowCount=handlerDB.countAllRows("USERS");

        if(rowCount>0){
            if(handlerDB.isLoggedIn("USERS","admin")||handlerDB.isLoggedIn("USERS","user")){
                if(handlerDB.isLoggedIn("USERS","admin")){
                    goToAdmin();
                }else if(handlerDB.isLoggedIn("USERS","user")){
                    goToUser();
                }
            }
        }else{
            goToLogin();
        }
    }

    public void goToLogin(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                avi.smoothToHide();
                Intent intent =new Intent(SplashActivity.this,LoginActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(SplashActivity.this,(View)avi, "profile");
                startActivity(intent,options.toBundle());
            }
        },5000);
    }

    public void goToAdmin(){
        Log.v("@@@WWE","WELCOME ADMIN");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                avi.smoothToHide();
                Intent intent =new Intent(SplashActivity.this,IMSAdminActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(SplashActivity.this,(View)avi, "profile");
                startActivity(intent,options.toBundle());
            }
        },5000);
    }
    public void goToUser(){
        Log.v("@@@WWE","WELCOME USER");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                avi.smoothToHide();
                Intent intent =new Intent(SplashActivity.this,IMSHomeActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(SplashActivity.this,(View)avi, "profile");
                startActivity(intent,options.toBundle());
            }
        },5000);
    }

}
