package com.college.recipes_collection.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_authentication")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthentication {
    
    @Id
    @Column(unique = true, nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
