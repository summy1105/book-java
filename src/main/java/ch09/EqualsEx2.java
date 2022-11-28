package ch09;

public class EqualsEx2 {
    public static void main(String[] args) {
        Person p1 = new Person(20110811112222L);
        Person p2 = new Person(20110811112222L);

        if(p1==p2)
            System.out.println("equals");
        else
            System.out.println("different");

        if(p1.equals(p2))
            System.out.println("equals");
        else
            System.out.println("different");
    }
}

class Person {
    long id;

    public boolean equals(Object obj){ // String이 이런 방식으로 비교함
        if(obj!= null && obj instanceof Person)
            return id == ((Person)obj).id;
        else
            return false;
    }

    Person(Long id){
        this.id = id;
    }
}
