package org.gfg.JBDL_71_Minor.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.gfg.JBDL_71_Minor.enums.UserStatus;
import org.gfg.JBDL_71_Minor.enums.UserType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(length = 30)
    String name;

    @Column(unique = true, nullable = false, length = 50)
    String email;

    @Column(unique = true, length = 15)
//    @Column(unique = true, nullable = false, length = 15)
    String phoneNo;

    String password;

    String authorities; //ADMIN,STUDENT
//
//    String temp;
//
//    @Column(nullable = false)
//    String temp2;
//
//    @Column(unique = true, nullable = false, length = 50)
//    String temp3;

    String address; //255

    @Enumerated(value = EnumType.STRING)
    UserType userType; //ADMIN, STUDENT

    @Enumerated
    UserStatus userStatus; //0,1,2

    @OneToMany(mappedBy = "user")
    List<Book> books;

    @OneToMany(mappedBy = "user")
    List<Transaction> transactions;

    @CreationTimestamp
    Date createdOn;

    @UpdateTimestamp
    Date updatedOn;

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(authorities.split(","))
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());
    }
}
