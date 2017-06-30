package at.tripic.model;

import at.tripic.interfaces.PictureHandler;

public class PictureData {
    private String desc;
    private String imageAsUrl;
    private String id;
    private String farm;
    private String server;
    private String secret;

    public PictureData(String desc, String imageAsUrl) {
        this.desc = desc;
        this.imageAsUrl = imageAsUrl;
    }

    public PictureData(String id, String farm, String server, String secret) {
        this.id = id;
        this.farm = farm;
        this.secret = secret;
        this.server = server;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getImageAsUrl() {
        return this.imageAsUrl;
    }
}
