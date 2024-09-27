package com.college.recipes_collection.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "recipes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Recipe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "preparation_method", nullable = false)
    private String preparationMethod;

    @Column(nullable = false)
    private Double portions;

    @Column(name = "preparation_time",nullable = false)
    private int preparationTime;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private Boolean isPublished;

    @Column(nullable = false)
    private Boolean isRated;

    @OneToMany(mappedBy = "recipe", orphanRemoval = false)
    private List<IngredientsRecipe> ingredients;

    @ManyToMany(mappedBy = "recipes")
    private List<Book> books;
}
