package dugin.irent.fragments;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.FacebookSdk;

import de.greenrobot.event.EventBus;
import dugin.irent.R;
import dugin.irent.eventBus.MessageEB;
import dugin.irent.util.FacebookUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private final String TAG = this.getClass().getSimpleName();

    Button btnCadastro;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        // Inflate the layout for this fragment
        EventBus.getDefault().register(this);


        new FacebookUtil(v, getActivity());

        btnCadastro = (Button) v.findViewById(R.id.btnCadastro);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim,
                        android.R.animator.fade_in, android.R.animator.fade_out);

                transaction.replace(R.id.mainLayout, new RegisterFormFragment(), "RegisterFormFragment");

                transaction.addToBackStack("RegisterFragment");
                transaction.commit();


            }
        });

        return v;
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }


    public void onEvent(MessageEB event) {

        if (event.getNomeClasse().equals("FacebookUtil")) {

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim,
                    android.R.animator.fade_in, android.R.animator.fade_out);

            transaction.replace(R.id.mainLayout, new CarListFragment(), "CarListFragment");

            transaction.addToBackStack(null);

            transaction.commit();

        }
    }
}
