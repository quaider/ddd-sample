package vip.kratos.ddd.zmall.infrastructure.repository.jpa.event;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "t_domain_event")
@Getter
@Setter
public class DomainEventJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String payload;
}
