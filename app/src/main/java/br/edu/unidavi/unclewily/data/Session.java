package br.edu.unidavi.unclewily.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Guilherme on 11/04/2018.
 */

public class Session {

    private final String FIELD_USERNAME = "username";
    private final String FIELD_TOKEN = "token";
    private final String CATEGORY_SESSION = "session";

    private SharedPreferences sharedPreferences;

    public Session(Context context){
        sharedPreferences =
                context.getSharedPreferences(CATEGORY_SESSION,
                        Context.MODE_PRIVATE);
    }


    public void saveEmailInSession(String emailValue){
        SharedPreferences.Editor editor =
                sharedPreferences.edit();
        editor.putString(FIELD_USERNAME, emailValue);
        editor.commit();
    }

    public String getEmailInSession(){
        return sharedPreferences.getString(FIELD_USERNAME,"");
    }


    public void saveTokenInSession(String tokenValue){
        SharedPreferences.Editor editor =
                sharedPreferences.edit();
        editor.putString(FIELD_TOKEN, tokenValue);
        editor.commit();
    }

    public String getTokenInSession(){
        return sharedPreferences.getString(FIELD_TOKEN,"");
    }
}
