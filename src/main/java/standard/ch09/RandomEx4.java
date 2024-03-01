package standard.ch09;

public class RandomEx4 {
    final static int RECORD_NUM = 10;
    final static String TABLE_NAME = "TEST_TABLE";
    final static String[] CODE1 = {"010", "011", "017", "018", "019"};
    final static String[] CODE2 = {"Xy", "XX"};
    final static String[] CODE3 = {"10", "20", "30", "40", "50"};

    public static void main(String[] args) {
        for (int i = 0; i < RECORD_NUM; i++) {
            System.out.printf("INSERT INTO %s VALUES ('%s', '%s', '%s', %d);\n"
                    , TABLE_NAME, getRandArr(CODE1), getRandArr(CODE2), getRandArr(CODE3), getRand(100, 200));
        }
    }

    public static String getRandArr(String[] arr){
        return arr[getRand(arr.length-1)];
    }

    public static int getRand(int n){ return getRand(0, n); }

    public static int getRand(int from, int to){
        return (int)(Math.random() * (Math.abs(to-from)+1))+Math.min(from, to);
    }
}
