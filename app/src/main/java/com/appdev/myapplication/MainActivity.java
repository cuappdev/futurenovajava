package com.appdev.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.appdev.futurenovajava.APIResponse;
import com.appdev.futurenovajava.Endpoint;
import com.appdev.futurenovajava.FutureNovaRequest;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Set once and then done
        Endpoint.Config config = new Endpoint.Config();
        config.scheme = Optional.of("https");
        config.host = Optional.of("transit-backend.cornellappdev.com");
        config.commonPath = Optional.of("/api/v1");
        Endpoint.config = config;

        Endpoint allStopsEndpoint = new Endpoint().path("allstops").method(Endpoint.Method.GET);

        FutureNovaRequest.make(Stops[].class, allStopsEndpoint).thenAccept((APIResponse<Stops[]> response) -> {
            System.out.println(response.getSuccess());
            for (Stops stop : response.getData()) {
                System.out.println(stop.getName());
            }
        });


        search("Stat");

    }

    protected void search(String query) {
        Map<String, String> map = new HashMap<String, String>();

        map.put("Content-Type", "application/json");

        JSONObject searchJSON = new JSONObject();
        try {
            searchJSON.put("query", query);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.get("application/json; charset=utf-8"), searchJSON.toString());

        RequestBody form = new FormBody.Builder().add("query", query).build();
        Endpoint searchEndpoint = new Endpoint()
                .path("search")
                .body(Optional.of(form))
                .headers(map)
                .method(Endpoint.Method.POST);

        FutureNovaRequest.make(Place[].class, searchEndpoint).thenAccept(response -> {
            System.out.println(response.getSuccess());
        });
    }
}
