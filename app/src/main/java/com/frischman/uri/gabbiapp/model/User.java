package com.frischman.uri.gabbiapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.Random;

@DynamoDBTable(tableName = "Users")
public class User implements Parcelable {
    private int userId;
    private String username;
    private String firstName;
    private String lastName;
    private boolean isGabbi;
    private String email;
    private String phoneNumber;
    private String password;

    public User() {

    }

    public User(int userId, String username, String firstName, String lastName, boolean isGabbi, String email, String phoneNumber, String password) {
        this.userId = userId;
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
        this.userId = new Random().nextInt(1000);
    }

    @DynamoDBHashKey(attributeName = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isGabbi=" + isGabbi +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    protected User(Parcel in) {
        userId = in.readInt();
        username = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        isGabbi = in.readByte() != 0;
        email = in.readString();
        phoneNumber = in.readString();
        password = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(username);
        dest.writeString(lastName);
        dest.writeString(email);
        dest.writeString(phoneNumber);
        dest.writeString(password);
        dest.writeInt(isGabbi ? 1 : 0);
    }
}
