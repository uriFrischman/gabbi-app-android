package com.frischman.uri.gabbiapp.model;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

@DynamoDBTable(tableName = "Users")
public class User {
    private int user_id;
    private String username;
    private String firstName;
    private String lastName;
    private boolean isGabbi;
    public String email;
    public String phoneNumber;
    public String password;

    public User() {

    }

    public User(int user_id, String username, String firstName, String lastName, boolean isGabbi, String email, String phoneNumber, String password) {
        this.user_id = user_id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isGabbi = isGabbi;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public User(String username, String firstName, String lastName, boolean isGabbi, String email, String phoneNumber, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isGabbi = isGabbi;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
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

    @DynamoDBAttribute(attributeName = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @DynamoDBAttribute(attributeName = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @DynamoDBAttribute(attributeName = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @DynamoDBAttribute(attributeName = "user_name")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @DynamoDBAttribute(attributeName = "is_gabbi")
    public boolean isGabbi() {
        return isGabbi;
    }

    public void setGabbi(boolean gabbi) {
        isGabbi = gabbi;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isGabbi=" + isGabbi +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
