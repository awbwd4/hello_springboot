package hello.hellospring.domain;

import javax.persistence.*;

@Entity //jpa가 관리하는 엔티티
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @id : pk 설정
    //Identity 전략 : db가 값을 알아서 시퀀스로 생성해줌.
    private Long id;

//    @Column(name="name") //db의 칼럼명이 "username"인 칼럼과 매핑함.
    private String name;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
