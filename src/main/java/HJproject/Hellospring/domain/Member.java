package HJproject.Hellospring.domain;

import javax.persistence.*;

@Entity
public class Member {

    // @Id : DB 에서 primary key 로 설정된 컬럼 맵핑
    // @GeneratedValue : pk 값의 생성 방식 -> 현재는 DB 에서 자동 생성임으로 이에 해당하는 Identity
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code; // 시스템에서 저장 & 식별 구분 하기 위한 code

    // @Column : 컬럼명 코드 변수 매핑 -> name = "컬럼명"
    @Column(name = "name")
    private String name; // 고객 이름


    @Column(name = "id")
    private String id; // 고객 id

    @Column(name = "passwd")
    private String passwd; // 고객 passwd

/* 6. 내 마음대로 구현하기 step 2 */

    @Column(name = "sex")
    private String sex;

    @Column(name = "email")
    private String email;

    @Column(name = "emaddress")
    private String emaddress;



    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmaddress() {
        return emaddress;
    }

    public void setEmaddress(String emaddress) {
        this.emaddress = emaddress;
    }
}
