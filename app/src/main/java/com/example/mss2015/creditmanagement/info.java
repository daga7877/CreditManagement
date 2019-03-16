package com.example.mss2015.creditmanagement;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class info extends AppCompatActivity implements View.OnClickListener {
    TextView t1,t2,t3;
    Button b1;
    String s;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Objects.requireNonNull( getSupportActionBar() ).setTitle("Details");
        t1=findViewById(R.id.textView2);
        t2=findViewById(R.id.textView4);
        t3=findViewById(R.id.textView6);
        b1=findViewById(R.id.button);
        Intent i = getIntent();
        s = i.getStringExtra("clickname");
        b1.setOnClickListener(this);

        try {
            database db = new database(this);
            SQLiteDatabase dh = db.getReadableDatabase();
            String qw2 = "select * from user where name=?";
            Cursor c = dh.rawQuery(qw2, new String[]{s});
            if (c.moveToFirst()) {
                t1.setText(c.getString(0));
                t2.setText(c.getString(1));
                t3.setText(c.getString(2));
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this,""+e, Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,selectuser.class);
        intent.putExtra("clickname",s);
        startActivity(intent);

    }
}
