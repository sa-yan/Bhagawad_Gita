package com.example.bhagawadgita;

import static com.example.bhagawadgita.MainActivity.EXTRA_NAME_1;
import static com.example.bhagawadgita.MainActivity.EXTRA_NAME_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SlokActivity extends AppCompatActivity {

    TextView slok, meaning, descp;
    RequestQueue queue;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slok);
        slok = findViewById(R.id.slok);
        meaning=findViewById(R.id.meaning);
        descp=findViewById(R.id.description);
        Intent intent = getIntent();
        int chapterNo = intent.getIntExtra(EXTRA_NAME_1, 0);
        int verseNo = intent.getIntExtra(EXTRA_NAME_2, 0);

        slokData(chapterNo, verseNo);

    }

    void slokData(int chapter, int verse){
        url ="https://bhagavad-gita3.p.rapidapi.com/v2/chapters/"+chapter+"/verses/"+verse+"/";
        queue= Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    slok.setText(response.getString("text"));

                    JSONArray jsonArray = response.getJSONArray("translations");
                    JSONObject object1=jsonArray.getJSONObject(1);
                    String meaningSlok = object1.getString("description");
                    meaning.setText(meaningSlok);

                    JSONArray jsonArray2 = response.getJSONArray("commentaries");
                    JSONObject object2=jsonArray2.getJSONObject(13);
                    String descpSlok = object2.getString("description");
                    descp.setText(descpSlok);


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("X-RapidAPI-Key","b1b0aebdc7msh50b9538ca18ec3ep1c171bjsnaf89b745bec0");
                headers.put("X-RapidAPI-Host","bhagavad-gita3.p.rapidapi.com");
                return headers;
            }
        };

        queue.add(objectRequest);
    }
}