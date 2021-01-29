package com.clone.insta.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // fromUser가 toUser를 following. 반대는 follower
    @ManyToOne
    @JoinColumn(name="fromUserId")
    @JsonIgnoreProperties({"images"})
    private User fromUser;

    @ManyToOne
    @JoinColumn(name="toUserId")
    @JsonIgnoreProperties({"images"})
    private User toUser;

    @Transient
    private boolean matpal;

    @CreationTimestamp
    private Timestamp createDate;

    @CreationTimestamp
    private Timestamp updateDate;
}
