package com.college.recipes_collection.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String cpf;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "roles")
    private Role role;

    @Column
    private Double salary;

    @Temporal(TemporalType.DATE)
    @Column(name = "ingressed_at")
    private Date ingressedAt;

    @Column(nullable = true, unique = true, name = "fantasy_name")
    private String fantasyName;
}
