package designpatterns.structural;

import java.util.LinkedList;
import java.util.List;

/**
 * Used when you have to represnt part-whole hierarchy(tree structure) &
 * each object in hierarchy needs to treated in the same way
 */
public class CompositeDP {
    /**
     * E.g. FileSystem can have directories and files
     * Directory can have multiple files...which forms the tree like structure
     *
     * Base Component: interface for all objects in tree
     * Leaf: implements base component and doesn't have reference to other objects
     * Composite: implements base component and has reference to other objects
     */
    public static void main(String[] args) {
        FileSystem movies = new Directory("Movies");
        FileSystem border = new File("Border");
        FileSystem titanic = new File("Titanic");
        FileSystem tangled = new File("Tangled");
        FileSystem hindMovies = new Directory("Hindi-Movies");
        FileSystem englishMovies = new Directory("English-Movies");
        ((Directory)englishMovies).add(titanic);
        ((Directory)englishMovies).add(tangled);
        ((Directory)hindMovies).add(border);

        ((Directory)movies).add(englishMovies);
        ((Directory)movies).add(hindMovies);

        movies.printName();
    }
}

// base component
interface FileSystem {
    void printName();
}

class File implements FileSystem {

    private final String name;

    public File(String name){
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println("File Name : " + this.name);
    }
}

class Directory implements FileSystem {

    private final String name;
    private List<FileSystem> fsObjects;

    public Directory(String name){
        this.name = name;
        fsObjects = new LinkedList<>();
    }

    public void add(FileSystem fs){
        this.fsObjects.add(fs);
    }

    @Override
    public void printName() {
        System.out.println("Directory Name : " + this.name);

        for (FileSystem fs: fsObjects) {
            fs.printName();
        }
    }
}