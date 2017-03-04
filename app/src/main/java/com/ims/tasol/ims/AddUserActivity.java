package com.ims.tasol.ims;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tasol on 24/2/17.
 */

public class AddUserActivity extends AppCompatActivity {
    Toolbar toolBarAdd;
    private TextView tvAddUSerDate;
    private TextView tvAddUSerTime;
    private EditText etAddUserPersonName;
    private EditText etAddUserPersonAge;
    private EditText etAddUserPersonReason;
    private Spinner spnAddUserPersonGender;
    private EditText etAddUserPersonAddress;
    private EditText etAddUserPersonNameRelative;
    private EditText etAddUserPersonMobile;
    private Spinner spnAddUserPersonRelation;
    private CheckBox cbGTKapoor;
    private EditText etGTKapoor;
    private CheckBox cbGTMakhana;
    private EditText etGTMakhana;
    private CheckBox cbGTRaal;
    private EditText etGTRaal;
    private CheckBox cbGTKharak;
    private EditText etGTKkarak;
    private CheckBox cbGTSingoda;
    private EditText etGTSingoda;
    private CheckBox cbGTJauTil;
    private EditText etGTJauTil;
    private CheckBox cbGTShaulSadi;
    private EditText etGTShaulSadi;
    private CheckBox cbGTShaulSuper;
    private EditText etGTShaulSuper;
    private CheckBox cbGTSaree;
    private EditText etGTSaree;
    private CheckBox cbGTSareeSuper;
    private EditText etGTSareeSuper;
    private CheckBox cbGTPeticoat;
    private EditText etGTPaticoat;
    private CheckBox cbGTBlauz;
    private EditText etGTBlauz;
    private EditText etGTKurta;
    private CheckBox cbGTPajama;
    private EditText etGTPajama;
    private CheckBox cbGTDhooti;
    private EditText etGTDhooti;
    private CheckBox cbGTPagdi;
    private EditText etGTPagdi;
    private CheckBox cbGTTopi;
    private EditText etGTTopi;
    private CheckBox cbGTSamgriSuhag;
    private EditText etGTSamgriSuhag;
    private CheckBox cbGTItra;
    private EditText etGTItra;
    private CheckBox cbGTChaddi;
    private EditText etGTChaddi;
    private CheckBox cbGTBaniyan;
    private EditText etGTBaniyan;
    private CheckBox cbGTTowel;
    private EditText etGTTowel;
    private CheckBox cbVGTBanns;
    private EditText etVGTBanns;
    private CheckBox cbVGTChipta;
    private EditText etVGTChipta;
    private CheckBox cbVGTKafan;
    private EditText etVGTKafan;
    private CheckBox cbVGTMunj;
    private EditText etVGTMunj;
    private CheckBox cbVGTNada;
    private EditText etVGTNada;
    private CheckBox cbVGTSutli;
    private EditText etVGTSutli;
    private CheckBox cbVGTMatki;
    private EditText etVGTMatki;
    private CheckBox cbVGTGulal;
    private EditText etVGTGulal;
    private CheckBox cbVGTPanchRatna;
    private EditText etVGTPanchRatna;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user_activity);
        toolBarAdd=(Toolbar)findViewById(R.id.toolBarAdd);
        toolBarAdd.setTitle("Add User");
        setSupportActionBar(toolBarAdd);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBarAdd.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvAddUSerDate = (TextView)findViewById( R.id.tvAddUSerDate );
        tvAddUSerTime = (TextView)findViewById( R.id.tvAddUSerTime );
        spnAddUserPersonGender = (Spinner)findViewById( R.id.spnAddUserPersonGender );
        spnAddUserPersonRelation = (Spinner)findViewById( R.id.spnAddUserPersonRelation );

        etAddUserPersonName = (EditText)findViewById( R.id.etAddUserPersonName );
        etAddUserPersonAge = (EditText)findViewById( R.id.etAddUserPersonAge );
        etAddUserPersonReason = (EditText)findViewById( R.id.etAddUserPersonReason );
        etAddUserPersonAddress = (EditText)findViewById( R.id.etAddUserPersonAddress );
        etAddUserPersonNameRelative = (EditText)findViewById( R.id.etAddUserPersonNameRelative );
        etAddUserPersonMobile = (EditText)findViewById( R.id.etAddUserPersonMobile );

        cbGTKapoor = (CheckBox)findViewById( R.id.cbGTKapoor );
        etGTKapoor = (EditText)findViewById( R.id.etGTKapoor );
        enableEditTextForCheckBox(cbGTKapoor,etGTKapoor);

        cbGTMakhana = (CheckBox)findViewById( R.id.cbGTMakhana );
        etGTMakhana = (EditText)findViewById( R.id.etGTMakhana );
        enableEditTextForCheckBox(cbGTMakhana,etGTMakhana);

        cbGTRaal = (CheckBox)findViewById( R.id.cbGTRaal );
        etGTRaal = (EditText)findViewById( R.id.etGTRaal );
        enableEditTextForCheckBox(cbGTRaal,etGTRaal);

        cbGTKharak = (CheckBox)findViewById( R.id.cbGTKharak );
        etGTKkarak = (EditText)findViewById( R.id.etGTKkarak );
        enableEditTextForCheckBox(cbGTKharak,etGTKkarak);

        cbGTSingoda = (CheckBox)findViewById( R.id.cbGTSingoda );
        etGTSingoda = (EditText)findViewById( R.id.etGTSingoda );
        enableEditTextForCheckBox(cbGTSingoda,etGTSingoda);

        cbGTJauTil = (CheckBox)findViewById( R.id.cbGTJauTil );
        etGTJauTil = (EditText)findViewById( R.id.etGTJauTil );
        enableEditTextForCheckBox(cbGTJauTil,etGTJauTil);

        cbGTShaulSadi = (CheckBox)findViewById( R.id.cbGTShaulSadi );
        etGTShaulSadi = (EditText)findViewById( R.id.etGTShaulSadi );
        enableEditTextForCheckBox(cbGTShaulSadi,etGTShaulSadi);

        cbGTShaulSuper = (CheckBox)findViewById( R.id.cbGTShaulSuper );
        etGTShaulSuper = (EditText)findViewById( R.id.etGTShaulSuper );
        enableEditTextForCheckBox(cbGTShaulSuper,etGTShaulSuper);

        cbGTSaree = (CheckBox)findViewById( R.id.cbGTSaree );
        etGTSaree = (EditText)findViewById( R.id.etGTSaree );
        enableEditTextForCheckBox(cbGTSaree,etGTSaree);

        cbGTSareeSuper = (CheckBox)findViewById( R.id.cbGTSareeSuper );
        etGTSareeSuper = (EditText)findViewById( R.id.etGTSareeSuper );
        enableEditTextForCheckBox(cbGTSareeSuper,etGTShaulSuper);

        cbGTPeticoat = (CheckBox)findViewById( R.id.cbGTPeticoat );
        etGTPaticoat = (EditText)findViewById( R.id.etGTPaticoat );
        enableEditTextForCheckBox(cbGTPeticoat,etGTPaticoat);

        cbGTBlauz = (CheckBox)findViewById( R.id.cbGTBlauz );
        etGTBlauz = (EditText)findViewById( R.id.etGTBlauz );
        enableEditTextForCheckBox(cbGTBlauz,etGTBlauz);

        etGTKurta = (EditText)findViewById( R.id.etGTKurta );

        cbGTPajama = (CheckBox)findViewById( R.id.cbGTPajama );
        etGTPajama = (EditText)findViewById( R.id.etGTPajama );
        enableEditTextForCheckBox(cbGTPajama,etGTPajama);

        cbGTDhooti = (CheckBox)findViewById( R.id.cbGTDhooti );
        etGTDhooti = (EditText)findViewById( R.id.etGTDhooti );
        enableEditTextForCheckBox(cbGTDhooti,etGTDhooti);

        cbGTPagdi = (CheckBox)findViewById( R.id.cbGTPagdi );
        etGTPagdi = (EditText)findViewById( R.id.etGTPagdi );
        enableEditTextForCheckBox(cbGTPagdi,etGTPagdi);

        cbGTTopi = (CheckBox)findViewById( R.id.cbGTTopi );
        etGTTopi = (EditText)findViewById( R.id.etGTTopi );
        enableEditTextForCheckBox(cbGTTopi,etGTTopi);

        cbGTSamgriSuhag = (CheckBox)findViewById( R.id.cbGTSamgriSuhag );
        etGTSamgriSuhag = (EditText)findViewById( R.id.etGTSamgriSuhag );
        enableEditTextForCheckBox(cbGTSamgriSuhag,etGTSamgriSuhag);

        cbGTItra = (CheckBox)findViewById( R.id.cbGTItra );
        etGTItra = (EditText)findViewById( R.id.etGTItra );
        enableEditTextForCheckBox(cbGTItra,etGTItra);

        cbGTChaddi = (CheckBox)findViewById( R.id.cbGTChaddi );
        etGTChaddi = (EditText)findViewById( R.id.etGTChaddi );
        enableEditTextForCheckBox(cbGTChaddi,etGTChaddi);

        cbGTBaniyan = (CheckBox)findViewById( R.id.cbGTBaniyan );
        etGTBaniyan = (EditText)findViewById( R.id.etGTBaniyan );
        enableEditTextForCheckBox(cbGTBaniyan,etGTBaniyan);

        cbGTTowel = (CheckBox)findViewById( R.id.cbGTTowel );
        etGTTowel = (EditText)findViewById( R.id.etGTTowel );
        enableEditTextForCheckBox(cbGTTowel,etGTTowel);

        cbVGTBanns = (CheckBox)findViewById( R.id.cbVGTBanns );
        etVGTBanns = (EditText)findViewById( R.id.etVGTBanns );
        enableEditTextForCheckBox(cbVGTBanns,etVGTBanns);

        cbVGTChipta = (CheckBox)findViewById( R.id.cbVGTChipta );
        etVGTChipta = (EditText)findViewById( R.id.etVGTChipta );
        enableEditTextForCheckBox(cbVGTChipta,etVGTChipta);

        cbVGTKafan = (CheckBox)findViewById( R.id.cbVGTKafan );
        etVGTKafan = (EditText)findViewById( R.id.etVGTKafan );
        enableEditTextForCheckBox(cbVGTKafan,etVGTKafan);

        cbVGTMunj = (CheckBox)findViewById( R.id.cbVGTMunj );
        etVGTMunj = (EditText)findViewById( R.id.etVGTMunj );
        enableEditTextForCheckBox(cbVGTMunj,etVGTMunj);

        cbVGTNada = (CheckBox)findViewById( R.id.cbVGTNada );
        etVGTNada = (EditText)findViewById( R.id.etVGTNada );
        enableEditTextForCheckBox(cbVGTNada,etVGTNada);

        cbVGTSutli = (CheckBox)findViewById( R.id.cbVGTSutli );
        etVGTSutli = (EditText)findViewById( R.id.etVGTSutli );
        enableEditTextForCheckBox(cbVGTSutli,etVGTSutli);

        cbVGTMatki = (CheckBox)findViewById( R.id.cbVGTMatki );
        etVGTMatki = (EditText)findViewById( R.id.etVGTMatki );
        enableEditTextForCheckBox(cbVGTMatki,etVGTMatki);

        cbVGTGulal = (CheckBox)findViewById( R.id.cbVGTGulal );
        etVGTGulal = (EditText)findViewById( R.id.etVGTGulal );
        enableEditTextForCheckBox(cbVGTGulal,etVGTGulal);

        cbVGTPanchRatna = (CheckBox)findViewById( R.id.cbVGTPanchRatna );
    }
    public void enableEditTextForCheckBox(final CheckBox cb, final EditText et){

        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked()){
//                    et.setEnabled(true);
                }else {
//                    et.setEnabled(false);
                }
            }
        });
//        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(cb.isChecked()){
//                    et.setEnabled(true);
//                }else {
//                    et.setEnabled(false);
//                }
//            }
//        });
    }
}
