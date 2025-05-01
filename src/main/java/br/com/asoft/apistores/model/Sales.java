package br.com.asoft.apistores.model;

import br.com.asoft.apistores.enums.StatusSales;
import br.com.asoft.apistores.enums.TypePayment;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private BigDecimal valueTotal;

    private LocalDateTime dateSales;

    private String observation;

    private StatusSales statusSales;

    private TypePayment typePayment;

    @ManyToOne
    @JoinColumn(name = "id_users")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;


}
