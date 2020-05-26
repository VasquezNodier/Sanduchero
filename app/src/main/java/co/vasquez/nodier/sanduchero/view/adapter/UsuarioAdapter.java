package co.vasquez.nodier.sanduchero.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.vasquez.nodier.sanduchero.R;
import co.vasquez.nodier.sanduchero.model.entity.Usuarios;

public class UsuarioAdapter extends RecyclerView.Adapter {

    List<Usuarios>usuarios;
    NombreDeInterface miEscuhador;

    public UsuarioAdapter(List<Usuarios> usuarios, NombreDeInterface miEscuhador) {
        this.usuarios = usuarios;
        this.miEscuhador = miEscuhador;
    }

    class UsuarioViewHolder extends RecyclerView.ViewHolder{

        TextView nombreUser, correoUser, contraUser;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreUser = itemView.findViewById(R.id.nombre_user);
            correoUser = itemView.findViewById(R.id.correo_user);
            contraUser = itemView.findViewById(R.id.contra_user);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View miVista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario,
                parent, false);
        return new UsuarioViewHolder(miVista);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        final UsuarioViewHolder miHolder = (UsuarioViewHolder) holder;
        final Usuarios miUsuario =usuarios.get(position);

        miHolder.nombreUser.setText(miUsuario.getNombre());
        miHolder.correoUser.setText(miUsuario.getCorreo());
        miHolder.contraUser.setText(miUsuario.getContra());

        miHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miEscuhador.metodoOnclick(miUsuario,position);
                Log.d("Prueba-click","Hice click en "+miUsuario);
            }
        });

    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public interface NombreDeInterface{
        void metodoOnclick(Usuarios usuario, int posicion);
    }



}
