package fordonor.com.fordonor.Profile;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import fordonor.com.fordonor.Utility.AppData;
import fordonor.com.fordonor.Utility.AsycnParser.Connection;
import fordonor.com.fordonor.Utility.AsycnParser.IParser;
import fordonor.com.fordonor.Utility.SharedPref;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by nitsd024 on 9/3/18.
 */

public class ProfileParser extends AsyncTask<Void, Void, String> {

    private Context mContext;
    private IParser iTypeOfRegister;
    private String username, password;
    private SharedPref pref;


    public ProfileParser(Context mContext,IParser iTypeOfRegister) {

        this.mContext = mContext;
        this.iTypeOfRegister = iTypeOfRegister;

        pref= new SharedPref(mContext);

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (iTypeOfRegister != null)
            iTypeOfRegister.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... voids) {
        final JSONObject params = new JSONObject();

        try {


            //+pref.getString("user_id")

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("param_login "+params);
        return Connection.excutPost(mContext, AppData.SHOWPROFILE+"?id="+pref.getString("user_id"),params);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Log.e("response_profile",s);
        try {
            JSONObject jObj = new JSONObject(s);
            JSONObject object=jObj.getJSONObject("value");
            String statuscode=jObj.getString("statuscode");
            if(statuscode.equals("404")){
                Toast.makeText(mContext, object.getString("message"), Toast.LENGTH_SHORT).show();

            }
            if(statuscode.equals("200")){
                //Toast.makeText(mContext, statuscode, Toast.LENGTH_SHORT).show();
                iTypeOfRegister.onPostExecute(object.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}