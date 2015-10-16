package poulsen.Controller;

import poulsen.Database.LoginDatabase;
import poulsen.Model.ModelLogin;

/**
 * Created by Mads on 08-10-2015.
 */
public class ControllerLogin
{
    LoginDatabase loginDB;
    public boolean handleLogin(String userID, String password)
    {
        boolean handled = false;
        //loginDB = new LoginDatabase();
        //if(loginDB.checkLogin(userID, password) == true)
        {
            handled = true;
        }
        return handled;
    }

    public boolean handleCreateUser(String userID, String password)
    {
        boolean handled = false;
        ModelLogin login = new ModelLogin(userID, password);
        //loginDB = new LoginDatabase();
       // if(loginDB.createUser(login)>=0)
        {
            handled = true;
        }
        return handled;
    }

    public String handleRetrievePassword(String userID)
    {
        String password = null;
        //loginDB = new LoginDatabase();
       // if(loginDB.retrievePassword(userID) != null)
        {
        //    password = loginDB.retrievePassword(userID);
        }
        return password;
    }
}
