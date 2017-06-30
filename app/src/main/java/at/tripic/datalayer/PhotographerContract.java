package at.tripic.datalayer;

import android.provider.BaseColumns;

public class PhotographerContract {
    public static class PhotographerEntry implements BaseColumns {
        public static final String TABLE_NAME = "photographer";
        public static final String COLUMN_FIRSTNAME = "firstname";
        public static final String COLUMN_LASTNAME = "lastname";
        public static final String COLUMN_DESC = "desc";
        public static final String COLUMN_IMAGE = "image";
    }

}
