package com.example.mytodoapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Date;

public class TaskDetails extends Fragment {
  String mtd,mtn,md;
  TextView mtname,mdetails,mdate;
    TaskDetails(String tn, String td, Date d)
    {
        mtd=td;
        mtn=tn;
        md=d.toString();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);
        View v=inflater.inflate(R.layout.taskdetails, container, false);
        mtname=v.findViewById(R.id.tname1);
        mdetails=v.findViewById(R.id.tdetails1);
        mdate=v.findViewById(R.id.tdate1);
        mtname.setText(mtn);
        mdetails.setText(mtd);
        mdate.setText(md);
        return v;

    }
}
