package com.example.inputdatapenjual;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ReadData extends AppCompatActivity {

    private Button btSubmit;
    private EditText etNama;
    private EditText etTanggal;
    private EditText etHarga;
    private EditText etBerat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);
        etNama = (EditText) findViewById(R.id.editNama);
        etTanggal = (EditText) findViewById(R.id.editTanggal);
        etHarga = (EditText) findViewById(R.id.editHarga);
        etBerat = (EditText) findViewById(R.id.editBerat);
        btSubmit = (Button) findViewById(R.id.btnOk);

        etNama.setEnabled(false);
        etTanggal.setEnabled(false);
        etHarga.setEnabled(false);
        etBerat.setEnabled(false);
        btSubmit.setVisibility(View.GONE);

        Penjual penjual = (Penjual) getIntent().getSerializableExtra("data");
        if(penjual!=null){
            etNama.setText(penjual.getNama());
            etTanggal.setText(penjual.getTanggal());
            etHarga.setText(penjual.getHarga());
            etBerat.setText(penjual.getBerat());
        }
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, ReadData.class);
    }
}
