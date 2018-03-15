package fordonor.com.fordonor.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import fordonor.com.fordonor.R;
import fordonor.com.fordonor.home.model.SubcategoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by and-36 on 14/3/18.
 */

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.CategoryViewHolder>{
    private List<SubcategoryModel> subcategoryModels = new ArrayList<>();
    Context context;
    private int selectedpos = -1;

    public SubCategoryAdapter(List<SubcategoryModel> subcategoryModels, Context context){
        this.subcategoryModels= subcategoryModels;
        this.context = context;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the layout file
        View catview = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_subcategory, parent, false);
       CategoryViewHolder gvh = new CategoryViewHolder(catview);
        return gvh;
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, final int position) {
        holder.btn_sub_cat.setText(subcategoryModels.get(position).getSubcategoryname());
        holder.btn_sub_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedpos = position;
                notifyDataSetChanged();

                holder.btn_sub_cat.setBackgroundResource(R.drawable.round_dark_grey);



            }
        });
        if (selectedpos == position) {
            holder.btn_sub_cat.setBackgroundResource(R.drawable.round_dark_grey);


        } else {
            holder.btn_sub_cat.setBackgroundResource(R.drawable.round_ash);


        }

    }

    @Override
    public int getItemCount() {
        return subcategoryModels.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        Button btn_sub_cat;


        public CategoryViewHolder(View view) {
            super(view);
            btn_sub_cat = view.findViewById(R.id.btn_sub_cat);

        }
    }
    }