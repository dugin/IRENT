package dugin.irent.fragments;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import dugin.irent.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RentChoiceFragment extends Fragment {


    private final String TAG = this.getClass().getSimpleName();
    Button btnQuero, btnTenho;
    Fragment registerFragment;
    FragmentTransaction transaction;

    public RentChoiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_rent_choice, container, false);

        btnQuero = (Button) v.findViewById(R.id.btnQuero);
        btnTenho = (Button) v.findViewById(R.id.btnTenho);


        transaction = getFragmentManager().beginTransaction();

        transaction.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim,
                android.R.animator.fade_in, android.R.animator.fade_out);


        registerFragment = new RegisterFragment();

        btnQuero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle args = new Bundle();
                args.putBoolean("TenhoCarro", false);


                registerFragment.setArguments(args);


                transaction.replace(R.id.mainLayout, registerFragment, "RegisterFragment");
                transaction.addToBackStack("RentChoiceFragment");
                transaction.commit();
            }
        });

        btnTenho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle args = new Bundle();
                args.putBoolean("TenhoCarro", true);

                registerFragment.setArguments(args);

                transaction.replace(R.id.mainLayout, registerFragment, "RegisterFragment");
                transaction.addToBackStack("RentChoiceFragment");
                transaction.commit();
            }
        });


        return v;
    }

}
