package bipulsarkar.com.firebaserealtimedatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import bipulsarkar.com.firebaserealtimedatabase.model.Course;

public class CourseAdapter  extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private List<Course> courseList;
    private Context context;

    public CourseAdapter(List<Course> courseList, Context context) {
        this.courseList = courseList;
        this.context = context;
    }

    @NonNull
    @Override
    public CourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.course_item,parent,false);

        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, int position) {

        Course course = courseList.get(position);
        holder.titleTV.setText(course.getTitle());
        holder.creditTV.setText(String.valueOf(course.getCredit()));
        holder.codeTV.setText(course.getCode());
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTV,codeTV,creditTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.titleTV);
            codeTV = itemView.findViewById(R.id.courseTV);
            creditTV = itemView.findViewById(R.id.creditTV);
        }
    }
}
