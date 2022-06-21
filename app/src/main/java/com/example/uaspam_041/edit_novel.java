package com.example.uaspam_041;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.uaspam_041.database.DBLightNovel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class edit_novel extends AppCompatActivity {

    TextInputEditText Judul, Penulis, Sinopsis, Id_novel;
    Button Save;
    String judul, penulis, sinopsis, id_novel;
    DBLightNovel controller = new DBLightNovel(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_novel);

        Judul = findViewById(R.id.edJudul);
        Penulis = findViewById(R.id.edPenulis);
        Sinopsis = findViewById(R.id.edSinopsis);
        Save = findViewById(R.id.simpanBtn);

        id_novel = getIntent().getStringExtra("id_novel");
        judul = getIntent().getStringExtra("judul");
        penulis = getIntent().getStringExtra("penulis");
        sinopsis = getIntent().getStringExtra("sinopsis");

        setTitle("Edit Data");
        Judul.setText(judul);
        Penulis.setText(penulis);
        Sinopsis.setText(sinopsis);

        Save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (Judul.getText().toString().equals("") || Penulis.getText().toString().equals("") || Sinopsis.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Mohon isi terlebih dahulu !!!", Toast.LENGTH_LONG).show();
                }else{
                    judul = Judul.getText().toString();
                    penulis = Penulis.getText().toString();
                    HashMap<String,String> values = new HashMap<>();
                    values.put("id_novel",id_novel);
                    values.put("judul",judul);
                    values.put("penulis",penulis);
                    values.put("sinopsis",sinopsis);
                    controller.UpdateData(values);
                    Intent i = new Intent(edit_novel.this, HomeActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}