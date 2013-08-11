package com.jeremyfox.CPM_Week_2.ModelGenerator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created with IntelliJ IDEA.
 * User: jfox
 * Date: 8/10/13
 * Time: 8:15 PM
 */
public class ModelGenerator {

    /**
     * VERY IMPORTANT
     * When changing anything in this file you must update the DATABASE_VERSION
     */
    private static final int DATABASE_VERSION = 1;
    private static final String PACKAGE = "com.jeremyfox.CPM_Week_2";

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(DATABASE_VERSION, PACKAGE);
        schema.enableKeepSectionsByDefault();

        addContact(schema);
        addPhoto(schema);

        new DaoGenerator().generateAll(schema, "src-gen");
    }

    private static void addContact(Schema schema) {
        Entity contact = schema.addEntity("Contact");
        contact.addIdProperty();
        contact.addStringProperty("email");
        contact.addStringProperty("name");
        contact.addStringProperty("phone");
        contact.addBooleanProperty("isShared");
    }

    private static void addPhoto(Schema schema) {
        Entity photo = schema.addEntity("Photo");
        photo.addIdProperty();
        photo.addDateProperty("dateTaken");
        photo.addStringProperty("image");
        photo.addBooleanProperty("instantShare");
        photo.addBooleanProperty("isMostRecent");
        photo.addIntProperty("recordID");
        photo.addStringProperty("recordURL");
        photo.addBooleanProperty("shared");
        photo.addBooleanProperty("sharedToFacebook");
        photo.addBooleanProperty("sharedToFlickr");
        photo.addBooleanProperty("sharedToInstagram");
        photo.addBooleanProperty("sharedToTumblr");
        photo.addBooleanProperty("sharedToTwitter");
        photo.addStringProperty("sharedWith");
        photo.addStringProperty("thumbnail");
    }

}