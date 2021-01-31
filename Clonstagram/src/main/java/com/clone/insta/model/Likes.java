package com.clone.insta.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "userId") // 빨간줄인데 실행은 잘됨.. 왜지..?
    // application.yml에 datasource관련 설정을 넣어놨는데 실행할 때 연결되는 것으로 추정. 그래서 코드단에선 에러인가?
    @JsonIgnoreProperties({"images", "password", "name", "website",
            "bio", "email", "phone", "gender", "createDate", "updateDate"})
    private User user;

    @ManyToOne
    @JoinColumn(name = "imageId")
    @JsonIgnoreProperties({"tags", "user", "likes"})
    private Image image; // 기본 :  image_id

    @CreationTimestamp
    private Timestamp createDate;

    @CreationTimestamp
    private Timestamp updateDate;
}
