package com.example.uaspam_041;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.uaspam_041.database.DBRegisLog;


public class MainActivity extends AppCompatActivity {

    Button btnLogin, btnRegister;
    EditText edemail, edpassword;
    TextView forgot;
    String email, password;
    DBRegisLog DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin=findViewById(R.id.btSignin);
        edemail=findViewById(R.id.edEmail);
        edpassword=findViewById(R.id.edPassword);
        btnRegister=findViewById(R.id.btnRegister);
        forgot=findViewById(R.id.btnforgot);
        DB = new DBRegisLog(this);

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                email = edemail.getText().toString();
                password = edpassword.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {

                    Toast t = Toast.makeText(getApplicationContext(), "Email dan password", Toast.LENGTH_LONG);

                    t.show();
                } else {
                    boolean checkemailpass = DB.checkemailpass(email, password);
                    if (checkemailpass==true)
                    {
                        Toast.makeText(getApplicationContext(), "Berhasil Login", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast t = Toast.makeText(getApplicationContext(), "Invalid Credential", Toast.LENGTH_LONG);
                        t.show();
                    }
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent main_toRegister = new Intent(getApplicationContext(), Register.class);
                startActivity(main_toRegister);
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), PasswordActivity.class);
                startActivity(intent);
            }
        });

    }
}