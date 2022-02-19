package HJproject.Hellospring.domain.member;

import javax.persistence.*;

@Entity(name = "MEMBER")
//@Table(name = "MEMBER") // TABLE 어노테이션 사용해서 테이블 이름을 지정 가능
public class Member {

    // @Id : DB 에서 primary key 로 설정된 컬럼 맵핑
    // @GeneratedValue : pk 값의 생성 방식 -> 현재는 DB 에서 자동 생성임으로 이에 해당하는 Identity
    // springdataJPA 에서는 _ 가 예약어로 되어있어서 사용이 불가능하단다ㅋㅋㅋㅋ => 컬럼명에서는 _ 가능
    // 만약 그래도 쓸꺼면 아래처럼 column 해서 컬럼명을 정해주고 변수명만 바꾸자
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_CODE")
    private Long MEMBERCODE; // 시스템에서 저장 & 식별 구분 하기 위한 code => primary key

    // @Column : 컬럼명 코드 변수 매핑 -> name = "컬럼명"
    @Column(name = "MNAME")
    private String MNAME; // 고객 이름


    @Column(name = "MID")
    private String MID; // 고객 id

    @Column(name = "MPASSWD")
    private String MPASSWD; // 고객 passwd

/* 6. 내 마음대로 구현하기 step 2 */

    @Column(name = "MGENDER")
    private String MGENDER;

    @Column(name = "MEMAIL")
    private String MEMAIL;

    @Column(name = "MEMADDRESS")
    private String MEMADDRESS;

    @Column(name = "MRDATE")
    private String RDate;



    public Long getMEMBERCODE() {
        return MEMBERCODE;
    }

    public void setMEMBERCODE(Long MEMBERCODE) {
        this.MEMBERCODE = MEMBERCODE;
    }

    public String getMNAME() {
        return MNAME;
    }

    public void setMNAME(String MNAME) {
        this.MNAME = MNAME;
    }

    public String getMID() {
        return MID;
    }

    public void setMID(String MID) {
        this.MID = MID;
    }

    public String getMPASSWD() {
        return MPASSWD;
    }

    public void setMPASSWD(String MPASSWD) {
        this.MPASSWD = MPASSWD;
    }

    public String getMGENDER() {
        return MGENDER;
    }

    public void setMGENDER(String MGENDER) {
        this.MGENDER = MGENDER;
    }

    public String getMEMAIL() {
        return MEMAIL;
    }

    public void setMEMAIL(String MEMAIL) {
        this.MEMAIL = MEMAIL;
    }

    public String getMEMADDRESS() {
        return MEMADDRESS;
    }

    public void setMEMADDRESS(String MEMADDRESS) {
        this.MEMADDRESS = MEMADDRESS;
    }

    public String getRDate() {
        return RDate;
    }

    public void setRDate(String RDate) {
        this.RDate = RDate;
    }
}
