package ch04.code2;

public class Member {

    private String userId;
    private String name;
    private String email;
    private String password;

    private String h1;
    private String h2;
    private String h3;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getH1() {
        return h1;
    }

    public void setH1(String h1) {
        this.h1 = h1;
    }

    public String getH2() {
        return h2;
    }

    public void setH2(String h2) {
        this.h2 = h2;
    }

    public String getH3() {
        return h3;
    }

    public void setH3(String h3) {
        this.h3 = h3;
    }

    public boolean isUseSmsYN() {
        return useSmsYN;
    }

    public void setUseSmsYN(boolean useSmsYN) {
        this.useSmsYN = useSmsYN;
    }

    private boolean useSmsYN;

}
