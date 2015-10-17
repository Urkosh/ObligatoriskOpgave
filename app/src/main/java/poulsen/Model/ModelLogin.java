package poulsen.Model;

/**
 * Created by Mads on 08-10-2015.
 */
public class ModelLogin extends SimpleObserver<ModelLogin>
{
    String userID;
    String password;
    Long DatabaseID;

    public ModelLogin()
    {
        DatabaseID = null;
        userID = "";
        password = "";
    }
    public ModelLogin(String userID, String password)
    {
        DatabaseID = null;
        this.userID = userID;
        this.password = password;
    }

    public void setUser(String userID)
    {
        this.userID = userID;
        notifyObservers(this);
    }

    public String getUser()
    {
        return userID;
    }

    public void setPassword(String password)
    {
        this.password = password;
        notifyObservers(this);
    }

    public String getPassword()
    {
        return password;
    }

    public void setDatabaseID(Long id)
    {
        this.DatabaseID = id;
        notifyObservers(this);
    }

    public Long getDatabaseID()
    {
        return DatabaseID;
    }

}
