package com.example.clientecommerce_mikhsangumanof;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainFoodActivity extends AppCompatActivity {
    private static final String URL_LIST_FOOD = "http://192.168.43.4/ServerEcommerceM.IkhsanGumanof/ListFood.php";
    List<Food> foodList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_food);
        recyclerView = findViewById(R.id.recyclerView10);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        foodList = new ArrayList<>();
        foodData();
    }

    private void foodData() {
        StringRequest sr = new StringRequest(Request.Method.GET, URL_LIST_FOOD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int x = 0; x < array.length(); x++) {
                        JSONObject food = array.getJSONObject(x);
                        foodList.add(new Food(
                                food.getInt("id"),
                                food.getString("nama"),
                                food.getString("keterangan"),
                                food.getDouble("harga"),
                                food.getDouble("promo"),
                                food.getString("gambar")
                        ));
                    }
                    FoodAdapter adapter = new FoodAdapter(MainFoodActivity.this, foodList);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainFoodActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(MainFoodActivity.this).add(sr);
    }
}
