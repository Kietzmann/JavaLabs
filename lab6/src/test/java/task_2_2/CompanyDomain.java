package task_2_2;

import edu.kytsmen.java.lambdas.task_2_2.*;
import org.junit.Before;

/**
 * Created by olenasyrota on 6/28/16.
 */
public abstract class CompanyDomain {
    protected final Company company = new Company("Bloggs Shed Supplies");

    @Before
    public void setUp() {
        Order.resetNextOrderNumber();
        this.setUpCustomersAndOrders();
        this.setUpSuppliers();
    }

    private void setUpSuppliers() {
        this.company.addSupplier(new Supplier("Shedtastic", new String[]{"shed", "big shed", "huge shed"}));
        this.company.addSupplier(new Supplier("Splendid Crocks", new String[]{"cup", "saucer", "bowl"}));
        this.company.addSupplier(new Supplier("Annoying Pets", new String[]{"dog", "cat", "goldfish"}));
        this.company.addSupplier(new Supplier("Gnomes 'R' Us", new String[]{"gnome"}));
        this.company.addSupplier(new Supplier("Furniture Hamlet", new String[]{"table", "sofa", "chair"}));
        this.company.addSupplier(new Supplier("SFD", new String[]{"sofa", "chair"}));
        this.company.addSupplier(new Supplier("Doxins", new String[]{"kettle", "plasma screen", "sandwich toaster"}));
    }

    private void setUpCustomersAndOrders() {
        Order fredOrder = new Order();
        fredOrder.addLineItem(new LineItem("shed", 50.0));

        fredOrder.addLineItemTimes(new LineItem("cup", 1.5), 3);

        fredOrder.addLineItemTimes(new LineItem("saucer", 1.0), 3);

        fredOrder.addLineItem(new LineItem("chair", 12.50));
        fredOrder.addLineItem(new LineItem("table", 1.0));

        Customer fred = new Customer("Fred", "London");
        fred.addOrder(fredOrder);
        this.company.addCustomer(fred);

        Order maryOrder = new Order();
        maryOrder.addLineItem(new LineItem("cat", 150.0));
        maryOrder.addLineItem(new LineItem("big shed", 500.0));

        maryOrder.addLineItemTimes(new LineItem("cup", 1.5), 4);

        maryOrder.addLineItemTimes(new LineItem("saucer", 1.5), 4);

        maryOrder.addLineItem(new LineItem("sofa", 120.0));
        maryOrder.addLineItem(new LineItem("dog", 75.0));

        Customer mary = new Customer("Mary", "Liphook");
        mary.addOrder(maryOrder);
        this.company.addCustomer(mary);

        Order billOrder1 = new Order();
        billOrder1.addLineItem(new LineItem("shed", 50.0));


        billOrder1.addLineItemTimes(new LineItem("gnome", 7.50), 43);

        Order billOrder2 = new Order();
        billOrder2.addLineItem(new LineItem("bowl", 1.25));
        billOrder2.addLineItem(new LineItem("goldfish", 0.50));

        Order billOrder3 = new Order();
        billOrder3.addLineItem(new LineItem("table", 1.0));

        Customer bill = new Customer("Bill", "London");
        bill.addOrder(billOrder1);
        bill.addOrder(billOrder2);
        bill.addOrder(billOrder3);

        this.company.addCustomer(bill);
    }
}
