package fordonor.com.fordonor.campaign.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import fordonor.com.fordonor.R;
import fordonor.com.fordonor.campaign.model.CampaignModel;

import java.util.List;

/**
 * Created by and-36 on 12/3/18.
 */

public class Campaignadapter  extends RecyclerView.Adapter<Campaignadapter.GroceryViewHolder>{
        private List<CampaignModel> horizontalGrocderyList;
        Context context;

        public Campaignadapter(List<CampaignModel> horizontalGrocderyList, Context context){
            this.horizontalGrocderyList= horizontalGrocderyList;
            this.context = context;
        }

        @Override
        public GroceryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //inflate the layout file
            View groceryProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_search_campaign, parent, false);
            GroceryViewHolder gvh = new GroceryViewHolder(groceryProductView);
            return gvh;
        }

        @Override
        public void onBindViewHolder(GroceryViewHolder holder, final int position) {
            holder.imageView.setImageResource(horizontalGrocderyList.get(position).getProductImage());
            holder.txtview.setText(horizontalGrocderyList.get(position).getProductName());
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String productName = horizontalGrocderyList.get(position).getProductName().toString();
                    Toast.makeText(context, productName + " is selected", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return horizontalGrocderyList.size();
        }

        public class GroceryViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView txtview;
            public GroceryViewHolder(View view) {
                super(view);
                imageView=view.findViewById(R.id.idProductImage);
                txtview=view.findViewById(R.id.idProductName);
            }
        }

}
