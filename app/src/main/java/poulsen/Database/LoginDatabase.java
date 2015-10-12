package poulsen.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import poulsen.Model.ModelLogin;

/**
 * Created by Mads on 09-10-2015.
 */
public class LoginDatabase
{
    protected static final String TABLE = "Login";
    protected static final String _ID = "_id";
    protected static final String USERID = "userID";
    protected static final String PASSWORD = "password";

    public LoginDatabase(){
        //
    }

    public long createUser(ModelLogin login)
    {
        SQLiteDatabase db = new DatabaseHelper().getWritableDatabase();
        ContentValues values = new ContentValues();
        if(login.getDatabaseID() > 0)
            values.put(_ID, login.getDatabaseID());
            values.put(PASSWORD, login.getPassword());
            values.put(USERID, login.getUser());

        long num = db.insert(TABLE, null, values);
        db.close();
        return num;
    }

    public boolean checkLogin(String userID, String password)
    {
        SQLiteDatabase db = new DatabaseHelper().getWritableDatabase();
        Cursor cursor = db.query(TABLE, null, USERID + "=?", new String[]{userID}, null, null, null);
        boolean loginWorked = false;
        if(cursor.moveToFirst())
        {
            if(cursor.getString(cursor.getColumnIndex(PASSWORD)).compareTo(password) == 0)
            {
                loginWorked = true;
            }

        }
        cursor.close();
        db.close();
        return loginWorked;
    }

    public String retrievePassword(String userID)
    {
        SQLiteDatabase db = new DatabaseHelper().getWritableDatabase();
        Cursor cursor = db.query(TABLE, null, USERID + "=?", new String [] {userID}, null, null, null);
        String newPassword = null;
        if(cursor.moveToFirst())
        {
            newPassword = cursor.getString(cursor.getColumnIndex(PASSWORD));
        }

        return newPassword;
    }

}
