package com.starskvim.socialnetwork.repository;


import com.starskvim.socialnetwork.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository <User, Long> {

    @Query("select u from User u where " +
            "lower(CONCAT(u.firstName, ' ', u.lastName)) like lower(CONCAT(:fullName, '%')) " +
            "or lower(CONCAT(u.lastName, ' ', u.firstName))  like lower(CONCAT(:fullName, '%'))")
    List<User> findAllByFullName (String fullName);  // TODO для более гибких запросов лучше Criteria API/QueryDsl/GraphQL

    User findByLogin(String login);

    void deleteByLogin(String login);

    @EntityGraph(value = "User.allFriends", type = EntityGraph.EntityGraphType.LOAD)
    @Query(value = "select u from User u where u.login = :userLogin")
    User getUserWithFriends(@Param("userLogin") String userLogin);

}
