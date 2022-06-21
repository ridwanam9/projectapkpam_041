package com.example.uaspam_041;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uaspam_041.database.DBRegisLog;

public class PasswordActivity extends AppCompatActivity {

    EditText email;
    Button reset;
    DBRegisLog DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        email = findViewById(R.id.email_reset);
        reset = findViewById(R.id.button);
        DB = new DBRegisLog(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Email = email.getText().toString();

                Boolean checkemail = DB.checkemail(Email);
                if(checkemail==true)
                {
                    Intent intent = new Intent(getApplicationContext(), ResetActivity.class);
                    intent.putExtra("email", Email);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(PasswordActivity.this, "Email yang anda masukkan tidak ada", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}