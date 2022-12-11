package com.example.pgCryptoExample.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@AllArgsConstructor
@Entity
@Table(name = "customer_details")
@NoArgsConstructor
public class CustomerDetails implements Serializable
{
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Setter(AccessLevel.NONE)
    @ColumnTransformer(
            write = "public.pgp_pub_encrypt(?,public.dearmor(current_setting('var.public_key')))")
    @Column(name = "cibil_score", columnDefinition = "bytea")
    private String cibilScore;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_active")
    private Boolean isActive;
}
