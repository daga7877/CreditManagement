package com.example.mss2015.creditmanagement;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class process extends AppCompatActivity implements View.OnClickListener {

    EditText e1;
    Button b3;
    String a;
    ArrayList arrayList = new ArrayList(  );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_process );
        getSupportActionBar().setTitle( "Payment" );
        e1=findViewById( R.id.edittext );
        b3=findViewById( R.id.button3 );
        Intent i1 = getIntent();
        arrayList= i1.getStringArrayListExtra( "suser" );
         a = i1.getStringExtra( "usern" );

        b3.setOnClickListener( this );

    }

    @Override
    public void onClick(View v) {
        String val = null;
        double amount = Double.parseDouble( e1.getText().toString() );
        try{
            database ds = new database( this );
            SQLiteDatabase sd = ds.getReadableDatabase();
            ContentValues contentValues=new ContentValues();
            for(int i=0; i<arrayList.size();i++) {
                String qw2 = "select * from user where name=?";
                Cursor c = sd.rawQuery( qw2, new String[]{String.valueOf( arrayList.get( i ) )} );
                if (c.moveToFirst()) {
                    val =c.getString(2);
                }
                double f=Double.parseDouble( val );
                f=f+amount;
                contentValues.put( "credit",f );
                sd.update( "user",contentValues,"name=?",new String[]{String.valueOf( arrayList.get( i ) )});

            }
        }catch (Exception e)
        {
            Toast.makeText(this,""+e, Toast.LENGTH_SHORT).show();
        }

        try{
            database ds1 = new database( this );
            SQLiteDatabase sd1 = ds1.getReadableDatabase();
            ContentValues contentValues1=new ContentValues();
            for(int i=0; i<arrayList.size();i++) {
                String qw3 = "select * from user where name=?";
                Cursor c = sd1.rawQuery( qw3, new String[]{a} );
                if (c.moveToFirst()) {
                    val =c.getString(2);
                }
                double f=Double.parseDouble( val );
                f=f-amount;
                contentValues1.put( "credit",f );
                int ans=sd1.update( "user",contentValues1,"name=?",new String[]{a});

                //entry in transationrecord

                if(ans==1)
                {
                    Toast.makeText( this,"amount transfer to"+(i+1),Toast.LENGTH_SHORT ).show();
                }
                else{
                    Toast.makeText( this,"amount transfer to failed"+(i+1),Toast.LENGTH_SHORT ).show();
                }


            }
        }catch (Exception e)
        {
            Toast.makeText(this,""+e, Toast.LENGTH_SHORT).show();
        }

        try {
            database dh = new database( this );
            SQLiteDatabase db = dh.getWritableDatabase();
            ContentValues cv = new ContentValues();
            for (int i = 0; i < arrayList.size(); i++) {

                cv.put( "namef", a );
                cv.put( "namet", String.valueOf( arrayList.get( i ) ) );
                cv.put( "amount", amount );
                db.insert( "Transferrecord", null, cv );

            }
        }
        catch (Exception e)
        {
            Toast.makeText(this,""+e, Toast.LENGTH_SHORT).show();
        }
        Intent sasa= new Intent( this,MainActivity.class );
        startActivity( sasa);
        finish();
    }
}
