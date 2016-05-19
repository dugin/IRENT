package dugin.irent.fragments;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.squareup.picasso.Picasso;

import dugin.irent.Extras.CircleTransformation;
import dugin.irent.R;
import dugin.irent.json.Facebook;
import dugin.irent.util.FirebaseUtil;
import dugin.irent.util.PrefManagerUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarOwnerInfoFragment extends Fragment {

    private final String TAG = this.getClass().getSimpleName();
    Button btnContinuar;
    PrefManagerUtil prefManager;
    ImageView profileImg;
    TextView nome;
    EditText email;

    public CarOwnerInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_car_owner_info, container, false);

        prefManager = new PrefManagerUtil(getActivity(), "FacebookUtil");

        profileImg = (ImageView) v.findViewById(R.id.profile_img);
        nome = (TextView) v.findViewById(R.id.textView_nome);
        email = (EditText) v.findViewById(R.id.editText_email);

        Firebase ref = FirebaseUtil.getFirebase().getRef();


        ref.child("usuarios").child(prefManager.getFacebookFirebaseID()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Facebook facebook = dataSnapshot.getValue(Facebook.class);


                Picasso.with(getActivity())
                        .load(facebook.getImgURL())
                        .transform(new CircleTransformation())

                        .into(profileImg);

                nome.setText(facebook.getNome());
                email.setText(facebook.getEmail());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        btnContinuar = (Button) v.findViewById(R.id.btnContinuar);

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim,
                        android.R.animator.fade_in, android.R.animator.fade_out);


                transaction.replace(R.id.mainLayout, new CarOwnerFormsFragment(), "CarOwnerFormsFragment");
                transaction.addToBackStack("CarOwnerInfoFragment");
                transaction.commit();

            }
        });


        return v;

    }

}
