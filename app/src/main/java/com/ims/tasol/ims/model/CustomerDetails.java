package com.ims.tasol.ims.model;

import android.database.Cursor;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by tasol on 14/3/17.
 */

public class CustomerDetails {
    public String custID;
    public String custName;
    public String custAge;
    public String custGender;
    public String custReason;
    public String custRelativeName;
    public String custRelativeRelation;
    public String custRelativeMobileNumber;
    public String custRelativeAddress;
    public String custTimeOfBill;
    public String custDateOfBill;


    public void populateCustomerDetails(Cursor cursor){
        try {
            int custIDIndex=cursor.getColumnIndexOrThrow("custID");
            int custNameIndex=cursor.getColumnIndexOrThrow("custName");
            int custAgeIndex=cursor.getColumnIndexOrThrow("custAge");
            int custGenderIndex=cursor.getColumnIndexOrThrow("custGender");
            int custReasonIndex=cursor.getColumnIndexOrThrow("custReason");
            int custRelativeNameIndex=cursor.getColumnIndexOrThrow("custRelativeName");
            int custRelativeRelationIndex=cursor.getColumnIndexOrThrow("custRelativeRelation");
            int custRelativeMobileNumberIndex=cursor.getColumnIndexOrThrow("custRelativeMobileNumber");
            int custRelativeAddressIndex=cursor.getColumnIndexOrThrow("custRelativeAddress");
            int custTimeOfBillIndex=cursor.getColumnIndexOrThrow("custTimeOfBill");
            int custDateOfBillIndex=cursor.getColumnIndexOrThrow("custDateOfBill");

            this.custID=cursor.getString(custIDIndex);
            this.custName=cursor.getString(custNameIndex);
            this.custAge=cursor.getString(custAgeIndex);
            this.custGender=cursor.getString(custGenderIndex);
            this.custReason=cursor.getString(custReasonIndex);
            this.custRelativeName=cursor.getString(custRelativeNameIndex);
            this.custRelativeRelation=cursor.getString(custRelativeRelationIndex);
            this.custRelativeMobileNumber=cursor.getString(custRelativeMobileNumberIndex);
            this.custRelativeAddress=cursor.getString(custRelativeAddressIndex);
            this.custTimeOfBill=cursor.getString(custTimeOfBillIndex);
            this.custDateOfBill=cursor.getString(custDateOfBillIndex);

        }catch (Exception je){
            je.printStackTrace();
        }
    }

    public CustomerDetails() {
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustAge() {
        return custAge;
    }

    public void setCustAge(String custAge) {
        this.custAge = custAge;
    }

    public String getCustGender() {
        return custGender;
    }

    public void setCustGender(String custGender) {
        this.custGender = custGender;
    }

    public String getCustReason() {
        return custReason;
    }

    public void setCustReason(String custReason) {
        this.custReason = custReason;
    }

    public String getCustRelativeName() {
        return custRelativeName;
    }

    public void setCustRelativeName(String custRelativeName) {
        this.custRelativeName = custRelativeName;
    }

    public String getCustRelativeRelation() {
        return custRelativeRelation;
    }

    public void setCustRelativeRelation(String custRelativeRelation) {
        this.custRelativeRelation = custRelativeRelation;
    }

    public String getCustRelativeMobileNumber() {
        return custRelativeMobileNumber;
    }

    public void setCustRelativeMobileNumber(String custRelativeMobileNumber) {
        this.custRelativeMobileNumber = custRelativeMobileNumber;
    }

    public String getCustRelativeAddress() {
        return custRelativeAddress;
    }

    public void setCustRelativeAddress(String custRelativeAddress) {
        this.custRelativeAddress = custRelativeAddress;
    }

    public String getCustTimeOfBill() {
        return custTimeOfBill;
    }

    public void setCustTimeOfBill(String custTimeOfBill) {
        this.custTimeOfBill = custTimeOfBill;
    }

    public String getCustDateOfBill() {
        return custDateOfBill;
    }

    public void setCustDateOfBill(String custDateOfBill) {
        this.custDateOfBill = custDateOfBill;
    }

    //Method for table columns
    public ArrayList<String> getTableColumnsName(){
        ArrayList<String> columnNameList= new ArrayList<>();
        //Class Name Here
        Field[] fields=CustomerDetails.class.getFields();
        for (int i = 0; i < fields.length; i++) {
            if((!fields[i].getName().equals("$change"))&&(!fields[i].getName().equals("serialVersionUID"))){
                columnNameList.add(fields[i].getName());
            }
        }
        return columnNameList;
    }
    //Method for table columns types
    public ArrayList<String> getTableColumnsType(){
        ArrayList<String> columnList= new ArrayList<>();
        Field[] fields= CustomerDetails.class.getFields();
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
