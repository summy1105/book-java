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
- int max : 약 20억, long max : 약 92경 => 0.92 * 10^19 => 숫자 19자리
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
- 배열에서 모든 경우의 수(순열 : permute)를 구하는 재귀함수
  - ```java
    // dist 배열의 순열을 생성하는 재귀 함수 (깊이 idx부터)
    private void permute(int[] arr, int idx, ArrayList<int[]> perms) {
        if (idx == arr.length) {
            perms.add(arr.clone());
            return;
        }
        for (int i = idx; i < arr.length; i++) {
            swap(arr, idx, i);
            permute(arr, idx + 1, perms);
            swap(arr, idx, i);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
    }
    ```
- Arrays.hashcode()를 쓰지 말자


#### 주운팁
- ```new char[1] == new char[]{'\0'}``` 과 같다 : ```"A".equals(new String(new char[1]).replaceAll("\0", "A")) == true```
- 제곱수인 경우 약수의 개수가 홀수 [코드](./programmers/lv1/TheNumberAndAdditionOfDivisors.java)
- ``` "123456".matches("^[\\d]{4}|[\\d]{6}$") ``` 텍스트 길이가 4 혹은 6이고, 숫자로만 구성되어 있는 지 검사 
- 문제 https://school.programmers.co.kr/learn/courses/30/lessons/12924
  - 주어진 자연수를 연속된 자연수의 합으로 표현하는 방법의 수는 주어진 수의 홀수 약수의 개수와 같다라는 정수론 정리가 있습니다.
- 자연수의 공약수 갯수를 구하는 로직 : [문제](https://school.programmers.co.kr/learn/courses/30/lessons/136798), [문제2](./programmers/lv3/MemorizingBigMultiplicationTable.java)
  - ```java
        // 여러개의 숫자의 공약수 갯수를 구할때는 아래보다 이 로직이 빠름
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

##### 다른 알고리즘
- 슬라이딩 윈도우, 카데인알고리즘, Difference Array Technique(차이배열기법), Manacher’s Algorithm, Tree DP(leaf부터 계산함), 
- 다중점 다익스트라(시작점이 여러개인 경우), 세그먼트 트리, 펜윅트리, 라인스위핑, 위상정렬, 최소공통조상, 네트워크플로우, 이분매칭, 최대플로우, 최소컷, 트라이, 비트마스크, 레드블랙트리, AVL트리, 스플레
- [알고리즘 분류](https://www.acmicpc.net/problem/tags)

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
- [리코쳇 로봇](https://school.programmers.co.kr/learn/courses/30/lessons/169199) : dfsX
- [여행경로](./programmers/lv3/TravelRoute.java) : TreeSet 유념하기
- [연속 펄스 부분 수열의 합](https://school.programmers.co.kr/learn/courses/30/lessons/161988) : 부분 수열의 합은 카데인 알고리즘으로 풀수있다.
- [가장 큰 정사각형 찾기](https://school.programmers.co.kr/learn/courses/30/lessons/12905)
- [경주로 건설](https://school.programmers.co.kr/learn/courses/30/lessons/67259)
- [디스크 컨트롤러](https://school.programmers.co.kr/learn/courses/30/lessons/42627) : 예외케이스 생각하기
- [가장 긴 팰린드롬](https://school.programmers.co.kr/learn/courses/30/lessons/12904) : Manacher’s Algorithm 학습하기[코드](./programmers/lv3/LongestPalindrome.java)
- [거스름돈](https://school.programmers.co.kr/learn/courses/30/lessons/12907)
- [순위](https://school.programmers.co.kr/learn/courses/30/lessons/49191) : 플로이드로 다시 풀어보기
- [파괴되지 않은 건물](https://school.programmers.co.kr/learn/courses/30/lessons/92344)
- [매출 하락 최소화](https://school.programmers.co.kr/learn/courses/30/lessons/72416) : 트리dp
- [후보키](./programmers/lv2/KaKaoCandidateKey.java) : 비트 연산
- [두 원 사이의 정수 쌍](https://school.programmers.co.kr/learn/courses/30/lessons/181187)
- [표 편집](./programmers/lv3/KaKaoEditTable.java)
- [숫자 블록](https://school.programmers.co.kr/learn/courses/30/lessons/12923) : 문제 잘 읽기
- [조이스틱](https://school.programmers.co.kr/learn/courses/30/lessons/42860)
- [기둥과 보 설치](./programmers/lv3/KaKaoInstallationPillarBeam.java)
- [광고 삽입](https://school.programmers.co.kr/learn/courses/30/lessons/72414): 문제별로 메모리 제한이 다름
- [무지의 먹방 라이브](https://school.programmers.co.kr/learn/courses/30/lessons/42891)
- [올바른 괄호 갯수](https://school.programmers.co.kr/learn/courses/30/lessons/12929)
- [110 옮기기](./programmers/lv3/Move110.java) : 순회와 아닌거, 메소드 잘 파악하기
- [쿠키구입](https://school.programmers.co.kr/learn/courses/30/lessons/49995)
- [외벽점검](./programmers/lv3/KakaoCheckExteriorWall.java)
- [표현 가능한 이진트리](./programmers/lv3/KakaoExpressableBinaryTree.java)
- [충돌위험 찾기](https://school.programmers.co.kr/learn/courses/30/lessons/340211) : 문제 좀 잘읽자
- [자동완성](./programmers/lv4/KaKaoAutocomplete.java)
- [스타수열](https://school.programmers.co.kr/learn/courses/30/lessons/70130) : 쉽게 생각하자
- [3*n 타일링](https://school.programmers.co.kr/learn/courses/30/lessons/12902)
- [자동완성](./programmers/lv4/KaKaoAutocomplete.java)
- [N으로 표현](./programmers/lv4/ExpressedN.java)
- [등산코스 정하기](https://school.programmers.co.kr/learn/courses/30/lessons/118669)
- [아이템 줍기](./programmers/lv3/PickingUpItems.java)
- [카운트다운](https://school.programmers.co.kr/learn/courses/30/lessons/131129)
- [블록 이동하기](./programmers/lv3/KaKaoMovingBlock.java)
- [표병합](./programmers/lv3/KaKaoMergeTable.java)
- [최적의 행렬곱셈](./programmers/lv3/OptimalMatrixMultiplication.java)
- [모두 0으로 만들기](./programmers/lv3/MakingAllZero.java)
- [봉인된 주문](./programmers/lv3/SealedSpell.java)
- [산모양 타일링](./programmers/lv3/MountainShapeTiling.java)
- [단어퍼즐](./programmers/lv4/WordPuzzle.java)
- [유사 칸토어 비트열](./programmers/lv2/AnalogousCantorSet.java) : 정석방법, 변칙방법 2가지
- [공 이동 시뮬레이션](./programmers/lv3/BallMovementSimulation.java) : 구현 예외 케이스 생각하기