package ra.edu.ptit_cntt2_it210_session14_ex2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @Column(name = "order_id")
    private Long orderId;
    private String status;
    private int quantity;
    private Long productId;

}
