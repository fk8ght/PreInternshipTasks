package by.innowise.task.second;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private String productName;
    private int quantity;
    private double price;
    private Category category;

    public double getTotalPrice() {
        return quantity * price;
    }
}

