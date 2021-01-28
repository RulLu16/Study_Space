package com.clone.insta.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity // jpa -> orm
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String loginId;
    private String password;
    private String name;
    private String url;
    private String bio; // 자기 소개
    private String email;
    private String phone;
    private String gender;
    private String profileImage; // 프로필사진 경로+이름

    @OneToMany(mappedBy = "user") // jpa가 관계도 지정해주나봄.. 대박
    @JsonIgnoreProperties({"user", "tags", "likes"})
    private List<Image> images = new ArrayList<>();

    @CreationTimestamp // 자동으로 현재 시간을 세팅해주는 어노테이션
    private Timestamp createDate;

    @CreationTimestamp
    private Timestamp updateDate;
}
