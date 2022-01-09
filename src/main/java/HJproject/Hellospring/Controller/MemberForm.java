package HJproject.Hellospring.Controller;

public class MemberForm {
    private String name;
    private String userid; // 고객 id
    private String userpw; // 고객 passwd

    /* 6. 내 맘대로 구현하기 step 2 */
    private String sex;
    private String email;
    private String emaddress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserpw() {
        return userpw;
    }

    public void setUserpw(String userpw) {
        this.userpw = userpw;
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
