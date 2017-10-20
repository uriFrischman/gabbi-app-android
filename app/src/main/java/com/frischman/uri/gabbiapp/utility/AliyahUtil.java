package com.frischman.uri.gabbiapp.utility;

import android.util.Log;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateItemResult;
import com.frischman.uri.gabbiapp.model.Aliyah;
import com.frischman.uri.gabbiapp.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.frischman.uri.gabbiapp.GabbiApp.getAppAmazonDynamoDBClient;
import static com.frischman.uri.gabbiapp.utility.UserUtil.getUserWithId;

public class AliyahUtil {

    private static final String TAG = "AliyahUtil";

    public static List<Aliyah> getAllAliyahsFromEvent(String eventName) {
        Map<String, String> attributeNames = new HashMap<>();
        attributeNames.put("#e", "aliyah_event");

        Map<String, AttributeValue> attributeValues = new HashMap<>();
        attributeValues.put(":v_event", new AttributeValue().withS(eventName));

        QueryRequest request = new QueryRequest()
                .withTableName("Aliyahs")
                .withIndexName("aliyah_event-aliyah_name-index")
                .withKeyConditionExpression("#e = :v_event")
                .withExpressionAttributeNames(attributeNames)
                .withExpressionAttributeValues(attributeValues);

        QueryResult result = getAppAmazonDynamoDBClient().query(request);

        return getListOfAliyahsFromQueryResult(result);
    }

    private static List<Aliyah> getListOfAliyahsFromQueryResult(QueryResult result) {
        ArrayList<Aliyah> listOfAliyahs = new ArrayList<>();
        Iterator<Map<String, AttributeValue>> iter = result.getItems().iterator();
        while (iter.hasNext()) {
            Map<String, AttributeValue> currentItem = iter.next();
            String aliyahEvent = currentItem.get("aliyah_event").getS();
            String aliyahName = currentItem.get("aliyah_name").getS();
            String reading = currentItem.get("reading").getS();
            String aliyahNumber = currentItem.get("aliyah_number").getS();
            int aliyahTaken = Integer.valueOf(currentItem.get("aliyah_taken").getN());
            String aliyahReader = currentItem.get("aliyah_reader").getS();
            String numPsukim = currentItem.get("num_psukim").getS();
            Aliyah aliyah = new Aliyah(aliyahName, aliyahEvent, aliyahReader, aliyahTaken, numPsukim, reading, aliyahNumber);
            listOfAliyahs.add(aliyah);
        }
        return listOfAliyahs;
    }

    public static boolean isAliyahTaken(Aliyah aliyah) {
        return aliyah.getAliyahTaken() == 0 ? false : true;
    }

    public static void claimAliyah(final User user, final Aliyah aliyah) {
        Runnable x = new Runnable() {
            @Override
            public void run() {
                User aliyahClamingUser = getUserWithId(user.getUserId());
                if (aliyahClamingUser!= null) {
                    Log.d(TAG, "run: The id of the current user is " + aliyahClamingUser.getFirstName());

                    HashMap<String, AttributeValue> key = new HashMap<>();
                    key.put("aliyah_name", new AttributeValue().withS(aliyah.getAliyahName()));

                    HashMap<String, AttributeValue> expressionAttributeValues = new HashMap<>();
                    expressionAttributeValues.put(":aliyah_reader", new AttributeValue().withS(user.getFirstName() + " " + user.getLastName()));
                    expressionAttributeValues.put(":aliyah_taken", new AttributeValue().withN("1"));

                    UpdateItemRequest updateItemRequest = new UpdateItemRequest()
                            .withTableName("Aliyahs")
                            .withKey(key)
                            .withUpdateExpression("set aliyah_taken = :aliyah_taken, aliyah_reader = :aliyah_reader")
                            .withExpressionAttributeValues(expressionAttributeValues)
                            .withReturnValues(ReturnValue.ALL_NEW);

                    UpdateItemResult result = getAppAmazonDynamoDBClient().updateItem(updateItemRequest);
                    Log.d(TAG, "run: " + result.getAttributes().toString());

                    
                } else {
                    Log.d(TAG, "run: The user does not exist in the database");
                }
            }
        };
        Thread a = new Thread(x);
        a.start();
    }
}
