package com.example.mytodoapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
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

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

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
        Realm realm=Realm.getDefaultInstance();
        RealmResults<Task> task=realm.where(Task.class).equalTo("userId",id).sort("dueDate", Sort.ASCENDING).findAll();
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
            RealmResults<Task> t1=realm.where(Task.class).equalTo("userId",id).and().equalTo("checked","true").findAll();
            if(t1.size()>0)
            {   realm.beginTransaction();

                        t1.deleteAllFromRealm();
                realm.commitTransaction();
                task = realm.where(Task.class).equalTo("userId", id).findAll();
            }

            ft.add(R.id.frame_container,new Fragment1(task));


        }
        ft.commit();
        realm.close();

    }

    @Override
    public void onResume() {
        super.onResume();
        Realm realm=Realm.getDefaultInstance();
        RealmResults<Task> task=realm.where(Task.class).equalTo("userId",id).sort("dueDate", Sort.ASCENDING).findAll();
        if(task.size()>0)
            Log.i("dashboard","task exist");
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        if(task.size()==0)
            {   Toast.makeText(this, "Fragment to be loaded", Toast.LENGTH_SHORT).show();
            ft.add(R.id.frame_container,new Fragment2());}
        else
        {
          /*  long millis=System.currentTimeMillis();
            java.sql.Date date=new java.sql.Date(millis);
            //System.out.println(date);
           // String s1=date.toString();
           // String s2=""+s1.charAt(8)+ s1.charAt(9);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, 1); //minus number would decrement the days
            Date date1= cal.getTime();*/
         /*   Date date2=java.util.Calendar.getInstance().getTime();
            String s2=new SimpleDateFormat("dd/mm/yyyy").format(date2);
            //String s1[]=date1.toString().split(" ");
            String s1=""+ s2.charAt(0)+s2.charAt(1);
            int j=Integer.parseInt(s1);
           // int k=
            Log.i("current date: ",s2);
            RealmResults<Task> t1=realm.where(Task.class).equalTo("userId",id).and().equalTo("checked","true").findAll();
            if(t1.size()>0)
            {   realm.beginTransaction();
                for(Task t2:t1){
                Log.i("task date inside : ",t2.getDueDate().toString());
                String s[]=t2.getDueDate().toString().split(" ");
                int i=Integer.parseInt(s[2]);
                Log.i("inside","task : "+i+" current: "+j);
                if(j>=(i+1))
                    t2.deleteFromRealm();
            }
                realm.commitTransaction();
                task = realm.where(Task.class).equalTo("userId", id).findAll();
            }
*/
            ft.replace(R.id.frame_container,new Fragment1(task));

            //ft.addToBackStack(null);
            }
        ft.commit();
   realm.close();

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
          realm.cancelTransaction();
       }
       finally {
           realm.close();
       }

     onBackPressed();


   }
   public void allDone(View view) {
       Realm realm = Realm.getDefaultInstance();
       RealmResults<Task> t = realm.where(Task.class).equalTo("userId", id).and().equalTo("checked", "false").findAll();

       try {
           realm.beginTransaction();
           for (Task t1 : t) {
               t1.setChecked("true");
           }
           realm.commitTransaction();

       } catch (Exception e) {
           realm.cancelTransaction();
       } finally {
           realm.close();
       }
     t=realm.where(Task.class).equalTo("userId", id).findAll();
       if(t.size()>0)
         getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new Fragment1(t)).commit();
   }

}
