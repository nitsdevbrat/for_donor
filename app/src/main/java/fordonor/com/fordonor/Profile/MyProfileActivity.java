package fordonor.com.fordonor.Profile;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import fordonor.com.fordonor.DonorDetails.DonorVerificationActivity;
import fordonor.com.fordonor.R;
import fordonor.com.fordonor.Utility.AsycnParser.IParser;
import fordonor.com.fordonor.Utility.ForDonorTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyProfileActivity extends AppCompatActivity {

    private String change;
    private CircleImageView profile_img_cv;
    private ForDonorTextView profile_name_tv,verified_email_tv,verified_driver_tv,
            profile_about_tv,age_tv,phone_tv,location_tv,become_a_donor;
    private SwitchCompat fb_switch,twitter_switch,linkedin_switch,instagram_switch;
    private CheckBox donor_selection;
    private RelativeLayout rbt_donor_pro_RL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        profile_img_cv=(CircleImageView)findViewById(R.id.profile_img_cv);
        profile_name_tv=(ForDonorTextView)findViewById(R.id.profile_name_tv);
        verified_email_tv=(ForDonorTextView)findViewById(R.id.verified_email_tv);
        verified_driver_tv=(ForDonorTextView)findViewById(R.id.verified_driver_tv);
        profile_about_tv=(ForDonorTextView)findViewById(R.id.profile_about_tv);
        age_tv=(ForDonorTextView)findViewById(R.id.age_tv);
        phone_tv=(ForDonorTextView)findViewById(R.id.phone_tv);
        become_a_donor=(ForDonorTextView)findViewById(R.id.become_a_donor);
        location_tv=(ForDonorTextView)findViewById(R.id.location_tv);
        fb_switch=(SwitchCompat)findViewById(R.id.fb_switch);
        twitter_switch=(SwitchCompat)findViewById(R.id.twitter_switch);
        linkedin_switch=(SwitchCompat)findViewById(R.id.linkedin_switch);
        instagram_switch=(SwitchCompat)findViewById(R.id.instagram_switch);
        donor_selection=(CheckBox)findViewById(R.id.donor_selection);
        rbt_donor_pro_RL=(RelativeLayout)findViewById(R.id.rbt_donor_pro_RL);

        become_a_donor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyProfileActivity.this, DonorVerificationActivity.class));
            }
        });

        new ProfileParser(this, new IParser() {

            @Override
            public void onPreExecute() {

            }

            @Override
            public void onPostExecute(ArrayList<?> resultArrayList) {

            }

            @Override
            public void onPostExecute(String s) {

                Log.e("response_profile",s);

                try {
                    JSONObject obj=new JSONObject(s);

                    obj.getString("id");
                    obj.getString("isDonor");
                    obj.getString("isCampaign");
                    JSONObject countryobj=obj.getJSONObject("country");

                    profile_name_tv.setText(obj.getString("first_name")+" "+obj.getString("last_name"));
                    verified_email_tv.setText(obj.getString("email"));
                    profile_about_tv.setText(obj.getString("designation"));
                    age_tv.setText(obj.getString("age"));
                    phone_tv.setText(obj.getString("phno_no"));
                    location_tv.setText(countryobj.getString("country_name"));

                    if(!obj.getString("fb_id").equals("")){
                        fb_switch.setChecked(true);
                    }else{
                        fb_switch.setChecked(false);
                    }
                    if(!obj.getString("linkedin_id").equals("")){
                        linkedin_switch.setChecked(true);
                    }else{
                        linkedin_switch.setChecked(false);
                    }
                    if(!obj.getString("twitter_id").equals("")){
                        twitter_switch.setChecked(true);
                    }else{
                        twitter_switch.setChecked(false);
                    }
                    if(!obj.getString("instagram_id").equals("")){
                        instagram_switch.setChecked(true);
                    }else{
                        instagram_switch.setChecked(false);
                    }

                    obj.getString("state");
                    obj.getString("city");
                    obj.getString("street");
                    obj.getString("google_id");
                    obj.getString("pinterest_id");
                    obj.getString("is_deleted");
                    obj.getString("is_active");
                    obj.getString("created_date");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onPostFailure() {

            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }
}
