package standard.ch12.enumabstract;

public enum Transportation {
    BUS(100) { int fare(int distance){ return distance*BASIC_FARE; } } // 추상 메소드 구현 -0-
    , TRAIN(150){ int fare(int distance){ return distance*BASIC_FARE; } }
    , SHIP(100){ int fare(int distance){ return distance*BASIC_FARE; } }
    , AIRPLANE(300){ int fare(int distance){ return distance*BASIC_FARE; } }
    ;
    protected final int BASIC_FARE;

    Transportation(int basic_fare) {
        BASIC_FARE = basic_fare;
    }
    
    //추상 메서드
    abstract int fare(int distance);
    
    public int getBasicFare() {return BASIC_FARE; }
}
