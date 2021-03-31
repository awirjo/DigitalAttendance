package sr.unasat.digitalattendance.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sr.unasat.digitalattendance.R;
import sr.unasat.digitalattendance.database.DtbHelper;
import sr.unasat.digitalattendance.entities.Teacher;

public class AddTeacherActivity extends AppCompatActivity {

    Button registerButton;
    Button cancelButton;
    EditText textFirstName;
    EditText textLastName;
    EditText textusername;
    EditText textpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teachers);

    textFirstName= (EditText) findViewById(R.id.editTextFirstName);
    textLastName= (EditText) findViewById(R.id.editTextLastName);
    textusername= (EditText) findViewById(R.id.editTextUserName);
    textpassword= (EditText) findViewById(R.id.editTextPassword);
    registerButton= (Button) findViewById(R.id.RegisterButton);
    cancelButton= (Button) findViewById(R.id.Cancel_Button);

		registerButton.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub

            String first_name = textFirstName.getText().toString();
            String last_name = textLastName.getText().toString();
            String userName = textusername.getText().toString();
            String passWord = textpassword.getText().toString();

            if (TextUtils.isEmpty(first_name)) {
                textFirstName.setError("please enter firstname");
            }
            else if (TextUtils.isEmpty(last_name)) {
                textLastName.setError("please enter lastname");
            }
            else {

                Teacher teacher = new Teacher();
                teacher.setTeacher_firstname(first_name);
                teacher.setTeacher_lastname(last_name);
                teacher.setTeacher_username(userName);
                teacher.setTeacher_password(passWord);

                DtbHelper db = new DtbHelper(AddTeacherActivity.this);
                db.addTeacher(teacher);

                Intent intent =new Intent(AddTeacherActivity.this,MenuActivity.class);
                startActivity(intent);
                Toast.makeText(AddTeacherActivity.this, "Teacher added successfully", Toast.LENGTH_SHORT).show();

            }

        }
    });

		cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTeacherActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });



}

}