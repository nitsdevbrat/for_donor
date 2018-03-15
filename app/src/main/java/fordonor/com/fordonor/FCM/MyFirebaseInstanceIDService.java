package fordonor.com.fordonor.FCM;


import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import fordonor.com.fordonor.Utility.SharedPref;


/**
 * Created by RMV-IT on 18-07-2016.
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {


    private SharedPref pref;

    @Override
    public void onTokenRefresh() {

        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        Log.e("fcm_token",refreshedToken);
        //Displaying token on logcat
        System.out.println("Devd_FireBaseInstanceIDService_Refreshed_token: "+refreshedToken);
        pref = new SharedPref(MyFirebaseInstanceIDService.this);
        pref.setString("fcm_token", refreshedToken);
//


    }


}