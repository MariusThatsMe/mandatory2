/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

import eventsim.Event;
import eventsim.EventSim;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author evenal
 */
public class SuperMarket {

    public static void main(String[] arts) {
        SuperMarket supern = new SuperMarket();
        supern.startSim();

    }

    public static final int NUM_CHECKOUTS = 1;
    public static final int NUM_CUSTOMERS = 10;

    Checkout[] checkouts;
    List<Customer> customers;
    List<Event> init;


    public SuperMarket() {
        checkouts = new Checkout[NUM_CHECKOUTS];
        for (int i = 0; i < NUM_CHECKOUTS; i++) {
            checkouts[i] = new Checkout(this, i);
        }
        customers = new ArrayList<>();
        init = new ArrayList<Event>();
        for (int i = 0; i < NUM_CUSTOMERS; i++) {
            Customer c = new Customer(this, i);
            init.add(new BeginShoppingEvent(c));
            customers.add(c);
        }
    }


    public void startSim() {
            EventSim sim = EventSim.getInstance();
            sim.setup(init);
            sim.run();
            printStats();
    }

    public Checkout[] getCheckouts() {
        return checkouts;
    }

    public void printStats(){
        for (Customer customer : customers) {
            System.out.println("\n" + customer.name);
            System.out.println("numProducts:      " + customer.numProducts);
            System.out.println("beginShoppingTime:" + customer.beginShoppingTime);
            System.out.println("shoppingDuration: " + customer.shoppingDuration);
            System.out.println("endShoppingTime:  " + customer.endShoppingTime);
            System.out.println("queueWaitDuration:" + customer.queueWaitDuration);
            System.out.println("checkoutDuration: " + customer.checkoutDuration);
            System.out.println("checkoutTime:     " + customer.checkoutTime);
            System.out.println("leaveTime:        " + customer.leaveTime);
        }
    }
}
