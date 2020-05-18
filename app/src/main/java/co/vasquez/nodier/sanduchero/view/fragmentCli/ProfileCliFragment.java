package co.vasquez.nodier.sanduchero.view.fragmentCli;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.vasquez.nodier.sanduchero.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileCliFragment extends Fragment {

    public ProfileCliFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_cli, container, false);
    }
}
