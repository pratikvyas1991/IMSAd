package com.ims.tasol.ims;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
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
    public ArrayList<FormDataPojo> rowList= new ArrayList<>();

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

        btnPreview = (Button) findViewById(R.id.btnPreview);


        //populating class

        FormDataPojo dataPojoTitleTextView= new FormDataPojo("name","text","1","Personal Information","value");
        FormDataPojo dataPojoFirstNameEditText= new FormDataPojo("firstName","edit_text","1","First Name","value");
        FormDataPojo dataPojoSecondNameEditText= new FormDataPojo("secondName","edit_text","1","Second Name","value");
        FormDataPojo dataPojoBaansCheckBox= new FormDataPojo("baans","checkbox","1","Baans","value");
        FormDataPojo dataPojoChiptaCheckBox= new FormDataPojo("chipta","checkbox","1","Chipta","value");

        rowList.add(dataPojoTitleTextView);
        rowList.add(dataPojoFirstNameEditText);
        rowList.add(dataPojoSecondNameEditText);
        rowList.add(dataPojoBaansCheckBox);
        rowList.add(dataPojoChiptaCheckBox);


        parentLayout = (LinearLayout) findViewById(R.id.parentLayout);
        LayoutInflater inflater = LayoutInflater.from(AddUserActivityDynamic.this);

        for (int k = 0; k < rowList.size(); k++) {

            FormDataPojo row=rowList.get(k);
            if (row.getType().equals("text")&&row.getName().equals("name")) {
                View fieldView = inflater.inflate(R.layout.dynamic_text_view, null);
                AppCompatTextView tvDynamic = (AppCompatTextView) fieldView.findViewById(R.id.tvDynamic);
                tvDynamic.setText(row.getCaption());
                fieldView.setTag(row);
                parentLayout.addView(fieldView);
            }
            if (row.getType().equals("edit_text")) {
                View fieldView = inflater.inflate(R.layout.dynamic_edit_text, null);
                AppCompatTextView etDynamicLabel = (AppCompatTextView) fieldView.findViewById(R.id.etDynamicLabel);
                etDynamicLabel.setText(row.getCaption());
                AppCompatEditText etDynamicValue=(AppCompatEditText)fieldView.findViewById(R.id.etDynamicValue);
                etDynamicValue.setText(row.getCaption());
                fieldView.setTag(row);
                parentLayout.addView(fieldView);
            }
            if (row.getType().equals("checkbox")) {
                View fieldView = inflater.inflate(R.layout.dynamic_check_box, null);
                AppCompatTextView cbDynamicLabel = (AppCompatTextView) fieldView.findViewById(R.id.cbDynamicLabel);
                cbDynamicLabel.setText(row.getCaption());
                AppCompatCheckBox cbDynamicValue=(AppCompatCheckBox)fieldView.findViewById(R.id.cbDynamicValue);
                cbDynamicValue.setText(row.getCaption());
                fieldView.setTag(row);
                parentLayout.addView(fieldView);
            }
        }



        btnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getResult();
            }
        });


    }

    public void getResult() {
        AppCompatEditText etDynamic = null;
        AppCompatTextView tvDynamic = null;
        AppCompatCheckBox cbDynamicValue=null;
        View view = null;
        FormDataPojo pojo = null;
        FormDataPojo dataPojo;
        for (int j = 0; j < parentLayout.getChildCount(); j++) {
            view = parentLayout.getChildAt(j);
            FormDataPojo field = (FormDataPojo) view.getTag();

            if(field.getType().equalsIgnoreCase("edit_text")){
                etDynamic = (AppCompatEditText) view.findViewById(R.id.etDynamicValue);
                rowList.get(j).setValue(etDynamic.getText().toString());
            }
            if(field.getType().equalsIgnoreCase("checkbox")){
                cbDynamicValue = (AppCompatCheckBox) view.findViewById(R.id.cbDynamicValue);
                rowList.get(j).setValue(cbDynamicValue.isChecked() ? "1":"0");
            }
        }
        for (int z = 0; z < rowList.size(); z++) {
            Log.v("@@@WWE", " Name : " + rowList.get(z).getName()+ " Value : " + rowList.get(z).getValue());
        }

    }
}
