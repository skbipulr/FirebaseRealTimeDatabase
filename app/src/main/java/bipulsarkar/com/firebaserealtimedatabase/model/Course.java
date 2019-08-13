package bipulsarkar.com.firebaserealtimedatabase.model;

public class Course {
    private String title;
    private String code;
    private int credit;
    private String courseId;
   

    public Course(String title, String code, int credit, String courseId) {
        this.title = title;
        this.code = code;
        this.credit = credit;
        this.courseId = courseId;
    }

    public Course() {
    }

    public Course(String title, String code, int credit) {
        this.title = title;
        this.code = code;
        this.credit = credit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
