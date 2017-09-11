package com.frischman.uri.gabbiapp.utility;


import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.model.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.frischman.uri.gabbiapp.GabbiApp.getAppAmazonDynamoDBClient;
import static com.frischman.uri.gabbiapp.utility.StringUtil.getString;

public class EventUtil {

    public static List<Event> getAllEvents() {

        List<Event> allEvents = new ArrayList<>();

        ScanRequest scanRequest = new ScanRequest("Events");

        ScanResult scanResult = getAppAmazonDynamoDBClient().scan(scanRequest);
        for (Map<String, AttributeValue> item: scanResult.getItems()) {
            String eventName = String.valueOf(item.get(getString(R.string.key_event_name)).getS());
            String eventDate = String.valueOf(item.get(getString(R.string.key_event_date)).getS());
            int numAliyahs = Integer.valueOf(item.get(getString(R.string.key_num_aliyahs)).getN());
            int numAliyahsTaken= Integer.valueOf(item.get(getString(R.string.key_num_aliyahs_taken)).getN());
            Event currentEvent = new Event(eventName, eventDate, numAliyahs, numAliyahsTaken);
            allEvents.add(currentEvent);
        }

        Collections.sort(allEvents);

        return allEvents;
    }

}
