package designpatterns.structural;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/**
 * Used when we need to create lots of objects of class and
 * there is memory constraint
 */
public class FlyweightDP {
    /**
     * Since each object takes memory space, sharing object becomes crucial in low memory devices
     * e.g. mobile or embedded system
     * Flyweight design pattern should be used in below cases:
     * 1. huge objects to be created and objects initialization is memory intensive task
     * 2. objects can be divides into intrinsic and extrinsic properties
     *  2.1 intrinsic properties -> makes the object unique
     *  2.2 extrinsic properties -> provided by client to perform some operations
     *
     * eg. Shapes -> circle, square, line
     * shapeType is intrinsic property
     * coordinates of shape is extrinsic property
     *
     * The sharing of object is done using flyweight factory
     */
    public static void main(String[] args) {
        IShape shape;
        for (int i = 0; i < 20; i++) {
            if(i%2==0){
                shape = FlyweightFactory.getShape("CIRCLE");
            }else{
                shape = FlyweightFactory.getShape("LINE");
            }
            shape.draw(i+1, i+2, i+3, i+4);
        }
        /**
         * you will see for the first time...object creation takes time...after that operations are quick
         */
    }
}

interface IShape {
    void draw(int x1, int y1, int x2, int y2);
}

class CircleShape implements IShape {

    private final String shapeType;

    public CircleShape() {
        // intentionally adding some delay
        // to simulate that object creation is heavy task
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.shapeType = "CIRCLE";
    }

    // intrinsic property is readOnly property
    public String getShapeType() {
        return shapeType;
    }

    @Override
    public void draw(int x1, int y1, int x2, int y2) {
        System.out.println("Drawing: "+ shapeType);
        System.out.printf("Start: (%d,%d) <> End: (%d,%d)\n",x1, y1, x2, y2);
    }
}

class LineShape implements IShape {

    private final String shapeType;

    public LineShape() {
        // intentionally adding some delay
        // to simulate that object creation is heavy task
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.shapeType = "LINE";
    }

    // intrinsic property is readOnly property
    public String getShapeType() {
        return shapeType;
    }

    @Override
    public void draw(int x1, int y1, int x2, int y2) {
        System.out.println("Drawing: "+ shapeType);
        System.out.printf("Center: (%d,%d) <> End: (%d,%d)\n",x1, y1, x2, y2);
    }
}


// Flyweight factory
class FlyweightFactory {
    private static Map<String, IShape> shapes = new HashMap<>();

    public static IShape getShape(String shapeType){
        IShape shape = shapes.get(shapeType.toUpperCase(Locale.ROOT));
        if(Objects.isNull(shape)){
            if("CIRCLE".equalsIgnoreCase(shapeType)){
                shape = new CircleShape();
            } else if ("Line".equalsIgnoreCase(shapeType)) {
                shape = new LineShape();
            } else{
                shape = (x1, y1, x2, y2) -> {
                    System.out.println("Unknown Shape");
                };
            }
            shapes.put(shapeType, shape);
        }
        return shape;
    }
}