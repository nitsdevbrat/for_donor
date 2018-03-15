package fordonor.com.fordonor.campaign.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import fordonor.com.fordonor.R;
import fordonor.com.fordonor.Utility.BaseActivity;
import fordonor.com.fordonor.campaign.adapter.Mycampaignadapter;
import fordonor.com.fordonor.campaign.model.Mycampaignmodel;

import java.util.ArrayList;
import java.util.List;

public class MycampaignActivity extends BaseActivity {

    private RecyclerView rl_my_campaign;
    private Mycampaignadapter Mycampaignadapter;
    private List<Mycampaignmodel> mycampaignmodels = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_mycampaign);
        enableDrawer(false);
        enablesearch(false);

        initview();


        /*//////////////////////////Adapter////////////////////*/
        Mycampaignadapter = new Mycampaignadapter();
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(MycampaignActivity.this, LinearLayoutManager.VERTICAL, false);
        rl_my_campaign.setLayoutManager(horizontalLayoutManager);
        rl_my_campaign.setAdapter(Mycampaignadapter);




    }





    private void initview() {
        rl_my_campaign = (RecyclerView) findViewById(R.id.rl_my_campaign);


    }

    @Override
    public String toolBarTitle() {
        return "My Campaigns";
    }
}


