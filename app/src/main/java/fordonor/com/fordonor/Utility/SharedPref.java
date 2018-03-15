package fordonor.com.fordonor.Utility;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by debasmita on 20/5/15.
 */
public class SharedPref {

    private static final String USER_PREFS = "FOR_DONOR";
    private SharedPreferences appSharedPrefs,parentloginPreferences;
    private SharedPreferences.Editor prefsEditor,parentEditorLogin;

    public SharedPref(Context context) {
        this.appSharedPrefs = context.getSharedPreferences(USER_PREFS, Activity.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();

        parentloginPreferences=context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
        parentEditorLogin=parentloginPreferences.edit();
    }

    public String getLoginValue(String loginkeyvalue){
        return parentloginPreferences.getString(loginkeyvalue,"");
    }
    public void setLoginValue(String loginkey,String loginvalue){
        parentEditorLogin.putString(loginkey, loginvalue).commit();
    }

    //get value

    public String getString(String stringKeyValue) {
        return appSharedPrefs.getString(stringKeyValue, "");
    }

    public int getInt(String intKeyValue) {

        return appSharedPrefs.getInt(intKeyValue, -1);
    }

    public Long getValue_long(String longKeyValue) {
        return appSharedPrefs.getLong(longKeyValue, 0L);
    }



    public Boolean getValue_boolean(String stringKeyValue) {
        return appSharedPrefs.getBoolean(stringKeyValue, false);
    }

    public float getValue_float(String stringKeyValue) {
        return appSharedPrefs.getFloat(stringKeyValue,0);
    }


    ///setvalue

    public void setString(String stringKeyValue, String _stringValue) {

        prefsEditor.putString(stringKeyValue, _stringValue).commit();

    }

    public void setInt(String intKeyValue, int _intValue) {

        prefsEditor.putInt(intKeyValue, _intValue).commit();
    }
    public void setValue_long(String longKeyValue, long _longValue) {

        prefsEditor.putLong(longKeyValue, _longValue).commit();
    }

    public void setValue_float(String longKeyValue, float _floatValue) {

        prefsEditor.putFloat(longKeyValue, _floatValue).commit();
    }



    public void setValue_boolean(String stringKeyValue, Boolean _bool) {

        prefsEditor.putBoolean(stringKeyValue, _bool).commit();

    }


    public void clearData() {
        prefsEditor.clear().commit();

    }



}
