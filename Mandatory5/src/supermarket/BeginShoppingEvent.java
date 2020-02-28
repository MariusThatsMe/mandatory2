/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

import eventsim.Event;


/**
 * A customer enters the shop
 *
 * @author evenal
 */
public class BeginShoppingEvent extends Event {
    Customer customer;


    public BeginShoppingEvent(Customer customer) {
        super(customer.beginShoppingTime);
        this.customer = customer;
        System.out.println("BeginShoppingEvent: " + customer.name + " " + getTime());
    }

    @Override
    public Event happen() {
        if(customer.numProducts == 0){
            customer.leaveTime = customer.endShoppingTime;
            return new LeaveStoreEvent(customer.leaveTime, customer);
        }
        return new JoinCheckoutQueueEvent(customer.endShoppingTime, customer);
    }

    @Override
    public String toString() {
        return "BeginShoppingEvent    {" + customer.name + "    Time: " + getTime() +
                "    Shopping duration: " + customer.shoppingDuration + '}';
    }
}