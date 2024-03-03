package standard.ch13thread;

public class ThreadRunnableEx21 implements Runnable {

    Account acc = new Account();

    @Override
    public void run() {
        while (acc.getBalance() >0 ){
            int money = (int) (Math.random() * 3+1)*100;
            acc.withdraw(money);
            System.out.println("acc.getBalance() = " + acc.getBalance());
        }
    }

    static class Account {
        private int balance = 1000;

        public int getBalance(){
            return balance;
        }

        // block 보다 method에 추천
        public synchronized void withdraw(int money) {
            if (balance >= money) {
                try{Thread.sleep(1000);} catch (InterruptedException e){}
                balance -= money;
            }
        }
//        public void withdraw(int money) {
//            synchronized (this) {
//                if (balance >= money) {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                    }
//                    balance -= money;
//                }
//            }
//        }
    }
}
