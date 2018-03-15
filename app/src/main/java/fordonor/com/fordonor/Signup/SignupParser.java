package fordonor.com.fordonor.Signup;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import fordonor.com.fordonor.Utility.AppData;
import fordonor.com.fordonor.Utility.AsycnParser.Connection;
import fordonor.com.fordonor.Utility.AsycnParser.IParser;
import fordonor.com.fordonor.Utility.SharedPref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nitsd024 on 8/3/18.
 */

public class SignupParser extends AsyncTask<Void, Void, String> {

    private Context mContext;
    private IParser iTypeOfRegister;
    private String username, password,country,street,city,state,
            fb_id,google_id,instagram_id,linkedin_id,twitter_id,first_name,last_name,designation,age,phno_no;
    private SharedPref pref;


    public SignupParser(Context mContext,String first_name,String last_name,String designation,String age,String phno_no, String username, String password,String country,String street,String city
            ,String state,String fb_id,String google_id,
                   String linkedin_id,String twitter_id,String instagram_id, IParser iTypeOfRegister) {

        this.mContext = mContext;
        this.iTypeOfRegister = iTypeOfRegister;
        this.username=username;
        this.password=password;
        this.city=city;
        this.country=country;
        this.state=state;
        this.street=street;
        this.fb_id=fb_id;
        this.google_id=google_id;
        this.instagram_id=instagram_id;
        this.linkedin_id=linkedin_id;
        this.twitter_id=twitter_id;
        this.phno_no=phno_no;
        this.designation=designation;
        this.age=age;
        this.first_name=first_name;
        this.last_name=last_name;
        this.designation=designation;
        this.age=age;
        this.phno_no=phno_no;
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
        final JSONObject country_param= new JSONObject();
        final JSONArray  role=new JSONArray();
        final JSONObject role_param= new JSONObject();
        Date date = new Date();
        String newstring = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);

        try {

            role_param.put("is_active","");
            role_param.put("role_name","");
            role.put(role_param);

            country_param.put("country_name",country);
            params.put("city",city);
            params.put("created_date",newstring);
            params.put("first_name",first_name);
            params.put("last_name",last_name);
            params.put("designation",designation);
            params.put("age",age);
            params.put("phno_no",phno_no);
            params.put("fb_id",fb_id);
            params.put("google_id",google_id);
            params.put("instagram_id",instagram_id);
            params.put("is_active","True");
            params.put("is_deleted","False");
            params.put("last_name","");
            params.put("linkedin_id",linkedin_id);
            params.put("pinterest_id","");
            params.put("state",state);
            params.put("street",street);
            params.put("twitter_id",twitter_id);
            params.put("email", username);
            params.put("password", password);
            params.put("roles", role);
            params.put("country", country_param);
            params.put("device_id", pref.getString("fcm_token"));
            params.put("device_type", "ANDROID");
            params.put("isCampaign", "True");


        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("param_login "+params);
        return Connection.excutPost(mContext, AppData.SIGNUP, params);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Log.e("response",s);
        try {
            JSONObject jObj = new JSONObject(s);
            JSONObject object=jObj.getJSONObject("value");
            String statuscode=jObj.getString("statuscode");
            if(statuscode.equals("409")){
                Toast.makeText(mContext, object.getString("message"), Toast.LENGTH_SHORT).show();


            }
            if(statuscode.equals("201")){
                pref.setString("user_id",object.getString("id"));
                //Toast.makeText(mContext, object.getString("statuscode"), Toast.LENGTH_SHORT).show();
                iTypeOfRegister.onPostExecute(statuscode);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}