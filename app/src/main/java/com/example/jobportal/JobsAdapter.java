package com.example.jobportal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class JobsAdapter extends

        RecyclerView.Adapter<JobsAdapter.ViewHolder> {
    //declare JobsList private member variable
    private List<JobList> jobList;
    //context variable
    private Context mContext;
    //keys for our intents
    public static final String KEY_TITLE = "title";

    public static final String KEY_PAYMENT = "payment";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_WORKHOURS = "workHours";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_VACANCY = "vacancy";
    public static final String KEY_CONTACT = "contact";
    public static final String KEY_EMAIL = "email";



    //constructor needs to have a
    // context parameter and a list of developers which will
    // be fetched remotely
    public JobsAdapter(List<JobList>jobList,
                             Context context){
        this.jobList=jobList;
        this.mContext=context;
    }

    @NonNull
    @Override
    public JobsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new
                ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.activity_task,parent,false
        ));
    }

    //this method connects data to the view holder
    @Override
    public void onBindViewHolder(@NonNull JobsAdapter.ViewHolder holder, final int position) {
//create a variable that get the current instance of the job in the list

        final JobList currentJob=jobList.get(position);
        //populate the text views and image view with data

        holder.title.setText(currentJob.getTitle());

        holder.payment.setText(currentJob.getPayment());
        holder.location.setText(currentJob.getLocation());
        holder.workHours.setText(currentJob.getWorkHours());
//        holder.description.setText(currentJob.getDescription());
//        holder.vacancy.setText(currentJob.getVacancy());
       // holder.contact.setText(currentJob.getContact());
     //   holder.email.setText(currentJob.getEmail());

        //Use the library Picasso to load images to prevent crashing...
        // loading images is resource intensive



//        Picasso.with(mContext)
//                .load(currentJob.getAvatar_url())
//                .into(holder.avatar_url);



        //set pn click listener to handle click events
        holder.relativeLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            //ensure you override the onClick method
            public void onClick(View v){
                //create an instance of the developer list and initialize it

                JobList jobsList1 = jobList.get(position);
                //create an intent and specify the target class as profile activity


                Intent skipIntent = new Intent(v.getContext(), DisplayActivity.class);
                    //use intent EXTRA TO PASS data from Main activity to profile activity
                    skipIntent.putExtra(KEY_TITLE, jobsList1.getTitle());

                skipIntent.putExtra(KEY_PAYMENT, jobsList1.getPayment());
                    skipIntent.putExtra(KEY_LOCATION, jobsList1.getLocation());
                skipIntent.putExtra(KEY_WORKHOURS, jobsList1.getWorkHours());
                skipIntent.putExtra(KEY_DESCRIPTION, jobsList1.getDescription());
                skipIntent.putExtra(KEY_VACANCY, jobsList1.getVacancy());
                skipIntent.putExtra(KEY_CONTACT, jobsList1.getContact());
                skipIntent.putExtra(KEY_EMAIL, jobsList1.getEmail());



                v.getContext().startActivity(skipIntent);

            }

        });
    }

    @Override
    public int getItemCount() {
        //return the size of developer list

        return jobList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        //Define the view objects
        public TextView title;

        public TextView payment;
        public TextView location;
        public TextView workHours;
        public TextView description;
        public TextView vacancy;
        public TextView contact;
        public TextView email;

       // public ImageView avatar_url;
        public RelativeLayout relativeLayout;
        //the constructor
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            //initialize view objects
            title=itemView.findViewById(R.id.job_title);

            payment=itemView.findViewById(R.id.job_payment);
            location=itemView.findViewById(R.id.job_location);
            workHours=itemView.findViewById(R.id.job_workHours);


            relativeLayout=itemView.findViewById(R.id.relativeLayout);
        }
    }
}
