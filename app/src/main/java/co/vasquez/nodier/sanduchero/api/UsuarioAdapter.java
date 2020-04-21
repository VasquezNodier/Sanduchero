package co.vasquez.nodier.sanduchero.api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.vasquez.nodier.sanduchero.R;
import co.vasquez.nodier.sanduchero.modelo.Usuarios;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuariosViewHolder  > {

    Context context;
    List<Usuarios> listaUsuarios;
    private ViewGroup parent;
    private int viewType;

    @NonNull
    @Override
    public UsuarioAdapter.UsuariosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        this.viewType = viewType;
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_usuario,null,false);
        return new UsuariosViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioAdapter.UsuariosViewHolder holder, int position) {
        holder.txvIdUsuario.setText(listaUsuarios.get(position).getIdUsu());
        holder.txvNombre.setText(listaUsuarios.get(position).getNombreUsu());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class UsuariosViewHolder extends RecyclerView.ViewHolder {

        TextView txvIdUsuario, txvNombre;

        public UsuariosViewHolder(@NonNull View itemView) {
            super(itemView);

            txvIdUsuario = itemView.findViewById(R.id.txv_IdUsuario);
            txvNombre = itemView.findViewById(R.id.txv_Nombre);

        }
    }
}
