package com.example.mytodoapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

public class Dashboard extends AppCompatActivity  {
  TextView mname;
  TextView memail;
    private RecyclerView recyclerView;
   static int id;
    DrawerLayout dl;
    NavigationView nv;
    TextView tv;
    ActionBarDrawerToggle toggle;
    @Override
    public void onCreate(Bundle savedInstanceState) {





        /*    //Initialize views from the layout
            dl = findViewById(R.id.drawerLayout);
            nv = findViewById(R.id.navigationView);


            //ActionBarDrawerToggle is initialized to sync drawer open and closed states
            toggle = new ActionBarDrawerToggle(this, dl, R.string.open_menu, R.string.close_menu);

            dl.addDrawerListener(toggle);
            toggle.syncState();

            //The Hamburger icon is applied to the action bar for working with the nav drawer
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //On clicking of any menu items, actions will be performed accordingly
            nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override

                @NonNull
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int id = item.getItemId();
                    switch (id){
                        case R.id.account:
                            Toast.makeText(Dashboard.this, "This activity", Toast.LENGTH_LONG).show();
                            break;
                        case R.id.settings:
                            startActivity(new Intent(Dashboard.this, ProfileActivity.class));
                            break;
                        case R.id.exit:
                            finish();
                            break;
                        default:
                            return true;
                    }
                    return true;
                }
            });*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        ////mname=findViewById(R.id.active_username);
        /*memail=findViewById(R.id.active_email);
        //Bundle extra=getIntent().getExtras();

        mname.setText(b.getString("username"));
        memail.setText(b.getString("email"));
        String phone=b.getString("phone");*/

        Bundle b= getIntent().getExtras();
         id=Integer.parseInt(b.getString("id"));

            /*Realm realm=Realm.getDefaultInstance();
            RealmResults<Task> task=realm.where(Task.class).equalTo("userId",id).sort("dueDate", Sort.ASCENDING).findAll();
        //RealmQuery<Task> task1=realm.where(Task.class).equalTo("userId",id);
        if(task.size()>0)
          Log.i("dashboard","task exist");
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
            if(task.size()==0){
                Toast.makeText(this, "Fragment to be loaded", Toast.LENGTH_SHORT).show();
                ft.add(R.id.frame_container,new Fragment2());}
            else
            {

                ft.add(R.id.frame_container,new Fragment1(task));}
                ft.commit();*/

    }

    @Override
    public void onResume() {
        super.onResume();
       /* FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        Realm realm=Realm.getDefaultInstance();
        RealmResults<Task> task=realm.where(Task.class).equalTo("userId",id).sort("dueDate", Sort.ASCENDING).findAll();
            ft.add(R.id.frame_container,new Fragment1(task));

        ft.commit();*/
        Realm realm=Realm.getDefaultInstance();
        RealmResults<Task> task=realm.where(Task.class).equalTo("userId",id).sort("dueDate", Sort.ASCENDING).findAll();
        //RealmQuery<Task> task1=realm.where(Task.class).equalTo("userId",id);
        if(task.size()>0)
            Log.i("dashboard","task exist");
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        if(task.size()==0)
            {   Toast.makeText(this, "Fragment to be loaded", Toast.LENGTH_SHORT).show();
            ft.add(R.id.frame_container,new Fragment2());}
        //ft.addToBackStack(null);}
        else
        {

            ft.replace(R.id.frame_container,new Fragment1(task));
        //ft.addToBackStack(null);
            }
        ft.commit();


    }

    public void createTask(View view)
    {
        Intent intent=new Intent(this, CreateTask.class);
        intent.putExtra("userId",id);
        startActivity(intent);
    }
   public void logOut(View view)
   {
       Realm realm=Realm.getDefaultInstance();
       realm.beginTransaction();
       try {
           RealmResults<Task> r = realm.where(Task.class).equalTo("userId", id).and().equalTo("checked", "true").findAll();
           if(r.size()>0)
           r.deleteAllFromRealm();
           realm.commitTransaction();
       }catch (Exception e)
       {

       }
       finally {
           realm.close();
       }

     onBackPressed();


   }

}
