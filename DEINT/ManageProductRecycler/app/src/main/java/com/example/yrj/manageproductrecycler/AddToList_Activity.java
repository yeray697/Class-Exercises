package com.example.yrj.manageproductrecycler;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yrj.manageproductrecycler.interfaces.IProductMvp;
import com.example.yrj.manageproductrecycler.presenter.ProductPresenter;

public class AddToList_Activity extends AppCompatActivity  implements IProductMvp.View{

    private final int SELECT_IMAGE = 2;
    private final int TAKE_PICTURE = 3;

    Button btPhoto, btSave;
    EditText etName, etDescription, etBrand, etDosage, etPrice, etStock;
    TextView tvPhoto;
    Uri photo;
    IProductMvp.Presenter listPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_list);
        listPresenter = new ProductPresenter(this);
        //Setting controls
        btSave = (Button) findViewById(R.id.btSave);
        btPhoto = (Button) findViewById(R.id.btPhoto);
        etName = (EditText) findViewById(R.id.etName);
        etDescription = (EditText) findViewById(R.id.etDescription);
        etBrand = (EditText) findViewById(R.id.etBrand);
        etDosage = (EditText) findViewById(R.id.etDosage);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etStock = (EditText) findViewById(R.id.etStock);
        tvPhoto = (TextView) findViewById(R.id.tvPhoto);
        //Checking data
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listPresenter.validateCredentials(etName.getText().toString(), etDescription.getText().toString(), etBrand.getText().toString(),
                        etDosage.getText().toString(), etPrice.getText().toString(), etStock.getText().toString(),photo)){
                    //If true, product added properly
                    finish();
                }
            }
        });
        btPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogPhoto();
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
                etDescription.setError(messageError);
                break;
            case R.id.etBrand:
                etBrand.setError(messageError);
                break;
            case R.id.etDosage:
                etDosage.setError(messageError);
                break;
            case R.id.etPrice:
                etPrice.setError(messageError);
                break;
            case R.id.etStock:
                etStock.setError(messageError);
                break;
            case -2: //Image error
                Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
                break;
        }
    }


    private void dialogPhoto(){
        try{
            /*final CharSequence[] items = {"Seleccionar de la galería", "Hacer una foto"};

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Seleccionar una foto");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                    switch(item){
                        case 0:
                            Intent intent = new Intent(Intent.ACTION_PICK);
                            intent.setType("image/*");
                            startActivityForResult(intent, SELECT_IMAGE);
                            break;
                        case 1:
                            startActivityForResult(new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE), TAKE_PICTURE);
                            break;
                    }

                }
            });
            AlertDialog alert = builder.create();
            alert.show();*/
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, SELECT_IMAGE);
        } catch(Exception e){}
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try{
            if (requestCode == SELECT_IMAGE)
                if (resultCode == Activity.RESULT_OK) {
                    photo = data.getData();
                    tvPhoto.setText(getPath(photo));
                }
            /*if(requestCode == TAKE_PICTURE)
                if(resultCode == Activity.RESULT_OK){
                    photo = data.getData();
                    tvPhoto.setText("Foto de la cámara tomada");
                }*/
        } catch(Exception e){}
    }

    private String getPath(Uri uri) {
        String[] projection = { android.provider.MediaStore.Images.Media.DATA };
        //Cursor cursor = getContentResolver().query(uri, null, null, null, ContactsContract.Contacts.DISPLAY_NAME); Da error
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(android.provider.MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
