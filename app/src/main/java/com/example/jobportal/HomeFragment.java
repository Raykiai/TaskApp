package com.example.jobportal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
public class HomeFragment extends Fragment  {

    //api
    private static final String URL_DATA =
            "http://192.168.8.111:8000/api/tasks";
    public static final String TAG = "HomeFragment";

    //Declare private member variables
    private RecyclerView jobRecyclerView;
    private JobsAdapter jobAdapter;
    //declare job list
    private List<JobList> jobList;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        //initialize the recycler view
        jobRecyclerView=rootView.findViewById(R.id.recycler_jobs);
        jobRecyclerView.setHasFixedSize(true);
        jobRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //initialize the developers list...This List will hold the contents of our
        // remote Json and pass it to recyclerView
        jobList = new ArrayList<>();

        //6. Initilize the new recipe adapter
        jobAdapter= new JobsAdapter(jobList, getContext());
        //7. set the adapter
        jobRecyclerView.setAdapter(jobAdapter);




        loadUrlData();


        return rootView;


    }

    public void setContentView(int fragment_home) {
    }
//Search view

    //method to fetch the url data for the RecyclerView
    private void loadUrlData() {
        final ProgressBar progressBar = new ProgressBar(getContext());

//        progressDialog.setMessage("Loading.....");
//
//        progressDialog.show();
        progressBar.setVisibility(View.VISIBLE);
      StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new
                Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("res", "Response:" + response);
                        progressBar.setVisibility(View.GONE);
                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            JSONArray array = jsonObject.getJSONArray("tasks");
                            JSONArray array = new JSONArray(response);
                            Log.i(TAG, "array: " + array.toString());
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jo = array.getJSONObject(i);
                                JobList jobs = new JobList(
                                        jo.getString("title"),

                                        jo.getString("payment"),
                                        jo.getString("location"),
                                        jo.getString("workHours"),
                                        jo.getString("description"),
                                        jo.getString("vacancies"),
                                        jo.getString("contact"),
                                        jo.getString("email")
                                );
                                //vacancies, contact, email
                                jobList.add(jobs);
                                Log.d("res", "tasks" + jobs);
                            }
                            jobAdapter = new JobsAdapter(jobList, getContext());
                            jobRecyclerView.setAdapter(jobAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(TAG, "try catch error");
                        }
                        Log.d("HomeFragment", "response: "+response);
                        Log.d("HomeFragment", "jobs list: "+jobList.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error" +
                        error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }


//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        menu.clear();
//        inflater.inflate(R.menu.search_options_menu, menu);
//        MenuItem item = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
//        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
//        MenuItemCompat.setActionView(item, searchView);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                //Do search code here
//                return true;
//            }
//        });
//
//    }
//@Override
//public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                         Bundle savedInstanceState) {
//    setHasOptionsMenu(true); // Add this!
//    return inflater.inflate(R.layout.fragment_add_app, container, false);
//}

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();

        inflater.inflate(R.menu.search_options_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Here is where we are going to implement the filter logic
                return true;
            }

        });
    }

}
