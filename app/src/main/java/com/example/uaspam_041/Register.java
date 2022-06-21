package com.example.uaspam_041;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uaspam_041.database.DBRegisLog;

public class Register extends AppCompatActivity {

    EditText rednama, redemail, redpass, reredpass;
    String rnama, remail, rpass, rrepass;
    Button sign_up;
    DBRegisLog DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rednama = findViewById(R.id.rednama);
        redemail = findViewById(R.id.redemail);
        redpass = findViewById(R.id.redpassword);
        reredpass = findViewById(R.id.reredpass);
        sign_up = findViewById(R.id.signup);
        DB = new DBRegisLog(this);

        sign_up.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                rnama = rednama.getText().toString();
                remail = redemail.getText().toString();
                rpass = redpass.getText().toString();
                rrepass = reredpass.getText().toString();



                if( rnama.isEmpty() || remail.isEmpty() || rpass.isEmpty() || rrepass.isEmpty()){
                    Toast.makeText(getApplicationContext(),
                            "masukkan semua data terlebih dahulu", Toast.LENGTH_LONG).show();
                } else {
                    if (rpass.equals(rrepass)){
                        Boolean checkemail = DB.checkemail(remail);
                        if (checkemail == false){
                            boolean insert = DB.insertData(rnama,remail,rpass);
                            if (insert==true){
                                Toast.makeText(getApplicationContext(),
                                        "Nama Anda: "+rnama+". Email Anda: "+remail+". Password Anda: "+rpass, Toast.LENGTH_LONG).show();
                                Intent Register_toMain = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(Register_toMain);
                            }
                            else {
                                Toast.makeText(getApplicationContext(),
                                        "Gagal Registrasi", Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(),
                                    "Nama atau email yang anda masukkan sudah ada silahkan login", Toast.LENGTH_LONG).show();
                            Intent Register_toMain = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(Register_toMain);
                        }
                    }
                }
            }
        });
    }
}