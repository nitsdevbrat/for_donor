package fordonor.com.fordonor;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.crashlytics.android.Crashlytics;
import fordonor.com.fordonor.Signin.SigninActivity;

import io.fabric.sdk.android.Fabric;

public class SplashActivity extends AppCompatActivity {
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        initialize();
    }


    private void initialize() {

        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {


                    startActivity(new Intent(SplashActivity.this, SigninActivity.class));
                    finish();

                //start your activity here
            }
        };
        handler.postDelayed(runnable, 3000);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
