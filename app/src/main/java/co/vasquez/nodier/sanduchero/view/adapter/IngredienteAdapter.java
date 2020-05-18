package co.vasquez.nodier.sanduchero.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import co.vasquez.nodier.sanduchero.R;
import co.vasquez.nodier.sanduchero.model.entity.Ingredientes;

public class IngredienteAdapter extends RecyclerView.Adapter {

    List<Ingredientes> ingredientes;
    NombreDeInterface miEscuchador;
    int precio=0;
    boolean chk;



    public IngredienteAdapter(List<Ingredientes> ingredientes, NombreDeInterface miEscuchador) {
        this.ingredientes = ingredientes;
        this.miEscuchador = miEscuchador;
    }

    public void setIngredientes(List<Ingredientes> ingredientes) {
        this.ingredientes = ingredientes;
    }

    class IngredienteViewHolder extends RecyclerView.ViewHolder{

        ImageView imgIngrediente;
        TextView txCategoriaIng,txNombreIng, txPrecioIng;
        CheckBox chkSeleccionIng;


        public IngredienteViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIngrediente = itemView.findViewById(R.id.img_Ingrediente);
            txCategoriaIng = itemView.findViewById(R.id.tx_CategoriaIng);
            txNombreIng = itemView.findViewById(R.id.tx_NombreIng);
            txPrecioIng = itemView.findViewById(R.id.tx_PrecioIng);
            chkSeleccionIng = itemView.findViewById(R.id.chk_SeleccionIng);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingrediente,parent,false);
        return new IngredienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        final IngredienteViewHolder miHolder = (IngredienteViewHolder) holder;
        final Ingredientes miIngrediente = ingredientes.get(position);

        Picasso.get().load(miIngrediente.getImagen()).into(miHolder.imgIngrediente);
        miHolder.txCategoriaIng.setText(miIngrediente.getCategoria());
        miHolder.txNombreIng.setText(miIngrediente.getNombre());
        miHolder.txPrecioIng.setText("$ "+miIngrediente.getPrecio());

        miHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                miEscuchador.metodoOnClick(miIngrediente,position,chk);
                Log.d("Prueba-Click","Hice click Adaptador en: "+miIngrediente.getNombre());
                /*if (miHolder.chkSeleccionIng.isChecked()) {
                    miHolder.chkSeleccionIng.setChecked(false);
                    //Toast.makeText(v.getContext(), "Retiraste: "+miIngrediente.getNombre() , Toast.LENGTH_LONG).show();
                    precio -= miIngrediente.getPrecio();
                    Toast.makeText(v.getContext(), "precio "+precio, Toast.LENGTH_SHORT).show();
                }else{
                    miHolder.chkSeleccionIng.setChecked(true);
                    //Toast.makeText(v.getContext(), "Seleccionaste: "+miIngrediente.getNombre()+ " Precio: " + miIngrediente.getPrecio(), Toast.LENGTH_LONG).show();
                    precio += miIngrediente.getPrecio();
                    Toast.makeText(v.getContext(), "precio "+precio, Toast.LENGTH_SHORT).show();
                }*/
            }
        });


    }


    @Override
    public int getItemCount() {
        return ingredientes.size();
    }

    public interface NombreDeInterface {
        void metodoOnClick(Ingredientes ingrediente, int posicion, boolean chk);
    }

}
