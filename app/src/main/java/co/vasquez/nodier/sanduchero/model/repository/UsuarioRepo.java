package co.vasquez.nodier.sanduchero.model.repository;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import co.vasquez.nodier.sanduchero.model.CallBackFirestore;
import co.vasquez.nodier.sanduchero.model.entity.Usuarios;

public class UsuarioRepo {

    private FirebaseFirestore bdFirestore;

    public UsuarioRepo(Context context) {
        this.bdFirestore = FirebaseFirestore.getInstance();
    }

    public void agregarUsuario (final Usuarios miUsuario, final CallBackFirestore<Usuarios> callBackFirestore){
        bdFirestore.collection("usuarios").add(miUsuario).addOnCompleteListener(
                new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()){
                    callBackFirestore.correcto(miUsuario);
                }
            }
        });
    }

}
