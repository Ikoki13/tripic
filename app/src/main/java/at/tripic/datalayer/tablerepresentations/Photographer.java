package at.tripic.datalayer.tablerepresentations;

import android.graphics.Bitmap;
import android.media.Image;

/**
 * Created by Michael on 01.07.2017.
 */

public class Photographer {

    String firstname;
    String lastname;
    String description;
    Bitmap image;

    public Photographer(String firstname, String lastname, String description, Bitmap image) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.description = description;
        this.image = image;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
