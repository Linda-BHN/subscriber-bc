package com.example.subscriberbc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "SUBSCRIBERID", nullable = false)
    private Long id;
    @Column(name = "FNAME",length = 32, nullable = false)
    @NotNull(message = "Le prénom ne peut pas être nul")
    private String firstName;
    @Column(name="LNAME",length = 32, nullable = false)
    @NotNull(message = "Le nom ne peut pas être nul")
    private String lastName;
    @Column(length = 100, nullable = false)
    @NotNull(message = "Le mail ne peut pas être nul")
    private String mail;
    @Column(length = 20, nullable = false)
    @NotNull(message = "Le numéro de téléphone ne peut pas être nul")
    private String phone;
    @Column(name = "ISACTIV", nullable = false,columnDefinition = "BOOLEAN DEFAULT true")
    private boolean active;
}
