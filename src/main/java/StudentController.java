import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/{studentId}/enroll/{courseId}")
    public String enrollStudentInCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student Not Found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course Not Found"));

        student.getCourses().add(course);
        studentRepository.save(student);

        return "Student enrolled in course successfully!";
    }
}
