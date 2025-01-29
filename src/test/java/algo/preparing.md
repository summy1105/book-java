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


#### 주운팁
- ```new char[1] == new char[]{'\0'}``` 과 같다 : ```"A".equals(new String(new char[1]).replaceAll("\0", "A")) == true```
- 제곱수인 경우 약수의 개수가 홀수 [코드](./programmers/lv1/TheNumberAndAdditionOfDivisors.java)
- ``` "123456".matches("^[\\d]{4}|[\\d]{6}$") ``` 텍스트 길이가 4 혹은 6이고, 숫자로만 구성되어 있는 지 검사 
- 문제 https://school.programmers.co.kr/learn/courses/30/lessons/12924
  - 주어진 자연수를 연속된 자연수의 합으로 표현하는 방법의 수는 주어진 수의 홀수 약수의 개수와 같다라는 정수론 정리가 있습니다.

#### 주요 알고리즘
- 스택, 큐, 백트래킹, bfs, dfs, 이진탐색, 정렬, 그리디, 투포인터, 다이나믹프로그래밍(점화식), 미니멈스패팅트리, 다익스트라, 플로이드 