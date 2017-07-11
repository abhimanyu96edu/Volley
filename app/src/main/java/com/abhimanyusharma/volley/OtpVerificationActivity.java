package com.abhimanyusharma.volley;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class OtpVerificationActivity extends AppCompatActivity {

    Button otp_verify, skip;
    EditText otp;
    TextView mobile;
    String mobile_number, otp_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        otp_verify = (Button) findViewById(R.id.verify_otp);
        skip = (Button) findViewById(R.id.skip);
        otp = (EditText) findViewById(R.id.otp);
        mobile = (TextView) findViewById(R.id.mobile);

        mobile_number = getIntent().getExtras().getString("Mobile");
        mobile.setText("Your Mobile: " + mobile_number);

        otp_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                otp_value = otp.getText().toString();
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

                Intent i = new Intent(getApplicationContext(), HomeScreenActivity.class);
                i.putExtra("Mobile", "1111111111");
                startActivity(i);
                finish();
            }
        });
    }

    private void callVolley() throws JSONException {

        String URL_POST = "http://YOUR_API.com";

        JSONObject iObject = new JSONObject();

        JSONObject data_arr = new JSONObject();

        data_arr.put("mobile", mobile_number);
        data_arr.put("otp", otp_value);

        iObject.put("mod", "OTP_VERIFY");
        iObject.put("data_arr", data_arr);

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        final JsonObjectRequest req = new JsonObjectRequest(
                URL_POST,
                iObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            //STATUS MESSAGE
                            String status_message = response.getString("status_message");
                            Toast.makeText(getApplicationContext(), status_message, Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getApplicationContext(), HomeScreenActivity.class));

                            Intent i = new Intent(getApplicationContext(), HomeScreenActivity.class);
                            i.putExtra("Mobile", mobile_number);
                            startActivity(i);
                            finish();

                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        queue.add(req);
    }
}
