package com.clone.insta.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String location; // 사진 장소
    private String caption;
    private String postImage; // 포스팅 사진 경로+이름

    @ManyToOne
    @JoinColumn(name="userId")
    @JsonIgnoreProperties({"password", "images"})
    private User user;
    // 즉, image와 user가 manytoone 관계로 매핑되어 있고 userId라는 column으로 어느 것이 매핑되어 있는지 구분

    // like list
    @OneToMany(mappedBy = "image")
    @JsonManagedReference
    private List<Likes> likes = new ArrayList<>();

    // tag list
    @OneToMany(mappedBy = "image")
    @JsonManagedReference // 객체의 상위 하위 관계 명시하여 순환 참조 방지
    private List<Tag> tags = new ArrayList<>();

    @Transient // db에 영향을 미치지 않음
    private int likeCount;

    @CreationTimestamp // 자동으로 현재 시간을 세팅해주는 어노테이션
    private Timestamp createDate;

    @CreationTimestamp
    private Timestamp updateDate;


}
