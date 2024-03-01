package standard.ch09;

class Point implements Cloneable{
    int x, y;

    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
//    public String toString(){
//        return String.format("x = %d, y = %d", x, y);
//    }

    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

    public Object clone(){
        Object obj = null;
        try {
            obj = super.clone();
        }catch (CloneNotSupportedException e){ }
        return obj;
    }
    public Point cloneToPoint() {
        return (Point)this.clone();
    }
}

public class CloneEx1 {
    public static void main(String[] args) {
        Point original = new Point(3,5);
        Point copy = (Point)original.clone();
        System.out.println(original);
        System.out.println(copy);
        System.out.println(original.cloneToPoint());
    }
}
