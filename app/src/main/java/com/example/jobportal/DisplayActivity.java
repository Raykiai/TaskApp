package com.example.jobportal;

import android.Manifest;
import android.app.MediaRouteButton;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.text.CaseMap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.PagerTitleStrip;

import com.example.jobapp.JobsAdapter;
import com.example.jobportal.R;
import com.squareup.picasso.Picasso;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//
//        //set up notitle
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //set up full screen
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.task_display);

        //initialize the views using the ID'S you set
        TextView titleTextView = findViewById(R.id.display_title);
        TextView paymentTextView = findViewById(R.id.display_payment);
        TextView locationTextView = findViewById(R.id.display_location);
        TextView workHoursTextView = findViewById(R.id.display_workHours);
        TextView descriptionTextView = findViewById(R.id.display_description);
        TextView vacancyTextView = findViewById(R.id.display_vacancy);
        TextView contactTextView = findViewById(R.id.display_contact);

        TextView emailTextView = findViewById(R.id.display_email);


        //getting the intents sent from job adapter
        Intent intent = getIntent();
        final String Title = intent.getStringExtra(JobsAdapter.KEY_TITLE);

        final String Payment = intent.getStringExtra(JobsAdapter.KEY_PAYMENT);
        final String Location = intent.getStringExtra(JobsAdapter.KEY_LOCATION);
        final String WorkHours = intent.getStringExtra(JobsAdapter.KEY_WORKHOURS);
        final String Description = intent.getStringExtra(JobsAdapter.KEY_DESCRIPTION);
        final String Vacancy = intent.getStringExtra(JobsAdapter.KEY_VACANCY);
        final String Contact = intent.getStringExtra(JobsAdapter.KEY_CONTACT);
        final String Email = intent.getStringExtra(JobsAdapter.KEY_EMAIL);



        //
//
//                title
//
//                payment
//                        location
//                workHours
//                        description
//                vacancies
//                        contact
//                email

        titleTextView.setText(Title);

        paymentTextView.setText(Payment);
        locationTextView.setText(Location);
        workHoursTextView.setText(WorkHours);
        descriptionTextView.setText(Description);
        vacancyTextView.setText(Vacancy);
        contactTextView.setText(Contact);
        emailTextView.setText(Email);


    }


    public void submitApplication(View view) {

    }
}
