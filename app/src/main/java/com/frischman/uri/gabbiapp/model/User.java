package com.frischman.uri.gabbiapp.model;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

@DynamoDBTable(tableName = "Users")
public class User {
    private int user_id;
    private String firstName;
    private String lastName;
    private boolean isGabbi;

    public User() {

    }

    public User(int user_id, String firstName, String lastName, boolean isGabbi) {
        this.user_id = user_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isGabbi = isGabbi;
    }

    @DynamoDBHashKey(attributeName = "user_id")
    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    @DynamoDBAttribute(attributeName = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @DynamoDBAttribute(attributeName = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @DynamoDBAttribute(attributeName = "is_gabbi")
    public boolean getIsGabbi() {
        return isGabbi;
    }

    public void setIsGabbi(boolean isGabbi) {
        this.isGabbi = isGabbi;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isGabbi=" + isGabbi +
                '}';
    }
}
