package ch09;

class Circle implements Cloneable{
    Point p;
    double r;

    Circle(Point p, double r){
        this.p = p;
        this.r = r;
    }

    public Circle shallowCopy() { //얕은 복사
        Object obj = null;

        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {}
        return (Circle) obj;
    }

    public Circle deepCopy() {
        Circle c = this.shallowCopy();
        c.p = c.p.cloneToPoint();
        return c;
    }

    public String toString() {
        return String.format("[p= %s, r=%.2f ]", p, r);
    }
}

public class ShallowDeepCopy {
    public static void main(String[] args) {
        Circle c1 = new Circle(new Point(1,1), 2.0);
        Circle c2 = c1.shallowCopy();
        Circle c3 = c1.deepCopy();

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);

        c1.p.x = 9; c1.p.y=9;
        c1.r =3.0;
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
    }
}
