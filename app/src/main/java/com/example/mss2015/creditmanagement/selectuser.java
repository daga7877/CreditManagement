package com.example.mss2015.creditmanagement;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class selectuser extends AppCompatActivity implements View.OnClickListener {
    RecyclerView r;
    Button b2;
    RecyclerAdapter a;
    String s1;
    ArrayList<String> arrayList1 = new ArrayList();
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectuser);
        Objects.requireNonNull( getSupportActionBar() ).setTitle("Select user");
        r = findViewById(R.id.rv);
        b2=findViewById(R.id.button2);
        r.setLayoutManager(new LinearLayoutManager(this));
        Intent i = getIntent();
         s1 = i.getStringExtra("clickname");


        try{
            database db = new database(this);
            SQLiteDatabase dh = db.getReadableDatabase();
            String qw2 = "select name from user where name !=?";
            Cursor c = dh.rawQuery(qw2,new String[]{s1});
            while(c.moveToNext()) {
                arrayList1.add(c.getString(0));
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this,""+e, Toast.LENGTH_SHORT).show();
        }
        a = new RecyclerAdapter(this,arrayList1);
        r.setAdapter(a);
        b2.setOnClickListener( this );
    }

    @Override
    public void onClick(View view) {

        Intent i1 = new Intent( this,process.class );
        i1.putStringArrayListExtra("suser",a.selectuser);
        i1.putExtra( "usern",s1 );
        startActivity( i1 );

    }

    }


