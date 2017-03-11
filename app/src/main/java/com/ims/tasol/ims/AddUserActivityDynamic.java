package com.ims.tasol.ims;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

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


        //populating class

        FormDataPojo dataPojoTitleTextView= new FormDataPojo("personalTitle","title","1","Personal Information","value");
        FormDataPojo dataPojoFirstNameEditText= new FormDataPojo("firstName","edit_text","1","First Name","value");
        FormDataPojo dataPojoSecondNameEditText= new FormDataPojo("secondName","edit_text","1","Second Name","value");
        FormDataPojo dataPojoGenderRadio= new FormDataPojo("gender","radiobutton","1","Gender","value");
        FormDataPojo dataPojoAgeEditText= new FormDataPojo("age","number","1","Age","value");
        FormDataPojo dataPojoOrgSpinner= new FormDataPojo("orga","spinner","1","Organization","value");
        FormDataPojo dataPojoBaansCheckBox= new FormDataPojo("baans","checkbox","1","Baans","value");
        FormDataPojo dataPojoChiptaCheckBox= new FormDataPojo("chipta","checkbox","1","Chipta","value");

        FormDataPojo dataPojoTitleRelTextView= new FormDataPojo("relative","title","1","Relative's Information","value");
        FormDataPojo dataPojoRelFirstNameEditText= new FormDataPojo("relfirstName","edit_text","1","First Name","value");
        FormDataPojo dataPojoRelSecondNameEditText= new FormDataPojo("relsecondName","edit_text","1","Second Name","value");

        FormDataPojo dataPojoProduct1CheckBox= new FormDataPojo("Product1","checkbox","1","Baans","value");
        FormDataPojo dataPojoProduct2CheckBox= new FormDataPojo("Product2","checkbox","1","Chipta","value");
        FormDataPojo dataPojoProduct3CheckBox= new FormDataPojo("Product3","checkbox","1","Baans","value");
        FormDataPojo dataPojoProduct4CheckBox= new FormDataPojo("Product4","checkbox","1","Chipta","value");
        FormDataPojo dataPojoProduct5CheckBox= new FormDataPojo("Product5","checkbox","1","Baans","value");
        FormDataPojo dataPojoProduct6CheckBox= new FormDataPojo("Product6","checkbox","1","Chipta","value");


        rowList.add(dataPojoTitleTextView);
        rowList.add(dataPojoFirstNameEditText);
        rowList.add(dataPojoSecondNameEditText);
        rowList.add(dataPojoGenderRadio);
        rowList.add(dataPojoOrgSpinner);
        rowList.add(dataPojoAgeEditText);
        rowList.add(dataPojoBaansCheckBox);
        rowList.add(dataPojoChiptaCheckBox);

        rowList.add(dataPojoTitleRelTextView);
        rowList.add(dataPojoRelFirstNameEditText);
        rowList.add(dataPojoRelSecondNameEditText);
        rowList.add(dataPojoProduct1CheckBox);
        rowList.add(dataPojoProduct2CheckBox);
        rowList.add(dataPojoProduct3CheckBox);
        rowList.add(dataPojoProduct4CheckBox);
        rowList.add(dataPojoProduct5CheckBox);
        rowList.add(dataPojoProduct6CheckBox);


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
                Intent intent =new Intent(AddUserActivityDynamic.this,PreviewActivity.class);
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
}
