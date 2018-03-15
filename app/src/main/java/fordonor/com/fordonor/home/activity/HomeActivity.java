package fordonor.com.fordonor.home.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import fordonor.com.fordonor.R;
import fordonor.com.fordonor.Utility.BaseActivity;
import fordonor.com.fordonor.home.adapter.CategoryAdapter;
import fordonor.com.fordonor.home.adapter.ListCategoryAdapter;
import fordonor.com.fordonor.home.adapter.SubCategoryAdapter;
import fordonor.com.fordonor.home.model.CategoyModel;
import fordonor.com.fordonor.home.model.ListcategoryModel;
import fordonor.com.fordonor.home.model.SubcategoryModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {

    private RecyclerView rv_category, rv_sub_category,lv_list_cat;
    private CategoryAdapter categoryAdapter;
    private SubCategoryAdapter subCategoryAdapter;
    private ListCategoryAdapter listCategoryAdapter;
    private List<CategoyModel> categoyModelList = new ArrayList<>();
    private List<SubcategoryModel> subcategoryModels = new ArrayList<>();
    private List<ListcategoryModel> listcategoryModels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_home);
        enableDrawer(true);
        enablesearch(true);

        initview();

        /*//////////////////////////Category////////////////////*/
        categoryAdapter = new CategoryAdapter(categoyModelList, getApplicationContext());
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rv_category.setLayoutManager(horizontalLayoutManager);
        rv_category.setAdapter(categoryAdapter);
        populatecategorylist();


        /*////////////////////////Sub Category/////////////////////*/
        subCategoryAdapter = new SubCategoryAdapter(subcategoryModels, getApplicationContext());
        LinearLayoutManager horizontalLayoutManager_new = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rv_sub_category.setLayoutManager(horizontalLayoutManager_new);
        rv_sub_category.setAdapter(subCategoryAdapter);
        populatesubcategorylist();

        /*///////////////////List Category////////////////////////*/
        listCategoryAdapter = new ListCategoryAdapter();
        LinearLayoutManager horizontalLayoutManage_new = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.VERTICAL, false);
        lv_list_cat.setLayoutManager(horizontalLayoutManage_new);
        lv_list_cat.setAdapter(listCategoryAdapter);

    }


    /*/////////////////////////////For Demo Category////////////////////////////*/
    private void populatecategorylist() {
        CategoyModel health = new CategoyModel("HEALTH");
        CategoyModel technology = new CategoyModel("TECHNOLOGY");
        CategoyModel art = new CategoyModel("ART & FILM");
        CategoyModel community = new CategoyModel("COMMUNITY");
        categoyModelList.add(health);
        categoyModelList.add(technology);
        categoyModelList.add(art);
        categoyModelList.add(community);
        categoryAdapter.notifyDataSetChanged();
    }


    /*/////////////////////////////For Demo Sub Category////////////////////////////*/

    private void populatesubcategorylist()
    {
        SubcategoryModel all = new SubcategoryModel("ALL");
        SubcategoryModel pledges = new SubcategoryModel("PLEDGES");
        SubcategoryModel offers = new SubcategoryModel("OFFERS");
        SubcategoryModel populars = new SubcategoryModel("POPULARS");
        SubcategoryModel newA = new SubcategoryModel("NEW");
        SubcategoryModel arrival = new SubcategoryModel("ARRIVAL");

        subcategoryModels.add(all);
        subcategoryModels.add(pledges);
        subcategoryModels.add(offers);
        subcategoryModels.add(populars);
        subcategoryModels.add(newA);
        subcategoryModels.add(arrival);
        subCategoryAdapter.notifyDataSetChanged();
    }

    private void initview() {
        rv_category = (RecyclerView) findViewById(R.id.rv_category);
        rv_sub_category = (RecyclerView) findViewById(R.id.rv_sub_category);
        lv_list_cat=(RecyclerView)findViewById(R.id.lv_list_cat);
    }

    @Override
    public String toolBarTitle() {
        return "Home";
    }
}
