package fordonor.com.fordonor.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fordonor.com.fordonor.R;
import fordonor.com.fordonor.home.model.CategoyModel;

import java.util.List;

/**
 * Created by and-36 on 14/3/18.
 */
public class CategoryAdapter  extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{
    private List<CategoyModel> categoyModelList;
    Context context;
    private int selectedpos = -1;

    public CategoryAdapter(List<CategoyModel> categoyModelList, Context context){
        this.categoyModelList= categoyModelList;
        this.context = context;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the layout file
        View catview = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category, parent, false);
        CategoryViewHolder gvh = new CategoryViewHolder(catview);
        return gvh;
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, final int position) {
        holder.tv_cat.setText(categoyModelList.get(position).getCatname());
        holder.tv_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedpos = position;
                notifyDataSetChanged();

                holder.tv_cat.setTextColor(Color.parseColor("#FFFFFF"));
                holder.view_new.setVisibility(View.VISIBLE);


            }
        });
        if (selectedpos == position) {
            holder.tv_cat.setTextColor(Color.parseColor("#FFFFFF"));
            holder.view_new.setVisibility(View.VISIBLE);
        } else {
            holder.tv_cat.setTextColor(Color.parseColor("#FFFFFF"));
            holder.view_new.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return categoyModelList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tv_cat;
        private View view_new;
        public CategoryViewHolder(View view) {
            super(view);
            tv_cat=view.findViewById(R.id.tv_cat);
            view_new=view.findViewById(R.id.view);
        }

}
}