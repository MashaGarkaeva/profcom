package sample;


public class ListTeacher {

    private String FIO;
    private String login;
    private String pass;
    private String birthday;
    private String phone;
    private String groups;

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public ListTeacher(String FIO, String login, String pass, String birthday,
                       String phone, String groups) {
        this.FIO = FIO;
        this.login = login;
        this.pass = pass;
        this.birthday = birthday;
        this.phone = phone;
        this.groups = groups;

    }
}