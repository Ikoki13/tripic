package at.tripic.dto;

import android.graphics.Picture;

import java.util.ArrayList;
import java.util.List;

import at.tripic.model.PictureData;

public class Response {
    String cod;
    PictureItem[] list;
    List<String> listNew;

    public List<PictureData> AsModel() {
        ArrayList<PictureData> result = new ArrayList<>();
        /*
        for (PictureItem item : list) {
            result.add(new PictureData(item.id, item.farm, item.server, item.secret));
        }
        */

        for (String x : listNew) {
            result.add(new PictureData("", x));
        }


        return result;
    }

    public void setPictureItem (PictureItem[] list) {
        this.list = list;
    }

    public void setlistNew (List<String> list) {
        this.listNew = list;
    }
}
