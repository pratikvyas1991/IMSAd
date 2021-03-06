package com.ims.tasol.ims;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ims.tasol.ims.model.Users;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.HashMap;

import utils.database.IMSDatabaseHandler;

public class LoginActivity extends AppCompatActivity {
    LinearLayout layout_home;
    Users users;
    ArrayList<String> tableColumnsNames= new ArrayList<>();
    ArrayList<String> tableColumnsTypes= new ArrayList<>();
    public String TABLE_NAME="USERS";
    HashMap<String,String> finalTableValues= new HashMap<>();
    HashMap<String,String> finalTableValues1= new HashMap<>();
    IMSDatabaseHandler handlerDB= new IMSDatabaseHandler(LoginActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_login);
        final EditText userPassword=(EditText)findViewById(R.id.userPassword);
        final EditText userName=(EditText)findViewById(R.id.userName);
        final Button btn_submit=(Button)findViewById(R.id.btn_submit);
        final AVLoadingIndicatorView avi=(AVLoadingIndicatorView)findViewById(R.id.avi);
        layout_home=(LinearLayout)findViewById(R.id.layout_home);
        final ImageView login_icon=(ImageView)findViewById(R.id.login_icon);
        Animation animation= AnimationUtils.loadAnimation(LoginActivity.this,R.anim.clockwise);
        login_icon.startAnimation(animation);
        users = new Users();

//        handlerDB.deleteTable(TABLE_NAME);

        Log.v("@@@TAB","Checking Table Existance "+TABLE_NAME);

        if(handlerDB.isTableExist(TABLE_NAME)){
            Log.v("@@@TAB","Table Exist");
        }else{
            createTable();
        }

        SharedPreferences preferences=getSharedPreferences("imsPrefernece",LoginActivity.this.MODE_PRIVATE);
        final SharedPreferences.Editor editor=preferences.edit();


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if((userName.getText().toString()!=null&&userName.getText().toString().length()>0)&&(userPassword.getText().toString()!=null&&userPassword.getText().toString().length()>0)){

                    avi.setVisibility(View.VISIBLE);
                    layout_home.setVisibility(View.GONE);

                    final Handler handler= new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(userName.getText().toString().equals("admin")&&userPassword.getText().toString().equals("admin")){
                                Intent adminIntent=new Intent(LoginActivity.this,IMSAdminActivity.class);
                                handlerDB.updateRecord(TABLE_NAME,"isLoggedIn","true","admin");
                                editor.putString("userType","admin");
                                editor.commit();

                                startActivity(adminIntent);
                            }else if(userName.getText().toString().equals("user")&&userPassword.getText().toString().equals("user")){
                                Intent homeIntent=new Intent(LoginActivity.this,IMSHomeActivity.class);
                                handlerDB.updateRecord(TABLE_NAME,"isLoggedIn","true","user");
                                editor.putString("userType","user");
                                editor.commit();

                                startActivity(homeIntent);
                            }
                        }
                    },8000);

                }else{
                }
            }
        });

    }


    public void createTable(){

        handlerDB.setTABLE_NAME(TABLE_NAME);
        if(users.getTableColumnsName().size()>0){
            tableColumnsNames=users.getTableColumnsName();
            tableColumnsTypes=users.getTableColumnsType();
        }

        handlerDB.setColumnNameList(tableColumnsNames);
        handlerDB.setColumnTypeList(tableColumnsTypes);
        handlerDB.addTable();

        finalTableValues.put("userName","admin");
        finalTableValues.put("userPassword","admin");
        finalTableValues.put("isLoggedIn","false");

        finalTableValues1.put("userName","user");
        finalTableValues1.put("userPassword","user");
        finalTableValues1.put("isLoggedIn","false");

        handlerDB.addTableRowFromHash(TABLE_NAME,finalTableValues);
        handlerDB.addTableRowFromHash(TABLE_NAME,finalTableValues1);
    }

}
