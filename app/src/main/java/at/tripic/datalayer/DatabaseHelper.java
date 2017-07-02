package at.tripic.datalayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

import at.tripic.R;
import at.tripic.datalayer.PhotographerContract.PhotographerEntry;
import at.tripic.datalayer.tablerepresentations.Photographer;
import at.tripic.helpers.BitmapHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "tripic";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_PHOTOGRAPHER =
            "CREATE TABLE " + PhotographerEntry.TABLE_NAME
            + " ("
                    + PhotographerEntry._ID + " INTEGER PRIMARY KEY,"
                    + PhotographerEntry.COLUMN_FIRSTNAME + " TEXT,"
                    + PhotographerEntry.COLUMN_LASTNAME + " TEXT,"
                    + PhotographerEntry.COLUMN_DESC + " TEXT,"
                    + PhotographerEntry.COLUMN_IMAGE + " BLOB)";

    private List<Photographer> photographerList;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase localDB = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PHOTOGRAPHER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
