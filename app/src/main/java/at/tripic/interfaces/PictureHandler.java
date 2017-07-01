package at.tripic.interfaces;

import java.util.List;

import at.tripic.model.PictureData;

public interface PictureHandler {
    void HandleResult(List<PictureData> data);
    void HandleError(int errorCode);
}
