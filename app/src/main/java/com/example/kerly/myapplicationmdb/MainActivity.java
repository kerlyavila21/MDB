package com.example.kerly.myapplicationmdb;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kerly.myapplicationmdb.models.Maquillaje;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
//import android.R;
//import android.R.*;

//import static com.example.kerly.myapplicationmdb.R.id.buttonEliminar;
//import static com.example.kerly.myapplicationmdb.R.id.editTextProducto_Nombre;


//import com.softserve.MDB1.models.Maquillaje;

public class MainActivity extends AppCompatActivity {
    Button Siguiente, Eliminar, btnFoto;
    ImageView imagen;

    EditText editTextProducto_Nombre, editTextColor, editTextPeso_Neto, editTextDescripcion, editTextPrecio, editTextDatos_Adicional_Producto;

    private DatabaseReference mConsultaReference;
    private DatabaseReference mMaquillajeReference;
    private String coleccionMaquillaje = "maquillajes";
    private StorageReference storage;
    private ProgressDialog progress;

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            progress.setMessage("Subiendo Imagen del Maquillaje");
            progress.show();

            Uri uri = data.getData();

            StorageReference filepath = storage.child("Fotos de maquillajes").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    progress.dismiss();

                    Uri downloadUri = taskSnapshot.getDownloadUrl();
                    Picasso.with(MainActivity.this).load(downloadUri).fit().centerCrop().into(imagen);

                    Toast.makeText(MainActivity.this, "Foto Subida Exitosamente...", Toast.LENGTH_LONG).show();

                }
            });
        }
    }


    // oncreate
   // @SuppressLint("WrongViewCast")
    //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mConsultaReference = FirebaseDatabase.getInstance().getReference();
        mMaquillajeReference = FirebaseDatabase.getInstance().getReference();

        mConsultaReference = FirebaseDatabase.getInstance().getReference();
        btnFoto = findViewById(R.id.btnFoto);
        imagen = findViewById(R.id.imagen);

        editTextProducto_Nombre = (EditText) findViewById(R.id.editTextProducto_Nombre);
        editTextColor = (EditText) findViewById(R.id.editTextColor);
        editTextPeso_Neto = (EditText) findViewById(R.id.editTextPeso_Neto);
        editTextDescripcion = (EditText) findViewById(R.id.editTextDescripcion);
        editTextPrecio = (EditText) findViewById(R.id.editTextPrecio);
        editTextDatos_Adicional_Producto = (EditText) findViewById(R.id.editTextDatos_Adicional_Producto);

        Siguiente = (Button) findViewById(R.id.buttons);

        Siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Siguiente = new Intent(MainActivity.this, SiguienteActividad.class);
                startActivity(Siguiente);
            }

        });

        progress = new ProgressDialog(this);
        storage = FirebaseStorage.getInstance().getReference();

        btnFoto = findViewById(R.id.btnFoto);
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

            }


        });
    }
/*
 // eliminar
    @Override
    public void onStart() {
        super.onStart();
        Eliminar = findViewById(R.id.buttonEliminar);

        Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar();

            }
        });


    }
    private void eliminar() {
        EditText editTextProducto_Nombre = findViewById(R.id.editTextProducto_Nombre);
     // mMaquillajeReference.child("Producto_Nombre").child(editTextProducto_Nombre.toString()).removeValue();
        mMaquillajeReference.child(editTextProducto_Nombre.getText().toString()).removeValue();
    }

*/


      //  @SuppressLint("WrongViewCast")
        public void buttonGrabarClick (View view){
            //adicional

            FirebaseDatabase mConsultaReference = FirebaseDatabase.getInstance();


            // Eliminar = findViewById(R.id.buttonEliminar);

            String cadena = "Maqui" + editTextProducto_Nombre.getText().toString().substring(0, editTextProducto_Nombre.getText().toString().indexOf(' ', 0) - 1);
            System.out.println(editTextProducto_Nombre.getText().toString().substring(0, editTextProducto_Nombre.getText().toString().indexOf(' ', 0) - 1));
            Maquillaje maq = new Maquillaje(editTextColor.getText().toString(),
                    editTextDatos_Adicional_Producto.getText().toString(),
                    editTextDescripcion.getText().toString(),
                    editTextPeso_Neto.getText().toString(),
                    editTextPrecio.getText().toString(),
                    editTextProducto_Nombre.getText().toString());

            mMaquillajeReference.child(coleccionMaquillaje).child(cadena).setValue(maq);
            // Grabar(maq);


        }

        public void remove (View view){
            FirebaseDatabase mConsultaReference = FirebaseDatabase.getInstance();

            String cadena = "Maqui" + editTextProducto_Nombre.getText().toString().substring(0, editTextProducto_Nombre.getText().toString().indexOf(' ', 0) - 1);
            mMaquillajeReference.child(coleccionMaquillaje).child(cadena).removeValue();

        }

    }
    /*
    private String Grabar(Maquillaje maq) {
        //verificar si es un codigo existente
        String codigo = maq.getCodigo();
        mMaquillajeReference.child(codigo).setValue(maq);
        return maq.getCodigo();
    }
*/





