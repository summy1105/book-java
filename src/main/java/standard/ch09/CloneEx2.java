package standard.ch09;

public class CloneEx2 {
    // clone이 가느ㅇ한지 확인 방법은 java api에서 cloneable구현 했는지 확인
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        int[] arrClone = arr.clone();
        arrClone[0] = 6 ;

        System.out.println(arr);
        System.out.println(arrClone);
    }
}
