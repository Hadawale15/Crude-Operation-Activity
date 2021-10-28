package com.example.crudeoperation_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText name1,email1,dob1,quali1,pass1;
    Button submit;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name1=findViewById(R.id.name_id);
        email1=findViewById(R.id.email1_id);
        dob1=findViewById(R.id.dob_id);
        quali1=findViewById(R.id.quali_id);
        pass1=findViewById(R.id.password1_id);
        databaseReference=FirebaseDatabase.getInstance().getReference("CrudeUser");

        submit=findViewById(R.id.submit_id);
        firebaseAuth=FirebaseAuth.getInstance();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name1=name1.getText().toString();
                String Email1=email1.getText().toString();
                String Dob1=dob1.getText().toString();
               String Quali1=quali1.getText().toString();
               String Pass1=pass1.getText().toString();
               String Id=databaseReference.push().getKey();

               firebaseAuth.createUserWithEmailAndPassword(Email1,Pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful())
                       {
                           Toast.makeText(RegisterActivity.this, "Account created Successfully", Toast.LENGTH_SHORT).show();
                       }
                   }
               }).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                   }
               });

               Model model=new Model(Name1,Email1,Dob1,Quali1,Pass1,Id);



               databaseReference.child(Id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                   @Override
                   public void onComplete(@NonNull Task<Void> task) {
                       if (task.isSuccessful())
                       {
                           Toast.makeText(RegisterActivity.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
                           finish();
                       }

                   }
               }).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                   }
               });
            }
        });
    }
}