package solidPrinciples.d;

/**
 * D : Dependency Inversion Principle
 * Class should not be dependent on concrete class, instead it should be dependent on interface
 */
public class D {
    public static void main(String[] args) {
        /**
         * here we are bound to use wired keyboard and mouse
         * because Macbook class is dependent on concrete classes
         */
        Macbook macbook = new Macbook(new WiredMouse(), new WiredKeyboard());
        macbook.wk.getKeys();
        macbook.wm.getDpi();

        /**
         * here we can use any type of mouse or keyboard
         * because Windows class is dependent on interfaces
         */
        Windows windows = new Windows(new BluetoothMouse(), new WiredKeyboard());
        windows.k.getKeys();
        windows.m.getDpi();
    }
}

interface Mouse {
    void getDpi();
}

class WiredMouse implements Mouse {

    @Override
    public void getDpi() {
        System.out.println("500 DIP for wired mouse");
    }
}

class BluetoothMouse implements Mouse {

    @Override
    public void getDpi() {
        System.out.println("500 DIP for bluetooth mouse");
    }
}

interface Keyboard {
    void getKeys();
}

class WiredKeyboard implements Keyboard {

    @Override
    public void getKeys() {
        System.out.println("No. of keys 85");
    }
}

class Macbook {
    WiredKeyboard wk;
    WiredMouse wm;

    Macbook(WiredMouse wm, WiredKeyboard wk) {
        this.wk = wk;
        this.wm = wm;
    }
}

class Windows {
    Keyboard k;
    Mouse m;

    Windows(Mouse m, Keyboard k) {
        this.m = m;
        this.k = k;
    }
}