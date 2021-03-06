/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;


import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author evenal
 */
public class Checkout {
    // amount of time per product (to scan barcode)
    public static final int PROD_DURATION = 2;
    // amount of time to pay
    public static final int PAY_DURATION = 10;
    //total time for checkout = PAY_DURATION + PROD_DURATION*customer.numProd

    SuperMarket shop;
    String name;
    LinkedList<Customer> checkoutQueue;


    public Checkout(SuperMarket shop, int i) {
        this.shop = shop;
        this.name = "Checkout" + i;
        checkoutQueue = new LinkedList<>();
    }

    public void addCustomerToQueue(Customer customer){
        System.out.println("addCustomer: " + customer.name);
        checkoutQueue.add(customer);
        System.out.println("Checkout queue: add " + checkoutQueue.size());
    }

    public void removeCustomerFromQueue(){
        checkoutQueue.remove();
    }

    public int calculateQueueDuration(){
        if (checkoutQueue.size() <= 1){
            return 0;
        }
        else {
            int queueDuration = 0;
            for (int i = 0; i < checkoutQueue.size() - 1; i++) {
                queueDuration = + checkoutQueue.get(i).numProducts * PROD_DURATION + PAY_DURATION;
            }
            return queueDuration;
        }
    }

    public int calculateCheckoutDuration(int numProducts){
        return numProducts * PROD_DURATION + PAY_DURATION;
    }
}
