package sr.unasat.digitalattendance.context;

import android.app.Application;

import java.util.ArrayList;

import sr.unasat.digitalattendance.entities.Attendance;
import sr.unasat.digitalattendance.entities.Student;
import sr.unasat.digitalattendance.entities.Teacher;

public class ApplicationContext extends Application {

    private ArrayList<Student> studentList;
    private ArrayList<Attendance> attendanceList;
    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

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
