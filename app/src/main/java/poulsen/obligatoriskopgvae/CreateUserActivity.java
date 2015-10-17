package poulsen.obligatoriskopgvae;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

import poulsen.Database.LoginDatabase;
import poulsen.Model.ModelLogin;
import poulsen.obligatoriskopgvae.R;

public class CreateUserActivity extends AppCompatActivity {

    private LoginDatabase datasource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        datasource = new LoginDatabase(this);
        try{
            datasource.open();
        }
        catch (SQLException openingException)
        {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_user, menu);
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

    public void createUserActivityMethod(View view)
    {
        EditText textName = (EditText) findViewById(R.id.createUserName);
        EditText textPassword = (EditText) findViewById(R.id.createPassword);
        String name = textName.getText().toString();
        String password = textPassword.getText().toString();
        ModelLogin user = datasource.createUser(name, password);
        Integer duration = Toast.LENGTH_SHORT;
        if(user.getUser().compareTo(name) == 0)
        {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.user_created, duration);
            toast.show();
            finish();
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.user_was_not_created, duration);
            toast.show();
        }
    }

    public void backFromCreateUser(View view)
    {
        finish();
    }
}
