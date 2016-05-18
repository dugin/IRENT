package dugin.irent.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dugin.irent.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFormFragment extends Fragment {


    public RegisterFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_form, container, false);
    }

}
