# Table of contents
1. [S.O.L.I.D. Principles](#paragraph1)
2. [Design Patterns](#paragraph2)
    1. [Creational Design Patterns](#subparagraph1)
    2. [Structural Design Patterns](#subparagraph2)
    3. [Behavioral Design Patterns](#subparagraph3)

## S.O.L.I.D. Principles<a name="paragraph1"></a>
Helps to write quality code which is easy to maintain, understand and extend. Following these principles while creating software will help is reduce code smell and complexity.

- S : Single Responsibility Principle
  - Class should have only one reason to change or only one responsiblity
- O : Open / Close Principle
  - Open for extension and close for modification
- L : Liskov Substitution Principle
  - Subclass should extend all the functionality of parent class and should not narrow it donw
- I : Interface Segmented/Seggregation Principle
  - Interface should be in such way that, implementing classes should not have to implement unnecessary methods
- D : Dependency Inversion Principle
  - Class should depend on interface instead of concrete class
  
[Learn More](https://www.digitalocean.com/community/conceptual-articles/s-o-l-i-d-the-first-five-principles-of-object-oriented-design){:target="_blank"}

## Design Patterns<a name="paragraph2"></a>
A well-described solution to a common software problem.

Java design patterns are divided into three categories - creational, structural, and behavioral design patterns.

[Learn More](https://www.digitalocean.com/community/tutorials/java-design-patterns-example-tutorial){target="_blank"}

### Creational Design Patterns<a name="subparagraph1"></a>
Creational design patterns provide solutions to instantiate an Object in the best possible way for specific situations.

#### Types of Creational Design Patterns

1. Factory
    1. Used to create object based on some conditions
2. Abstract Factory
    1. Factory of factories
    2. Get rid of if-else or switch-case
3. Singleton
    1. Used to make sure only one object is created for a class
    2. Ways to achieve singleton patter:
       1. Eager Initialization
       2. Lazy Initialization
       3. Synchronized method
       4. Double Locking {*recommended because it's thread-safe and performant*}
4. Prototype
    1. Used to make clone/copy of existing object
5. Builder
    1. Step-By-Step object creation
    2. Used if class has a lot of optional fields

### Structural Design Patterns<a name="subparagraph2"></a>
Structural design patterns provide different ways to create a Class structure (for example, using inheritance and composition to create a large Object from small Objects).

### Behavioral Design Patterns<a name="subparagraph3"></a>
Behavioral patterns provide a solution for better interaction between objects and how to provide loose-coupling and flexibility to extend easily.
