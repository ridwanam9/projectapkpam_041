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

public class ResetActivity extends AppCompatActivity {

    TextView email;
    EditText pass, repass;
    Button confirm;
    DBRegisLog DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        email = findViewById(R.id.email_reset_text);
        pass = findViewById(R.id.password_reset);
        repass = findViewById(R.id.repassword_reset);
        confirm = findViewById(R.id.btnconfirmpass);
        DB = new DBRegisLog(this);

        Intent intent = getIntent();
        email.setText(intent.getStringExtra("email"));

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String useremail = email.getText().toString();
                String password = pass.getText().toString();
                String repassword = repass.getText().toString();
                if(password.equals(repassword)) {


                    Boolean checkpasswordupdate = DB.updatepassword(useremail, password);
                    if (checkpasswordupdate == true) {

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(ResetActivity.this, "Password berhasil diubah", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(ResetActivity.this, "Password gagal diubah", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(ResetActivity.this, "Password tidak cocok", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}