package at.tripic;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class DetailView extends AppCompatActivity {

    EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        et1 = (EditText) findViewById(R.id.spot_1_desc);
        et1.setKeyListener(null);
    }
    public void navigateBack(View view) {
        Intent intent = new Intent(this, MapViewOverview.class);
        startActivity(intent);
    }

}
