package sr.unasat.digitalattendance.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import sr.unasat.digitalattendance.R;
import sr.unasat.digitalattendance.database.DtbHelper;
import sr.unasat.digitalattendance.entities.Teacher;

public class ViewTeachersActivity extends AppCompatActivity {

    ArrayList<Teacher> teachersList;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_teachers);

        DtbHelper db = new DtbHelper(this);

        ListView listView=(ListView)findViewById(R.id.listview);
        final ArrayList<String> teacherList = new ArrayList<String>();

        teachersList=db.getAllTeachers();

        for(Teacher teacher : teachersList)
        {
            String users = " FirstName: " + teacher.getTeacher_firstname()+"\nLastname:"+teacher.getTeacher_lastname();

            teacherList.add(users);

        }

        listAdapter = new ArrayAdapter<String>(this, R.layout.view_teacher_list, R.id.labelF, teacherList);
        listView.setAdapter( listAdapter );

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           final int position, long id) {



                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewTeachersActivity.this);

                alertDialogBuilder.setTitle(getTitle()+"decision");
                alertDialogBuilder.setMessage("Are you sure?");

                alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                        teacherList.remove(position);
                        listAdapter.notifyDataSetChanged();
                        listAdapter.notifyDataSetInvalidated();

                        db.deleteTeacher(teachersList.get(position).getTeacher_id());
                        teachersList=db.getAllTeachers();

                        for(Teacher teacher : teachersList)
                        {
                            String users = " FirstName: " + teacher.getTeacher_firstname()+"\nLastname:"+teacher.getTeacher_lastname();
                            teacherList.add(users);

                        }

                    }

                });
                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // cancel the alert box and put a Toast to the user
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(), "You choose cancel",
                                Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                // show alert
                alertDialog.show();

                return false;
            }
        });
    }
}