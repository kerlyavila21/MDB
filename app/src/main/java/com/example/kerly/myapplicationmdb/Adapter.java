package com.example.kerly.myapplicationmdb;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
//import android.R;


import com.example.kerly.myapplicationmdb.models.CarritoCompra;
import com.example.kerly.myapplicationmdb.models.Maquillaje;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Adapter  extends RecyclerView.Adapter<Adapter.MaquillajesviewHolder> {

    private List<Maquillaje> maquillajes;

    public Adapter(List<Maquillaje> maquillajes) {
        this.maquillajes = maquillajes;
    }


    @Override
    public MaquillajesviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler, parent, false);
        MaquillajesviewHolder holder = new MaquillajesviewHolder(v);
        return holder;
    }

    public void onBindViewHolder(MaquillajesviewHolder holder, int position) {
        Maquillaje maquillaje = maquillajes.get(position);
        holder.textview_Productonombre.setText(maquillaje.getProducto_Nombre());
        holder.textview_color.setText(maquillaje.getColor());
        holder.textview_PesoNeto.setText(maquillaje.getPeso_Neto());
        holder.textview_Descripcion.setText(maquillaje.getDescripcion());
        holder.textview_Precio.setText(maquillaje.getPrecio());
        holder.textview_DatosAdicionalProducto.setText(maquillaje.getDatos_Adicional_Producto());
        holder.setOnclickListener();
    }

    @Override
    public int getItemCount() {
        return maquillajes.size();
    }

    public static class MaquillajesviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private DatabaseReference mMaquillajeReference;
        private String coleccionMaquillaje = "maquillajes";

        TextView textview_Productonombre, textview_color, textview_PesoNeto, textview_Descripcion, textview_Precio, textview_DatosAdicionalProducto;
        Button añadir;
        ImageButton lista;

        public MaquillajesviewHolder(View itemView) {
            super(itemView);
            lista = itemView.findViewById(R.id.buttons);
            añadir = itemView.findViewById(R.id.buttonAñadir);

            textview_Productonombre = itemView.findViewById(R.id.textview_producto);
            textview_color = itemView.findViewById(R.id.textview_color);
            textview_PesoNeto = itemView.findViewById(R.id.textview_peso);
            textview_Descripcion = itemView.findViewById(R.id.textview_descripcion);
            textview_Precio = itemView.findViewById(R.id.textview_precio);
            textview_DatosAdicionalProducto = itemView.findViewById(R.id.textview_datosproducto);
        }


        public void setOnclickListener() {
            //  lista.setOnClickListener(this);

            añadir.setOnClickListener(this);
        }



       /*
       }*/

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonAñadir:
                    FirebaseDatabase mConsultaReference = FirebaseDatabase.getInstance();
                    mMaquillajeReference = mConsultaReference.getReference();  //FirebaseDatabase.getInstance().getReference();
                    String coleccionCarrito ="CarritoCompra";


                    CarritoCompra car = new CarritoCompra(textview_Productonombre.getText().toString(),
                            textview_color.getText().toString(),
                            textview_PesoNeto.getText().toString(),
                            textview_Descripcion.getText().toString(),
                            textview_Precio.getText().toString(),
                            textview_DatosAdicionalProducto.getText().toString());

                    String cadena = "Maqui" + textview_Productonombre.getText().toString().substring(0, textview_Productonombre.getText().toString().indexOf(' ', 0) - 1);

                    mMaquillajeReference.child(coleccionCarrito).child(cadena).setValue(car);
            }
        }

    }
}