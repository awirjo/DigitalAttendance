package sr.unasat.digitalattendance.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import sr.unasat.digitalattendance.R;
import sr.unasat.digitalattendance.context.ApplicationContext;
import sr.unasat.digitalattendance.database.DtbHelper;
import sr.unasat.digitalattendance.entities.Attendance;
import sr.unasat.digitalattendance.entities.Student;

public class AttendanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        Button addAttendance = (Button) findViewById(R.id.addAttendance);
        Button viewAttendance = (Button) findViewById(R.id.viewAttendance);
        Button clearAttendance = (Button) findViewById(R.id.clearAttendance);

        DtbHelper db = new DtbHelper(AttendanceActivity.this);

        addAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {


                ArrayList<Student> studentList= db.getAllStudents();
                ((ApplicationContext)AttendanceActivity.this.getApplicationContext()).setStudentList(studentList);
                Intent intent = new Intent(AttendanceActivity.this, AddAttendanceActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Attendance List Setup", Toast.LENGTH_LONG).show();
            }
        });

        viewAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Attendance> attendanceViewList = db.getAttendance();
                ((ApplicationContext)AttendanceActivity.this.getApplicationContext()).setAttendanceList(attendanceViewList);
                Intent intent = new Intent(AttendanceActivity.this, ViewAttendanceActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "View the attendance list of the students", Toast.LENGTH_LONG).show();
            }
        });

        clearAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteAttendance();
                Toast.makeText(AttendanceActivity.this, "Attendance cleared", Toast.LENGTH_SHORT).show();
            }
        });
    }
}