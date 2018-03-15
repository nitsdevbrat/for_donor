package fordonor.com.fordonor.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fordonor.com.fordonor.R;

/**
 * Created by and-36 on 14/3/18.
 */


public class ListCategoryAdapter extends RecyclerView.Adapter<ListCategoryAdapter.Categoryviewholder> {


    @Override
    public Categoryviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the layout file
        View catview = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_listcategory, parent, false);
        Categoryviewholder gvh = new Categoryviewholder(catview);
        return gvh;
    }

    @Override
    public void onBindViewHolder(ListCategoryAdapter.Categoryviewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class Categoryviewholder extends RecyclerView.ViewHolder {
        public Categoryviewholder(View itemView) {
            super(itemView);
        }
    }
}