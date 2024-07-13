package org.gfg.JBDL_71_Minor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.gfg.JBDL_71_Minor.enums.BookType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(exclude = {"author", "transactions", "user"})
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(length = 30)
    String bookTitle;

    @Column(length = 10, unique = true, nullable = false)
    String bookNo;

    int securityAmount;

    @Enumerated(value = EnumType.STRING)
    BookType bookType;

    @ManyToOne
    @JoinColumn //optional author_id
    @JsonIgnore
    Author author;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    List<Transaction> transactions;

    @ManyToOne
    @JsonIgnore
    User user;

    @CreationTimestamp
    Date createdOn;

    @UpdateTimestamp
    Date updatedOn;
}
