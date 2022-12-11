package com.example.pgCryptoExample.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.ColumnTransformer;

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
    @Column(name = "id", nullable = false)
    private UUID id;

    @Setter(AccessLevel.NONE)
    @ColumnTransformer(
            write = "pgp_pub_encrypt(?,dearmor(current_setting('var.public_key')))",
            read = "pgp_pub_decrypt(cibil_score, dearmor(current_setting('var.private_key')),current_setting('var.private_key_password'))")
    @Column(name = "cibil_score", columnDefinition = "bytea")
    private String cibilScore;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_active")
    private Boolean isActive;
}
