package sr.unasat.digitalattendance.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import sr.unasat.digitalattendance.R;
import sr.unasat.digitalattendance.database.DtbHelper;

public class MenuActivity extends AppCompatActivity {

    DtbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        Button addTeacher =(Button)findViewById(R.id.addTeacher);
        Button viewTeacher =(Button)findViewById(R.id.viewTeachers);
        Button logout =(Button)findViewById(R.id.logout);

        db = new DtbHelper(this);

        addTeacher.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent =new Intent(MenuActivity.this,AddTeacherActivity.class);
                startActivity(intent);
            }
        });

        viewTeacher.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent =new Intent(MenuActivity.this,ViewTeachersActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent =new Intent(MenuActivity.this,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}