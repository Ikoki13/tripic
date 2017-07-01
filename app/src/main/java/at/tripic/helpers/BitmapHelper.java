package at.tripic.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by Michael on 01.07.2017.
 */

public class BitmapHelper {

    public static String ConvertBitmapToString(Bitmap bitmap) {
        String result = "";

        ByteArrayOutputStream byteArrayOutputStream =new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, byteArrayOutputStream);

        byte [] byteArray = byteArrayOutputStream.toByteArray();

        result = Base64.encodeToString(byteArray, Base64.DEFAULT);

        return result;
    }

    public static Bitmap ConvertStringToBitmap(String bitmap) {
        Bitmap result = null;

        try {
            byte[] encodeByte = Base64.decode(bitmap,Base64.DEFAULT);
            result = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        } catch(Exception e) {
            e.getMessage();
            return null;
        }

        return result;
    }

}
