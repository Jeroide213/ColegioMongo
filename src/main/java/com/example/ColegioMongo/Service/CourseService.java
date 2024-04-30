package com.example.ColegioMongo.Service;

import com.example.ColegioMongo.Models.Course;
import com.example.ColegioMongo.Models.Subject;
import com.example.ColegioMongo.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SubjectService subjectService;

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public Optional<Course> getById(String id) {
        return courseRepository.findById(id);
    }

    public Course saveCourse(Course course) {
        if (course.getName() == null || course.getName().isEmpty()) {
            throw new IllegalArgumentException("El nombre del curso es obligatorio");
        }
        return courseRepository.save(course);
    }

    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }

    public Optional<Course> updateCourse(String id, Course newCourse) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) {
            newCourse.setId(id);
            return Optional.of(courseRepository.save(newCourse));
        } else {
            return Optional.empty();
        }
    }

    public Subject addSubjectToCourse(String courseId, String subjectId) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isPresent()) {
            Optional<Subject> optionalSubject = subjectService.getById(subjectId);
            if (optionalSubject.isPresent()) {
                Course course = optionalCourse.get();
                Subject subject = optionalSubject.get();
                if (!course.getSubjects().contains(subject)) {
                    course.getSubjects().add(subject);
                    courseRepository.save(course);
                }
                return subject;
            } else {
                throw new NoSuchElementException("The subject with ID " + subjectId + " does not exist");
            }
        } else {
            throw new NoSuchElementException("The course with ID " + courseId + " does not exist");
        }
    }

}
