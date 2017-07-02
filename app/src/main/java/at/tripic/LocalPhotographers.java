package at.tripic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.util.ArrayList;

import at.tripic.adapters.PhotographerAdapter;
import at.tripic.adapters.PictureAdapter;
import at.tripic.datalayer.DatabaseHelper;
import at.tripic.datalayer.DbRepo;
import at.tripic.datalayer.PhotographerContract;
import at.tripic.datalayer.tablerepresentations.Photographer;
import at.tripic.helpers.BitmapHelper;
import at.tripic.services.FlickrPictureService;

public class LocalPhotographers extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PhotographerAdapter photographerAdapter;
    private ArrayList<Photographer> photographerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_photographers);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        String selectQuery = "SELECT  * FROM " + PhotographerContract.PhotographerEntry.TABLE_NAME;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] queryColumns = {
                PhotographerContract.PhotographerEntry.COLUMN_FIRSTNAME,
                PhotographerContract.PhotographerEntry.COLUMN_LASTNAME,
                PhotographerContract.PhotographerEntry.COLUMN_DESC,
                PhotographerContract.PhotographerEntry.COLUMN_IMAGE
        };
        Cursor cursor = db.query(true, PhotographerContract.PhotographerEntry.TABLE_NAME, queryColumns, null, null, null, null, null, null);
        photographerList = new ArrayList<Photographer>();

        while(cursor.moveToNext()) {
            photographerList.add(new Photographer(
                    cursor.getString(cursor.getColumnIndex(PhotographerContract.PhotographerEntry.COLUMN_FIRSTNAME)),
                    cursor.getString(cursor.getColumnIndex(PhotographerContract.PhotographerEntry.COLUMN_LASTNAME)),
                    cursor.getString(cursor.getColumnIndex(PhotographerContract.PhotographerEntry.COLUMN_DESC)),
                    BitmapHelper.ConvertStringToBitmap(cursor.getString(cursor.getColumnIndex(PhotographerContract.PhotographerEntry.COLUMN_IMAGE)))
            ));
        }
        cursor.close();

        recyclerView = (RecyclerView) findViewById(R.id.list_photographers);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        photographerAdapter = new PhotographerAdapter(this, photographerList);
        recyclerView.setAdapter(photographerAdapter);
    }

    public void navigateBack(View view) {
        Intent intent = new Intent(this, PictureView.class);
        startActivity(intent);
    }
}
