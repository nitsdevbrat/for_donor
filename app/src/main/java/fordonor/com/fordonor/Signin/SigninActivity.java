package fordonor.com.fordonor.Signin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.linkedin.platform.APIHelper;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIApiError;
import com.linkedin.platform.errors.LIAuthError;
import com.linkedin.platform.listeners.ApiListener;
import com.linkedin.platform.listeners.ApiResponse;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;
import fordonor.com.fordonor.R;
import fordonor.com.fordonor.Signin.Instagram.InstagramApp;
import fordonor.com.fordonor.Signup.SignupActivity;
import fordonor.com.fordonor.Utility.AppData;
import fordonor.com.fordonor.Utility.AsycnParser.IParser;
import fordonor.com.fordonor.Utility.ForDonorEditText;
import fordonor.com.fordonor.home.activity.HomeActivity;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes.getStatusCodeString;

public class SigninActivity extends AppCompatActivity implements
        View.OnClickListener,
        GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private ImageView fb, gplus, link_in, tweet, insta;
    private LinearLayout l_register;
    private CallbackManager callbackManager;
    private String fbUserId, firstName, lastName, eMail;
    private RelativeLayout rbt_signin;
    private AccessTokenTracker accessTokenTracker;
    private AccessToken accessToken;
    private static final int RC_SIGN_IN = 007;
    private ForDonorEditText et_email, et_password;
    //private String url =  "https://api.linkedin.com/v1/people/~:(id,first-name,last-name,email)";
    private String url = "https://api.linkedin.com/v1/people-search:(people:(id,first-name,last-name,headline,picture-url,industry,positions:(id,title,summary,start-date,end-date,is-current,company:(id,name,type,size,industry,ticker)),educations:(id,school-name,field-of-study,start-date,end-date,degree,activities,notes)),num-results)?first-name=parameter&last-name=parameter";

    private static final String TWITTER_KEY = "saWrDpPp7dR7J5CQh9BGJIBLF";
    private static final String TWITTER_SECRET = "9zkgAQ5njIXJNQfYHLgT1cGpL4FWBf4MOv0qf2VCTDfNGtGKVi";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PROFILE_IMAGE_URL = "image_url";
    TwitterAuthClient twitterAuthClient = new TwitterAuthClient();
    private InstagramApp mApp;
    private HashMap<String, String> userInfoHashmap = new HashMap<String, String>();
    private Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == InstagramApp.WHAT_FINALIZE) {
                userInfoHashmap = mApp.getUserInfo();
            } else if (msg.what == InstagramApp.WHAT_FINALIZE) {
                Toast.makeText(SigninActivity.this, "Check your network.",
                        Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        TwitterCore.getInstance();
        fb = (ImageView) findViewById(R.id.fb);
        gplus = (ImageView) findViewById(R.id.gplus);
        link_in = (ImageView) findViewById(R.id.link_in);
        l_register = (LinearLayout) findViewById(R.id.l_register);
        tweet = (ImageView) findViewById(R.id.tweet);
        insta = (ImageView) findViewById(R.id.insta);
        rbt_signin = (RelativeLayout) findViewById(R.id.rbt_signin);
        et_email = (ForDonorEditText) findViewById(R.id.et_email);
        et_password = (ForDonorEditText) findViewById(R.id.et_password);
        ////G+ LOGIN
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
// options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        facebookOncreateCalling();
//        linkedInit();

//        fb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    LoginManager.getInstance().logOut();
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                LoginManager.getInstance().logInWithReadPermissions(SigninActivity.this, Arrays.asList("public_profile", "user_friends", "email" /*, "user_mobile_phone", "email", "user_birthday"*/));
//            }
//        });
        fb.setOnClickListener(this);
        gplus.setOnClickListener(this);
        link_in.setOnClickListener(this);
        l_register.setOnClickListener(this);
        tweet.setOnClickListener(this);
        insta.setOnClickListener(this);


        rbt_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(et_email.getText().toString())){

                }else if(TextUtils.isEmpty(et_password.getText().toString())){

                }else if(!isEmailValid(et_email.getText().toString())){

                }else {
                    new SigninParser(SigninActivity.this, et_email.getText().toString(), et_password.getText().toString(), new IParser() {
                        @Override
                        public void onPreExecute() {

                        }

                        @Override
                        public void onPostExecute(ArrayList<?> resultArrayList) {

                        }

                        @Override
                        public void onPostExecute(String s) {
                            if(s.equals("200")){
                                startActivity(new Intent(SigninActivity.this, HomeActivity.class));
                            }
                            else{
                                startActivity(new Intent(SigninActivity.this, HomeActivity.class));

                            }
                        }

                        @Override
                        public void onPostFailure() {

                        }
                    }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
            }


            // }
        });

        mApp = new InstagramApp(this, AppData.CLIENT_ID,
                AppData.CLIENT_SECRET, AppData.CALLBACK_URL);
        mApp.setListener(new InstagramApp.OAuthAuthenticationListener() {

            @Override
            public void onSuccess() {
                mApp.fetchUserName(handler);
                Intent signup=new Intent(SigninActivity.this, SignupActivity.class);
                String access_insta_token=mApp.getTOken();
                String access_insta_username=mApp.getUserName();
                String access_insta_id=mApp.getId();
                String access_insta_userinfo=mApp.getUserInfo().toString();
                String access_insta_name=mApp.getName();

                System.out.println("ushasi_insta_check"+" "+access_insta_id+" "+access_insta_username+" "+access_insta_name+" "+access_insta_token+" "+access_insta_userinfo);
// Toast.makeText(SigninActivity.this,access_insta_id+" "+access_insta_username+" "+access_insta_name+" "+access_insta_token+" "+access_insta_userinfo,Toast.LENGTH_SHORT).show();

                signup.putExtra("insta_id",access_insta_id);
                signup.putExtra("userfname",access_insta_name);
                signup.putExtra("userlname","");
                signup.putExtra("email","");
                startActivity(signup);
            }

            @Override
            public void onFail(String error) {
                Toast.makeText(SigninActivity.this, error, Toast.LENGTH_SHORT)
                        .show();
            }
        });

        if (mApp.hasAccessToken()) {
            //.setText("Disconnect");
            mApp.fetchUserName(handler);

        }


    }// End of onCreate()


    private void facebookOncreateCalling() {

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getApplicationContext().getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());

                System.out.println("Dev_D " + "KeyHash:-> " + Base64.encodeToString(md.digest(), Base64.DEFAULT));
                Log.d("Facebook KeyHash:", "KeyHash:-> " + Base64.encodeToString(md.digest(), Base64.DEFAULT));

                /* client:-   cbQb+Bb38ygmqEMqiJfjyCGdzg0= */
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FacebookSdk.sdkInitialize(getApplicationContext());
        //AppEventsLogger.activateApp(this);

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        //AppApplication.getLogObject(LoginActivity.this).showToastLong("Fb Login Success");
                        // AppApplication.getLogObject().setLogDBlue("Fb Cancel success: " + loginResult.getAccessToken());
                        callGraphApi(loginResult);
                        //loginButton.setText("LogOut");
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        // AppApplication.getLogObject().showToastLong("Fb Cancel");
                        Log.e("====Login Activity===", "Cancel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        // AppApplication.getLogObject().showToastLong("Fb Login Failure");
                        Log.e("====Login Activity===", "Error" + exception);
                    }
                });

        //======================================================
        //USING ACCESS TOKEN for the case INSTEAD OF LoginManager
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // Set the access token using
                // currentAccessToken when it's loaded or set.
                accessToken = currentAccessToken;
                //AppApplication.getLogObject().setLogDBlue("accessToken_Inside: " + accessToken);
            }
        };
        // If the access token is available already assign it.
        accessToken = AccessToken.getCurrentAccessToken();
        //AppApplication.getLogObject().setLogDBlue("accessToken_onCreate: " + accessToken);
    }

    /*////////////////check/////////////////////*/

    /**
     * Calling graph api to get user details if not using accesstoken for this.
     */
    private void callGraphApi(LoginResult loginResult) {
        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {

                            fbUserId = object.optString("id");
                            firstName = object.optString("first_name");
                            lastName = object.optString("last_name");
                            eMail = object.optString("email");

                            System.out.println("Dev_D fb details: " + fbUserId + " " + firstName + " " + lastName + " " + eMail);

                            Intent signup = new Intent(SigninActivity.this, SignupActivity.class);
                            signup.putExtra("fbUserId", fbUserId);
                            signup.putExtra("userfname", firstName);
                            signup.putExtra("userlname", lastName);
                            signup.putExtra("email", eMail);
                            startActivity(signup);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,first_name,last_name,link,birthday,gender,email");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

        LISessionManager.getInstance(getApplicationContext()).onActivityResult(this, requestCode, resultCode, data);
        twitterAuthClient.onActivityResult(requestCode, resultCode, data);


    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("Devd", "handleSignInResult:" + result.isSuccess());
        int statusCode = result.getStatus().getStatusCode();
        System.out.println("DevD_status_code " + statusCode);
        System.out.println("DevD_status_string " + getStatusCodeString(statusCode));
        if (result.isSuccess()) {

            Toast.makeText(this, "Google+ Login Success", Toast.LENGTH_SHORT).show();
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e("Devd", "display name: " + acct.getDisplayName());

            String personName = acct.getDisplayName();
            //String personPhotoUrl = acct.getPhotoUrl().toString();
            String email = acct.getEmail();
            String g_id = acct.getId();
            acct.getServerAuthCode();

            Log.e("Devd", "Name: " + personName + ", email: " + email
                    + ", Image: " + g_id);
            Intent signup=new Intent(SigninActivity.this, SignupActivity.class);
            signup.putExtra("g_id",g_id);
            signup.putExtra("userfname",personName);
            signup.putExtra("userlname","");
            signup.putExtra("email",email);
            startActivity(signup);


        } else {

            Toast.makeText(SigninActivity.this, "Google+ Login Unsuccessfull", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.gplus:
                signIn();
                break;
            case R.id.fb:
                try {
                    LoginManager.getInstance().logOut();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                LoginManager.getInstance().logInWithReadPermissions(SigninActivity.this, Arrays.asList("public_profile", "user_friends", "email" /*, "user_mobile_phone", "email", "user_birthday"*/));
                break;
            case R.id.link_in:
                loginLinkedin();
                break;

            case R.id.l_register:
                startActivity(new Intent(SigninActivity.this, SignupActivity.class));
                finish();
                break;

            case R.id.tweet:


                twitterAuthClient.authorize(SigninActivity.this, new Callback<TwitterSession>() {
                    @Override
                    public void success(Result<TwitterSession> result) {
                        // Do something with result, which provides a TwitterSession for making API calls
                        final TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                        TwitterAuthToken authToken = session.getAuthToken();
                        String token = authToken.token;
                        String secret = authToken.secret;

                        Log.e("ApiResponse", String.valueOf(session.getUserId() + " " + session.getUserName() + " " + session.getId()));

                        twitterAuthClient.requestEmail(session, new Callback<String>() {
                            @Override
                            public void success(Result<String> result) {
                                // Do something with the result, which provides the email address
                                Log.e("ApiResponse", String.valueOf(result.data));
                                Intent signup = new Intent(SigninActivity.this, SignupActivity.class);
                                signup.putExtra("twitter_id", session.getUserId());
                                signup.putExtra("userfname", session.getUserName());
                                signup.putExtra("userlname", "");
                                signup.putExtra("email", result.data);
                                startActivity(signup);

                            }

                            @Override
                            public void failure(TwitterException exception) {
                                // Do something on failure
                            }
                        });

                    }

                    @Override
                    public void failure(TwitterException exception) {
                        // Do something on failure
                    }
                });
                break;

            case R.id.insta:
                //signInWithInstagram();
                connectOrDisconnectUser();
                break;


        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("g+ error ", String.valueOf(connectionResult));
    }

    private void connectOrDisconnectUser() {
        if (mApp.hasAccessToken()) {
            String access_insta_token = mApp.getTOken();
            String access_insta_username = mApp.getUserName();
            String access_insta_id = mApp.getId();
            String access_insta_userinfo = mApp.getUserInfo().toString();
            String access_insta_name = mApp.getName();

            System.out.println("ushasi_insta_check" + " " + access_insta_id + access_insta_name + " " + access_insta_token + " " + access_insta_userinfo + " " + access_insta_username);

            final AlertDialog.Builder builder = new AlertDialog.Builder(
                    SigninActivity.this);
            builder.setMessage("Disconnect from Instagram?")
                    .setCancelable(false)
                    .setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    mApp.resetAccessToken();
                                    Toast.makeText(SigninActivity.this, "Connnect", Toast.LENGTH_SHORT).show();
                                    // tvSummary.setText("Not connected");
                                }
                            })
                    .setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.cancel();
                                }
                            });
            final AlertDialog alert = builder.create();
            alert.show();
        } else {
            mApp.authorize();
        }
    }

    private boolean isEmailValid(String email) {
        String regExpn = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches())
            return true;
        else
            return false;
    }

    private static final String topCardUrl = "https://" + "api.linkedin.com" + "/v1/people/~:(id,first-name,last-name,email-address,formatted-name,phone-numbers,public-profile-url,picture-url,picture-urls::(original))";

    public void loginLinkedin() {
        LISessionManager.getInstance(getApplicationContext()).init(this,
                buildScope(), new AuthListener() {
                    @Override
                    public void onAuthSuccess() {
                        APIHelper apiHelper = APIHelper.getInstance(getApplicationContext());
                        apiHelper.getRequest(SigninActivity.this, topCardUrl, new ApiListener() {
                            @Override
                            public void onApiSuccess(ApiResponse s) {
                                // Toast.makeText(SigninActivity.this,"su",Toast.LENGTH_SHORT).show();
                                Log.e("ApiResponse", String.valueOf(s.getResponseDataAsJson()));
                                String s_link = s.getResponseDataAsJson().toString();
                                try {
                                    JSONObject obj = new JSONObject(s_link);
                                    String email = obj.optString("emailAddress");
                                    String firstName = obj.optString("firstName");
                                    String lastName = obj.optString("lastName");
                                    String id = obj.optString("id");

                                    Intent signup = new Intent(SigninActivity.this, SignupActivity.class);
                                    signup.putExtra("link_id", id);
                                    signup.putExtra("userfname", firstName);
                                    signup.putExtra("userlname", lastName);
                                    signup.putExtra("email", email);
                                    startActivity(signup);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            @Override
                            public void onApiError(LIApiError error) {
                                Toast.makeText(SigninActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onAuthError(LIAuthError error) {
                    }
                }, true);
    }


    // Build the list of member permissions our LinkedIn session requires
    private static Scope buildScope() {
        return Scope.build(Scope.R_BASICPROFILE, Scope.R_EMAILADDRESS, Scope.W_SHARE);
    }


}