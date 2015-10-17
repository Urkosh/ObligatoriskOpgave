package poulsen.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.util.Log;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import poulsen.Model.ModelLogin;

/**
 * Created by Mads on 09-10-2015.
 */
public class LoginDatabase {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = {DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_USERID, DatabaseHelper.COLUMN_PASSWORD};

    public LoginDatabase(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public ModelLogin createUser(String userID, String password) {
        Cursor checkForUser = database.query(DatabaseHelper.TABLE_LOGIN, new String [] {DatabaseHelper.COLUMN_USERID},
                " user_id = ? ", new String[] { userID }, null, null, null, null);
        ModelLogin newLogin = new ModelLogin();
        if(checkForUser.getCount() == 0) {
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.COLUMN_USERID, userID);
            values.put(DatabaseHelper.COLUMN_PASSWORD, password);
            long insertId = database.insert(DatabaseHelper.TABLE_LOGIN, null, values);
            Cursor cursor = database.query(DatabaseHelper.TABLE_LOGIN, allColumns, DatabaseHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
            cursor.moveToFirst();
            newLogin = cursorToLogin(cursor);
            cursor.close();
        }
        else
        {
            Log.d("User Creation", "User allready exists");
        }
        checkForUser.close();
        Log.d("User creation", "name: " + newLogin.getUser() + "password: " + newLogin.getPassword());
        return newLogin;
    }

    public boolean checkLogin(String userID, String password)
    {

        Cursor cursor = database.query(DatabaseHelper.TABLE_LOGIN, new String [] {DatabaseHelper.COLUMN_USERID, DatabaseHelper.COLUMN_PASSWORD},
                " user_id = ? and password = ?", new String[] { userID, password }, null, null, null, null);
        boolean loggedIn = false;
        if(cursor != null)
        {
            cursor.moveToFirst();
            Log.d("checkLogin", "Login Achieved");
            loggedIn = true;
            cursor.close();
        }

        return loggedIn;
    }

    public String retrievePassword(String userID)
    {
        String lostPassword = "";
        Cursor retrievePassword = database.query(DatabaseHelper.TABLE_LOGIN, new String [] { DatabaseHelper.COLUMN_USERID,  DatabaseHelper.COLUMN_PASSWORD}, " user_id = ?", new String [] { userID }, null, null, null, null);
        if(retrievePassword != null)
            {
                retrievePassword.moveToFirst();;
                Log.d("Columns: ", String.valueOf(
                        retrievePassword.getColumnCount()));
                lostPassword = retrievePassword.getString(1);
                retrievePassword.close();
            }


        return lostPassword;
    }

    private ModelLogin cursorToLogin(Cursor cursor) {
        ModelLogin login = new ModelLogin();
        login.setDatabaseID(cursor.getLong(0));
        login.setUser(cursor.getString(1));
        login.setPassword(cursor.getString(2));
        return login;
    }
}
