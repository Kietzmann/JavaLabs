package task_2_2;

import edu.kytsmen.java.lambdas.task_2_2.Company;
import edu.kytsmen.java.lambdas.task_2_2.Supplier;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by olenasyrota on 6/28/16.
 */
public class Exercise1SuppliersTest extends CompanyDomain {
    /**
     * entry point is company.
     * Solve this without changing the return type of {@link Company#getSuppliers()}.
     */
    @Test
    public void findSupplierNames() {
        List<String> suppliersNames = Arrays.asList(company.getSuppliers()).stream().map(Supplier::getName).collect(Collectors.toList());

        List<String> expectedSupplierNames = Arrays.asList(
                "Shedtastic",
                "Splendid Crocks",
                "Annoying Pets",
                "Gnomes 'R' Us",
                "Furniture Hamlet",
                "SFD",
                "Doxins");
        Assert.assertEquals(expectedSupplierNames, suppliersNames);
    }

    /**
     * entry point is company.
     * Create a {@link Predicate} for Suppliers that supply more than 2 items. Find the number of suppliers that
     * satisfy that Predicate.
     */
    @Test
    public void countSuppliersWithMoreThanTwoItems() {
        Predicate<Supplier> moreThanTwoItems = supplier -> supplier.getItemNames().length > 2;
        int suppliersWithMoreThanTwoItems = (int) Arrays.asList(company.getSuppliers()).stream().filter(moreThanTwoItems).count();
        Assert.assertEquals("suppliers with more than 2 items", 5, suppliersWithMoreThanTwoItems);
    }

}