package sr.unasat.digitalattendance.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import sr.unasat.digitalattendance.R;
import sr.unasat.digitalattendance.context.ApplicationContext;
import sr.unasat.digitalattendance.database.DtbHelper;
import sr.unasat.digitalattendance.entities.Teacher;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    String userrole;
    private String[] userRoleString = new String[]{"admin", "teacher"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login = (Button) findViewById(R.id.button);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        Spinner spinnerloginas = (Spinner) findViewById(R.id.spinnerloginas);


        spinnerloginas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                userrole = (String) spinnerloginas.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        ArrayAdapter<String> adapter_role = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, userRoleString);
        adapter_role.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerloginas.setAdapter(adapter_role);


        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (userrole.equals("admin")) {

                    String user_name = username.getText().toString();
                    String pass_word = password.getText().toString();

                    if (TextUtils.isEmpty(user_name)) {
                        username.setError("Invalid User Name");
                    } else if (TextUtils.isEmpty(pass_word)) {
                        password.setError("enter password");
                    } else {
                        if (user_name.equals("admin") & pass_word.equals("shit")) {
                            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    String user_name = username.getText().toString();
                    String pass_word = password.getText().toString();

                    if (TextUtils.isEmpty(user_name)) {
                        username.setError("Invalid username");
                    } else if (TextUtils.isEmpty(pass_word)) {
                        password.setError("enter password");
                    }
                    DtbHelper db = new DtbHelper(LoginActivity.this);
                    Teacher teacher = db.validateTeacher(user_name, pass_word);

                    if (teacher != null) {
                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        startActivity(intent);
                        ((ApplicationContext) LoginActivity.this.getApplicationContext()).setTeacher(teacher);
                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }
}


