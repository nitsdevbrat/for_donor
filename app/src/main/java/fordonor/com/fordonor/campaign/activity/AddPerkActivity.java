package fordonor.com.fordonor.campaign.activity;

import android.os.Bundle;

import fordonor.com.fordonor.R;
import fordonor.com.fordonor.Utility.BaseActivity;

public class AddPerkActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_add_perk);
        enableDrawer(false);
        enablesearch(false);
    }

    @Override
    public String toolBarTitle() {
        return "Add Perk";
    }
}
