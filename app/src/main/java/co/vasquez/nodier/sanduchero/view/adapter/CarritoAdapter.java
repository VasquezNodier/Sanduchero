package co.vasquez.nodier.sanduchero.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.vasquez.nodier.sanduchero.R;
import co.vasquez.nodier.sanduchero.model.entity.ProductoPedir;

public class CarritoAdapter extends RecyclerView.Adapter {

    List<ProductoPedir> productos;
    NombreDeInterface miEscuchador;

    public CarritoAdapter(List<ProductoPedir> productos) {
        this.productos = productos;
    }

    public void setProductos(List<ProductoPedir> productos) {
        this.productos = productos;
    }

    public void setMiEscuchador(NombreDeInterface miEscuchador) {
        this.miEscuchador = miEscuchador;
    }

    class CarritoViewHolder extends RecyclerView.ViewHolder{

        TextView txNombreProducto, txPrecioProducto, txCantidadProducto;

        public CarritoViewHolder(@NonNull View itemView) {
            super(itemView);
            txNombreProducto = itemView.findViewById(R.id.tx_Nombre_Producto);
            txCantidadProducto = itemView.findViewById(R.id.tx_Cantidad_Producto);
            txPrecioProducto = itemView.findViewById(R.id.tx_Precio_Producto);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carrito, parent, false);
        return new CarritoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final CarritoViewHolder miHolder = (CarritoViewHolder) holder;
        final ProductoPedir productoPedir = productos.get(position);

        miHolder.txNombreProducto.setText(productoPedir.getNombre());
        miHolder.txCantidadProducto.setText(""+productoPedir.getCantidad());
        miHolder.txPrecioProducto.setText(""+productoPedir.getPrecio());

        miHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miEscuchador.metodoOnClick(productoPedir,position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public interface NombreDeInterface {
        void metodoOnClick(ProductoPedir productoPedir, int posicion);
    }

}
