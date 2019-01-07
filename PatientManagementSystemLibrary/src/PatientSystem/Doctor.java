/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientSystem;

import java.util.Random;
import java.util.ArrayList;

/**
 *
 * @author Jonbr
 */
public class Doctor extends SystemUsers {
    private ArrayList doctorAppointments;
    private ArrayList ratings;
    private ArrayList notifications;
    
    public Doctor(String userId, String firstName, String lastName, String addressLineOne, String city, String postcode, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressLineOne = addressLineOne;
        this.city = city;
        this.postcode = postcode;
        this.password = password;
        this.doctorAppointments = new ArrayList();
        this.ratings = new ArrayList();
        this.notifications = new ArrayList();
    }
    
    protected static String newUsername() {
        Random rand = new Random();
        SystemUsers.userId = "D" + rand.nextInt(10000);
            if (registeredUsers.containsKey(SystemUsers.userId)  == true){
                newUsername();
            }
            else{
                return SystemUsers.userId;
            }
        return SystemUsers.userId;
    }

    /*Setters*/
    public void setDoctorAppointments(ArrayList doctorAppointments) {
        this.doctorAppointments = doctorAppointments;
    }
    public void setRatings(ArrayList ratings) {
        this.ratings = ratings;
    }
    public void setNotifications(ArrayList notifications) {
        this.notifications = notifications;
    }    
    
    /*Getters*/
    public ArrayList getDoctorAppointments() {
        return doctorAppointments;
    }
    public ArrayList getRatings() {
        return ratings;
    }
    public ArrayList getNotifications() {
        return notifications;
    }    
  
}