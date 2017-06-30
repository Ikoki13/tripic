package at.tripic.dto;

import android.graphics.Picture;

import java.util.ArrayList;
import java.util.List;

import at.tripic.model.PictureData;

public class Response {
    String cod;
    PictureItem[] list;

    public List<PictureData> AsModel() {
        ArrayList<PictureData> result = new ArrayList<>();
        for (PictureItem item : list) {
            result.add(new PictureData(item.id, item.farm, item.server, item.secret));
        }
        return result;
    }
}
