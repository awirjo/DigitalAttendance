package sr.unasat.digitalattendance.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import sr.unasat.digitalattendance.R;
import sr.unasat.digitalattendance.context.ApplicationContext;
import sr.unasat.digitalattendance.database.DtbHelper;
import sr.unasat.digitalattendance.entities.Attendance;
import sr.unasat.digitalattendance.entities.Student;

public class AddAttendanceActivity extends AppCompatActivity {

    ArrayList<Student> studentList;
    private ListView listView;
    private ArrayAdapter<String> listAdapter;
    String status;
    DtbHelper db = new DtbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        listView = (ListView) findViewById(R.id.listview);
        final ArrayList<String> studentLists = new ArrayList<>();

        studentList = ((ApplicationContext) AddAttendanceActivity.this.getApplicationContext()).getStudentList();

        for (Student student : studentList) {
            String students = student.getStudent_firstname() + "," + student.getStudent_lastname();

            studentLists.add(students);
        }

        listAdapter = new ArrayAdapter<String>(this, R.layout.add_student_attendance, R.id.labelA, studentLists);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                final Student student = studentList.get(position);
                final Dialog dialog = new Dialog(AddAttendanceActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.set_attendance_layout);
                // set title and cancelable
                RadioGroup radioGroup = (RadioGroup) dialog.findViewById(R.id.radioGroup);
                RadioButton present = (RadioButton) dialog.findViewById(R.id.PresentradioButton);
                RadioButton absent = (RadioButton) dialog.findViewById(R.id.AbsentradioButton);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.PresentradioButton) {

                            status = "P";
                        } else if (checkedId == R.id.AbsentradioButton) {

                            status = "A";
                        }
                    }
                });

                Button attendanceSubmit = (Button) dialog.findViewById(R.id.attendanceSubmitButton);
                attendanceSubmit.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Attendance attendance = new Attendance();
                        attendance.setAttendance_student_id(student.getStudent_id());
                        attendance.setAttendance_status(status);

                        db = new DtbHelper(AddAttendanceActivity.this);
                        db.addNewAttendance(attendance);


                        dialog.dismiss();

                    }
                });

                dialog.setCancelable(true);
                dialog.show();
            }
        });

    }
}