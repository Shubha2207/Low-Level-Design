package designpatterns.behavioral;

import java.util.List;

/**
 * used when we have to perform operations on similar kind of Elements.
 * we can add new functionality without modifying the exiting element class
 */
public class VisitorDP {
    public static void main(String[] args) {
        /**
         * Let's say we have two elements ConcreteElementA and ConcreteElementB
         * these two elements have their own implementations
         *
         * but if we need to add a new functionality without modifying them we can use visitor pattern
         *
         * Each visitor represents a different functionality/algorithm
         */
        List<Element> elementList = List.of(new ConcreteElementA("Bat"),
                new ConcreteElementA("ball"),
                new ConcreteElementB("fruItS"),
                new ConcreteElementB("pEn"),
                new ConcreteElementA("sTiCker"));

        Visitor v1 = new Visitor1();
        Visitor v2 = new Visitor2();

        // Visitor v1
        for (Element element : elementList) {
            element.accept(v1);
        }

        // Visitor v2
        for (Element element : elementList) {
            element.accept(v2);
        }

        /**
         * as you can see here, we introduced two different functionalities to these elements
         * without modifying the class definition
         *
         * downside: if we add new concrete-element then each visitor has to be updated
         */
    }
}

/**
 * Elements : accepts the visitor(functionality) that they want to perform
 */
interface Element {
    void accept(Visitor v);
}

class ConcreteElementA implements Element {

    private String name;

    public ConcreteElementA(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}

class ConcreteElementB implements Element {

    private String name;

    public ConcreteElementB(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}


/**
 * Visitor: represent functionality that need to be performed on elements
 */
interface Visitor {
    void visit(ConcreteElementA concreteElementA);
    void visit(ConcreteElementB concreteElementB);
}

// this visitor displays upper case name of element
class Visitor1 implements Visitor {

    @Override
    public void visit(ConcreteElementA concreteElementA) {
        System.out.println("Concrete Element A : Upper Case :" + concreteElementA.getName().toUpperCase());
    }

    @Override
    public void visit(ConcreteElementB concreteElementB) {
        System.out.println("Concrete Element B : Upper Case :" + concreteElementB.getName().toUpperCase());
    }
}

// this  visitor displays lower case name of element
class Visitor2 implements Visitor {

    @Override
    public void visit(ConcreteElementA concreteElementA) {
        System.out.println("Concrete Element A : Lower Case :" + concreteElementA.getName().toLowerCase());
    }

    @Override
    public void visit(ConcreteElementB concreteElementB) {
        System.out.println("Concrete Element B : Lower Case :" + concreteElementB.getName().toLowerCase());
    }
}