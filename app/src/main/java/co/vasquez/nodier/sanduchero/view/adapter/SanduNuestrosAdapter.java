package co.vasquez.nodier.sanduchero.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import co.vasquez.nodier.sanduchero.R;
import co.vasquez.nodier.sanduchero.model.entity.Sanduches;

public class SanduNuestrosAdapter extends RecyclerView.Adapter {

    List<Sanduches> sanduches;
    NombreDeInterface miEscuchador;

    public SanduNuestrosAdapter(List<Sanduches> sanduches, NombreDeInterface miEscuchador) {
        this.sanduches = sanduches;
        this.miEscuchador = miEscuchador;
    }

    public void setSanduches(List<Sanduches> sanduches){
        this.sanduches = sanduches;
    }

    class SanducheViewHolder extends RecyclerView.ViewHolder {

        ImageView imgSanduche;
        TextView txvNombreSan, txvPrecioSan;


        public SanducheViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imgSanduche = itemView.findViewById(R.id.img_sanduche);
            this.txvNombreSan = itemView.findViewById(R.id.txv_nombreSan1);
            this.txvPrecioSan = itemView.findViewById(R.id.txv_precioSan1);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View miVista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanduche, parent,false);

        return new SanducheViewHolder(miVista);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        final SanducheViewHolder miHolder = (SanducheViewHolder)holder;
        final Sanduches miSanduche =sanduches.get(position);

        Picasso.get().load(miSanduche.getImagen()).into(miHolder.imgSanduche);
        miHolder.txvNombreSan.setText(miSanduche.getNombre());
        miHolder.txvPrecioSan.setText("$ "+miSanduche.getPrecio() );

        miHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miEscuchador.metodoOnClick(miSanduche,position);
                Log.d("Prueba-Click","Hice Click Adaptador en: "+miSanduche);
            }
        });

    }

    @Override
    public int getItemCount() {
        return sanduches.size();
    }

    public interface NombreDeInterface {
        void metodoOnClick(Sanduches sanduche, int posicion);
    }
}
