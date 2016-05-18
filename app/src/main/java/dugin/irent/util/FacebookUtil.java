package dugin.irent.util;


import android.content.Context;
import android.util.Log;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import de.greenrobot.event.EventBus;
import dugin.irent.R;
import dugin.irent.domain.FacebookInfo;
import dugin.irent.eventBus.MessageEB;


/**
 * Created by Rodrigo on 10/03/2016.
 */
public class FacebookUtil {

    static CallbackManager callbackManager;

    private final String TAG = this.getClass().getSimpleName();
    LoginButton loginButton;
    Firebase ref;
    Context mContext;
    AccessTokenTracker mFacebookAccessTokenTracker;
    PrefManagerUtil prefManager;


    public FacebookUtil(View v, Context context) {


        mContext = context;

        prefManager = new PrefManagerUtil(mContext, TAG);


        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) v.findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("email", "user_photos", "public_profile"));


        mFacebookAccessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

                ref = new Firebase("https://irent.firebaseio.com/");


                onFacebookAccessTokenChange(currentAccessToken);

            }
        };


    }

    public static CallbackManager getCallbackManager() {
        return callbackManager;
    }


    private void onFacebookAccessTokenChange(AccessToken token) {

        if (token != null) {

            ref.authWithOAuthToken("facebook", token.getToken(), new Firebase.AuthResultHandler() {
                @Override
                public void onAuthenticated(AuthData authData) {


                    HashMap<String, Object> map = (HashMap<String, Object>) authData.getProviderData().get("cachedUserProfile");

                    Map<String, String> mapaBD = new HashMap<>();


                    String nome = (String) map.get("first_name");
                    String sobrenome = (String) map.get("last_name");
                    String email = (String) map.get("email");

                    String sexo = (String) map.get("gender");

                    LinkedHashMap ageRange = (LinkedHashMap) map.get("age_range");

                    mapaBD.put("id", authData.getUid());
                    mapaBD.put("nome", nome + " " + sobrenome);
                    mapaBD.put("email", email);
                    mapaBD.put("sexo", sexo);


                    mapaBD.put("imgURL", authData.getProviderData().get("profileImageURL").toString());

                    String faixa_idade;

                    if (ageRange.get("max") == null)
                        faixa_idade = "21+";
                    else
                        faixa_idade = ageRange.get("min") + "-" + ageRange.get("max");

                    mapaBD.put("faixa_idade", faixa_idade);

                    String aniversario = (String) map.get("birthday");

                    if (aniversario != null)
                        mapaBD.put("aniversario", aniversario);


                    new FacebookInfo(nome, faixa_idade, email, sexo, authData.getUid(), authData.getProviderData().get("profileImageURL").toString());


                    for (Map.Entry<String, String> entry : mapaBD.entrySet()) {
                        Log.println(Log.ASSERT, TAG, entry.getKey() + "/" + entry.getValue());
                    }

                    ref.child("usuarios").child(authData.getUid()).setValue(mapaBD);

                    prefManager.setIsLogged(true);

                    MessageEB m = new MessageEB(TAG);

                    EventBus.getDefault().post(m);

                    // The Facebook user is now authenticated with your Firebase app
                }


                @Override
                public void onAuthenticationError(FirebaseError firebaseError) {
                    Log.println(Log.ASSERT, TAG, "" + firebaseError.getMessage());
                    Log.println(Log.ASSERT, TAG, "" + firebaseError.getDetails());
                }
            });
        } else {
        /* Logged out of Facebook so do a logout from the Firebase app */
            ref.unauth();
        }


    }


}
