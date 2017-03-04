package sg.edu.nus.personalmedipal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import sg.edu.nus.personalmedipal.medipal.PersonalBio;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MediPal.db";
    private static final String TABLE_PERSONALBIO= "PersonalBio";
    private static final String TABLE_HEALTHBIO= "HealthBio";
    private static final String TABLE_CATEGORIES= "Categories";
    private static final String TABLE_MEDICINE= "Medicine";
    private static final String TABLE_MEASUREMENTS= "Measurements";
    private static final String TABLE_CONSUMPTION= "Consumption";
    private static final String TABLE_REMINDERS= "Reminders";
    private static final String TABLE_APPOINTMENT= "Appointment";
    private static final String TABLE_ICE= "ICE";

    private static final String COLUMN_PERSONALBIO[] = {"ID", "Name", "DOB", "IDNo", "Address",
                                                    "PostalCode", "Height", "BloodType"};

    //begin SQL statements
    private static final String CREATE_TABLE_PERSONALBIO = "CREATE TABLE " + TABLE_PERSONALBIO +
            "(" +
            COLUMN_PERSONALBIO[0] + " INTEGER PRIMARY KEY AUTOINCREMENT " +
            COLUMN_PERSONALBIO[1] + " VARCHAR(100) " +
            COLUMN_PERSONALBIO[2] + " Date " +
            COLUMN_PERSONALBIO[3] + " VARCHAR(20) " +
            COLUMN_PERSONALBIO[4] + " VARCHAR(100) " +
            COLUMN_PERSONALBIO[5] + " VARCHAR(10) " +
            COLUMN_PERSONALBIO[6] + " INTEGER " +
            COLUMN_PERSONALBIO[7] + " VARCHAR(10) " +
            ");";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";
    //end of SQL statements


    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PERSONALBIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE + TABLE_PERSONALBIO);
        onCreate(db);
    }


    //Personal Bio
    public void addPersonalBio(PersonalBio record) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_PERSONALBIO[0], record.getId());
        values.put(COLUMN_PERSONALBIO[1], record.getName());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PERSONALBIO, null, values);
        db.close();
    }

    public void deletePersonalBio(String key) {

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PERSONALBIO + " WHERE " +
                COLUMN_PERSONALBIO[0] + " =\"" + key + "\";");
        db.close();
    }

    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PERSONALBIO + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        Log.i(TAG, "Record(s) count " + c.getCount());

        for( int i=0; i<c.getCount(); i++) {
            if(c.getString(c.getColumnIndex(COLUMN_PERSONALBIO[0])) != null) {
                dbString += c.getString(c.getColumnIndex(COLUMN_PERSONALBIO[1]));
                dbString += "\t";
                dbString += c.getString(c.getColumnIndex(COLUMN_PERSONALBIO[2]));
                dbString += "\n";
                Log.i(TAG, "Record: " + dbString);
            }
            c.moveToNext();
        }

        c.close();
        db.close();
        return dbString;
    }
}
