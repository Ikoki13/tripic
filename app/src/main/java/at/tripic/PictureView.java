package at.tripic;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import java.util.List;

import at.tripic.adapters.PictureAdapter;
import at.tripic.interfaces.PictureHandler;
import at.tripic.interfaces.PictureService;
import at.tripic.model.PictureData;
import at.tripic.services.FlickrPictureService;

public class PictureView extends AppCompatActivity implements PictureHandler {
    private RecyclerView recyclerView;
    private List<String> photoIdList;
    private ProgressDialog progressDialog;
    private PictureService pictureService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_view);

        pictureService = new FlickrPictureService(this, this);
        recyclerView = (RecyclerView) findViewById(R.id.list_pictures);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //photoIdList = pictureService.requestFlickrData();
        pictureService.requestFlickrData();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.loading_flickr));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void HandleResult(List<PictureData> data) {
        progressDialog.dismiss();
        //dbRepository.InsertWeatherData(locationName, data);
        PictureAdapter adapter = new PictureAdapter(getApplicationContext(), data);
        //recyclerView.swapAdapter(new PictureAdapter(getApplicationContext(), data), false);
        recyclerView.swapAdapter(adapter, true);
    }

    @Override
    public void HandleError(int errorCode) {
        System.out.println("");

    }

    public void navigateBack(View view) {
        Intent intent = new Intent(this, MapViewOverview.class);
        startActivity(intent);
    }

    public void openPopupView(View view) {
        //unused at the moment
        LayoutInflater inflater = (LayoutInflater)
                this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        PopupWindow pw = new PopupWindow(
                inflater.inflate(R.layout.activity_popup_view, null, false),
                500,
                500,
                true);
        // The code below assumes that the root container has an id called 'main'
        pw.showAtLocation(this.findViewById(R.id.spot_1), Gravity.CENTER, 0, 0);

        //Todo: this is just a quickfix in order to prevent the app from crashing when navigating to a view via a popup button
        Intent intent = new Intent(this, PopupView.class);
        startActivity(intent);
    }
}
