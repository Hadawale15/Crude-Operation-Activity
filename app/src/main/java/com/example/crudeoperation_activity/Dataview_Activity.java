package com.example.crudeoperation_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Dataview_Activity extends AppCompatActivity {
    Button show;
    ListView listView;
    DatabaseReference databaseReference;
    List<Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataview);
        show=findViewById(R.id.show_id);
        listView=findViewById(R.id.list_id);
        databaseReference= FirebaseDatabase.getInstance().getReference("CrudeUser");
        list=new ArrayList<>();

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();

                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                            Model model=dataSnapshot.getValue(Model.class);
                            list.add(model);
                        }
                        Myadapter myadapter=new Myadapter(Dataview_Activity.this,list);
                        listView.setAdapter(myadapter);

                        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                            @Override
                            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                Model model=list.get(position);
                                ShowAlertDialogue(model.getId(),model.getName());
                                return false;
                            }


                        });

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Dataview_Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


            }

        });



    }
    private void ShowAlertDialogue(String id, String name) {
        databaseReference= FirebaseDatabase.getInstance().getReference("CrudeUser").child(id);
        AlertDialog.Builder builder=new AlertDialog.Builder(Dataview_Activity.this);
        LayoutInflater layoutInflater=getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.updatelayout,null,false);
        builder.setView(view);

        builder.setTitle("Name:"+name);
        EditText T1=view.findViewById(R.id.nameU_id);
        EditText T2=view.findViewById(R.id.emailU_id);
        EditText T3=view.findViewById(R.id.dobU_id);
        EditText T4=view.findViewById(R.id.qualiU_id);
        EditText T5=view.findViewById(R.id.passwordU_id);
        Button update=view.findViewById(R.id.update_id);
        Button delete=view.findViewById(R.id.dalete_id);

        AlertDialog alertDialog=builder.create();
        builder.show();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NameU=T1.getText().toString();
                String EmailU=T2.getText().toString();
                String DobU=T3.getText().toString();
                String QualiU=T4.getText().toString();
                String PassU=T5.getText().toString();

                Model model=new Model(NameU,EmailU,DobU,QualiU,PassU,id);
                databaseReference.setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(Dataview_Activity.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                            return;
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Dataview_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.removeValue();
                alertDialog.dismiss();
                finish();

            }
        });

    }
}