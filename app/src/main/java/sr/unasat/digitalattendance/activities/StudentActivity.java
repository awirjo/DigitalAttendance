package sr.unasat.digitalattendance.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sr.unasat.digitalattendance.R;
import sr.unasat.digitalattendance.context.ApplicationContext;
import sr.unasat.digitalattendance.database.DtbHelper;
import sr.unasat.digitalattendance.entities.Student;

public class StudentActivity extends AppCompatActivity {

    DtbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        EditText number = (EditText) findViewById(R.id.stdNumber);
        EditText firstName = (EditText) findViewById(R.id.firstName);
        EditText lastName = (EditText) findViewById(R.id.lastName);
        EditText studentClass = (EditText) findViewById(R.id.stdClass);
        Button insert = (Button) findViewById(R.id.insert);
        Button delete = (Button) findViewById(R.id.delete);
        Button update = (Button) findViewById(R.id.update);
        Button view = (Button) findViewById(R.id.view);
        db = new DtbHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studentNumberTXT = number.getText().toString();
                String firstNameTXT = firstName.getText().toString();
                String lastNameTXT = lastName.getText().toString();
                String studentClassTXT = studentClass.getText().toString();

                Student student = new Student();

                student.setStudent_number(studentNumberTXT);
                student.setStudent_firstname(firstNameTXT);
                student.setStudent_lastname(lastNameTXT);
                student.setStudent_class(studentClassTXT);

                ArrayList<Student> studentList= db.getAllStudents();
                ((ApplicationContext)StudentActivity.this.getApplicationContext()).setStudentList(studentList);

                Boolean checkinsertdata = db.addStudent(studentNumberTXT, firstNameTXT, lastNameTXT, studentClassTXT);
                if(checkinsertdata==true)
                    Toast.makeText(StudentActivity.this, "Student added, just like magic!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(StudentActivity.this, "Student not added", Toast.LENGTH_SHORT).show();
            }        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studentNumberTXT = number.getText().toString();
                Boolean checkudeletedata = db.deleteStudent(studentNumberTXT);
                if(checkudeletedata==true)
                    Toast.makeText(StudentActivity.this, "Student Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(StudentActivity.this, "Student not Deleted", Toast.LENGTH_SHORT).show();
            }        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studentNumberTXT = number.getText().toString();
                String firstNameTXT = firstName.getText().toString();
                String lastNameTXT = lastName.getText().toString();
                String studentClassTXT = studentClass.getText().toString();

                Boolean checkupdatedata = db.updateStudent(studentNumberTXT, firstNameTXT, lastNameTXT, studentClassTXT);
                if(checkupdatedata==true)
                    Toast.makeText(StudentActivity.this, "Student updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(StudentActivity.this, "Student not updated", Toast.LENGTH_SHORT).show();
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor result = db.getStudents();
                if(result.getCount()==0){
                    Toast.makeText(StudentActivity.this, "No Students Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(result.moveToNext()){
                    buffer.append("StudentNumber :"+result.getString(1)+"\n");
                    buffer.append("FirstName :"+result.getString(2)+"\n");
                    buffer.append("LastName :"+result.getString(3)+"\n");
                    buffer.append("Class :"+result.getString(4)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(StudentActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }


}