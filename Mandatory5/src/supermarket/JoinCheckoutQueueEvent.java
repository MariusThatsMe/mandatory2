package supermarket;

import eventsim.Event;
import java.util.Arrays;

public class JoinCheckoutQueueEvent extends Event {
    SuperMarket shop;
    Customer customer;
    Checkout checkout;
    int[] sortedCheckout;
    int nextCheckoutTime;

    public JoinCheckoutQueueEvent(int time, Customer customer) {
        super(time);
        shop = customer.getShop();
        this.customer = customer;
        this.checkout = customer.getShop().checkouts[0];
        System.out.println("JoinCheckoutQueueEvent created with customer: " + customer.name);
        System.out.println("\n At time: " + time);
        //checkout.addCustomer(this.customer);
        sortedCheckout = new int[shop.NUM_CHECKOUTS];
    }

    @Override
    public Event happen() {
        sortCheckoutQueue(); //sortedQueue[0] checkouten er alltid ledig/kortest
        int shortestQueue = sortedCheckout[0]; //for ordensskyld
        
        //hvis det ikke er en ledig checkout, send ham til køen
        if(!shop.checkouts[shortestQueue].open){ 
            shop.checkouts[shortestQueue].addCustomerToQueue(customer);
            setQueueWaitDuration();
            System.out.println("JoinQueue happen: " + customer.name + "\n numProd: " + customer.numProducts);
            nextCheckoutTime = shop.checkouts[shortestQueue].getFirstInLine();
        } else
            nextCheckoutTime = getTime();
        
        if (getTime() >= nextCheckoutTime){
            checkout.removeCustomerFromQueue();
        }
        
        return new CheckoutEvent(nextCheckoutTime, customer, checkout);
    }

    @Override
    public String toString() {
        return "JoinShoppingQueueEvent    {" + customer.name + "    Time: " + getTime() +
                "    Queue duration: " + customer.queueWaitDuration + '}';
    }
    
    int[] sortCheckoutQueue(){ 
        for(int i = 0; i < sortedCheckout.length; i++){
            sortedCheckout[i] = shop.checkouts[i].calculateQueueDuration();
        }
        Arrays.sort(sortedCheckout);
        return sortedCheckout;
    }
    
    private void setQueueWaitDuration(){
        customer.queueWaitDuration = checkout.calculateQueueDuration();
    }
}