package co.vasquez.nodier.sanduchero.model.repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import co.vasquez.nodier.sanduchero.model.CallBackFirestore;
import co.vasquez.nodier.sanduchero.model.entity.Ingredientes;

public class IngredienteRepo {

    private FirebaseFirestore bdFirestore;

    public IngredienteRepo(Context context) {
        this.bdFirestore = FirebaseFirestore.getInstance();
    }

    public void obtenerIngredientes(final CallBackFirestore<List<Ingredientes>> callBackFirestore) {

        bdFirestore.collection("ingredientes").get().addOnCompleteListener(
                new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Ingredientes> ingredientes = new ArrayList<>();
                            if (task.getResult() != null) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Ingredientes miIngrediente = document.toObject(Ingredientes.class);
                                    ingredientes.add(miIngrediente);
                                }
                            }
                            callBackFirestore.correcto(ingredientes);
                        }
                    }
                });


    }public void obtenerPanes(final CallBackFirestore<List<Ingredientes>> callBackFirestore) {

        //FILTRAR POR TIPO DE CATEGORÍA (PROTEINAS)
        final List<Ingredientes> ingredientes = new ArrayList<>();
        bdFirestore.collection("ingredientes").whereEqualTo("categoria",
                "panes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<Ingredientes> ingredientes = new ArrayList<>();
                    if (task.getResult() != null) {
                        for (QueryDocumentSnapshot document : task.getResult()){
                            Ingredientes miIngrediente = document.toObject(Ingredientes.class);
                            ingredientes.add(miIngrediente);
                        }
                    }
                    callBackFirestore.correcto(ingredientes);
                }
            }
        });


    }

}
