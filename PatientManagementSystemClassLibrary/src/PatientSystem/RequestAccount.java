/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientSystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.io.*;

/**
 *
 * @author Jonbr
 */
public class RequestAccount implements Serializable{
    
    private String firstName;
    private String lastName;
    private String addressLineOne;
    private String city;
    private String postcode;
    private String password;
    private int age;
    private String gender;
    private static ArrayList<RequestAccount> requestAccount = new ArrayList<RequestAccount>();

    private RequestAccount(String firstName, String lastName, String addressLineOne, String city, String postcode, String password, int age, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressLineOne = addressLineOne;
        this.city = city;
        this.postcode = postcode;
        this.password = password;
        this.age = age;
        this.gender = gender;
    }
    
    public void requestAccount(String firstName, String lastName, String addressLineOne, String city, String postcode, String password, int age, String gender){
        RequestAccount account = new RequestAccount(firstName, lastName, addressLineOne, city, postcode, password, age, gender);
        requestAccount.add(account);
        String notification = "new account requested";
        Notifications notifications = new Notifications(notification);
        for(Secretary i : UsersSingleton.getInstance().getListOfSecretarys()) {
            i.getNotifications().add(notifications);
            Secretary.write(i);
        }
        write(account);
        Notifications.write(notifications);
    }
    
    public void requestTermination(Patient patient) {
        String notification = patient.getUserId() + patient.getFirstName() + patient.getLastName() + " Requested account termination";
        Notifications notifications = new Notifications(notification);
        for(Secretary i : UsersSingleton.getInstance().getListOfSecretarys()) {
            i.getNotifications().add(notifications);
            Secretary.write(i);            
        }
        Notifications.write(notifications);
    }

    //Serialization
    public static void write(RequestAccount account) {
        Serialiser.writeObject(account, "request_account_file.ser");
    }
    public static void read() {
        RequestAccount account = (RequestAccount) Serialiser.readObject("request_account_file.ser");
        requestAccount.add(account);
    }      
    
    /*Setters*/
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public static void setRequestAccount(ArrayList<RequestAccount> requestAccount) {
        RequestAccount.requestAccount = requestAccount;
    }
    

    /*Getters*/
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getAddressLineOne() {
        return addressLineOne;
    }
    public String getCity() {
        return city;
    }
    public String getPostcode() {
        return postcode;
    }
    public String getPassword() {
        return password;
    }
    public int getAge() {
        return age;
    }
    public String getGender() {
        return gender;
    }
    public static ArrayList<RequestAccount> getRequestAccount() {
        return requestAccount;
    }
}
