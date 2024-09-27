package com.college.recipes_collection.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.college.recipes_collection.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
    @Query("SELECT COUNT(r) > 0 FROM Recipe r WHERE r.user.id = :userId")
    boolean hasRelatedRecipes(@Param("userId") Long userId);

    @Query("SELECT COUNT(rev) > 0 FROM Review rev WHERE rev.user.id = :userId")
    boolean hasRelatedReviews(@Param("userId") Long userId);

    @Query("SELECT COUNT(b) > 0 FROM Book b WHERE b.user.id = :userId")
    boolean hasRelatedBooks(@Param("userId") Long userId);
}
