package fordonor.com.fordonor.Signin;

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
 * Created by nitsd024 on 8/3/18.
 */

public class SigninParser extends AsyncTask<Void, Void, String> {

    private Context mContext;
    private IParser iTypeOfRegister;
    private String username, password;
    private SharedPref pref;


    public SigninParser(Context mContext, String username, String password,IParser iTypeOfRegister) {

        this.mContext = mContext;
        this.iTypeOfRegister = iTypeOfRegister;
        this.username=username;
        this.password=password;
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

            params.put("email", username);
            params.put("password", password);


        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("param_login "+params);
        return Connection.excutPost(mContext, AppData.LOGIN, params);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Log.e("response",s);
        try {
            JSONObject jObj = new JSONObject(s);
            JSONObject object=jObj.getJSONObject("value");
            String statuscode=jObj.getString("statuscode");
            if(statuscode.equals("404")){
                Toast.makeText(mContext, object.getString("message"), Toast.LENGTH_SHORT).show();

            }
            if(statuscode.equals("200")){
                //Toast.makeText(mContext, object.getString("statuscode"), Toast.LENGTH_SHORT).show();
                iTypeOfRegister.onPostExecute(statuscode);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}