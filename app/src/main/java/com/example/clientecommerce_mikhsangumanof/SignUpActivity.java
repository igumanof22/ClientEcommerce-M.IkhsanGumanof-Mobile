package com.example.clientecommerce_mikhsangumanof;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    EditText firstName, lastName, email, pass;
    Button insert;
    RequestQueue requestQueue;
    String firstNameUser, lastNameUser, emailUser, passUser;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firstName = findViewById(R.id.editFirstName);
        lastName = findViewById(R.id.editLastName);
        email = findViewById(R.id.editEmail);
        pass = findViewById(R.id.editPassword);
        insert = findViewById(R.id.btnInsert);
        requestQueue = Volley.newRequestQueue(SignUpActivity.this);
        progressDialog = new ProgressDialog(SignUpActivity.this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String URL_SIGNUP = "http://192.168.43.4/ServerEcommerceM.IkhsanGumanof/signup.php";
                progressDialog.setMessage("Silakan tunggu, input data ke server sedang berlangsung");
                progressDialog.show();
                firstNameUser = firstName.getText().toString().trim();
                lastNameUser = lastName.getText().toString().trim();
                emailUser = email.getText().toString().trim();
                passUser = pass.getText().toString().trim();
                clearData();

                StringRequest sr = new StringRequest(Request.Method.POST, URL_SIGNUP, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Toast.makeText(SignUpActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(SignUpActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("first_name", firstNameUser);
                        params.put("last_name", lastNameUser);
                        params.put("email", emailUser);
                        params.put("password", passUser);
                        return params;
                    }
                };
                requestQueue.add(sr);
            }
        });
    }

    private void clearData() {
        firstName.setText("");
        lastName.setText("");
        email.setText("");
        pass.setText("");
    }
}
