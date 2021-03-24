package sr.unasat.digitalattendance.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
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
    String status = "P";
    Button attendanceSubmit;
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

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.add_student_attendance, R.id.labelA, studentLists);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

                arg0.getChildAt(arg2).setBackgroundColor(Color.TRANSPARENT);
                //arg0.setBackgroundColor(234567);
                arg1.setBackgroundColor(334455);
                final Student student = studentList.get(arg2);
                final Dialog dialog = new Dialog(AddAttendanceActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//...........
                dialog.setContentView(R.layout.set_attendance_layout);
                // set title and cancelable
                RadioGroup radioGroup;
                RadioButton present;
                RadioButton absent;
                radioGroup = (RadioGroup) dialog.findViewById(R.id.radioGroup);
                present = (RadioButton) dialog.findViewById(R.id.PresentradioButton);
                absent = (RadioButton) dialog.findViewById(R.id.AbsentradioButton);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.PresentradioButton) {

                            status = "P";
                        } else if (checkedId == R.id.AbsentradioButton) {

                            status = "A";
                        } else {
                        }
                    }
                });

                Button attendanceSubmit = (Button) dialog.findViewById(R.id.attendanceSubmitButton);
                attendanceSubmit.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
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