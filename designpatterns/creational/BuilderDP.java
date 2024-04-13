package designpatterns.creational;

import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

/**
 * Useful to create object for a class in which a lot of optional fields are present
 * Object creation is done step-by-step
 */
public class BuilderDP {
    public static void main(String[] args) {
        // create Student object using builder
        // each setter method return builder object and at last build method return the actual Student class object
        Student student = new EnggStudentBuilder().builder()
                .rollNo(123)
                .name("abc")
                .subjects(List.of("AI", "ML"))
                .build();

        StudentBuilder studentBuilder = new EnggStudentBuilder();
        studentBuilder.builder()
                .rollNo(234)
                .name("xyz")
                .subjects(List.of("OS", "DB"));

        Student newStudent = new Student(studentBuilder);

        System.out.println(student);
        System.out.println(newStudent);

        /**
         * implementing Business logic while creating object
         * here we are only providing name and roll number; subjects are provided by business
         */
        Student student1 = new Director(new EnggStudentBuilder().name("pqr").rollNo(434)).createStudent();
        System.out.println(student1);
    }
}

class Student {
    private int rollNo;
    private String name;
    List<String> subjects;

    /**
     * No need to create constructor with a lot of arguments;
     * No need to create multiple constructors
     * this constructor is being called on invocation of build method
     * @param studentBuilder
     */
    public Student(StudentBuilder studentBuilder) {
        this.rollNo = studentBuilder.rollNo;
        this.name = studentBuilder.name;
        this.subjects = studentBuilder.subjects;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Student.class.getSimpleName() + "[", "]")
                .add("rollNo=" + rollNo)
                .add("name='" + name + "'")
                .add("subjects=" + subjects)
                .toString();
    }
}

abstract class StudentBuilder {
    int rollNo;
    String name;
    List<String> subjects;

    // following are the setters
    // not following the naming convention (prefix set is not prepended)
    // to make it more clean while building object
    public StudentBuilder rollNo(int rollNo) {
        this.rollNo = rollNo;
        return this;
    }

    public StudentBuilder name(String name) {
        this.name = name;
        return this;
    }

    public abstract StudentBuilder subjects(List<String> subjects);

    public Student build() {
        return new Student(this);
    }

    public StudentBuilder builder() {
        return this;
    }
}

class EnggStudentBuilder extends StudentBuilder {

    @Override
    public StudentBuilder subjects(List<String> subjects) {
        this.subjects = subjects;
        return this;
    }
}

/**
 * Using Director layer you can create builder object according to business logic
 */
class Director {
    StudentBuilder studentBuilder;
    Director(StudentBuilder studentBuilder){
        // if student is of engg stream then add these subjects
        if(studentBuilder instanceof EnggStudentBuilder){
            this.studentBuilder = studentBuilder.builder().subjects(List.of("EG", "EXTC"));
        } else {
            this.studentBuilder = studentBuilder.builder().subjects(Collections.EMPTY_LIST);
        }
    }

    public Student createStudent(){
        return studentBuilder.build();
    }
}
