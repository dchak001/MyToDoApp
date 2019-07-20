package com.example.mytodoapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;

import io.realm.Realm;

public class CreateTask extends AppCompatActivity {
    EditText mname,mdate,mdetails,mcolor;
    int id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task);
        mname=findViewById(R.id.tname);
        mdate=findViewById(R.id.date);
        mdetails=findViewById(R.id.details);
        mcolor=findViewById(R.id.color);
        id=getIntent().getExtras().getInt("userId");

    }

    public void submit(View view)
    {
        Realm realm=Realm.getDefaultInstance();
        realm.beginTransaction();
        try{
            Number currentIdNum=realm.where(Task.class).max("taskId");
            int nextId = (currentIdNum == null) ? 1 : currentIdNum.intValue() + 1;
            Task task=realm.createObject(Task.class,nextId);
            task.setTaskName(mname.getText().toString());
            task.setDueDate(new SimpleDateFormat("dd/MM/yyyy").parse(mdate.getText().toString()));
            task.setTaskDetails(mdetails.getText().toString());
            task.setColor(mcolor.getText().toString());
            task.setUserId(id);
            task.setChecked("false");
            realm.commitTransaction();
            Toast.makeText(this, "Current task id is "+nextId, Toast.LENGTH_SHORT).show();
           // Intent intent=new Intent(this,Dashboard.class);
            //startActivity(intent);
        }catch(Exception e){realm.cancelTransaction();}
        finally {
            realm.close();
        }
         onBackPressed();
    }

    public void discard(View view)
    {   //new Dashboard().onResume();
       // Intent intent=new Intent(this,Dashboard.class);
       // startActivity(intent);
        onBackPressed();
    }

   /* @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "OnDestroy caleed", Toast.LENGTH_SHORT).show();
    }*/
}
