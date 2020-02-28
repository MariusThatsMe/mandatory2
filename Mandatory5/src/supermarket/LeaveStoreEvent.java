package supermarket;

import eventsim.Event;

public class LeaveStoreEvent extends Event {
    Customer customer;

    public LeaveStoreEvent(int time, Customer customer) {
        super(time);
        this.customer = customer;
        System.out.println("LeaveStoreEvent created with customer: " + customer.name);
        System.out.println("\n At time: " + time);
    }

    @Override
    public Event happen() {
        customer.leaveTime = getTime();
        //System.out.println("\n Customer: " + customer.name + "\n Products: " + customer.numProducts + "\n BeginTime: " + customer.beginShoppingTime);
        //System.out.println("\n Shoppingduration: " + customer.shoppingDuration + "\n Endtime: " + customer.endShoppingTime + "\n Checkout duration: " + customer.checkoutDuration + "\n Checkout time: " + customer.checkoutTime + "\n Leavetime" + customer.leaveTime);
        return null;
    }

    @Override
    public String toString() {
        return "LeaveStoreEvent    {" + customer.name + "    Time: " + getTime() +
                "    Leave time: " + customer.leaveTime + '}';
    }
}
