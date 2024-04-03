package com.robbank.crudApis.services;

import org.springframework.stereotype.Service;

import com.robbank.crudApis.model.Bank;
import com.robbank.crudApis.repositories.BankRepository;

@Service
/**
 * A service is a class responsible for encapsulating and implementing the
 * business logic of an application. Services perform operations related to
 * specific domains or functionalities, such as data processing, calculations,
 * validations, and interactions with other components. They are managed as
 * Spring beans and leverage dependency injection for loose coupling
 */
public class BankService {

    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {

        this.bankRepository = bankRepository;
    }

//    public List<Student> getAccounts() {
//
//        return robbankApiRepository.findAll();
//    }

    public void addNewBank(final Bank bank) {

        if (bankRepository.findBankByName(bank.getName()).isPresent()) {

            throw new IllegalStateException("Bank already exists");
        }
        bankRepository.save(bank);
    }

//    public void deleteStudent(final Long studentId) {
//
//        if (!_studentRepository.existsById(studentId)) {
//
//            throw new IllegalStateException("student with id: " + studentId + " doesn't exist");
//        }
//        _studentRepository.deleteById(studentId);
//    }
//
//    @Transactional
//    public void updateStudent(final Long studentId, final String name, final String email) {
//
//        Student student = _studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException(
//                "student with id: " + studentId + " doesn't exist"));
//
//        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
//
//            student.setName(name);
//        }
//
//        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
//
//            if (_studentRepository.findStudentByEmail(email).isPresent()) {
//
//                throw new IllegalStateException("email is taken");
//            }
//
//            student.setEmail(email);
//        }
//
//    }
}
