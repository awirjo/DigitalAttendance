package sr.unasat.digitalattendance.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import sr.unasat.digitalattendance.entities.Attendance;
import sr.unasat.digitalattendance.entities.Student;
import sr.unasat.digitalattendance.entities.Teacher;

public class DtbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = " Attendance Database";
    private static final int DB_VERSION = 1;

    public DtbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE STUDENT_TABLE(student_id INTEGER PRIMARY KEY AUTOINCREMENT not null , student_number TEXT," +
                " student_firstname TEXT, student_lastname TEXT, student_class TEXT)");
        db.execSQL("CREATE TABLE ATTENDANCE_TABLE(attendance_session_id INTEGER PRIMARY KEY AUTOINCREMENT, attendance_student_id INTEGER," +
                " attendance_status TEXT)");
        db.execSQL("CREATE TABLE TEACHER_TABLE(teacher_id INTEGER PRIMARY KEY AUTOINCREMENT not null , teacher_firstname TEXT," +
                " teacher_lastname TEXT, teacher_username TEXT, teacher_password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if exists STUDENT_TABLE");
        db.execSQL("DROP TABLE if exists ATTENDANCE_TABLE");
        db.execSQL("DROP TABLE if exists TEACHER_TABLE");
    }
    //student crud
    public boolean addStudent(String student_number, String student_firstname, String student_lastname, String student_class) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("student_number", student_number);
        contentValues.put("student_firstname", student_firstname);
        contentValues.put("student_lastname", student_lastname);
        contentValues.put("student_class", student_class);

        long result = db.insert("student_table", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateStudent(String student_number, String student_firstname, String student_lastname, String student_class) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("student_number", student_number);
        contentValues.put("student_firstname", student_firstname);
        contentValues.put("student_lastname", student_lastname);
        contentValues.put("student_class", student_class);

        Cursor cursor = db.rawQuery(" SELECT * FROM STUDENT_TABLE WHERE student_firstname = ?", new String[]{student_firstname});
        if (cursor.getCount() > 0) {

            long result = db.update("student_table", contentValues, "student_firstname=?", new String[]{student_firstname});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean deleteStudent(String student_number) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM STUDENT_TABLE WHERE student_number = ?", new String[]{student_number});
        if (cursor.getCount() > 0) {
            long result = db.delete("student_table", "student_number=?", new String[]{student_number});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getStudents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM STUDENT_TABLE ", null);
        return cursor;
    }


    public Student getStudentById(int studentId) {
        Student student = new Student();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM STUDENT_TABLE where student_id=" + studentId;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {

                student.setStudent_id(Integer.parseInt(cursor.getString(0)));
                student.setStudent_number(cursor.getString(1));
                student.setStudent_firstname(cursor.getString(2));
                student.setStudent_lastname(cursor.getString(3));
                student.setStudent_class(cursor.getString(4));

            } while (cursor.moveToNext());
        }
        return student;
    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> list = new ArrayList<Student>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM student_table";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do{
                Student student = new Student();
                student.setStudent_id(Integer.parseInt(cursor.getString(0)));
                student.setStudent_number(cursor.getString(1));
                student.setStudent_firstname(cursor.getString(2));
                student.setStudent_lastname(cursor.getString(3));
                student.setStudent_class(cursor.getString(4));
                list.add(student);
            }while(cursor.moveToNext());
        }
        return list;
    }
    //attendance crud
    public void addNewAttendance(Attendance attendance) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO attendance_table (attendance_student_id, attendance_status) values ("+
                attendance.getAttendance_student_id()+", '"+
                attendance.getAttendance_status()+"')";
        db.execSQL(query);
        db.close();
    }

    public ArrayList<Attendance> getAttendance() {
        ArrayList<Attendance> list = new ArrayList<Attendance>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query1="SELECT * FROM attendance_table";
        Cursor cursor1 = db.rawQuery(query1, null);
		    if(cursor1.moveToFirst())
        {
            do{
                Attendance attendance = new Attendance();
                attendance.setAttendance_session_id(Integer.parseInt(cursor1.getString(0)));
                attendance.setAttendance_student_id(Integer.parseInt(cursor1.getString(1)));
                attendance.setAttendance_status(cursor1.getString(2));
                list.add(attendance);

            }while(cursor1.moveToNext());
        }
		    return list;
    }

    public void deleteAttendance() {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM attendance_table";

        db.execSQL(query);
        db.close();
    }
    //teacher crud
    public void addTeacher(Teacher teacher) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO TEACHER_TABLE (teacher_firstname,teacher_lastname,teacher_username,teacher_password) values ('"+
                teacher.getTeacher_firstname()+"', '"+
                teacher.getTeacher_lastname()+"', '"+
                teacher.getTeacher_username()+"', '"+
                teacher.getTeacher_password()+"')";
        db.execSQL(query);
        db.close();
    }

    public Teacher validateTeacher(String userName,String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM teacher_table where teacher_username='"+userName+"' and teacher_password='"+password+"'";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {

            Teacher teacher = new Teacher();
            teacher.setTeacher_id(Integer.parseInt(cursor.getString(0)));
            teacher.setTeacher_firstname(cursor.getString(1));
            teacher.setTeacher_lastname(cursor.getString(2));
            teacher.setTeacher_username(cursor.getString(3));
            teacher.setTeacher_password(cursor.getString(4));
            return teacher;
        }
        return null;
    }

    public ArrayList<Teacher> getAllTeachers()
    {
        ArrayList<Teacher> list = new ArrayList<Teacher>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM TEACHER_TABLE";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do{
                Teacher teacher = new Teacher();
                teacher.setTeacher_id(Integer.parseInt(cursor.getString(0)));
                teacher.setTeacher_firstname(cursor.getString(1));
                teacher.setTeacher_lastname(cursor.getString(2));
                teacher.setTeacher_username(cursor.getString(3));
                teacher.setTeacher_password(cursor.getString(4));
                list.add(teacher);

            }while(cursor.moveToNext());
        }
        return list;
    }

    public void deleteTeacher(int teacherId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM teacher_table WHERE teacher_id="+teacherId ;

        db.execSQL(query);
        db.close();
    }
}
