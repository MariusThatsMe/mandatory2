package supermarket;

import eventsim.Event;

public class CheckoutEvent extends Event {
    Customer customer;
    Checkout checkout;
    public CheckoutEvent(int time, Customer customer, Checkout checkout) {
        super(time);
        this.customer = customer;
        this.checkout = checkout;
    }

    @Override
    public Event happen() {
        setCheckoutDuration();
        setCheckoutTime(getTime() + customer.checkoutDuration);
        this.checkout.removeCustomerFromQueue();
        System.out.println("Checkout queue: remove "+ checkout.checkoutQueue.size());
        return new LeaveStoreEvent(customer.checkoutTime, customer);
    }

    private void setCheckoutDuration(){
        customer.checkoutDuration = checkout.calculateCheckoutDuration(customer.numProducts);
    }

    private void setCheckoutTime(int checkoutTime){
        customer.checkoutTime = checkoutTime;
    }

    @Override
        public String toString() {
            return "CheckoutEvent    {" + customer.name + "    Time: " + getTime() +
                    "    Checkout duration: " + customer.checkoutDuration + '}';
        }
}
