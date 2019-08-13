package bipulsarkar.com.firebaserealtimedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import bipulsarkar.com.firebaserealtimedatabase.model.Course;

public class MainActivity extends AppCompatActivity {

    private List<Course> courseList;
    private CourseAdapter courseAdapter;
    RecyclerView recyclerView;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    private ProgressDialog mDialog;

    private EditText courseTitleET, courseCodeET, courseCreditET;
    private Button addBtn;

    private String userId;

    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDialog.setMessage("Please waiting...");
                mDialog.show();

                String title = courseTitleET.getText().toString();
                String code = courseCodeET.getText().toString();
                int credit = Integer.parseInt(courseCreditET.getText().toString());

                if(title==null&& code==null&&credit==0){
                    Toast.makeText(MainActivity.this, "Please fill field!!", Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();

                }else {
                    saveCourse(title, code, credit);
                    mDialog.dismiss();
                }
            }
        });

        getCourses();
    }

    private void getCourses() {
        DatabaseReference coursesRef = databaseReference.child("users").child("courses");
        coursesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    courseList.clear();
                    for (DataSnapshot courseData : dataSnapshot.getChildren()) {
                        Course course = courseData.getValue(Course.class);
                        courseList.add(course);
                        courseAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void saveCourse(String title, String code, int credit) {
        DatabaseReference courseRef = databaseReference.child("users").child("courses");

        String courseId = courseRef.push().getKey();
        course = new Course(title, code, credit, courseId);
        courseRef.child(courseId).setValue(course).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        mDialog = new ProgressDialog(MainActivity.this);
        courseTitleET = findViewById(R.id.courseTitleET);
        courseCodeET = findViewById(R.id.courseCodeET);
        courseCreditET = findViewById(R.id.courseCreditET);
        addBtn = findViewById(R.id.addBtn);
        recyclerView = findViewById(R.id.courseRV);
        courseList = new ArrayList<>();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        courseAdapter = new CourseAdapter(courseList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(courseAdapter);

    }
}
