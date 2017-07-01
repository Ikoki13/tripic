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

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        photographerList = new ArrayList<Photographer>();
        //Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.profile_circle);

        Bitmap imageRanz = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ranz_patrick);
        Bitmap imageMosgoeller = BitmapFactory.decodeResource(context.getResources(), R.mipmap.mosgoeller_michael);
        photographerList.add(new Photographer("Patrick", "Ranz", "He is one of the shining stars in the local photography scene being admired even by the greatest and most famous photographers.", imageRanz));
        photographerList.add(new Photographer("Michael", "Mosgöller", "Specialist for landscapes, streetart and portraits as well as promoting local businesses.", imageMosgoeller));
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        SQLiteDatabase localDB = getWritableDatabase();
        db.execSQL(CREATE_PHOTOGRAPHER);

        ContentValues contentValues = new ContentValues();
        for(Photographer p : photographerList) {
            contentValues.clear();
            contentValues.put(PhotographerEntry.COLUMN_FIRSTNAME, p.getFirstname());
            contentValues.put(PhotographerEntry.COLUMN_LASTNAME, p.getLastname());
            contentValues.put(PhotographerEntry.COLUMN_DESC, p.getDescription());
            contentValues.put(PhotographerEntry.COLUMN_IMAGE, BitmapHelper.ConvertBitmapToString(p.getImage()));
            localDB.insert(PhotographerEntry.TABLE_NAME, null, contentValues);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


}
