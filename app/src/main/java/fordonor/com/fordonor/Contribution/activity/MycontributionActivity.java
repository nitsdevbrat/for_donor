package fordonor.com.fordonor.Contribution.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import fordonor.com.fordonor.Contribution.adapter.MycontributionAdapter;
import fordonor.com.fordonor.R;
import fordonor.com.fordonor.Utility.BaseActivity;

public class MycontributionActivity extends BaseActivity {

    private RecyclerView rl_my_campaign;
    private MycontributionAdapter Mycampaignadapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_mycontribution);
        enableDrawer(false);
        enablesearch(false);

        initview();


        /*//////////////////////////Adapter////////////////////*/
        Mycampaignadapter = new MycontributionAdapter();
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(MycontributionActivity.this, LinearLayoutManager.VERTICAL, false);
        rl_my_campaign.setLayoutManager(horizontalLayoutManager);
        rl_my_campaign.setAdapter(Mycampaignadapter);




    }





    private void initview() {
        rl_my_campaign = (RecyclerView) findViewById(R.id.rl_my_campaign);


    }

    @Override
    public String toolBarTitle() {
        return "My Contribution";
    }
}
