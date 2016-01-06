package com.example.shrisharmi.interactingwithotherapps;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final int PICK_CONTACT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Uri data = intent.getData();
        if(intent.getType().indexOf("image/")!= -1)
        {

        }

        else if(intent.getType().equals("text/plain"))
        {
            
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void pickContact()
    {
        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
    }


     protected void onActivityResult(int requestCode, int resultCode, Intent data)
     {
         if(requestCode == PICK_CONTACT_REQUEST)
         {
             if(resultCode==RESULT_OK)
             {
                 Uri contactUri = data.getData();
                 String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};
                 Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
                 cursor.moveToFirst();
                 int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                 String number = cursor.getString(column);
             }
         }
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
        if (id == R.id.action_about) {
           Intent intent = new Intent(this, intentActivity.class);

           // Uri number = Uri.parse("tel:334567889");
           // Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
           // Uri location = Uri.parse("geo:0, 0?q=1600+Amphitheatre+Parkway,+San+Jose,+California");
            //Intent mapIntent = new Intent(Intent.ACTION_VIEW,location);

            //Verify it resolves
            //PackageManager packageManager = getPackageManager();
            //List<ResolveInfo> activities = packageManager.queryIntentActivities(mapIntent, 0);
            //boolean isIntentSafe = activities.size() > 0;

            //start an activity if its safe
            //if(isIntentSafe)
            //{
             //   startActivity(mapIntent);
           // }

          //  Uri webpage = Uri.parse("http://www.android.com");
          //  Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(intent);
            //startActivity(webIntent);
            //startActivity(callIntent);
            //startActivity(mapIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
