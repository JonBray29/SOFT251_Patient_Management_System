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
public class Orders implements Serializable{
    private Medicine medicine;
    private int quantity;
    private static ArrayList<Orders> listOfOrders = new ArrayList<Orders>();
    
    private Orders(Medicine medicine, int quantity) {
        this.medicine = medicine;
        this.quantity = quantity;
    }

    public void createOrders(){
        Orders order = new Orders(medicine, quantity);
        listOfOrders.add(order);
        write();

    }
    
    public void deliverOrder(Orders order, int quantity) {
        //Simulate a delivery, updates the stock using existing update method.
        
        for(Orders o : listOfOrders) {
            if(o.getMedicine() == order.getMedicine()) {
                Medicine m = o.getMedicine();
                Stock.updateStock("add", m, quantity);
            }
            listOfOrders.remove(o);
            write();
        }
    }
    
    //Serialization
    public static void write() {
        try {
            FileOutputStream fileWrite = new FileOutputStream("order_file.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileWrite);
            out.writeObject(listOfOrders);
            out.close();
            fileWrite.close();
         } catch (IOException i) {
            i.printStackTrace();
         }
    }

    public static void read(){
        ArrayList<Orders> order = new ArrayList<>();
        try {
            FileInputStream fileRead = new FileInputStream("order_file.ser");
            ObjectInputStream in = new ObjectInputStream(fileRead);
            order = (ArrayList<Orders>)in.readObject();       
            in.close();
            fileRead.close();
            for(Orders o : order) {
                listOfOrders.add(o);
            }
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
    }
    
    /*Setters*/
    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public static void setListOfOrders(ArrayList<Orders> listOfOrders) {
        Orders.listOfOrders = listOfOrders;
    }

    /*getters*/
    public Medicine getMedicine() {
        return medicine;
    }
    public int getQuantity() {
        return quantity;
    }
    public static ArrayList<Orders> getListOfOrders() {
        return listOfOrders;
    }   
    
    
    
}
