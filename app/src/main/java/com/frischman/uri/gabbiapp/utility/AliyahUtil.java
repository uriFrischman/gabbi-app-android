package com.frischman.uri.gabbiapp.utility;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.model.Aliyah;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.frischman.uri.gabbiapp.GabbiApp.getAppAmazonDynamoDBClient;
import static com.frischman.uri.gabbiapp.utility.StringUtil.getString;

public class AliyahUtil {

    private static final String TAG = "AliyahUtil";

    public static List<Aliyah> getAllAliyahsFromEvent(String eventName) {
        Map<String, String> attributeNames = new HashMap<>();
        attributeNames.put("#e", "aliyah_event");

        Map<String, AttributeValue> attributeValues = new HashMap<>();
        attributeValues.put(":v_event", new AttributeValue().withS(eventName));

        QueryRequest request = new QueryRequest()
                .withTableName(getString(R.string.dynamoDB_table_aliyahs))
                .withIndexName(getString(R.string.dynamoDB_aliyahs_index_name))
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
            String aliyahEvent = currentItem.get(getString(R.string.key_aliyah_event)).getS();
            String aliyahName = currentItem.get(getString(R.string.key_aliyah_name)).getS();
            String reading = currentItem.get(getString(R.string.key_reading)).getS();
            String aliyahNumber = currentItem.get(getString(R.string.key_aliyah_number)).getS();
            int aliyahTaken = Integer.valueOf(currentItem.get(getString(R.string.key_aliyah_taken)).getN());
            String aliyahReader = currentItem.get(getString(R.string.key_aliyah_reader)).getS();
            String numPsukim = currentItem.get(getString(R.string.key_num_psukim)).getS();
            Aliyah aliyah = new Aliyah(aliyahName, aliyahEvent, aliyahReader, aliyahTaken, numPsukim, reading, aliyahNumber);
            listOfAliyahs.add(aliyah);
        }
        return listOfAliyahs;
    }

}
