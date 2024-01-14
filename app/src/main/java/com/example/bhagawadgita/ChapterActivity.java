package com.example.bhagawadgita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChapterActivity extends AppCompatActivity {

    RecyclerView chapterRecyclerView;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);
        chapterRecyclerView = findViewById(R.id.chapterRecyclerView);

setChapName();

    }

    void setChapName(){
        String url = "https://bhagavad-gita3.p.rapidapi.com/v2/chapters/";
        queue= Volley.newRequestQueue(getApplicationContext());
        String[] chapters = new String[18];
        String[] summary = new String[18];
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getApplicationContext(), chapters, summary);
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i=0;i<response.length();i++){
                        JSONObject object = response.getJSONObject(i);
                        chapters[i]=object.getString("chapter_number")+". "+object.getString("name")+" ("+object.getString("name_meaning")+")";
                        summary[i]=object.getString("chapter_summary");
//                        Log.d("JSONOBJECT", object.getString("chapter_number"));

                        chapterRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        chapterRecyclerView.setAdapter(adapter);

                    }
//                    Log.d("JSONARRAY", response.getString(1));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR", error.networkResponse.statusCode+"");
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

        queue.add(arrayRequest);
    }
}