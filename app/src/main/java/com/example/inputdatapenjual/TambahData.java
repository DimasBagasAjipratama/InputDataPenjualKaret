package com.example.inputdatapenjual;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahData extends AppCompatActivity {
    private DatabaseReference database;

    private Button btSubmit;
    private EditText etNama;
    private EditText etTanggal;
    private EditText etHarga;
    private EditText etBerat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        etNama = (EditText) findViewById(R.id.editNama);
        etTanggal = (EditText) findViewById(R.id.editTanggal);
        etHarga = (EditText) findViewById(R.id.editHarga);
        etBerat = (EditText) findViewById(R.id.editBerat);
        btSubmit = (Button) findViewById(R.id.btnOk);

        database = FirebaseDatabase.getInstance().getReference();

        final Penjual penjual = (Penjual) getIntent().getSerializableExtra("data");

        if (penjual != null){
            etNama.setText(penjual.getNama());
            etTanggal.setText(penjual.getTanggal());
            etHarga.setText(penjual.getHarga());
            etBerat.setText(penjual.getBerat());
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    penjual.setNama(etNama.getText().toString());
                    penjual.setTanggal(etTanggal.getText().toString());
                    penjual.setHarga(etHarga.getText().toString());
                    penjual.setBerat(etBerat.getText().toString());
                    updatePenjual(penjual);
                }
            });
        }else {
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!(etNama.getText().toString().isEmpty()) &&
                            !(etTanggal.getText().toString().isEmpty())&&
                            !(etHarga.getText().toString().isEmpty())&&
                            !(etBerat.getText().toString().isEmpty()))
                        submitPenjual(new Penjual(etNama.getText().toString(),
                                etTanggal.getText().toString(),
                                etHarga.getText().toString(),
                                etBerat.getText().toString()));
                    else
                        Toast.makeText(getApplicationContext(), "Data tidak boleh Kosong",
                                Toast.LENGTH_LONG).show();

                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(etNama.getWindowToken(), 0);
                }
            });
        }
    }
    public void submitPenjual(Penjual penjual){
        database.child("Barang").push().setValue(penjual).addOnSuccessListener(this,
                new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        etNama.setText("");
                        etTanggal.setText("");
                        etHarga.setText("");
                        etBerat.setText("");
                        Toast.makeText(getApplicationContext(),"Data berhasil ditambahkan" ,
                                Toast.LENGTH_LONG).show();
                    }
                });
    }
    public static Intent getActIntent(Activity activity){
        return new Intent(activity, TambahData.class);
    }

    private void updatePenjual(Penjual penjual) {
        database.child("Barang")
                .child(penjual.getTanggal())
                .setValue(penjual)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Data berhasil di Update",
                                Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }
}