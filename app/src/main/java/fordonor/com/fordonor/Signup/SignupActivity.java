package fordonor.com.fordonor.Signup;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.hbb20.CountryCodePicker;
import fordonor.com.fordonor.Profile.MyProfileActivity;
import fordonor.com.fordonor.R;
import fordonor.com.fordonor.Utility.AsycnParser.IParser;

import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {
    private String userfname = "",g_id="",link_id="",twitter_id="", insta_id = "", userlname = "", email = "",fbUserId="";
    private EditText user_fname, et_user_lname, et_email,et_postal,et_state
                     ,et_city,et_street,et_country,et_password,et_phn_no,et_age,et_designation;
    private RelativeLayout btn_signup;
    private CountryCodePicker ccp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        initview();

        if (!getIntent().getExtras().equals(null)) {
            insta_id = getIntent().getExtras().getString("insta_id");
            fbUserId = getIntent().getExtras().getString("fbUserId");
            g_id = getIntent().getExtras().getString("g_id");
            twitter_id = getIntent().getExtras().getString("twitter_id");
            link_id = getIntent().getExtras().getString("link_id");

            userfname = getIntent().getExtras().getString("userfname");
            userlname = getIntent().getExtras().getString("userlname");
            email = getIntent().getExtras().getString("email");

            Log.e("intent_value"," "+insta_id+" "+fbUserId+" "
                    +g_id+" "+twitter_id+" "+link_id);
            /*//////////////Set Value///////////*/
            user_fname.setText(userfname);
            et_user_lname.setText(userlname);
            et_email.setText(email);


        }

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phonenumber="+"+ccp.getSelectedCountryCode()+et_phn_no.getText().toString();
                new SignupParser(SignupActivity.this,user_fname.getText().toString(),et_user_lname.getText().toString(),
                        et_designation.getText().toString(),
                        et_age.getText().toString(),phonenumber, et_email.getText().toString(),
                        et_password.getText().toString(), et_country.getText().toString(), et_street.getText().toString(),
                        et_city.getText().toString(), et_state.getText().toString(),fbUserId,g_id,link_id,twitter_id,
                        insta_id, new IParser() {
                    @Override
                    public void onPreExecute() {

                    }

                    @Override
                    public void onPostExecute(ArrayList<?> resultArrayList) {

                    }

                    @Override
                    public void onPostExecute(String s) {
                        if(s.equals("201")){
                            startActivity(new Intent(SignupActivity.this, MyProfileActivity.class));
                        }
                    }

                    @Override
                    public void onPostFailure() {

                    }
                }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            }
        });

    }

    /*//////////////////Id declare/////////////////*/
    private void initview() {
        user_fname = (EditText) findViewById(R.id.username);
        et_user_lname = (EditText) findViewById(R.id.et_user_lname);
        et_email = (EditText) findViewById(R.id.et_email);
        et_postal = (EditText) findViewById(R.id.et_postal);
        et_state = (EditText) findViewById(R.id.et_state);
        et_city = (EditText) findViewById(R.id.et_city);
        et_street = (EditText) findViewById(R.id.et_street);
        et_country = (EditText) findViewById(R.id.et_country);
        et_password = (EditText) findViewById(R.id.et_password);
        et_email = (EditText) findViewById(R.id.et_email);
        btn_signup=(RelativeLayout) findViewById(R.id.btn_signup);
        et_age=(EditText) findViewById(R.id.et_age);
        et_phn_no=(EditText) findViewById(R.id.et_phn_no);
        et_designation=(EditText) findViewById(R.id.et_designation);
        ccp=(CountryCodePicker) findViewById(R.id.ccp);



    }
}
