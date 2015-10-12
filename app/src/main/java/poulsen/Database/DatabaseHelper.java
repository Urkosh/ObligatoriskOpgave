package poulsen.Database;

/**
 * Created by Mads on 09-10-2015.
 */
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import poulsen.obligatoriskopgvae.MainActivity;


final class DatabaseHelper extends SQLiteOpenHelper {

    @SuppressWarnings("unused")
    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "TapCounter";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper() {
        super(MainActivity.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        final String counter = "CREATE TABLE " + LoginDatabase.TABLE + "(" +
                LoginDatabase._ID + " integer primary key, " +
                LoginDatabase.USERID + " text, ";
        database.execSQL(counter);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // first iteration. do nothing.
    }

}
