package rxjava_reactive_programming.chap03;

import io.reactivex.rxjava3.core.Observable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UsingMethodExample {
    public static void main(String[] args) {
        // 현재 디렉토리 경로를 가져와 example.txt 파일의 경로 설정
        String filePath = System.getProperty("user.dir") + "/src/main/java/rxjava_reactive_programming/chap03/example.txt";
        Observable<String> observable = Observable.using(
                // 리소스 생성: 파일을 열고 BufferedReader를 생성
                () -> {
                    System.out.println("Resource open");
                    return new BufferedReader(new FileReader(filePath));
                },
                // 리소스를 사용하여 Observable을 생성
                reader -> Observable.create(emitter -> {
                    try {
                        String line;
                        while ((line = reader.readLine()) != null && !emitter.isDisposed()) {
                            emitter.onNext(line);
                        }
                        emitter.onComplete();
                    } catch (IOException e) {
                        emitter.onError(e);
                    }
                }),

                // 리소스 해제: BufferedReader를 닫음
                reader -> {
                    try {
                        reader.close();
                        System.out.println("Resource (BufferedReader) closed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

        // 구독하여 파일을 읽음
        observable.subscribe(
                line -> System.out.println("Read: " + line),
                Throwable::printStackTrace,
                () -> System.out.println("Reading completed")
        );
    }
}
