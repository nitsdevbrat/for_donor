package fordonor.com.fordonor.campaign.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import fordonor.com.fordonor.R;
import fordonor.com.fordonor.Utility.BaseActivity;
import fordonor.com.fordonor.campaign.adapter.Campaignadapter;
import fordonor.com.fordonor.campaign.model.CampaignModel;

import java.util.ArrayList;
import java.util.List;

public class AddCampaignActivity extends BaseActivity {
    private List<CampaignModel> groceryList = new ArrayList<>();
    private RecyclerView groceryRecyclerView;
    private Campaignadapter groceryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.add_campaign);
        enableDrawer(true);
        enablesearch(true);
        groceryRecyclerView = findViewById(R.id.rv);
        groceryAdapter = new Campaignadapter(groceryList, getApplicationContext());
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(AddCampaignActivity.this, LinearLayoutManager.HORIZONTAL, false);
        groceryRecyclerView.setLayoutManager(horizontalLayoutManager);
        groceryRecyclerView.setAdapter(groceryAdapter);
        populategroceryList();
    }

    private void populategroceryList(){
        CampaignModel potato = new CampaignModel("Ushasi \n Guha", R.drawable.user);
        CampaignModel onion = new CampaignModel("Devrat \n Dutta", R.drawable.user);
        CampaignModel cabbage = new CampaignModel("Jayjit \n Dutta", R.drawable.user);
        CampaignModel cauliflower = new CampaignModel("Sohini \n Basak", R.drawable.user);
        groceryList.add(potato);
        groceryList.add(onion);
        groceryList.add(cabbage);
        groceryList.add(cauliflower);
        groceryAdapter.notifyDataSetChanged();
    }


    @Override
    public String toolBarTitle() {
        return "Create Campaign";
    }
}
