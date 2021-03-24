package sr.unasat.digitalattendance.entities;

public class Teacher {

    private int teacher_id;
    private String teacher_firstname;
    private String teacher_lastname;
    private String teacher_username;
    private String teacher_password;

    public int getTeacher_id() {
        return teacher_id;
    }

    public String getTeacher_firstname() {
        return teacher_firstname;
    }

    public String getTeacher_lastname() {
        return teacher_lastname;
    }

    public String getTeacher_username() {
        return teacher_username;
    }

    public String getTeacher_password() {
        return teacher_password;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public void setTeacher_firstname(String teacher_firstname) {
        this.teacher_firstname = teacher_firstname;
    }

    public void setTeacher_lastname(String teacher_lastname) {
        this.teacher_lastname = teacher_lastname;
    }

    public void setTeacher_username(String teacher_username) {
        this.teacher_username = teacher_username;
    }

    public void setTeacher_password(String teacher_password) {
        this.teacher_password = teacher_password;
    }
}
