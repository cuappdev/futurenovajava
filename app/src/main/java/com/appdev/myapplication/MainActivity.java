package com.appdev.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.appdev.futurenovajava.APIResponse;
import com.appdev.futurenovajava.Endpoint;
import com.appdev.futurenovajava.FutureNovaRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
            System.out.println(response);
        });

    }
}
