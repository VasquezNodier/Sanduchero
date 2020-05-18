package co.vasquez.nodier.sanduchero.view.fragmentCli;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import co.vasquez.nodier.sanduchero.R;
import co.vasquez.nodier.sanduchero.model.entity.Sanduches;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleSanducheFragment extends Fragment {

    TextView txNombreSanduche, txPrecioSanduche;
    ListView lvIngredienteSan;
    private ImageView imgSan;
    List<HashMap<String, Object>> ingrediente;

    public DetalleSanducheFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_detalle_sanduche, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        asociarElementos(view);
        Sanduches sanduche= DetalleSanducheFragmentArgs.fromBundle(getArguments()).getMiSanduche();
        Picasso.get().load(sanduche.getImagen()).into(imgSan);
        txNombreSanduche.setText(sanduche.getNombre());
        txPrecioSanduche.setText("$"+sanduche.getPrecio());

        //Toast.makeText(view.getContext(), "ingre: "+ sanduche.getIngrediente() , Toast.LENGTH_SHORT).show();

        /*for (int i = 0; i < sanduche.getIngrediente().size(); i++) {
            Toast.makeText(view.getContext(), "ingre: "+ sanduche.getIngrediente().get(i).getNombreIng(), Toast.LENGTH_SHORT).show();
        }*/
        /*Object valor;
        ingrediente = sanduche.getIngrediente();
        for (HashMap<String, Object> map : ingrediente) {
            for (HashMap.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                valor = entry.getValue();
                //Toast.makeText(view.getContext(), "prueba "+ key, Toast.LENGTH_SHORT).show();
                Toast.makeText(view.getContext(), ""+valor, Toast.LENGTH_SHORT).show();
            }
        }*/
        // ASIGNO MI LISTA DE MAP DE INGREDIENTES AL LIST VIEW
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),android.R.layout.
                simple_list_item_1,sanduche.getIngrediente());

        //lvIngredienteSan.setAdapter(arrayAdapter);
    }

    public void asociarElementos(View view){
        txNombreSanduche = view.findViewById(R.id.tx_NombreSanduche);
        txPrecioSanduche = view.findViewById(R.id.tx_PrecioSanduche);
        lvIngredienteSan = view.findViewById(R.id.lv_IngredienteSan);
        imgSan = view.findViewById(R.id.img_San);
    }
}
