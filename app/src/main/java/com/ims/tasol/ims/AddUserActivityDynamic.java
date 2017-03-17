package com.ims.tasol.ims;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ims.tasol.ims.model.CustomerDetails;
import com.wang.avi.AVLoadingIndicatorView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import utils.database.IMSDatabaseHandler;

/**
 * Created by tasol on 24/2/17.
 */

public class AddUserActivityDynamic extends AppCompatActivity {
    Toolbar toolBarAdd;
    LinearLayout parentLayout;
    Button btnPreview;
    TextView tvDate,tvTime;
    public ArrayList<FormDataPojo> rowList= new ArrayList<>();
    public String[] organization={"veergati","gauravtrading"};
    public ArrayAdapter<String> adapter;
    IMSDatabaseHandler helperDB;
    CustomerDetails details;
    int custID=0;
    public String TABLE_NAME="CUST_DETAILS_TEMP";

    CustomerDetails customerDetails;
    ArrayList<String> tableColumnsNames= new ArrayList<>();
    ArrayList<String> tableColumnsTypes= new ArrayList<>();
    ArrayList<String> tableValues= new ArrayList<>();
    HashMap<String,String> finalTableValues= new HashMap<>();
    AVLoadingIndicatorView avi;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user_activity_dynamic);
        toolBarAdd = (Toolbar) findViewById(R.id.toolBarAdd);
        toolBarAdd.setTitle("Add User");
        setSupportActionBar(toolBarAdd);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBarAdd.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        adapter= new ArrayAdapter<String>(AddUserActivityDynamic.this,R.layout.support_simple_spinner_dropdown_item,organization);
        btnPreview = (Button) findViewById(R.id.btnPreview);

        avi=(AVLoadingIndicatorView)findViewById(R.id.avi);
        helperDB= new IMSDatabaseHandler(AddUserActivityDynamic.this);
        details= new CustomerDetails();
//
        createUserTable();
//        deleteTable();

        //populating class




        // when inflating make sure name of pojo and field name is same ,it will help later on

        FormDataPojo dataPojoTitleTextView= new FormDataPojo("personalTitle","title","1","Personal Information","value");

        FormDataPojo dataPojoNameEditText= new FormDataPojo("custName","edit_text","1","Name","value");
        FormDataPojo dataPojoAgeEditText= new FormDataPojo("custAge","number","1","Age","value");
        FormDataPojo dataPojoGenderRadio= new FormDataPojo("custGender","radiobutton","1","Gender","value");
        FormDataPojo dataPojoReasonEditText= new FormDataPojo("custReason","edit_text","1","Reason","value");


        FormDataPojo dataPojoRelativeNameEditText= new FormDataPojo("custRelativeName","edit_text","1","Relative Name","value");
        FormDataPojo dataPojoRelativeRelationEditText= new FormDataPojo("custRelativeRelation","edit_text","1","Relative Relation","value");
        FormDataPojo dataPojoMobileNumberEditText= new FormDataPojo("custRelativeMobileNumber","number","1","Mobile","value");

        FormDataPojo dataPojoRelativeAddressEditText= new FormDataPojo("custRelativeAddress","edit_text","1","Address","value");



//          custID
//         custName
//         custAge
//         custGender;
//         custReason;
//         custRelativeName;
//         custRelativeRelation;
//         custRelativeMobileNumber;
//         custRelativeAddress;
//         custTimeOfBill;
//         custDateOfBill;
//


        rowList.add(dataPojoTitleTextView);

        rowList.add(dataPojoNameEditText);
        rowList.add(dataPojoAgeEditText);
        rowList.add(dataPojoGenderRadio);
        rowList.add(dataPojoReasonEditText);

        rowList.add(dataPojoRelativeNameEditText);
        rowList.add(dataPojoRelativeRelationEditText);
        rowList.add(dataPojoMobileNumberEditText);
        rowList.add(dataPojoRelativeAddressEditText);




        parentLayout = (LinearLayout) findViewById(R.id.parentLayout);
        LayoutInflater inflater = LayoutInflater.from(AddUserActivityDynamic.this);

        for (int k = 0; k < rowList.size(); k++) {

            FormDataPojo row=rowList.get(k);
            if (row.getType().equals("title")) {
                View fieldView = inflater.inflate(R.layout.dynamic_form_section_title, null);
                TextView tvDynamic = (TextView) fieldView.findViewById(R.id.tvTitle);
                tvDynamic.setText(row.getCaption());
                fieldView.setTag(row);
                parentLayout.addView(fieldView);
            }
            if (row.getType().equals("text")) {
                View fieldView = inflater.inflate(R.layout.dynamic_text_view, null);
                AppCompatTextView tvDynamic = (AppCompatTextView) fieldView.findViewById(R.id.tvDynamic);
                tvDynamic.setText(row.getCaption());
                fieldView.setTag(row);
                parentLayout.addView(fieldView);
            }
            if (row.getType().equals("number")) {
                View fieldView = inflater.inflate(R.layout.dynamic_edit_text_number, null);
                TextInputLayout etDynamicLabel=(TextInputLayout)fieldView.findViewById(R.id.etDynamicLabel);
                EditText etDynamicValue=(EditText)fieldView.findViewById(R.id.etDynamicValue);
                etDynamicLabel.setHint(row.getCaption());
                fieldView.setTag(row);
                parentLayout.addView(fieldView);
            }
            if (row.getType().equals("edit_text")) {
                View fieldView = inflater.inflate(R.layout.dynamic_edit_text, null);
                TextInputLayout etDynamicLabel = (TextInputLayout) fieldView.findViewById(R.id.etDynamicLabel);
                etDynamicLabel.setHint(row.getCaption());
                EditText etDynamicValue=(EditText)fieldView.findViewById(R.id.etDynamicValue);
                fieldView.setTag(row);
                parentLayout.addView(fieldView);
            }
            if (row.getType().equals("checkbox")) {
                View fieldView = inflater.inflate(R.layout.dynamic_check_box, null);
                final AppCompatCheckBox cbDynamicValue=(AppCompatCheckBox)fieldView.findViewById(R.id.cbDynamicValue);
                cbDynamicValue.setText(row.getCaption());
                final AppCompatEditText cbDynamicNumber=(AppCompatEditText)fieldView.findViewById(R.id.cbDynamicNumber);
                cbDynamicValue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(cbDynamicValue.isChecked()){
                            cbDynamicNumber.setEnabled(true);
                            cbDynamicNumber.setText("0");
                        }else{
                            cbDynamicNumber.setText("");
                            cbDynamicNumber.setEnabled(false);
                        }
                    }
                });
                fieldView.setTag(row);
                parentLayout.addView(fieldView);
            }
            if (row.getType().equals("radiobutton")) {
                View fieldView = inflater.inflate(R.layout.dynamic_radio_button, null);
                TextView rbDynamicLabel = (TextView) fieldView.findViewById(R.id.rbDynamicLabel);
                rbDynamicLabel.setText(row.getCaption());
                final RadioGroup rbGender =(RadioGroup) fieldView.findViewById(R.id.rbGender);
                final RadioButton rbFemal=(RadioButton)fieldView.findViewById(R.id.rbFemal);
                final RadioButton rbMale=(RadioButton)fieldView.findViewById(R.id.rbMale);
                fieldView.setTag(row);
                parentLayout.addView(fieldView);
            }
            if (row.getType().equals("spinner")) {
                View fieldView = inflater.inflate(R.layout.dynamic_spinner, null);
                final Spinner spnDynamic =(Spinner) fieldView.findViewById(R.id.spnDynamic);
                TextView spnDynamicLabel=(TextView)fieldView.findViewById(R.id.spnDynamicLabel);
                spnDynamicLabel.setText(row.getCaption());
                spnDynamic.setAdapter(adapter);
                fieldView.setTag(row);
                parentLayout.addView(fieldView);
            }
        }

        btnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avi.setVisibility(View.VISIBLE);
                saveUser();
                Intent intent =new Intent(AddUserActivityDynamic.this,PreviewActivity.class);
                intent.putExtra("custID",String.valueOf(custID));
                intent.putExtra("tableName",TABLE_NAME);
                startActivity(intent);
            }
        });

    }

    public void getResult() {
        EditText etDynamic = null,etDynamicValue=null;
        AppCompatTextView tvDynamic = null;
        AppCompatCheckBox cbDynamicValue=null;
        AppCompatEditText cbDynamicNumber=null;
        RadioGroup rbGroup=null;
        Spinner spnDynaimc;
        View view = null;
        FormDataPojo pojo = null;
        FormDataPojo dataPojo;
        for (int j = 0; j < parentLayout.getChildCount(); j++) {
            view = parentLayout.getChildAt(j);
            FormDataPojo field = (FormDataPojo) view.getTag();
            if(field.getType().equalsIgnoreCase("number")){
                etDynamicValue = (EditText) view.findViewById(R.id.etDynamicValue);
                rowList.get(j).setValue(etDynamicValue.getText().toString());
            }
            if(field.getType().equalsIgnoreCase("edit_text")){
                etDynamic = (EditText) view.findViewById(R.id.etDynamicValue);
                rowList.get(j).setValue(etDynamic.getText().toString());
            }
            if(field.getType().equalsIgnoreCase("checkbox")){
                cbDynamicValue = (AppCompatCheckBox) view.findViewById(R.id.cbDynamicValue);
                cbDynamicNumber=(AppCompatEditText)view.findViewById(R.id.cbDynamicNumber);
                if(cbDynamicValue.isChecked()){
                    rowList.get(j).setValue(cbDynamicNumber.getText().toString());
                }else{
                    rowList.get(j).setValue("0");
                }
//                rowList.get(j).setValue(cbDynamicValue.isChecked() ? "1":"0");
            }
            if(field.getType().equalsIgnoreCase("radiobutton")){
                cbDynamicValue = (AppCompatCheckBox) view.findViewById(R.id.cbDynamicValue);
                rbGroup=(RadioGroup)view.findViewById(R.id.rbGender);
                RadioButton rbMale=(RadioButton)view.findViewById(R.id.rbMale);
                RadioButton rbFemal=(RadioButton)view.findViewById(R.id.rbFemal);
                int selectedId=rbGroup.getCheckedRadioButtonId();
                if(selectedId==rbMale.getId()){
                    rowList.get(j).setValue("male");
                }else{
                    rowList.get(j).setValue("female");
                }
            }
            if(field.getType().equalsIgnoreCase("spinner")){
                spnDynaimc = (Spinner) view.findViewById(R.id.spnDynamic);
                rowList.get(j).setValue(spnDynaimc.getSelectedItem().toString());
            }
        }
        for (int z = 0; z < rowList.size(); z++) {
            Log.v("@@@WWE", " Name : " + rowList.get(z).getName()+ " Value : " + rowList.get(z).getValue());
        }

    }


    public void createUserTable(){
        customerDetails = new CustomerDetails();

        helperDB.setTABLE_NAME(TABLE_NAME);


        if(customerDetails.getTableColumnsName().size()>0){
            tableColumnsNames=customerDetails.getTableColumnsName();
            tableColumnsTypes=customerDetails.getTableColumnsType();
        }

        helperDB.setColumnNameList(tableColumnsNames);
        helperDB.setColumnTypeList(tableColumnsTypes);
        helperDB.addTable();
    }

    public void saveUser(){

        helperDB.getTableList();

        Log.v("@@@WWE"," table rows before add");
        helperDB.displayAllRows(TABLE_NAME);

        getResult();
        Calendar calendar=Calendar.getInstance();


        int sec=calendar.get(Calendar.SECOND);
        int min=calendar.get(Calendar.MINUTE);
        int hrs=calendar.get(Calendar.HOUR);

        int day=calendar.get(Calendar.DATE);
        int month=calendar.get(Calendar.MONTH);
        int year=calendar.get(Calendar.YEAR);

        String time=String.valueOf(hrs)+"."+String.valueOf(min)+"."+String.valueOf(sec);
        String date=String.valueOf(day)+"."+String.valueOf(month)+"."+String.valueOf(year);




        custID=helperDB.countAllRows(TABLE_NAME)+1;

        Log.v("@@@WE"," Date "+date);
        Log.v("@@@WE"," Time "+time);
        Log.v("@@@WE"," New Cust ID "+custID);

        if(custID!=0){
            tableValues.add(String.valueOf(custID));
            finalTableValues.put("custID",String.valueOf(custID));
        }
        for (int y = 1; y < rowList.size(); y++) {
            if(!rowList.get(y).getType().equalsIgnoreCase("title")){
                tableValues.add(rowList.get(y).getValue());
                finalTableValues.put(rowList.get(y).getName(),rowList.get(y).getValue());
            }

        }
        customerDetails = new CustomerDetails();
        tableColumnsNames=customerDetails.getTableColumnsName();
        tableValues.add(time);
        finalTableValues.put("custTimeOfBill",String.valueOf(time));
        tableValues.add(date);
        finalTableValues.put("custDateOfBill",String.valueOf(date));



        helperDB.addTableRowFromHash(TABLE_NAME,finalTableValues);






        helperDB.displayAllRows(TABLE_NAME);
        Snackbar snackbar=Snackbar.make(parentLayout,"Customer Added",Snackbar.LENGTH_LONG);
        snackbar.show();
        avi.setVisibility(View.GONE);

        supportFinishAfterTransition();
    }

    public void deleteTable(){
        helperDB.deleteTable(TABLE_NAME);
    }
}
