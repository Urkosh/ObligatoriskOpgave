package poulsen.obligatoriskopgvae;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import poulsen.Database.LoginDatabase;
import poulsen.obligatoriskopgvae.R;

public class RecoverPassword extends AppCompatActivity {

    private LoginDatabase datasource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recover_password, menu);
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

    public void recoverPassword()
    {
        EditText loginID = (EditText) findViewById(R.id.recoverPasswordUserName);
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
