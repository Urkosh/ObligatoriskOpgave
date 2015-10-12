package poulsen.obligatoriskopgvae;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import poulsen.Controller.ControllerLogin;
import poulsen.obligatoriskopgvae.R;

public class CreateUserActivity extends AppCompatActivity {

    private ControllerLogin controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
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
        String name = textName.toString();
        String password = textPassword.toString();
        Integer duration = Toast.LENGTH_SHORT;
        boolean handled = controller.handleCreateUser(name, password);
        if(handled)
        {
            String text = "@string/user_created";
            Toast toast = Toast.makeText(getApplicationContext(), text, duration);
            toast.show();
            finish();
        }
        else
        {
            String text = "@string/user_was_not_created";
            Toast toast = Toast.makeText(getApplicationContext(), text, duration);
            toast.show();
        }
    }

    public void backFromCreateUser(View view)
    {
        finish();
    }
}
