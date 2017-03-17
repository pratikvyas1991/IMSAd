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
import android.widget.Toast;

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
    public String TABLE_NAME="USERS";
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

        int rowCount=0;

//        handlerDB.deleteTable(TABLE_NAME);

        Log.v("@@@TAB","Splash Checking Table Existance "+TABLE_NAME);
        if(handlerDB.isTableExist(TABLE_NAME)){
            Log.v("@@@TAB","Table Exist");
            rowCount=handlerDB.countAllRows(TABLE_NAME);
            Log.v("@@TAB"," row count "+rowCount);
        }else{
            Log.v("@@@TAB","No Table Exist");
        }


        if(rowCount>0){
            if(handlerDB.isLoggedIn(TABLE_NAME,"admin")||handlerDB.isLoggedIn(TABLE_NAME,"user")){
                if(handlerDB.isLoggedIn(TABLE_NAME,"admin")){
                    goToAdmin();
                }else if(handlerDB.isLoggedIn(TABLE_NAME,"user")){
                    goToUser();
                }
            }
        }else{
            goToLogin();
        }
    }

    public void goToLogin(){
//        Toast.makeText(SplashActivity.this,"Login",Toast.LENGTH_LONG).show();
        Log.v("@@@WWELogin","WELCOME Login");
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
//        Toast.makeText(SplashActivity.this,"Admin",Toast.LENGTH_LONG).show();
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
//        Toast.makeText(SplashActivity.this,"User",Toast.LENGTH_LONG).show();
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
