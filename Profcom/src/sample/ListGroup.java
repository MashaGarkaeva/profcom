package sample;

public class ListGroup {
    String number_group;
    String people_group;
    String spec_group;
    String otdel_group;

    public String getNumber_group() {
        return number_group;
    }

    public void setNumber_group(String number_group) {
        this.number_group = number_group;
    }

    public String getPeople_group() {
        return people_group;
    }

    public void setPeople_group(String people_group) {
        this.people_group = people_group;
    }

    public String getSpec_group() {
        return spec_group;
    }

    public void setSpec_group(String spec_group) {
        this.spec_group = spec_group;
    }

    public String getOtdel_group() {
        return otdel_group;
    }

    public void setOtdel_group(String otdel_group) {
        this.otdel_group = otdel_group;
    }

    public ListGroup(String number, String people, String spec,
                     String otdel){
        this.number_group = number;
        this.people_group = people;
        this.spec_group = spec;
        this.otdel_group = otdel;
    }

}
