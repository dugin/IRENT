package dugin.irent.activities;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import dugin.irent.R;
import dugin.irent.fragments.CarListFragment;
import dugin.irent.fragments.RentChoiceFragment;
import dugin.irent.util.FacebookUtil;
import dugin.irent.util.PrefManagerUtil;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PrefManagerUtil prefManagerUtil = new PrefManagerUtil(this, "FacebookUtil");

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim,
                android.R.animator.fade_in, android.R.animator.fade_out);

        if (prefManagerUtil.getIsLogged()) {
            transaction.replace(R.id.mainLayout, new CarListFragment(), "CarListFragment");
            transaction.addToBackStack(null);
        } else {
            transaction.replace(R.id.mainLayout, new RentChoiceFragment(), "RentChoiceFragment");
            transaction.addToBackStack(null);
        }

        transaction.commit();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.println(Log.ASSERT, TAG, "facebookUtil onActivityResult");
        FacebookUtil.getCallbackManager().onActivityResult(requestCode, resultCode, data);


    }

    @Override
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() == 1)
            finish();

        super.onBackPressed();

    }
}

