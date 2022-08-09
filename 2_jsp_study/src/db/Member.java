package db;

import java.util.Date;

public class Member {

    private String memberType;
    private String userId;
    private String password;
    private String name;
    private String mobile_no;
    private boolean marketing_yn;
    private Date register_date;

    public Member() {
    }

    public Member(String memberType, String userId, String password, String name) {
        this.memberType = memberType;
        this.userId = userId;
        this.password = password;
        this.name = name;
    }

    public Member(String memberType, String userId, String password, String name, String mobile_no, boolean marketing_yn, Date register_data) {
        this.memberType = memberType;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.mobile_no = mobile_no;
        this.marketing_yn = marketing_yn;
        this.register_date = register_data;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public boolean getMarketing_yn() {
        return marketing_yn;
    }

    public void setMarketing_yn(boolean marketing_yn) {
        this.marketing_yn = marketing_yn;
    }

    public Date getRegister_date() {
        return register_date;
    }

    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }
}
