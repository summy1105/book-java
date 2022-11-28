package ch09;

public class EqualsEx1 {
    public static void main(String[] args) {
        Value v1 = new Value(10);
        Value v2 = new Value(10);

        if(v1.equals(v2))
            System.out.println("equals");
        else
            System.out.println("different");

        v1 = v2;
        if(v1.equals(v2))
            System.out.println("equals");
        else
            System.out.println("different");
    }
}

class Value {
    int value;
    Value(int value){
        this.value = value;
    }
}
