package sample;


public class ListStudent {
    private String number;
    private String FIO;
    private String groups;
    private String birthday;
    private String login;
    private String password;
    private String phone;


    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getLogin() {
        return login;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPass(String pass) {
        this.password = password;
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

    public ListStudent(String number, String FIO, String groups, String birthday, String login, String password,
                       String phone) {
        this.number = number;
        this.FIO = FIO;
        this.login = login;
        this.password = password;
        this.birthday = birthday;
        this.phone = phone;
        this.groups = groups;

    }
}
