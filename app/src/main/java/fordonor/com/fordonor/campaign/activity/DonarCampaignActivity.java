package fordonor.com.fordonor.campaign.activity;

import android.os.Bundle;

import fordonor.com.fordonor.R;
import fordonor.com.fordonor.Utility.BaseActivity;

public class DonarCampaignActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_add_campaign_donar);
        enableDrawer(false);
        enablesearch(false);
    }

    @Override
    public String toolBarTitle() {
        return "Create Campaign";
    }
}
