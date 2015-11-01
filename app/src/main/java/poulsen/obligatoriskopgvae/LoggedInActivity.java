package poulsen.obligatoriskopgvae;

import android.content.ClipData;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import poulsen.obligatoriskopgvae.R;

public class LoggedInActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> listAdapter;
    final int PARKING = R.array.Parking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        listView = (ListView) findViewById(R.id.listView);
        final ArrayList<String> questionList = new ArrayList<String>();
        final String [] questionArray = getResources().getStringArray(R.array.Questions);
                questionList.addAll(Arrays.asList(questionArray));
                Log.d("question list size", Integer.toString(questionList.size()));

        listAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, questionList);
        listView.setAdapter(listAdapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Object o = listView.getItemAtPosition(position);
                int index = 0;
                int posIndex = position;
                boolean didAChangeOccur = false;
                ArrayList<String> listOfArray = GetArrays();
                Log.d("Click registered", "Process started after click");
                while(index < listOfArray.size()) {
                    Log.d("While loop", "While loop initiated");
                    if (o.toString().contains("Parking")) {
                        listAdapter.insert(listOfArray.get(index), posIndex);
                        Log.d("Updated list size", Integer.toString(questionList.size()));
                        //update with input fields
                        //and items
                        posIndex++;
                        index++;
                        didAChangeOccur = true;
                    } else {
                        //update with input fields
                        Log.d("Failed to update list", "failure in the if statement");
                        index++;
                    }
                }
                if(didAChangeOccur)
                {
                    listAdapter.remove(o.toString());
                }
                listAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_logged_in, menu);
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

    private ArrayList<String> GetArrays()
    {
        ArrayList listOfArrays = new ArrayList();
        final String [] array = getResources().getStringArray(R.array.Parking);
        listOfArrays.addAll(Arrays.asList(array));
        return listOfArrays;
    }
}
