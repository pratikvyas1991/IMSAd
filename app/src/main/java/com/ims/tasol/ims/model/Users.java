package com.ims.tasol.ims.model;

import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by tasol on 14/3/17.
 */

public class Users {
    public String userName;
    public String userPassword;
    public boolean isLoggedIn;


    public Users() {
    }
    public Users(String userName, String userPassword, boolean isLoggedIn) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.isLoggedIn = isLoggedIn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }







    public ArrayList<String> getTableColumnsName(){
        ArrayList<String> columnNameList= new ArrayList<>();
        Field[] fields= Users.class.getFields();
        for (int i = 0; i < fields.length; i++) {
            if((!fields[i].getName().equals("$change"))&&(!fields[i].getName().equals("serialVersionUID"))){
                columnNameList.add(fields[i].getName());
            }
        }
        return columnNameList;
    }
    public ArrayList<String> getTableColumnsType(){
        ArrayList<String> columnList= new ArrayList<>();
        Field[] fields= Users.class.getFields();
        for (int i = 0; i < fields.length; i++) {
            if((!fields[i].getName().equals("$change"))&&(!fields[i].getName().equals("serialVersionUID"))){
                if(fields[i].getType().toString().equals("class java.lang.String")){
                    columnList.add("TEXT");
                }else if(fields[i].getType().toString().equals("int")){
                    columnList.add("INTEGER");
                }else if(fields[i].getType().toString().equals("boolean")){
                    columnList.add("TEXT");
                }else{
                    columnList.add(fields[i].getType().toString());
                }
            }
        }
        return columnList;
    }
}
