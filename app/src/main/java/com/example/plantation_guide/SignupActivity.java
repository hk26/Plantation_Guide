package com.example.plantation_guide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.widget.EditText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class SignupActivity extends AppCompatActivity {

    TextView exist,guest;
    EditText textEmail, textPassword, textConfirm;
    Button sbtn;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        textEmail = (EditText)findViewById(R.id.signup_email);
        textPassword = (EditText)findViewById(R.id.signup_password);
        textConfirm = (EditText)findViewById(R.id.signup_confirmpassword);
        sbtn = (Button)findViewById(R.id.signup_button);
        exist = (TextView) findViewById(R.id.exist);

        firebaseAuth = FirebaseAuth.getInstance();

        sbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = textEmail.getText().toString().trim();
                String password = textPassword.getText().toString().trim();
                String confirm = textConfirm.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(SignupActivity.this, "Please Fill-up Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(SignupActivity.this, "Please Fill-up Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confirm)) {
                    Toast.makeText(SignupActivity.this, "Please Fill-up Confirm Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length() < 6) {
                    Toast.makeText(SignupActivity.this, "Enter more than 6 characters", Toast.LENGTH_SHORT).show();
                }

                if(password.equals(confirm)) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        startActivity(new Intent(getApplicationContext(), login_Activity.class));
                                        Toast.makeText(SignupActivity.this, "User Confirmed", Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        Toast.makeText(SignupActivity.this, "You already have an account", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });
                }
            }
        });
        exist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, login_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        guest=(TextView) findViewById(R.id.askguestlogin);

        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(SignupActivity.this, Categories.class);
                startActivity(intent2);
            }
        });
    }

}