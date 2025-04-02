import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository repository;

    @GetMapping("/filter")
    public List<Employee> filterBySalary(@RequestParam Double salaryThreshold) {
        return repository.findBySalaryGreaterThan(salaryThreshold);
    }
}
