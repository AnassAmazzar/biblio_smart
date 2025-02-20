package ma.emsi.salesmanagementservice.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId; // Reference to the customer

    @OneToMany(fetch = FetchType.EAGER,mappedBy ="commande")
    private List<Vente> sales; // Items in the order

    private LocalDateTime validationDate;

    private String status; // Example: "VALIDATED", "SHIPPED", "DELIVERED"

    // Getters and setters

}

