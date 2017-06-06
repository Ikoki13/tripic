package at.tripic;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
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
        Intent intent = new Intent(this, PopupView.class);
        startActivity(intent);
    }

}
