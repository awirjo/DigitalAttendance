package sr.unasat.digitalattendance.context;

import android.app.Application;

import java.util.ArrayList;

import sr.unasat.digitalattendance.entities.Attendance;
import sr.unasat.digitalattendance.entities.Student;

public class ApplicationContext extends Application {

    private ArrayList<Student> studentList;
    private ArrayList<Attendance> attendanceList;

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    public ArrayList<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(ArrayList<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }
}
