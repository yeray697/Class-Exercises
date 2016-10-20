package com.example.yrj.manageproduct;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddToList_Activity extends AppCompatActivity  implements IListMvp.View{

    Button btSave;
    EditText etName, etDescription, etBrand, etDosage, etPrice, etStock;
    IListMvp.Presenter listPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_list);
        listPresenter = new ListPresenter(this);
        //Setting controls
        btSave = (Button) findViewById(R.id.btSave);
        etName = (EditText) findViewById(R.id.etName);
        etDescription = (EditText) findViewById(R.id.etDescription);
        etBrand = (EditText) findViewById(R.id.etBrand);
        etDosage = (EditText) findViewById(R.id.etDosage);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etStock = (EditText) findViewById(R.id.etStock);
        //Checking data
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listPresenter.validateCredentials(etName.getText().toString(), etDescription.getText().toString(), etBrand.getText().toString(),
                        etDosage.getText().toString(), etPrice.getText().toString(), etStock.getText().toString());
            }
        });
    }

    @Override
    public void setMessageError(String messageError, int idView) {
        switch (idView){
            case R.id.etName:
                etName.setError(messageError);
                break;
            case R.id.etDescription:
                etName.setError(messageError);
                break;
            case R.id.etBrand:
                etName.setError(messageError);
                break;
            case R.id.etDosage:
                etName.setError(messageError);
                break;
            case R.id.etPrice:
                etName.setError(messageError);
                break;
            case R.id.etStock:
                etName.setError(messageError);
                break;
        }
    }
}
