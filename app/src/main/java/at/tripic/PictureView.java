package at.tripic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RatingBar;

public class PictureView extends AppCompatActivity {

    EditText et1;
    EditText et2;
    EditText et3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_view);

        et1 = (EditText) findViewById(R.id.spot_1_desc);
        et1.setKeyListener(null);
        et2 = (EditText) findViewById(R.id.spot_2_desc);
        et2.setKeyListener(null);
        et3 = (EditText) findViewById(R.id.spot_3_desc);
        et3.setKeyListener(null);


    }

    public void navigateBack(View view) {
        Intent intent = new Intent(this, MapViewOverview.class);
        startActivity(intent);
    }

    public void openPopupView(View view) {
        //Intent intent = new Intent(this, PopupView.class);
        //startActivity(intent);


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
