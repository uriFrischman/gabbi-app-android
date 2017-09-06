package com.frischman.uri.gabbiapp;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.*;

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
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
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
}
