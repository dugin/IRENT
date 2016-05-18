package dugin.irent.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.firebase.client.Firebase;

import java.util.Timer;
import java.util.TimerTask;

import dugin.irent.R;

/**
 * Created by Rodrigo on 17/05/2016.
 */
public class SplashScreenActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Firebase.setAndroidContext(this);

        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                finish();

                Intent intent = new Intent();
                intent.setClass(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 2000);
    }

}