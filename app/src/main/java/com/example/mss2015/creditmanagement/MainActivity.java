package com.example.mss2015.creditmanagement;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    String name[]= {"ram","shyam","seeta","geeta","suresh","ram1","shyam1","seeta1","geeta1","suresh1"};
    String email[]={"ram@gmail.com","shyam@gmail.com","sita@gmail.com","gitu@gmail.com","su@gmail.com","ram1@gmail.com","shyam1@gmail.com","sita1@gmail.com","gitu1@gmail.com","su1@gmail.com"};
    double credit[]={1000,2000,3000,2000,4000,1000,2000,3000,2000,4000};
    ListView l1;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.adminmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull( getSupportActionBar() ).setTitle("Users");
        l1=findViewById(R.id.listview);

        try {
            database dh = new database(this);
            SQLiteDatabase db = dh.getWritableDatabase();
            ContentValues cv = new ContentValues();
            for (int i = 0; i < name.length; i++) {
                cv.put("name", name[i]);
                cv.put("email", email[i]);
                cv.put("credit", credit[i]);
                db.insert("user", null, cv);
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this,""+e, Toast.LENGTH_SHORT).show();
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,name);
        l1.setAdapter(arrayAdapter);
        l1.setOnItemClickListener(this);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent g1 = new Intent( this,record.class );
        startActivity( g1 );
        return super.onOptionsItemSelected(item);

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this,info.class);
        i.putExtra("clickname",name[position]);
        startActivity(i);

    }
}
