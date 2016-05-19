package dugin.irent.fragments;


import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import dugin.irent.R;
import dugin.irent.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarOwnerFormsFragment extends Fragment {


    private final String TAG = this.getClass().getSimpleName();
    private final int RESULT_LOAD_IMAGE = 1000;
    Button addImg;
    ImageView carImg;

    public CarOwnerFormsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_car_owner_forms, container, false);


        addImg = (Button) v.findViewById(R.id.btnAddImg);

        carImg = (ImageView) v.findViewById(R.id.imageView_car);

        addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);


            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == RESULT_LOAD_IMAGE && resultCode == MainActivity.RESULT_OK
                && null != data) {
            Uri selectedImage = data.getData();
            Picasso.with(getActivity())
                    .load(selectedImage)
                    .into(carImg);


        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}
