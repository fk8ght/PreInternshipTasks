package by.innowise.task.second;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AnalysisService {
    public Set<String> getUniqueCities(List<Order> orders){
        return orders.stream()
                .map(Order::getCustomer)
                .map(Customer::getCity)
                .collect(Collectors.toSet());
    }

    public double getTotalIncome(List<Order> orders){
        return orders.stream()
                .filter(o -> o.getStatus() == OrderStatus.DELIVERED)
                .flatMap(o -> o.getItems().stream())
                .mapToDouble(OrderItem::getTotalPrice).sum();
    }

    public String getMostPopularProduct(List<Order> orders){
        int maxCount = orders.stream()
                .flatMap(o -> o.getItems().stream())
                .mapToInt(OrderItem::getQuantity)
                .max()
                .orElse(0);

        return orders.stream()
                .flatMap(o -> o.getItems().stream())
                .filter(i -> i.getQuantity() == maxCount)
                .map(OrderItem::getProductName)
                .findFirst()
                .orElse("");
    }

    public long getCountOfSuccessfullyDeliveredOrders(List<Order> orders){
        return orders.stream()
                .filter(o -> o.getStatus() == OrderStatus.DELIVERED)
                .count();
    }

    public List<Customer> getCustomersWithMoreThanFiveOrders(List<Order> orders){
        return orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer))
                .entrySet()
                .stream()
                .filter(e -> e.getValue().size() > 5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
