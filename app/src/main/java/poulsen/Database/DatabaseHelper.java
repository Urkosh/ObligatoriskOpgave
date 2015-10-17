package poulsen.Database;

/**
 * Created by Mads on 09-10-2015.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import poulsen.obligatoriskopgvae.MainActivity;


final class DatabaseHelper extends SQLiteOpenHelper {

    @SuppressWarnings("unused")
    protected static final String TABLE_LOGIN = "login";
    protected static final String COLUMN_ID = "_id";
    protected static final String COLUMN_USERID = "user_id";
    protected static final String COLUMN_PASSWORD = "password";

    private static final String DATABASE_NAME = "logins.db";
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_LOGIN + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_USERID + " TEXT, " +
            COLUMN_PASSWORD + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DatabaseHelper.class.getName(), "Upgrading database from version " + oldVersion + "to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);

        onCreate(db);
    }

}
