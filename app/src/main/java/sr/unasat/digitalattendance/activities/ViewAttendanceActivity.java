package sr.unasat.digitalattendance.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import sr.unasat.digitalattendance.R;
import sr.unasat.digitalattendance.context.ApplicationContext;
import sr.unasat.digitalattendance.database.DtbHelper;
import sr.unasat.digitalattendance.entities.Attendance;
import sr.unasat.digitalattendance.entities.Student;

public class ViewAttendanceActivity extends AppCompatActivity {

    ArrayList<Attendance> attendanceList;
    private ListView listView ;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        DtbHelper db = new DtbHelper(this);

        ListView listView= (ListView) findViewById(R.id.listview);
        final ArrayList<String> attendanceLists = new ArrayList<String>();
        attendanceLists.add("Id |    StudentName    |  Status");

        attendanceList=((ApplicationContext)ViewAttendanceActivity.this.getApplicationContext()).getAttendanceList();

        for(Attendance attendance : attendanceList)
        {
            String students = "";
            if(attendance.getAttendance_session_id() != 0)
            {
                Student student = db.getStudentById(attendance.getAttendance_student_id());
                students = attendance.getAttendance_student_id()+".   "+student.getStudent_firstname()+ "  "+ student.getStudent_lastname()+"     "+attendance.getAttendance_status();
            }
            else
            {
                students = attendance.getAttendance_status();
            }

            attendanceLists.add(students);
            Log.d("students: ", students);

        }

        listAdapter = new ArrayAdapter<String>(this, R.layout.view_attendance_list, R.id.labelAttendance, attendanceLists);
        listView.setAdapter( listAdapter );
    }
}