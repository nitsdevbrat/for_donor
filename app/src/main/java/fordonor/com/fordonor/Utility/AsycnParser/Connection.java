package fordonor.com.fordonor.Utility.AsycnParser;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class Connection {

    public static String RESPONSE_CODE_SUCCESS = "200";

    public static boolean checkConnection(Context ctx) {
        ConnectivityManager conMgr = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo i = conMgr.getActiveNetworkInfo();
        if (i == null)
            return false;
        if (!i.isConnected())
            return false;
        if (!i.isAvailable())
            return false;

        return true;
    }


    /*////////Get///////////*/
    public static String executeGet(Context context,String urlString) {
        urlString = urlString.replaceAll(" ", "%20");
        Log.d("url---", urlString + ">>");
        InputStream in = null;
        int rescode = -1;
        HttpURLConnection httpConn = null;
        try {

            if(checkConnection(context)) {

                URL url = new URL(urlString);
                URLConnection conn = url.openConnection();

                httpConn = (HttpURLConnection) conn;
                httpConn.setAllowUserInteraction(false);
                httpConn.setInstanceFollowRedirects(true);
                httpConn.setRequestMethod("GET");
                httpConn.connect();
                JSONObject jsonObj = new JSONObject();
                rescode = httpConn.getResponseCode();
                if (rescode == HttpURLConnection.HTTP_OK) {
                    in = httpConn.getInputStream();
                }

                BufferedReader rd = new BufferedReader(new InputStreamReader(in));
                String line;
                StringBuffer response = new StringBuffer();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
                rd.close();
                jsonObj.put("statuscode",rescode);
                jsonObj.put("value",new JSONObject(response.toString()));

                return jsonObj.toString();

            }else{
                    Toast.makeText(context, "No Internet Available!", Toast.LENGTH_SHORT).show();
                    return "";
                }

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        } finally {

            if (httpConn != null) {
                httpConn.disconnect();
            }
        }
    }
//*////////Post///////*/
    public static String excutePost(Context context, String targetURL, String urlParameters) {
        Log.d("url---", targetURL + ">>");
        Log.d("parameter---", urlParameters + ">>");
        URL url;
        HttpURLConnection connection = null;
        int rescode = -1;
        try {

            if(checkConnection(context)){
                //Create connection
                url = new URL(targetURL);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setConnectTimeout(10000);
                connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");

                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);

                connection.setFixedLengthStreamingMode(urlParameters.getBytes().length);
                PrintWriter out = new PrintWriter(connection.getOutputStream());
                out.print(urlParameters);
                out.close();

                JSONObject jsonObj = new JSONObject();
                rescode=connection.getResponseCode();


                Log.e("rescode"," "+rescode);



                //Get Response
                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }


                rd.close();
                jsonObj.put("statuscode",rescode);
                jsonObj.put("value",new JSONObject(response.toString()));

                return jsonObj.toString();

            }else{
                Toast.makeText(context, "No Internet Available!", Toast.LENGTH_SHORT).show();
                return "";
            }


        } catch (Exception e) {

            e.printStackTrace();
            return "";

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public static String excutPost(Context context, String targetURL, JSONObject params) {



        Log.d("url---", targetURL + ">>");
        Log.d("parameter---", params + ">>");
        URL url;
        HttpURLConnection connection = null;
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPostRequest = new HttpPost(targetURL);

            StringEntity se;
            se = new StringEntity(params.toString());

            httpPostRequest.setEntity(se);

//            httpPostRequest.setHeader("Authorization", "Bearer "+accessToken);
//            Log.e("accessToken ",accessToken);

            httpPostRequest.setHeader("Content-type", "application/json");


            long t = System.currentTimeMillis();
            HttpResponse response = (HttpResponse) httpclient.execute(httpPostRequest);
            Log.i("DEV_D_HTTPRESPONS_POST", "HTTPResponse received in [" + (System.currentTimeMillis() - t) + "ms]");

            HttpEntity entity = response.getEntity();
            StatusLine statusLine = response.getStatusLine();
            int statuscode = statusLine.getStatusCode();


            JSONObject jsonObj = new JSONObject();


            System.out.println("code "+statuscode);

            InputStream is = entity.getContent();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;

            StringBuffer resultString = new StringBuffer();

            while ((line = rd.readLine()) != null) {
                resultString.append(line);
                resultString.append('\r');

                //obj.put("dir",jsonObj);
                //resultString.append(obj);
            }


            rd.close();
            jsonObj.put("statuscode",statuscode);
            jsonObj.put("value",new JSONObject(resultString.toString()));

            Log.e("resultString",resultString.toString()+"");
            return jsonObj.toString();


        } catch (Exception e) {

            e.printStackTrace();
            return "";

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }

    }


    /*////////Put////////*/
    public static String excutePut(Context context, String targetURL, String urlParameters) {
        Log.d("url---", targetURL + ">>");
        Log.d("parameter---", urlParameters + ">>");
        URL url;
        HttpURLConnection connection = null;
        try {
            //Create connection
            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setConnectTimeout(10000);
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            connection.setFixedLengthStreamingMode(urlParameters.getBytes().length);
            PrintWriter out = new PrintWriter(connection.getOutputStream());
            out.print(urlParameters);
            out.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();

        } catch (Exception e) {

            e.printStackTrace();
            return "";

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
    }
/*//////////Delete/////////*/
    public static String excuteDelete(Context context, String targetURL, String urlParameters) {
        Log.d("url---", targetURL + ">>");
        Log.d("parameter---", urlParameters + ">>");
        URL url;
        HttpURLConnection connection = null;
        try {
            //Create connection
            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setConnectTimeout(10000);
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            connection.setFixedLengthStreamingMode(urlParameters.getBytes().length);
            PrintWriter out = new PrintWriter(connection.getOutputStream());
            out.print(urlParameters);
            out.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();

        } catch (Exception e) {

            e.printStackTrace();
            return "";

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
    }

}
