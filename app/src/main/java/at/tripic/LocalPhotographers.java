package at.tripic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import at.tripic.services.FlickrPictureService;

public class LocalPhotographers extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_photographers);

        setContentView(R.layout.activity_picture_view);

        recyclerView = (RecyclerView) findViewById(R.id.list_photographers);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    public void navigateBack(View view) {
        Intent intent = new Intent(this, PictureView.class);
        startActivity(intent);
    }

}
