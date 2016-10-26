package task_2_2;

import edu.kytsmen.java.lambdas.task_2_2.Customer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * Created by olenasyrota on 6/28/16.
 */
public class Exercise2OrdersTest extends CompanyDomain {
    /**
     * Aggregate the total order values by city.
     */
    @Test
    public void totalOrderValuesByCity() {
        // implement customer.getTotalOrderValue() for this exercise;
// TODO refactor this
        Map<String, Double> map = new HashMap<>();
        company.getCustomers()
                .stream()
                .forEach(customerC -> map.put(customerC.getCity(), company.getCustomers().stream()
                        .filter(customer -> customer.getCity().equals(customerC.getCity()))
                        .mapToDouble(Customer::getTotalOrderValue)
                        .sum()));

//        map.put("London", company.getCustomers().stream()
//                .filter(customer -> customer.getCity() == "London")
//                .map(customer -> customer.getTotalOrderValue())
//                .reduce(0.0, Double::sum)
//        );
//        map.put("Liphook", company.getCustomers().stream()
//                .filter(customer -> customer.getCity() == "Liphook")
//                .map(customer -> customer.getTotalOrderValue())
//                .reduce(0.0, Double::sum)
//        );

        assertEquals(2, map.size());
        assertEquals(446.25, map.get("London"), 0.0);
        assertEquals(857.0, map.get("Liphook"), 0.0);
    }

    /**
     * Extra credit. Create a map where the values are customers and the key is the price of
     * the most expensive item that the customer ordered.
     */

    @Test
    public void mostExpensiveItem() {
        Map<Double, List<Customer>> map = new HashMap<>();
//        company
//                .getCustomers()
//                .stream()
//                .forEach(customer -> map.put(company
//                        .getCustomers()
//                        .stream()
//                        .filter(c -> c.getName().equals(customer.getName()))
//                        .map(customer1 -> customer.getOrders())
//                        .collect(toList()),);
//        map.put(company
//                .getCustomers()
//                .stream()
//                .filter(customer ->), "Fred")


//        company.getCustomers()
//                .stream()
//                .forEach(customer -> map.put(
//                        company.getCustomers()
//                                .stream()
//                                .filter(c -> c.getName().equals(customer.getName()))
//                                .collect(Collectors.mapping(Customer::getMostExpensiveItemValue))
//                                ., customer.getName()
//                ));
//        List<Customer> valuesList;
//        for (Customer cust : company.getCustomers()) {
//            valuesList = new ArrayList<>();
//            double price = cust.getOrders().stream().map(order -> order.getMostExpensiveItemValue()).max(Comparator.naturalOrder()).get();
//            if (map.keySet().contains(price)) {
//                valuesList = map.get(price);
//                valuesList.add(cust);
//                map.put(price, valuesList);
//            } else {
//                valuesList.add(cust);
//                map.put(price, valuesList);
//            }
//
//        }
        map = company.getCustomers().stream().collect(Collectors.groupingBy(Customer::getMostExpensiveItemValue, Collectors.toList()));

        Assert.assertEquals(2, map.size());
        Assert.assertEquals(2, map.entrySet().size());
        Assert.assertEquals(
                Arrays.asList(
                        this.company.getCustomerNamed("Fred"),
                        this.company.getCustomerNamed("Bill")),
                map.get(50.0));
    }


}
