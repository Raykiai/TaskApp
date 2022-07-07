package com.example.jobportal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class PostTaskActivity extends AppCompatActivity {
    Button save;
    EditText title, location, payment, description, workHours, contact, vacancies, email, user_id;
    RequestQueue requestQueue;


    String insertUrl="http://192.168.8.111:8000/api/job";
    String showUrl="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_task);
        title=findViewById(R.id.task_title);
        location=findViewById(R.id.location);
        payment=findViewById(R.id.payment);
        contact=findViewById(R.id.phone);
        vacancies=findViewById(R.id.vacancies);

        email=findViewById(R.id.email);
        description=findViewById(R.id.description);
        workHours=findViewById(R.id.workHours);
        save=findViewById(R.id.save);
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request=new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        user_id.setText("");
                        title.setText("");
                        location.setText("");
                        payment.setText("");
                        description.setText("");
                        workHours.setText("");
                        contact.setText("");
                        vacancies.setText("");

                        email.setText("");


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String>parameters=new HashMap<String,String>();
                        parameters.put("id", title.getText().toString());

                        parameters.put("title", title.getText().toString());
                        parameters.put("location",location.getText().toString());
                        parameters.put("payment",payment.getText().toString());
                        parameters.put("description",description.getText().toString());
                        parameters.put("workHours",workHours.getText().toString());
                        parameters.put("vacancies",vacancies.getText().toString());
                        parameters.put("contact",contact.getText().toString());
                        parameters.put("email",email.getText().toString());

                        return parameters;

                    }
                };
                requestQueue.add(request);


            }

        });
    }

}
