package designpatterns.creational;

import java.util.StringJoiner;

/**
 * Useful to create copy or clone of the existing object
 */
public class PrototypeDP {
    public static void main(String[] args) {
        /**
         * we can clone existing object easily from client class
         * e.g.
         * Student s1 = new Student(123, "abc");
         * Student s2 = new Student();
         * s2.setId(s1.getId());
         *
         * but this will not be clean code and if some instance variables including get method is private we cannot clone it
         * hence the cloning is the responsibility of that particular entity class
         */

        Address address = new Address();
        StudentClazz s1 = new StudentClazz(123, "abc", address);
        StudentClazz s2 = (StudentClazz) s1.clone();

        System.out.println(s2);
        // both reference to separate object
        System.out.println(s1 == s2);

        /**
         * this type of cloning creates shallow copy of object
         * because embedded objects within Student class will not be cloned
         * e.g.
         * Address class object in student will not be cloned
         *
         * To make deep copy, we can have dedicated clone() method implementation for embedded classes
         */
        // both reference to same object
        System.out.println(s1.getAddress() == s2.getAddress());
    }
}


/**
 * Will create an interface so that method signature would be same
 */
interface Prototype {
    Prototype clone();
}

class StudentClazz implements Prototype {

    private int id;
    private String name;
    private Address address;

    public StudentClazz(int id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    @Override
    public Prototype clone() {
        return new StudentClazz(this.id, this.name, this.address);
    }

    public Address getAddress() {
        return this.address;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", StudentClazz.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("address=" + address)
                .toString();
    }
}

class Address {

}