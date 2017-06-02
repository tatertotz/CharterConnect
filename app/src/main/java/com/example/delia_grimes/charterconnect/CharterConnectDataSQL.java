package com.example.delia_grimes.charterconnect;

//I have dropped the table so we'll see now if it creates the table correctly...

import android.provider.BaseColumns;

public final class CharterConnectDataSQL {
    public CharterConnectDataSQL() {}


    public static final String DATABASE_NAME = "CharterConnectData000.db";
    //public static String RESOURCES_TABLE_NAME = "Resources";
    /* Inner class that defines the table contents */
    public static abstract class Resources implements BaseColumns {

        //CHANGE this to a fresh DB later on
        //public static final String DATABASE_NAME = "CharterConnectData10.db";
        //99 uses old column format //"CharterConnectData99.db"; //oh, i name it here



        public static final String TABLE_NAME = "Resources";
        public static final String COLUMN_NAME_ENTRY_ID = "id";
        public static final String CATEGORY= "category"; //books, movies, art, science
        public static final String NAME= "name"; //ex book title and author, "microscope" etc
        public static final String GRADELEVEL= "gradelevel";
        public static final String NUM_UNITS= "quantity"; //10 microscopes, 10 copies of blah book
        public static final String DATE_NEXT_AVAILABLE= "dateAvailable";
        public static final String CONTACT_INFO= "contactInfo";// for who you reserve it with
        public static final String COLUMN_NAME_NULLABLE="canbenull";


        private static final String TEXT_TYPE = " TEXT"; //how do i say number type?
        private static final String NUM_TYPE = " INT";
        private static final String COMMA_SEP = ",";

        public static final String SQL_CREATE_ENTRIES =
                //"CREATE TABLE " + TABLE_NAME + " (" +
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        TABLE_NAME+ TEXT_TYPE + COMMA_SEP +
                        COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                        CATEGORY + TEXT_TYPE + COMMA_SEP +
                        NAME + TEXT_TYPE + COMMA_SEP +
                        GRADELEVEL + TEXT_TYPE + COMMA_SEP +
                        NUM_UNITS + TEXT_TYPE + COMMA_SEP +
                        DATE_NEXT_AVAILABLE + TEXT_TYPE + COMMA_SEP +
                        CONTACT_INFO +  TEXT_TYPE  + COMMA_SEP +
                        COLUMN_NAME_NULLABLE + TEXT_TYPE  +
                        " )";
        //CharterConnectDataSQL.Resources.COLUMN_NAME_ENTRY_ID + NUM_TYPE + COMMA_SEP +

    }

    public static abstract class Events implements BaseColumns {

        //CHANGE this to a fresh DB later on
        //public static final String DATABASE_NAME = "CharterConnectData20.db";
        //99 uses old column format //"CharterConnectData99.db"; //oh, i name it here



        public static final String TABLE_NAME = "Events";
        public static final String COLUMN_NAME_ENTRY_ID = "id";
        public static final String NAME= "name"; //ex book title and author, "microscope" etc
        public static final String HOST_SCHOOL = "hostSchool";
        public static final String LOCATION = "location";
        public static final String DATE = "date";
        public static final String TIME= "time";
        public static final String COST = "cost";
        public static final String GRADELEVEL = "gradelevel";
        public static final String SPACES_AVAILABLE = "spacesAvailable";
        public static final String EMAIL_ADDRESS = "emailAddress";
        public static final String PHONE_NUMBER = "phoneNumber";// for who you reserve it with
        public static final String COLUMN_NAME_NULLABLE = "canbenull";


        private static final String TEXT_TYPE = " TEXT"; //how do i say number type?
        private static final String NUM_TYPE = " INT";
        private static final String COMMA_SEP = ",";

        //does calling create table erase teh current table?
        public static final String SQL_CREATE_ENTRIES =
                //"CREATE TABLE " + TABLE_NAME + " (" +
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +



                        TABLE_NAME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                        NAME + TEXT_TYPE + COMMA_SEP +
                        HOST_SCHOOL + TEXT_TYPE + COMMA_SEP +
                        LOCATION + TEXT_TYPE + COMMA_SEP +
                        DATE + TEXT_TYPE + COMMA_SEP +
                        TIME + TEXT_TYPE + COMMA_SEP +
                        COST + TEXT_TYPE + COMMA_SEP +
                        GRADELEVEL + TEXT_TYPE + COMMA_SEP +
                        SPACES_AVAILABLE + TEXT_TYPE + COMMA_SEP +
                        EMAIL_ADDRESS + TEXT_TYPE + COMMA_SEP +
                        PHONE_NUMBER +  TEXT_TYPE  + COMMA_SEP +
                        COLUMN_NAME_NULLABLE + TEXT_TYPE  +


                        " )";
        //CharterConnectDataSQL.Resources.COLUMN_NAME_ENTRY_ID + NUM_TYPE + COMMA_SEP +

    }

    public static abstract class Schools implements BaseColumns {
        public static final String TABLE_NAME = "Schools";
        public static final String COLUMN_NAME_ENTRY_ID = "id";
        public static final String SCHOOL_NAME = "schoolName";
        public static final String URL = "url";
        public static final String ADDRESS = "address";
        public static final String GRADES = "grades";
        public static final String MISSION_STATEMENT = "missionStatement";
        public static final String CONTACT_INFO = "contactInfo";
        public static final String COLUMN_NAME_NULLABLE="canbenull";

        private static final String TEXT_TYPE = " TEXT";
        private static final String NUM_TYPE = " INT";
        private static final String COMMA_SEP = ",";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        TABLE_NAME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                        SCHOOL_NAME + TEXT_TYPE + COMMA_SEP +
                        URL + TEXT_TYPE + COMMA_SEP +
                        ADDRESS + TEXT_TYPE + COMMA_SEP +
                        GRADES + TEXT_TYPE + COMMA_SEP +
                        MISSION_STATEMENT + TEXT_TYPE + COMMA_SEP +
                        CONTACT_INFO + TEXT_TYPE + COMMA_SEP +
                        COLUMN_NAME_NULLABLE + TEXT_TYPE  + " )";
    }

}
