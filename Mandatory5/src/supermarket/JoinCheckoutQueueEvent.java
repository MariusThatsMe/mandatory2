package supermarket;

import eventsim.Event;
import java.util.Arrays;
import eventsim.EventSim;

import java.util.List;
import java.util.PriorityQueue;

public class JoinCheckoutQueueEvent extends Event {
    Customer customer;
    Checkout checkout;

    public JoinCheckoutQueueEvent(int time, Customer customer) {
        super(time);
        this.customer = customer;
        this.checkout = customer.getShop().checkouts[0];
        System.out.println("JoinCheckoutQueueEvent created with customer: " + customer.name);
        System.out.println("\n At time: " + time);
        //checkout.addCustomer(this.customer);
    }
/*
    private void chooseShortestQueue(){
        // Hvilken datastruktur er best for sammenligning?
        Checkout[] checkouts = customer.getShop().getCheckouts();
        //Arrays.sort(checkouts);
        checkout = checkouts[0];
        checkout.checkoutQueue.add(customer); // checkout.addCustomer(customer);
    }
*/
    private void setQueueWaitDuration(){
        customer.queueWaitDuration = checkout.calculateQueueDuration();
    }


    @Override
    public Event happen() {
        //chooseShortestQueue();

        System.out.println("JoinQueue happen: " + customer.name + "\n numProd: " + customer.numProducts);
        checkout.addCustomerToQueue(customer);
        setQueueWaitDuration();
        return new CheckoutEvent(customer.queueWaitDuration + customer.endShoppingTime + customer.checkoutDuration, customer, checkout);
    }

    @Override
    public String toString() {
        return "JoinShoppingQueueEvent    {" + customer.name + "    Time: " + getTime() +
                "    Queue duration: " + customer.queueWaitDuration + '}';
    }
}