package fordonor.com.fordonor.Utility;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

/**
 * Created by and-36 on 24/4/17.
 */

public class FontCache {
    private static Hashtable<String, Typeface> fontCache = new Hashtable<>();

    public static Typeface getTypeface(String name, Context context) {
        Typeface tf = fontCache.get(name);
        if(tf == null) {
            try {
                tf = Typeface.createFromAsset(context.getAssets(), name);
            }
            catch (Exception e) {
                return null;
            }
            fontCache.put(name, tf);
        }
        return tf;
    }
}
