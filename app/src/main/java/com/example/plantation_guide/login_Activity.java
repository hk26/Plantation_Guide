package com.example.plantation_guide;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import android.text.TextUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class login_Activity extends AppCompatActivity {
    EditText txtEmail, txtPassword;
    Button lbtn;
    TextView forgotTextLink;
    FirebaseAuth firebaseAuth;
    public static int count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button start_button=(Button)findViewById(R.id.login_button);

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login_Activity.this, home_page.class);
                startActivity(intent);
            }
        });

        txtEmail = (EditText)findViewById(R.id.login_email);
        txtPassword = (EditText)findViewById(R.id.login_password);
        lbtn = (Button)findViewById(R.id.login_button);
        forgotTextLink = findViewById(R.id.forgot);



        firebaseAuth = FirebaseAuth.getInstance();

        lbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(login_Activity.this, "Please Fill-up Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(login_Activity.this, "Please Fill-up Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length() < 6) {
                    Toast.makeText(login_Activity.this, "Enter more than 6 characters", Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(login_Activity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    count++;
                                    startActivity(new Intent(getApplicationContext(), home_page.class));
                                    finish();
                                } else {
                                    Toast.makeText(login_Activity.this, "Email or Password does not exist", Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });

            }
        });

        forgotTextLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText resetMail = new EditText(v.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter Your Email To Received Reset Link.");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // extract the email and send reset link
                        String mail = resetMail.getText().toString();
                        firebaseAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(login_Activity.this, "Reset Link Sent To Your Email.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(login_Activity.this, "Error ! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // close the dialog
                    }
                });

                passwordResetDialog.create().show();

            }
        });







    }
}