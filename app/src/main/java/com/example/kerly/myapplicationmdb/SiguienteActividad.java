package com.example.kerly.myapplicationmdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
 import android.os.Bundle;
 import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.kerly.myapplicationmdb.models.Maquillaje;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class SiguienteActividad extends AppCompatActivity {

    RecyclerView rv;
    Button añadir;

    List<Maquillaje> maquillajes;
        Adapter adapter;

private String  coleccionMaquillaje = "maquillajes";
private DatabaseReference mMaquillajeReference;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.siguiente_actividad);
            añadir = (Button) findViewById(R.id.buttonAñadir);




            rv = (RecyclerView) findViewById(R.id.recycler);

            rv.setLayoutManager(new LinearLayoutManager(this));

            maquillajes = new ArrayList<>();

            FirebaseDatabase database = FirebaseDatabase.getInstance();

            adapter = new Adapter(maquillajes);

            rv.setAdapter(adapter);

            database.getReference().child(coleccionMaquillaje).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    maquillajes.removeAll(maquillajes);
                    for (DataSnapshot snapshot :
                            dataSnapshot.getChildren()){
                        Maquillaje mascota = snapshot.getValue(Maquillaje.class);
                        maquillajes.add(mascota);
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }

}