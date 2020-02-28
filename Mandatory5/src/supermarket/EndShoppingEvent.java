/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

import eventsim.Event;
import eventsim.EventSim;


/**
 * A customer finishes shopping and heads for the checkout with the shortest
 * queue
 *
 * @author evenal
 */
public class EndShoppingEvent extends Event {
    Customer customer;
    int time;


    public EndShoppingEvent(int time, Customer customer) {
        super(EventSim.getClock() + customer.shoppingDuration);
        this.customer = customer;
        this.time = time;
        System.out.println("EndShoppingEvent created with customer: " + customer.name);
        System.out.println("\n At time: " + time);
    }


    @Override
    public Event happen() {
        customer.leaveTime = customer.checkoutTime + customer.checkoutDuration;
        return null;
    }

    @Override
    public String toString() {
        return "EndShoppingEvent    {" + customer.name + "    Time: " + getTime() +
                "    End shopping time: " + customer.endShoppingTime + '}';
    }

}
