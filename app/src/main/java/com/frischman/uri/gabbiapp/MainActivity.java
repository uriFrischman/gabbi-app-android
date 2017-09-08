package com.frischman.uri.gabbiapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.frischman.uri.gabbiapp.GabbiApp.getAppAmazonDynamoDBClient;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Runnable x = new Runnable() {
            @Override
            public void run() {
                fetchEvents();

            }
        };

        Thread myThread = new Thread(x);

        myThread.start();
    }


    private ArrayList<String> fetchEvents() {
        ArrayList<String> events = new ArrayList<>();

        ScanResult result = null;

        do {
            ScanRequest req = new ScanRequest();
            req.setTableName("Events");

            if (result != null) {
                req.setExclusiveStartKey(result.getLastEvaluatedKey());
            }

            result = getAppAmazonDynamoDBClient().scan(req);

            List<Map<String, AttributeValue>> rows = result.getItems();

            for (Map<String, AttributeValue> map : rows) {
                try {
                    AttributeValue v = map.get("event_name");
                    String eventName = v.getS();
                    Log.d("main", eventName);
                    events.add(eventName);

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }


        } while (result.getLastEvaluatedKey() != null);

        return events;
    }

}
