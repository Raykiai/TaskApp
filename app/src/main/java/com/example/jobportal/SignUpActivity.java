package com.example.jobportal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.jobportal.classes.FreelanceServiceManager.loginPreference;
import static java.lang.Integer.parseInt;

public class SignUpActivity extends AppCompatActivity {

    private EditText mFirstName;
    private EditText mLastName;
    private EditText mEmailAdd;
    private EditText mPhoneNo;
    private EditText mLocation;
    private EditText mCountry;
    private EditText mPassword;
    private EditText mPasswordConfirm;
    private TextView mLoginText;
    private Button mBtnSignUp;
    private String mUsername;
    private int mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mFirstName = findViewById(R.id.FirstName);
        mLastName = findViewById(R.id.LastName);
        mEmailAdd = findViewById(R.id.username);
        mPhoneNo = findViewById(R.id.phone);
        mLocation = findViewById(R.id.location);
        mCountry = findViewById(R.id.country);
        mPassword = findViewById(R.id.password);
        mPasswordConfirm = findViewById(R.id.confirmPassword);
        mLoginText = findViewById(R.id.textView_login);
        mBtnSignUp = findViewById(R.id.btn_signUp);
        login();
        signUp();
    }

    private void signUp() {
        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                1. Check if the inputs are empty
                boolean isValid = validateInput();
                if(!isValid){
                    return;
                }
//                2.Make a post request using volley
                String url = "http://192.168.8.111:8000/api/register";
                RequestQueue queue = Volley.newRequestQueue(SignupActivity.this);
                final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this);
                progressDialog.setMessage("Loading....");
                progressDialog.show();
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // response
                                progressDialog.dismiss();
                                Toast.makeText(SignupActivity.this, " User created successfully", Toast.LENGTH_SHORT).show();
                                Log.d("Response", response);
                                storePreference(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                                Log.e("Response error","" + error.getMessage() );
                                Toast.makeText(SignupActivity.this, "Error connecting to the server.Please ensure you are connected to the internet", Toast.LENGTH_SHORT).show();
//                                Toast.makeText(SignupActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }){
                    @Override
                    protected Map getParams()
                    {
                        Map params = new HashMap();
                        final String FirstName = mFirstName.getText().toString();
                        final String LastName = mLastName.getText().toString();
                        final String email = mEmailAdd.getText().toString();
                        final String phone = mPhoneNo.getText().toString();
                        final String location = mLocation.getText().toString();
                        final String country = mCountry.getText().toString();
                        final String password = mPassword.getText().toString();

                        params.put("FirstName", FirstName);
                        params.put("LastName", LastName);
                        params.put("email", email);
                        params.put("phone", phone);
                        params.put("location", location);
                        params.put("country", country);
                        params.put("password", password);

                        return params;
                    }
                };
                queue.add(postRequest);
            }
        });
    }

    private void storePreference(String response) {
        final String DATA = "user";
        final String FNAME="FirstName";
        final String ID="id";



        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject userJsonObject = jsonObject.getJSONObject(DATA);
            mUsername = userJsonObject.getString(FNAME);
            mId = userJsonObject.getInt(ID);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("SignUpStorePreference", e.getMessage());
        }
        SharedPreferences preferences = getSharedPreferences(loginPreference, MODE_PRIVATE);
        SharedPreferences.Editor editor =preferences.edit();
        editor.putString("username", mUsername);//Username gottenn from the api
        editor.putInt("id", mId );//Id gotten from the api
        editor.putBoolean("IsUserLoggedIn", true);
        editor.apply();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private boolean validateInput() {
        if(mFirstName.getText().toString().isEmpty()
                || mFirstName.getText().toString().isEmpty() ||
                mLastName.getText().toString().isEmpty() ||
                mEmailAdd.getText().toString().isEmpty() ||
                mPhoneNo.getText().toString().isEmpty() ||
                mLocation.getText().toString().isEmpty() ||
                mCountry.getText().toString().isEmpty() ||
                mPassword.getText().toString().isEmpty() ||
                mPasswordConfirm.getText().toString().isEmpty()){
            Toast.makeText(this, "Please make sure all input fields are filled", Toast.LENGTH_LONG).show();
            return false;
        }else if(!(mPassword.getText().toString().equals(mPasswordConfirm.getText().toString()))){


            Toast.makeText(this, "The passwords provided do not match", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;

    }

    private void login() {
        mLoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, HomeFragment.class);
                startActivity(intent);
            }
        });
    }
}
