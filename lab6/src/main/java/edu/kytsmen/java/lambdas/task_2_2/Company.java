package edu.kytsmen.java.lambdas.task_2_2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olenasyrota on 6/28/16.
 */
public class Company {

    private final String name;
    private final List<Customer> customers = new ArrayList<>();
    // suppliers are array based.
    private Supplier[] suppliers = new Supplier[0];

    public Company(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addCustomer(Customer aCustomer) {
        this.customers.add(aCustomer);
    }

    public List<Customer> getCustomers() {
        return this.customers;
    }

    public List<Order> getOrders() {
        return customers.stream().map(Customer::getOrders).collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);
    }

    public Customer getMostRecentCustomer() {
        return this.customers.get(customers.size() - 1);
    }

    public void addSupplier(Supplier supplier) {
        // need to replace the current array of suppliers with another, larger array
        // Of course, normally one would not use an array.

        final Supplier[] currentSuppliers = this.suppliers;
        this.suppliers = new Supplier[currentSuppliers.length + 1];
        System.arraycopy(currentSuppliers, 0, this.suppliers, 0, currentSuppliers.length);
        this.suppliers[this.suppliers.length - 1] = supplier;
    }

    public Supplier[] getSuppliers() {
        return this.suppliers;
    }

    public Customer getCustomerNamed(String name) {
        return customers.stream().filter(customer -> customer.getName().equals(name)).findAny().orElse(null);
    }
}