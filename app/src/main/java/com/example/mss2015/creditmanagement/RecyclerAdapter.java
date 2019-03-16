package com.example.mss2015.creditmanagement;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.myviewHolder> {

ArrayList selectuser = new ArrayList(  );
    Context context;
    ArrayList<String> stringArrayList;

    public RecyclerAdapter(Context context, ArrayList<String> strings) {
        this.context =context;
        this.stringArrayList=strings;
    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom,parent,false);
        return new myviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final myviewHolder holder, final int position) {
        holder.t1.setText(stringArrayList.get(position));
        holder.t1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.t1.isChecked())
                {
                    holder.t1.setChecked( false );
                    selectuser.remove( stringArrayList.get( position ) );
                }
                else
                {
                    holder.t1.setChecked( true );
                    selectuser.add( stringArrayList.get( position ) );

                }
            }
        } );
    }

    @Override
    public int getItemCount() {
        return stringArrayList.size();
    }
    static class myviewHolder extends RecyclerView.ViewHolder
    {
        public CheckedTextView t1;
        View v;
        public myviewHolder(View itemView) {
            super(itemView);
            v= itemView;
            initview();
        }
        void initview()
        {
            t1=v.findViewById(R.id.ctv);
        }
    }

}
