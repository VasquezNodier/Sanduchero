package co.vasquez.nodier.sanduchero.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

    public IngredienteAdapter(List<Ingredientes> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void setIngredientes(List<Ingredientes> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void setMiEscuchador(NombreDeInterface miEscuchador) {
        this.miEscuchador = miEscuchador;
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

        miHolder.chkSeleccionIng.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(miEscuchador!=null){
                    miEscuchador.metodoOnClick(miIngrediente,position,isChecked);
                }

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
