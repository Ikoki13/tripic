package at.tripic.interfaces;

import java.util.List;

public interface PictureHandler {
    void HandleResult(List<String> data);
    void HandleError(int errorCode);
}
