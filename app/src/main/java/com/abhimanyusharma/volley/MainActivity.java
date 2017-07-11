package com.abhimanyusharma.volley;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button button, skip;
    EditText mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        skip = (Button) findViewById(R.id.skip);
        mobile = (EditText) findViewById(R.id.mobile);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    callVolley();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), OtpVerificationActivity.class);
                i.putExtra("Mobile", "0000000000");
                startActivity(i);
                finish();
            }
        });
    }

    private void callVolley() throws JSONException {

        //THIS IS FOR POST API REQUEST
        String URL_POST = "http://YOUR_API.com";

        JSONObject iObject = new JSONObject();

        JSONObject data_arr = new JSONObject();

        data_arr.put("mobile", mobile.getText().toString());
        iObject.put("mod", "MOBILE_REG");
        iObject.put("data_arr", data_arr);

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        final JsonObjectRequest req = new JsonObjectRequest(
                URL_POST,
                iObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    //public void onResponse(JSONObject response) {
                    public void onResponse(JSONObject jsonObject) {

                        //Now you can parse the JSON Object.

                        try {
                            //JSONObject jsonObject = new JSONObject(response);

                            //STATUS MESSAGE
                            String status_message = jsonObject.getString("status_message");
                            Toast.makeText(getApplicationContext(), status_message, Toast.LENGTH_SHORT).show();

                            //DATA
                            JSONObject data = new JSONObject(jsonObject.getString("data"));
                            //SUCCESS
                            String success = data.getString("success");
                            Toast.makeText(getApplicationContext(), success, Toast.LENGTH_SHORT).show();

                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                        Intent i = new Intent(getApplicationContext(), OtpVerificationActivity.class);
                        i.putExtra("Mobile", mobile.getText().toString());
                        startActivity(i);
                        finish();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });

        queue.add(req);

    }
}
        /*StringRequest stringRequest = new StringRequest(
                StringRequest.Method.GET,
                URL_POST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            //STATUS MESSAGE
                            String status_message = jsonObject.getString("status_message");
                            Toast.makeText(getApplicationContext(), status_message, Toast.LENGTH_SHORT).show();

                            //DATA
                            JSONObject data = new JSONObject(jsonObject.getString("data"));
                            //SUCCESS
                            String success = data.getString("success");
                            Toast.makeText(getApplicationContext(), success, Toast.LENGTH_SHORT).show();

                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }

                        Toast.makeText(getApplicationContext(), "OTP SENT SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
            };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
*/