package modernaction.chap13;

public class C extends D implements B, A {
    public static void main(String[] args) {
        new C().hello(); //  D는 직접 구현X, 2번 규칙에 의해 B의 메소드 호출

        // default method 해석 규칙
        // 1. 해당 클래스에 정의 되어 있으면 우선권을 같는다.
        // 2. 서브 인터페이스
        // 3. 1,2로 우선순위가 정의 되지 않으면, 오버라이드 해야함
    }
}
