package PaloosaBank.OnlineBanking.controllers.accounts;

import PaloosaBank.OnlineBanking.DTOs.accounts.AccountPostDTO;
import PaloosaBank.OnlineBanking.controllers.accounts.interfaces.StudentsCheckingControllerInterface;
import PaloosaBank.OnlineBanking.entities.accounts.StudentsChecking;
import PaloosaBank.OnlineBanking.services.accounts.interfaces.StudentsCheckingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentsCheckingController implements StudentsCheckingControllerInterface {

    @Autowired
    StudentsCheckingServiceInterface studentsCheckingServiceInterface;

    @Override
    @PostMapping("/admin/students_checking")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentsChecking addStudentsChecking(AccountPostDTO studentsChecking) {
        return studentsCheckingServiceInterface.addStudentsChecking(studentsChecking);
    }

    @Override
    @GetMapping("/students_checking/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentsChecking getStudentsCheckingById(Long id) {
        return studentsCheckingServiceInterface.getStudentsCheckingById(id);
    }

    @Override
    @GetMapping("/students_checkings")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentsChecking> getAllStudentsCheckings() {
        return studentsCheckingServiceInterface.getAllStudentsCheckings();
    }

    @Override
    @PutMapping("/admin/students_checking/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public StudentsChecking updateStudentsChecking(@PathVariable Long id, @RequestBody AccountPostDTO studentsChecking) {
        return studentsCheckingServiceInterface.updateStudentsChecking(id, studentsChecking);
    }
}
