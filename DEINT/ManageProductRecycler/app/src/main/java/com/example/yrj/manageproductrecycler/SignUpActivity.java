package com.example.yrj.manageproductrecycler;

import android.content.res.TypedArray;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yrj.manageproductrecycler.interfaces.IValidateUser;
import com.example.yrj.manageproductrecycler.presenter.SignUpPresenter;

public class SignUpActivity extends AppCompatActivity implements IValidateUser.View{

    SignUpPresenter presenter;
    Spinner spCounty;
    Spinner spCity;
    RadioGroup rgType;
    Button btSubmit;
    TextInputLayout tilUser, tilPass, tilEmail, tilBusiness;
    ArrayAdapter<CharSequence> adapterCounty, adapterCity;
    CheckBox cbPrivacy;
    RadioButton rbBusiness;

    private AdapterView.OnItemSelectedListener spinnerListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        presenter = new SignUpPresenter(this);
        spCity = (Spinner) findViewById(R.id.spCity);
        spCounty = (Spinner) findViewById(R.id.spCounty);
        btSubmit = (Button) findViewById(R.id.btSubmit);
        tilUser = (TextInputLayout) findViewById(R.id.tilUser);
        tilPass = (TextInputLayout) findViewById(R.id.tilPass);
        tilEmail = (TextInputLayout) findViewById(R.id.tilEmail);
        tilBusiness = (TextInputLayout) findViewById(R.id.tilBusiness);
        cbPrivacy = (CheckBox) findViewById(R.id.cbPrivacy);
        rbBusiness = (RadioButton) findViewById(R.id.rbBusiness);
        rgType = (RadioGroup) findViewById(R.id.rgType);
        rgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbPart:
                        showCompany(false);
                        break;
                    case R.id.rbBusiness:
                        showCompany(true);
                        break;
                }
            }
        });
        loadSpinnerCounty();

        tilUser.getEditText().addTextChangedListener(new MyTextWatcher(tilUser));
        tilPass.getEditText().addTextChangedListener(new MyTextWatcher(tilPass));
        tilEmail.getEditText().addTextChangedListener(new MyTextWatcher(tilEmail));
        tilBusiness.getEditText().addTextChangedListener(new MyTextWatcher(tilBusiness));
    }

    private void showCompany(boolean b) {
        if (b)
            tilBusiness.setVisibility(View.VISIBLE);
        else
            tilBusiness.setVisibility(View.GONE);
    }

    public void signUp(View v) {
        String user = tilUser.getEditText().getText().toString()
                , email = tilEmail.getEditText().getText().toString()
                , pass = tilPass.getEditText().getText().toString()
                ,county = spCounty.getSelectedItem().toString(),
                city = spCity.getSelectedItem().toString();
        boolean isBusinessType = rbBusiness.isChecked();
        String businessName = tilBusiness.getEditText().getText().toString();
        boolean privacyAccepted = cbPrivacy.isChecked();
        presenter.validateCredentials(user,email,pass);
    }

    private void loadSpinnerCounty(){
        spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getId()){
                    case R.id.spCounty:
                        TypedArray ids = getResources().obtainTypedArray(R.array.array_provincia_a_localidades);

                        CharSequence[] data = getResources().getStringArray(ids.getResourceId(position, -1));

                        ids.recycle();
                        adapterCity = new ArrayAdapter<CharSequence>(SignUpActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, data);
                        spCity.setAdapter(adapterCity);
                        break;
                    case R.id.spCity:
                        showCitySelected();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        adapterCounty = ArrayAdapter.createFromResource(this,R.array.provincias,android.R.layout.simple_spinner_dropdown_item);
        spCounty.setAdapter(adapterCounty);

        spCounty.setOnItemSelectedListener(spinnerListener);
        spCity.setOnItemSelectedListener(spinnerListener);
    }

    private void showCitySelected() {
        Toast.makeText(getApplicationContext(),
                String.format(
                        getResources().getString(R.string.message),
                        spCity.getSelectedItem().toString(),
                        spCounty.getSelectedItem().toString()),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setMessageError(String messageError, int idView) {
        switch (idView){
            case SignUpPresenter.TOAST:
                Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
                break;
            default:
                ((TextInputLayout)findViewById(idView)).setError(messageError);
        }

    }
    class MyTextWatcher implements TextWatcher{

        TextInputLayout mView;

        public MyTextWatcher(TextInputLayout view){
            this.mView = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mView.setErrorEnabled(false);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
