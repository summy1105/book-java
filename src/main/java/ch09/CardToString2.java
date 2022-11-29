package ch09;

class Card2 extends Card {
    public String toString(){
        return String.format("kind : %s, number : %d", kind, number);
    }
}

public class CardToString2 {
    public static void main(String[] args) {
        Card2 c1 = new Card2();
        Card2 c2 = new Card2();
        System.out.println(c1.toString());
        System.out.println(c2);
    }
}
