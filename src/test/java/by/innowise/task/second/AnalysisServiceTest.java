package by.innowise.task.second;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AnalysisServiceTest {
    private AnalysisService service;
    private List<Order> orders;

    @BeforeEach
    void setup(){
        service = new AnalysisService();
        orders = new ArrayList<Order>();

        Customer customerA = new Customer("C1", "Alice", "alice@email.com", LocalDateTime.now().minusYears(2), 30, "New York");
        Customer customerB = new Customer("C2", "Bob", "bob@email.com", LocalDateTime.now().minusYears(1), 25, "Los Angeles");
        Customer customerC = new Customer("C3", "Charlie", "charlie@email.com", LocalDateTime.now().minusYears(3), 40, "Chicago");
        Customer customerD = new Customer("C4", "Diana", "diana@email.com", LocalDateTime.now().minusYears(2), 28, "New York");
        Customer customerE = new Customer("C5", "Eve", "eve@email.com", LocalDateTime.now().minusYears(1), 35, "Los Angeles");

        OrderItem laptop = new OrderItem("Laptop", 2, 1000.0, Category.ELECTRONICS);
        OrderItem phone = new OrderItem("Phone", 1, 500.0, Category.ELECTRONICS);
        OrderItem book = new OrderItem("Book", 3, 20.0, Category.BOOKS);
        OrderItem shirt = new OrderItem("Shirt", 2, 30.0, Category.CLOTHING);
        OrderItem toy = new OrderItem("Toy", 4, 15.0, Category.TOYS);
        OrderItem makeup = new OrderItem("Makeup", 1, 50.0, Category.BEAUTY);
        OrderItem blender = new OrderItem("Blender", 1, 120.0, Category.HOME);

        orders.add(new Order("O1", LocalDateTime.now().minusDays(10), customerA, List.of(laptop, book), OrderStatus.DELIVERED));
        orders.add(new Order("O2", LocalDateTime.now().minusDays(9), customerA, List.of(phone), OrderStatus.DELIVERED));
        orders.add(new Order("O3", LocalDateTime.now().minusDays(8), customerA, List.of(toy), OrderStatus.SHIPPED));
        orders.add(new Order("O4", LocalDateTime.now().minusDays(7), customerA, List.of(shirt), OrderStatus.DELIVERED));
        orders.add(new Order("O5", LocalDateTime.now().minusDays(6), customerA, List.of(book), OrderStatus.NEW));
        orders.add(new Order("O6", LocalDateTime.now().minusDays(5), customerB, List.of(shirt, phone), OrderStatus.DELIVERED));
        orders.add(new Order("O7", LocalDateTime.now().minusDays(4), customerB, List.of(book), OrderStatus.CANCELLED));
        orders.add(new Order("O8", LocalDateTime.now().minusDays(3), customerC, List.of(book, toy), OrderStatus.DELIVERED));
        orders.add(new Order("O9", LocalDateTime.now().minusDays(2), customerD, List.of(blender), OrderStatus.NEW));
        orders.add(new Order("O10", LocalDateTime.now().minusDays(1), customerE, List.of(makeup, shirt), OrderStatus.DELIVERED));
        orders.add(new Order("O11", LocalDateTime.now().minusDays(12), customerC, List.of(phone), OrderStatus.DELIVERED));
        orders.add(new Order("O12", LocalDateTime.now().minusDays(11), customerB, List.of(toy), OrderStatus.NEW));
        orders.add(new Order("O13", LocalDateTime.now().minusDays(13), customerD, List.of(laptop, blender), OrderStatus.DELIVERED));
        orders.add(new Order("O14", LocalDateTime.now().minusDays(14), customerE, List.of(book, shirt), OrderStatus.SHIPPED));
        orders.add(new Order("O15", LocalDateTime.now().minusDays(15), customerA, List.of(makeup), OrderStatus.DELIVERED));
    }

    @Test
    void shouldReturnCorrectUniqueCities() {
        Set<String> uniqueCities = service.getUniqueCities(orders);

        assertEquals(3, uniqueCities.size());
        assertTrue(uniqueCities.contains("New York"));
        assertTrue(uniqueCities.contains("Los Angeles"));
        assertTrue(uniqueCities.contains("Chicago"));
    }

    @Test
    void shouldReturnCorrectTotalIncome() {
        double income = service.getTotalIncome(orders);

        assertEquals(6080, income);
    }

    @Test
    void shouldReturnMostPopularProduct(){
        String nameOfMostPopularProduct = service.getMostPopularProduct(orders);

        assertEquals("Toy", nameOfMostPopularProduct);
    }

    @Test
    void shouldReturnCountOfSuccessfullyDeliveredOrders(){
        long count = service.getCountOfSuccessfullyDeliveredOrders(orders);

        assertEquals(9L, count);
    }

    @Test
    void shouldReturnCustomersWithMoreThanFiveOrders(){
        List<Customer> customers = service.getCustomersWithMoreThanFiveOrders(orders);

        assertEquals(1, customers.size());
        assertEquals("Alice", customers.get(0).getName());
    }
}
