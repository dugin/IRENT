package dugin.irent.util;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Rodrigo on 16/12/2015.
 */
public class PrefManagerUtil {
    // Shared pref file name

    private static final String PREF_NAME_1 = "MainActivity";
    private static final String PREF_NAME_2 = "FacebookUtil";


    private static final String MEU_NOME = "nome";
    private static final String MINHA_IMG = "imgURL";

    private static final String IS_LOGGED = "isLogged";


    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context mContext;
    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Constructor
    public PrefManagerUtil(Context context, String nomeDaClasse) {
        this.mContext = context;


        switch (nomeDaClasse) {
            case PREF_NAME_1:
                pref = mContext.getSharedPreferences(PREF_NAME_1, PRIVATE_MODE);
                break;
            case PREF_NAME_2:
                pref = mContext.getSharedPreferences(PREF_NAME_2, PRIVATE_MODE);
                break;


        }
        editor = pref.edit();
    }


    public String getNomeFacebook() {

        return pref.getString(MEU_NOME, null);
    }

    public void setNomeFacebook(String meuNome) {

        editor.putString(MEU_NOME, meuNome);
        editor.commit();

    }

    public String getImgURLFacebook() {

        return pref.getString(MINHA_IMG, null);
    }

    public void setImgURLFacebook(String imgURLFacebook) {

        editor.putString(MINHA_IMG, imgURLFacebook);
        editor.commit();


    }

    public boolean getIsLogged() {

        return pref.getBoolean(IS_LOGGED, false);
    }

    public void setIsLogged(Boolean isLogged) {
        editor.putBoolean(IS_LOGGED, isLogged);
        editor.commit();
    }

    public void apagar() {
        editor.clear();
        editor.commit();
    }
}