package at.tripic.datalayer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import at.tripic.datalayer.PhotographerContract.PhotographerEntry;

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

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PHOTOGRAPHER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
