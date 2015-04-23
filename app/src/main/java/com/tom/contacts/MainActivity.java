package com.tom.contacts;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] proj = {ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME};
//        Cursor c = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
//                null,
//                ContactsContract.Contacts.STARRED+"=?",
//                new String[]{"1"},
//                null
//        );
//        Cursor c = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
//                null,
//                "_id=?",
//                new String[]{"2"},
//                null
//        );
        //Log.d("DD", c.moveToNext()+"");
        Cursor c = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                null
        );
        while(c.moveToNext()){
            int id = c.getInt(c.getColumnIndex(ContactsContract.Contacts._ID));
            String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            int hasPhone = c.getInt(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
            Log.d("DD", id+"/"+name+"/"+hasPhone);
            Cursor c2 = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"=?",
                    new String[]{id+""},
                    null
                    );
            while(c2.moveToNext()){
                String phone = c2.getString(c2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Log.d("PH", phone);
            }

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
}
