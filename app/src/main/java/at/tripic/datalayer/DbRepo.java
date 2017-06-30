package at.tripic.datalayer;

import android.content.Context;

public class DbRepo {
    private static DatabaseHelper dbHelper;

    public DbRepo(Context context) {
        if (dbHelper == null) dbHelper = new DatabaseHelper(context);
    }

    public void insertData(String data) {}
}
