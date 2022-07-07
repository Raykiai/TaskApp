package com.example.jobportal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class TasksFragment extends Fragment {

//    //api
//    private static final String URL_DATA =
//          ///  "http://192.168.8.111/api/job";
//    public static final String TAG = "TasksFragment";
//
//    //Declare private member variables
    private RecyclerView InterestsRecyclerView;

    public TasksFragment() {
        // Required empty public constructor
    }



    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tasks, container, false);


        return rootView;
    }
}
