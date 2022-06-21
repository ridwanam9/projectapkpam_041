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

public class add_novel extends AppCompatActivity {

    private TextInputEditText tJudul, tPenulis, tSinopsis;
    private Button simpanBtn;
    String judul, penulis, sinopsis;
    DBLightNovel controller = new DBLightNovel(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_novel);

        tJudul = (TextInputEditText) findViewById(R.id.tietJudul);
        tPenulis = (TextInputEditText) findViewById(R.id.tietPenulis);
        tSinopsis = (TextInputEditText) findViewById(R.id.tietSinopsis);
        simpanBtn = (Button) findViewById(R.id.buttonSave);

        simpanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tJudul.getText().toString().equals("")||tPenulis.getText().toString().equals("")||tSinopsis.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Data Belum komplit !", Toast.LENGTH_SHORT).show();
                }else{
                    judul = tJudul.getText().toString();
                    penulis = tPenulis.getText().toString();
                    sinopsis = tSinopsis.getText().toString();

                    HashMap<String,String> qvalues = new HashMap<>();
                    qvalues.put("judul",judul);
                    qvalues.put("penulis",penulis);
                    qvalues.put("sinopsis",sinopsis);

                    controller.insertBook(qvalues);
                    Intent intent = new Intent(add_novel.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}