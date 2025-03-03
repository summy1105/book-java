### 실전 준비
1. 하루에 1~3개 알고리즘 씩 돌아가면서 풀기
   - 각각 1~2씩 문제 풀기
2. 한 문제에 30분 넘기지 않기
3. 오답노트 -> 틀린문제 복습하면서 반복
4. 실전처럼 연습하기


### 오답노트
- 알고리즘 별 정리
- 문제 유형을 스스로 요약
- 틀린문제 주기적으로 풀기
- 면접과 코테가 반복될 때 유용


### 스터디
- 시험환경 노출
- 두시간에 4문제
- 난이도 : 실버1 ~ 골드1(백준), 레벨3(프로그래머스), Leetcode(Medium)


#### 까먹지 말것
- 최대공약수 구하는 알고리즘 : [유클리드 호제법](./programmers/lv0/AdditionOfFractions.java) - gcdRecursion()
- int max : 약 20억, long max : 약 920경 => 0.92 * 10^19 => 숫자 19자리
- ```"  asb cdk  ".split(" ", -1)```=> {"" ,"", "asb", "cdk", "", ""} 이렇게 생성됨
- regex - matcher 활용 : [코드](./programmers/lv2/JadenCaseString.java)
- 소수(prime number) 확인
    - 제곱근까지 나누어지는지 확인해도 된다. ```Math.sqrt(number)```
  - ```java
        boolean[] check = new boolean[n+1];
        for(int i=2; i<= n ; i++){
            if(check[i] == false){
                for(int j=i*2; j<=n ; j+=i) check[j] = true;
            }
        }
    ```


#### 주운팁
- ```new char[1] == new char[]{'\0'}``` 과 같다 : ```"A".equals(new String(new char[1]).replaceAll("\0", "A")) == true```
- 제곱수인 경우 약수의 개수가 홀수 [코드](./programmers/lv1/TheNumberAndAdditionOfDivisors.java)
- ``` "123456".matches("^[\\d]{4}|[\\d]{6}$") ``` 텍스트 길이가 4 혹은 6이고, 숫자로만 구성되어 있는 지 검사 
- 문제 https://school.programmers.co.kr/learn/courses/30/lessons/12924
  - 주어진 자연수를 연속된 자연수의 합으로 표현하는 방법의 수는 주어진 수의 홀수 약수의 개수와 같다라는 정수론 정리가 있습니다.
- 자연수의 공약수 갯수를 구하는 로직 : [문제](https://school.programmers.co.kr/learn/courses/30/lessons/136798)
  - ```java
        // count 배열의 인덱스가 자연수이고 각의 수를 구함
        for (int i = 1; i <= number; i++) {
            for (int j = 1; j <= number / i; j++) {
                count[i * j]++;
            }
        }
    ```
  - ```java
        if(Math.sqrt(n)%1==0)number++;
        for(int i=1; i<Math.sqrt(n);i++){
            if(n%i==0) number+=2; // n과 제곱근보다 큰수인 약수를 한번에 계산
        }
    ```
    
#### 주요 알고리즘
- 스택, 큐, 백트래킹, bfs, dfs, 이진탐색, 정렬, 그리디, 투포인터, 다이나믹프로그래밍(점화식), 미니멈스패팅트리, 다익스트라, 플로이드
- DP 고려 케이스
  - “최대”, “최소”, “경우의 수” 등을 구할 때
  - 이전의 결과를 재사용해야 하거나, 결정에 따라 미래의 결과가 영향을 받는 경우
  - DP는 메모리를 많이 사용할 수 있으므로 메모리 제한이 있는 문제에서는 주의
- Greedy 고려 케이스
  - 각 단계에서 최선의 선택이 전체 최적해로 이어질 수 있는 경우
  - 문제에 “가장 큰 값”, “가장 작은 값” 등을 요구하며, 각 선택이 독립적일 때
- 스택/큐 고려 케이스
  - 순차적으로 데이터를 처리해야 하고, 특정 시점에 “최근” 데이터(스택)나 “먼저 들어온” 데이터(큐)가 필요할 때
- dfs/bfs 선택 기준
  - 최단 경로나 최소 이동 횟수를 요구한다면 BFS(ex.미로 문제: 시작점에서 목표까지 최단 경로)
  - 모든 가능한 경로 탐색이나 백트래킹이 필요하면 DFS(ex. 퍼즐 문제: 가능한 모든 이동 경로를 탐색)

#### 다시볼것
- [가장큰 돗자리 구하기](./programmers/lv1/PCCE10Park.java)
- [동영상재생기](./programmers/lv1/PCCP1VideoPlayer.java)
- [공원산책](./programmers/lv1/WalkInThePark.java)
- [아날로그 시계](./programmers/lv2/AnalogClock.java)
- [게임 맵 최단거리](./programmers/lv2/BfsGameMapShortestDistance.java)
- [카펫](./programmers/lv2/Carpet.java)
- [의상](./programmers/lv2/Clothes.java)
- [타겟 넘버](./programmers/lv2/DfsTargetNumber.java)
- [예상 대진표](./programmers/lv2/EstimatedMatchSheet.java)
- [피로도](./programmers/lv2/FatigueLevel.java)
- [H-Index](./programmers/lv2/HIndex.java)
- [JadenCase 문자열 만들기](./programmers/lv2/JadenCaseString.java)
- [빛의 경로 사이클](./programmers/lv2/LightPathCycle.java)
- [멀리 뛰기](./programmers/lv2/LongJump.java)
- [다음 큰 숫자](./programmers/lv2/NextBigNumber.java)
- [N개의 최소공배수](./programmers/lv2/NLeastCommonMultiple.java)
- [연속 부분 수열 합의 개수](./programmers/lv2/NumberOfConsecutivePartialSequenceSums.java)
- [프로세스](./programmers/lv2/Process.java)
- [연속된 부분 수열의 합](./programmers/lv2/SumOfConsecutiveSubsequences.java)
- [단어 변환](./programmers/lv3/BfsCountingWordConversion.java)
- [네트워크](./programmers/lv3/BfsNetwork.java)
- [뉴스 클러스터링](./programmers/lv2/KakaoNewsClustering.java)
- [모음사전](./programmers/lv2/VowelDictionary.java)
- [야근 지수](./programmers/lv3/IndexOvertimeWork.java)
- [뒤에 있는 큰 수 찾기](./programmers/lv2/FindLargeNumberBehind.java)
- [땅따먹기](./programmers/lv2/Hopscotch.java)
- [스킬트리](https://school.programmers.co.kr/learn/courses/30/lessons/49993) : 더 나은 수준으로 다시 풀기
- [택배상자](https://school.programmers.co.kr/learn/courses/30/lessons/131704)
- [숫자 변환하기](https://school.programmers.co.kr/learn/courses/30/lessons/154538) : 경우의 수를 줄일수 있는 방법을 생각해 보자
- [파일명 정렬](./programmers/lv2/KakaoSortFileNames.java) : Arrays 정렬 이용 방법도 잇음
- [가장 큰수](./programmers/lv2/LargestNumber.java) : 정렬 기준을 잘 생각해 보자
- [큰 수 만들기](./programmers/lv2/MakeBigNumber.java) : 알고리즘 분류된것으로 풀면 안됨
- [스티커모으기](https://school.programmers.co.kr/learn/courses/30/lessons/12971)
- [불량 사용자](https://school.programmers.co.kr/learn/courses/30/lessons/64064) : regex, boolean 배열대신 bit연산 사용하는 방법으로 다시
- [미로탈출](https://school.programmers.co.kr/learn/courses/30/lessons/159993) : 더 적절한 방법으로 다시 풀기
- [메뉴 리뉴얼](./programmers/lv2/KaKaoMenuRenewal.java) : 후보선정을 줄일수 있는 방법 생각하기
- [보석쇼핑](https://school.programmers.co.kr/learn/courses/30/lessons/67258) : 이중포문X
- [징검다리 건너기](https://school.programmers.co.kr/learn/courses/30/lessons/64062)