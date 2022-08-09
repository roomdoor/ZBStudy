package ch04.dbtest;

public class Member {

    public Member() {
    }

    public Member(String memberType, String userId, String password, String name) {
        this.memberType = memberType;
        this.userId = userId;
        this.password = password;
        this.name = name;
    }

    private String memberType;
    private String userId;
    private String password;
    private String name;

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
}
