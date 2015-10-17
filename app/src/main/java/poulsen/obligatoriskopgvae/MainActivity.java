package poulsen.obligatoriskopgvae;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.SQLException;

import poulsen.Controller.ControllerLogin;
import poulsen.Database.LoginDatabase;

public class MainActivity extends AppCompatActivity {

    private LoginDatabase datasource;
    private static MainActivity instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datasource = new LoginDatabase(this);
        try{
            datasource.open();
        }
        catch (SQLException dataOpeningException)
        {

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void login(View view)
    {
        EditText loginID = (EditText) findViewById(R.id.login_id);
        EditText loginPassword = (EditText) findViewById(R.id.password);
        String userID = loginID.getText().toString();
        String password = loginPassword.getText().toString();
        int duration = Toast.LENGTH_SHORT;
        boolean handled = datasource.checkLogin(userID, password);
        if(handled)
        {
            Intent loggedInActivity = new Intent(getApplicationContext(), LoggedInActivity.class);
            startActivity(loggedInActivity);
            Toast toast = Toast.makeText(getApplicationContext(), R.string.login_achieved, duration);
            toast.show();
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.login_failed, duration);
            toast.show();
        }
    }

    public void createUser(View view)
    {
        Intent createUserView = new Intent(getApplicationContext(), CreateUserActivity.class);
        startActivity(createUserView);
    }

    public void retrievePassword(View view)
    {

        EditText loginID = (EditText) findViewById(R.id.login_id);
        String userID = loginID.getText().toString();
        String password = datasource.retrievePassword(userID);
        int duration = Toast.LENGTH_SHORT;
        if(password.compareTo("") != 0)
        {
            Toast toast = Toast.makeText(getApplicationContext(), password, duration);
            toast.show();
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.could_not_retrieve_password, duration);
            toast.show();
        }
    }

}
