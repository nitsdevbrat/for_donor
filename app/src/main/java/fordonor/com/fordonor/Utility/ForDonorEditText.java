package fordonor.com.fordonor.Utility;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by nits-33 on 2/6/17.
 */

public class ForDonorEditText extends EditText {


    public ForDonorEditText(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public ForDonorEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public ForDonorEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("Lato-Regular.ttf", context);
        setTypeface(customFont);
    }

}