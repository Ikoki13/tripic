package at.tripic.datalayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.sql.SQLClientInfoException;
import java.util.ArrayList;
import java.util.List;

import at.tripic.R;
import at.tripic.datalayer.tablerepresentations.Photographer;
import at.tripic.helpers.BitmapHelper;

public class DbRepo {
    private static DatabaseHelper dbHelper;

    public DbRepo(Context context) {
        if (dbHelper == null) {
            dbHelper = new DatabaseHelper(context);

            DbRepo dbRepo = new DbRepo(context);
            DatabaseHelper dbHelper = new DatabaseHelper(context);

            List<Photographer> photographerList = new ArrayList<Photographer>();
            Bitmap imageRanz = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ranz_patrick);
            Bitmap imageMosgoeller = BitmapFactory.decodeResource(context.getResources(), R.mipmap.mosgoeller_michael);
            photographerList.add(new Photographer("Patrick", "Ranz", "He is one of the shining stars in the local photography scene being admired even by the greatest and most famous photographers.", imageRanz));
            photographerList.add(new Photographer("Michael", "Mosgöller", "Specialist for landscapes, streetart and portraits as well as promoting local businesses.", imageMosgoeller));
            dbRepo.InsertPhotographers(photographerList);
        }
    }

    public void InsertPhotographers(List<Photographer> photographerList) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        for(Photographer p : photographerList) {
            contentValues.clear();
            contentValues.put(PhotographerContract.PhotographerEntry.COLUMN_FIRSTNAME, p.getFirstname());
            contentValues.put(PhotographerContract.PhotographerEntry.COLUMN_LASTNAME, p.getLastname());
            contentValues.put(PhotographerContract.PhotographerEntry.COLUMN_DESC, p.getDescription());
            contentValues.put(PhotographerContract.PhotographerEntry.COLUMN_IMAGE, BitmapHelper.ConvertBitmapToString(p.getImage()));
            db.insert(PhotographerContract.PhotographerEntry.TABLE_NAME, null, contentValues);
        }

        db.close();
    }
}
