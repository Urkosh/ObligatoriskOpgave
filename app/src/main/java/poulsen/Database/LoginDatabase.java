package poulsen.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USERID, userID);
        values.put(DatabaseHelper.COLUMN_PASSWORD, password);
        long insertId = database.insert(DatabaseHelper.TABLE_LOGIN, null, values);
        Cursor cursor = database.query(DatabaseHelper.TABLE_LOGIN, allColumns, DatabaseHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        ModelLogin newLogin = cursorToLogin(cursor);
        cursor.close();

        Log.d("User creation", "name: " + newLogin.getUser() + "password: " + newLogin.getPassword());
        return newLogin;
    }

    public boolean checkLogin(String userID, String password)
    {
        boolean loggedIn = false;
        int index = 0;
        while(index < getAllLogins().size())
        {
            if(getAllLogins().get(index).getUser().compareTo(userID) == 0)
            {
                if(getAllLogins().get(index).getPassword().compareTo(password) == 0)
                {
                    Log.d("checkLogin", "Login Achieved");
                    loggedIn = true;
                }
            }

            index++;
        }

        return loggedIn;
    }

    public String retrievePassword(String userID)
    {
        String lostPassword = "";
        int index = 0;
        while(index < getAllLogins().size())
        {
            if(getAllLogins().get(index).getUser().compareTo(userID) == 0)
            {
                lostPassword = getAllLogins().get(index).getPassword();
            }

            index++;
        }

        return lostPassword;
    }

    private List<ModelLogin> getAllLogins()
    {
        List<ModelLogin> logins = new LinkedList<ModelLogin>();

        String query = "SELECT * FROM " + DatabaseHelper.TABLE_LOGIN;

        Cursor cursor = database.rawQuery(query, null);
        ModelLogin login = null;
        if(cursor.moveToFirst())
        {
            do{
                login = new ModelLogin();
                login.setDatabaseID(cursor.getLong(0));
                login.setUser(cursor.getString(1));
                login.setPassword(cursor.getString(2));

                logins.add(login);
            } while(cursor.moveToNext());
        }
        Log.d("getAllLogins()", logins.toString());

        return logins;
    }
    private ModelLogin cursorToLogin(Cursor cursor) {
        ModelLogin login = new ModelLogin();
        login.setDatabaseID(cursor.getLong(0));
        login.setUser(cursor.getString(1));
        login.setPassword(cursor.getString(2));
        return login;
    }
}
