package co.vasquez.nodier.sanduchero.model.repository;

import android.content.Context;
import android.telecom.Call;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.vasquez.nodier.sanduchero.model.CallBackFirestore;
import co.vasquez.nodier.sanduchero.model.entity.Ingredientes;
import co.vasquez.nodier.sanduchero.model.entity.Sanduches;

public class SanducheRepo {

    private FirebaseFirestore bdFirestore;


    public SanducheRepo(Context context) {
        this.bdFirestore = FirebaseFirestore.getInstance();
    }

    public void obtenerNuestrosSanduches(final CallBackFirestore<List<Sanduches>> callBackFirestore) {
        //bdFirestore.collection("sanduches").get().addOnCompleteListener(
        bdFirestore.collection("sanduches").whereEqualTo("categoria", "nuestros").get().addOnCompleteListener(
                new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Sanduches> sanduches = new ArrayList<>();
                            if (task.getResult() != null) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Sanduches miSanduche = document.toObject(Sanduches.class);

                            /*miSanduche.setNombre(document.getString("nombre"));
                            miSanduche.setPrecio(document.getDouble("precio"));
                            miSanduche.setCategoria(document.getString("categoria"));
                            miSanduche.setImagen(document.getString("imagen"));*/
                                    sanduches.add(miSanduche);
                                }
                            }
                            callBackFirestore.correcto(sanduches);
                        }
                    }
                });
    }

    public void obtenerSanduchesCreados(final CallBackFirestore<List<Sanduches>> callBackFirestore) {
        //Para obtener el Array que está dentro del documento
        //bdFirestore.collection("sanducheprueba").get().addOnCompleteListener(
        bdFirestore.collection("sanduches").whereEqualTo("categoria", "creados").get().addOnCompleteListener(
                new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Sanduches> sanduches = new ArrayList<>();
                            if (task.getResult() != null) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Sanduches miSanduche = document.toObject(Sanduches.class);

                            /*miSanduche.setNombre(document.getString("nombre"));
                            miSanduche.setPrecio(document.getDouble("precio"));
                            miSanduche.setCategoria(document.getString("categoria"));
                            miSanduche.setImagen(document.getString("imagen"));*/
                                    sanduches.add(miSanduche);
                                }
                            }
                            callBackFirestore.correcto(sanduches);
                        }
                    }
                });
    }

    public void agregarSanduche(final Sanduches miSanduche, final CallBackFirestore<Sanduches> callBackFirestore) {
        bdFirestore.collection("sanduches").add(miSanduche).addOnCompleteListener(
                new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            callBackFirestore.correcto(miSanduche);
                        }
                    }
                });
    }

    public void agregarSanducheR(final Sanduches miSanduche, final CallBackFirestore<Sanduches> callBackFirestore, String id) {
        bdFirestore.collection("sanduches").document(id).set(miSanduche).addOnCompleteListener(
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            callBackFirestore.correcto(miSanduche);
                        }
                    }
                }
        );
    }

    /*public void eliminarSanduche(final Sanduches miSanduche,final CallBackFirestore<Sanduches> callBackFirestore){
        bdFirestore.collection("sanduches").document(miSanduche.getIdSan()).delete().
                addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    callBackFirestore.correcto(miSanduche);
                }
            }
        });
    }

    public void editarSanduche(final Sanduches miSanduche, final CallBackFirestore<Sanduches> callBackFirestore){
        bdFirestore.collection("sanduches").document(miSanduche.getIdSan()).
                set(miSanduche).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    callBackFirestore.correcto(miSanduche);
                }
            }
        });
    }*/

}
