package fordonor.com.fordonor.Utility;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by nits-33 on 2/6/17.
 */

public class ForDonorTextView extends TextView {


    public ForDonorTextView(Context context) {
        super(context);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "Lato-Regular.ttf");
        this.setTypeface(face);
    }

    public ForDonorTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "Lato-Regular.ttf");
        this.setTypeface(face);
    }

    public ForDonorTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface face = Typeface.createFromAsset(context.getAssets(),"Lato-Regular.ttf");
        this.setTypeface(face);
    }





}