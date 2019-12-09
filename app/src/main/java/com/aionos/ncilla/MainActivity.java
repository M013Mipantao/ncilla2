package com.aionos.ncilla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText emailId, password, firstname, lastname,username, repassword,birthday;
    // txtfname, txtlname,txtusername,txtemail,txtpass, txtrepassword,txtbday
    Button btnSignUp;
    TextView tvSignIn;
    //Auth object
    FirebaseAuth mFirebaseAuth;
    // Add database
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firstname =  findViewById(R.id.txtfname);
        lastname=  findViewById(R.id.txtlname);
        username =  findViewById(R.id.txtusername);
        birthday =  findViewById(R.id.txtbday);
        emailId =  findViewById(R.id.txtemail);
        password  =  findViewById(R.id.txtpassword);
        repassword  = findViewById(R.id.txtrepassword);
        tvSignIn = findViewById(R.id.txtvSignUp);
        btnSignUp = findViewById(R.id.btnsignup);
        btnSignUp.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v){
                String fname = firstname.getText().toString();
                String lname = lastname.getText().toString();
                String username_ = username.getText().toString();
                String bday = birthday.getText().toString();
                String email =  emailId.getText().toString();
                String pwd = password.getText().toString();
                String repwd = repassword.getText().toString();

                if ( fname.isEmpty() ){
                    firstname.setError("Please enter your First Name");
                    firstname.requestFocus();
                }
                else if(lname.isEmpty()){
                    lastname.setError("Please enter your Last Name");
                    lastname.requestFocus();
                }
                else if(username_.isEmpty()){
                    username.setError("Please enter Username");
                    username.requestFocus();
                }
                else if(pwd.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else if(repwd.isEmpty()){
                    repassword.setError("Please enter your password");
                    repassword.requestFocus();
                }
                else if(pwd.equals(repwd)== false){
                    repassword.setError("Incorrect password");
                    repassword.requestFocus();
                }
                else if (email.isEmpty()){
                    emailId.setError("Please enter email ID");
                    emailId.requestFocus();
                }
                else if(bday.isEmpty()){
                    birthday.setError("Please enter your Birthday");
                    birthday.requestFocus();
                }


                else if (email.isEmpty() && pwd.isEmpty() && fname.isEmpty() && lname.isEmpty()&& username_.isEmpty() && bday.isEmpty()){
                    Toast.makeText(MainActivity.this,"Feilds are Empty!",Toast.LENGTH_SHORT).show();

                }else if ((!email.isEmpty()) && (!pwd.isEmpty()) && (!fname.isEmpty()) && (!lname.isEmpty()) && (!username_.isEmpty()) && (!bday.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"SignUp Unsucessful, Please Try Again",Toast.LENGTH_SHORT).show();
                            }else {
                                startActivity(new Intent(MainActivity.this, HomeActivity.class));

                            }
                        }
                    });
                }else{
                    Toast.makeText(MainActivity.this,"Error Occured!",Toast.LENGTH_SHORT).show();
                }
            }

            private void saveUserInformation(){

            }

        });



        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);

            }


        });

    }

}
