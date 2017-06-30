package at.tripic.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseFile;
import com.parse.ParseObject;
import com.squareup.picasso.Picasso;

import java.util.List;

import at.tripic.R;
import at.tripic.model.PictureData;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.PictureViewHolder> {
    private List<String> data;
    private Context context;

    public PictureAdapter(Context context,List<String> data) {
        this.context = context;
        this.data = data;
    }


    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_item, parent, false);
        return new PictureViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PictureViewHolder holder, int position) {
        //PictureData item = data.get(position);
        //holder.bind(item);
        Picasso.with(context).load(data.get(position)).resize(120, 60).into(holder.flickrImage);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class PictureViewHolder extends RecyclerView.ViewHolder {
        private ImageView flickrImage;
        private TextView flickrDesc;


        public PictureViewHolder(View itemView) {
            super(itemView);

            flickrImage = (ImageView) itemView.findViewById(R.id.flickrImage); //is a url
            flickrDesc = (TextView) itemView.findViewById(R.id.flickrDesc);
        }

        public void bind(PictureData item) {

            flickrDesc.setText(item.getDesc());

        }
    }
}
