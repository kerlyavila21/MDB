package com.example.kerly.myapplicationmdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.kerly.myapplicationmdb.models.Maquillaje;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConsultarMaquillaje extends AppCompatActivity {


    private DatabaseReference refMaq;
    private String coleccionMaquillaje="maquillajes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refMaq = FirebaseDatabase.getInstance().getReference().child(coleccionMaquillaje);
        consultar();
    }

    private void consultar() {
        //refEst.orderByKey()
        refMaq .orderByKey().limitToFirst(10);
        refMaq .orderByKey().limitToLast(10);
        refMaq .orderByKey().startAt(11);
        refMaq .orderByKey().endAt(15);
        refMaq .orderByKey().startAt(11).endAt(20);
        refMaq .orderByChild("Color").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Maquillaje maq = dataSnapshot.getValue(Maquillaje.class);
                System.out.println(dataSnapshot.getKey() + " ma " + maq.getProducto_Nombre());
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    }