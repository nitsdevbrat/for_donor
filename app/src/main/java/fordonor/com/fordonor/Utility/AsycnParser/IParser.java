package fordonor.com.fordonor.Utility.AsycnParser;

import java.util.ArrayList;

/**
 * Created by and-36 on 24/4/17.
 */

public interface IParser {

    void onPreExecute();

    void onPostExecute(ArrayList<?> resultArrayList);

    void onPostExecute(String s);

    void onPostFailure();


}
