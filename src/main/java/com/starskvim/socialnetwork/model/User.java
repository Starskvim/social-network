package com.starskvim.socialnetwork.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@NamedEntityGraph(
        name = "User.allFriends",
        attributeNodes = {
                @NamedAttributeNode("friends")})
@Entity
@Table(name = "social_user")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String login;

    private String password;

    private String mail;

    private String firstName;

    private String lastName;

    @JsonIgnoreProperties("friends")
    @ManyToMany
    @JoinTable(name = "user_friends",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "friendId"))
    private Set<User> friends = new LinkedHashSet<>();


    public User (String login, String password, String mail, String firstName, String lastName){
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return new EqualsBuilder()
                .append(login, user.login)
                .append(mail, user.mail)
                .append(firstName, user.firstName)
                .append(lastName, user.lastName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(login)
                .append(mail)
                .append(firstName)
                .append(lastName)
                .toHashCode();
    }
}
