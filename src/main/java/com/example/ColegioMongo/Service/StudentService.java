package com.example.ColegioMongo.Service;

import com.example.ColegioMongo.Models.Course;
import com.example.ColegioMongo.Models.Student;
import com.example.ColegioMongo.Models.Professor;
import com.example.ColegioMongo.Repository.CourseRepository;
import com.example.ColegioMongo.Repository.ProfessorRepository;
import com.example.ColegioMongo.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private RestTemplate restTemplate;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(String id) {
        return studentRepository.findById(id);
    }

    public Student saveStudent(Student student) {
        studentValidation(student);
        Optional<Professor> existingProfessor = professorRepository.findByDni(student.getDni());
        if (existingProfessor.isPresent()) {
            throw new IllegalArgumentException("El DNI ya está registrado para otro profesor.");
        }
        Optional<Student> existingStudent = studentRepository.findByDni(student.getDni());
        if (existingStudent.isPresent()) {
            throw new IllegalArgumentException("El DNI ya está registrado para otro alumno.");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = "{\"username\": \"" + student.getDni() + "\", \"password\": \"" + student.getDni() + "\", \"rol\": \"ALUMNO\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Enviar la solicitud para crear el usuario en la API externa
        restTemplate.postForObject("http://localhost:8081/api/crearUsuario", requestEntity, String.class);
        return studentRepository.save(student);
    }

    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }

    public List<Student> FindByName(String name) {
        return studentRepository.findByName(name);
    }

    public Optional<Student> updateStudent(String id, Student newStudent) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            newStudent.setId(id);
            return Optional.of(studentRepository.save(newStudent));
        } else {
            return Optional.empty();
        }
    }

    public void addStudentToCourse(String studentId, String courseId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalStudent.isPresent() && optionalCourse.isPresent()) {
            Student student = optionalStudent.get();
            Course course = optionalCourse.get();
            List<Student> studentsCourse = course.getStudents();
            if (studentsCourse == null) {
                studentsCourse = new ArrayList<>();
            }
            studentsCourse.add(student);
            course.setStudents(studentsCourse);
            courseRepository.save(course);
        } else {
            throw new IllegalArgumentException("No se encontró el alumno o el curso.");
        }
    }

    private void studentValidation(Student student) {
        if (student.getDni() == null || student.getDni() == 0) {
            throw new IllegalArgumentException("El DNI del alumno no puede estar vacío");
        }

        if (student.getName() == null || student.getName().isEmpty()) {
            throw new IllegalArgumentException("El nombre del student no puede estar vacío");
        }

        if (student.getLastName() == null || student.getLastName().isEmpty()) {
            throw new IllegalArgumentException("El apellido del alumno no puede estar vacío");
        }

        if (student.getBirthDate() == null || student.getBirthDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento del alumno no es válida");
        }

        if (student.getSchoolCycle() == null || student.getSchoolCycle().isEmpty()) {
            throw new IllegalArgumentException("El ciclo del alumno no puede estar vacío");
        }

        if (student.getSpeciality() == null || student.getSpeciality().isEmpty()) {
            throw new IllegalArgumentException("La especialidad del alumno no puede estar vacía");
        }
    }
    public Optional<Student> FindByDni(Long dni) {
        return studentRepository.findByDni(dni);
    }
}