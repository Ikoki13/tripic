package at.tripic.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import at.tripic.R;
import at.tripic.datalayer.DatabaseHelper;
import at.tripic.datalayer.PhotographerContract;
import at.tripic.datalayer.tablerepresentations.Photographer;

/**
 * Created by Michael on 01.07.2017.
 */

public class PhotographerAdapter extends RecyclerView.Adapter<PhotographerAdapter.PhotographerViewHolder> {

    private List<Photographer> photographerList;
    private Context context;

    public PhotographerAdapter(Context context, List<Photographer> newList) {
        this.context = context;
        photographerList = newList;
    }

    public List<Photographer> getPhotographerList() {
        return photographerList;
    }

    public void setPhotographerList(List<Photographer> photographerList) {
        this.photographerList = photographerList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public PhotographerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_photographer, parent, false);
        return new PhotographerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PhotographerViewHolder holder, int position) {
        Photographer p = getPhotographerList().get(position);
        holder.bind(p);
    }

    @Override
    public int getItemCount() {
        return photographerList.size();
    }

    public class PhotographerViewHolder extends RecyclerView.ViewHolder {
        private ImageView photographerListItem_Image;
        private TextView photographerListItem_Name;
        private TextView photographerListItem_Description;

        public PhotographerViewHolder(View itemView) {
            super(itemView);

            photographerListItem_Image = (ImageView) itemView.findViewById(R.id.photographerListItem_Image);
            photographerListItem_Name = (TextView) itemView.findViewById(R.id.photographerListItem_Name);
            photographerListItem_Description = (TextView) itemView.findViewById(R.id.photographerListItem_Description);
        }

        public void bind(Photographer p) {
            photographerListItem_Name.setText(p.getFirstname() + " " + p.getLastname());
            photographerListItem_Description.setText(p.getDescription());
            photographerListItem_Image.setImageBitmap(p.getImage());
        }
    }
}