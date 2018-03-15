package fordonor.com.fordonor.FCM;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


/**
 * Created by RMV-IT on 18-07-2016.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "dev_";
    //private SharedPref pref;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //pref = new SharedPref(MyFirebaseMessagingService.this);

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getNotification() != null) {
            System.out.println("dev_data " + remoteMessage.getNotification().getBody());

        }

        if (remoteMessage.getData() != null)
            System.out.println("dev_data " + remoteMessage.getData());




    }





}
