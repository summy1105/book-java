package ch09;

class Card3 extends Card{
    Card3(String kind, int number){
        super(kind, number);
    }
    Card3(){
        super();
    }

    public String toString() {
        return String.format("%s : $d", kind, number);
    }
}

public class ClassEx1 {
    public static void main(String[] args) throws Exception {
        Card3 c = new Card3("HEART", 3);
        Card3 c2 = Card3.class.newInstance(); // Class객체 통해서 객체 생성, parameter가 없는 생성자가 있어야함
        Class cObj = c.getClass();

        System.out.println(c);
        System.out.println(c2);
        System.out.println(cObj.getName());
        System.out.println(cObj.toGenericString());
        System.out.println(cObj.toString());
    }
}
