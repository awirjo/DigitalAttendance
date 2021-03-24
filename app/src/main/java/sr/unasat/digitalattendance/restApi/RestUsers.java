package sr.unasat.digitalattendance.restApi;

import com.google.gson.annotations.SerializedName;

public class RestUsers {
    private String firstName;
    private String lastName;
    private String studentClass;
    @SerializedName("body")

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStudentClass() {
        return studentClass;
    }
}
