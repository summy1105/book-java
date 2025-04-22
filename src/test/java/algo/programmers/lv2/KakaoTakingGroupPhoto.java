package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/1835 lv2

public class KakaoTakingGroupPhoto {
    char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    int[] friendsIdx = new int[26];
    public int solution(int n, String[] data) {
        // data미리 split
        ArrayList<char[]> datas = new ArrayList<>();
        for(String dt : data){
            datas.add(dt.toCharArray());
        }

        // 모든 경우의 수
        int[] friendPosition = new int[8];
        for(int i=0; i<friends.length; i++){
            friendsIdx[friends[i]-'A'] = i;
            friendPosition[i] = i+1;
        }
        ArrayList<int[]> perms = new ArrayList<>();
        permute(friendPosition, 0, perms);

        int answer = 0;
        for(int[] position :perms){
            boolean isPass = true;
            for(int i=0; isPass && i<datas.size(); i++){
                char[] dt = datas.get(i);
                int firstIdx = friendsIdx[dt[0]-'A'];
                int secondIdx = friendsIdx[dt[2]-'A'];
                int diff = Math.abs(position[firstIdx] - position[secondIdx]);
                int valid = dt[4]-'0';
                if(dt[3] == '='){
                    if(diff-1 != valid)
                        isPass = false;
                }else if(dt[3] == '<'){
                    if(diff-1 >= valid)
                        isPass = false;
                }else { //dt[3] == '>'
                    if(diff-1 <= valid)
                        isPass = false;
                }
            }
            if(isPass) answer++;
        }
        return answer;
    }

    void permute(int[] arr, int idx, ArrayList<int[]> perms){
        if(idx == arr.length){
            perms.add(arr.clone());
        }
        for(int i=idx; i<arr.length; i++){
            swap(arr, idx, i);
            permute(arr, idx+1, perms);
            swap(arr, idx, i);
        }
    }

    void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    //2	["N~F=0", "R~T>2"]	3648
    @Test
    public void test() {
        KakaoTakingGroupPhoto kakaoTakingGroupPhoto = new KakaoTakingGroupPhoto();
        int n = 2;
        String[] data = {"N~F=0", "R~T>2"};
        int result = kakaoTakingGroupPhoto.solution(n, data);
        Assertions.assertThat(result).isEqualTo(3648);
    }

    //2	["M~C<2", "C~M>1"]	0
    @Test
    public void test2() {
        KakaoTakingGroupPhoto kakaoTakingGroupPhoto = new KakaoTakingGroupPhoto();
        int n = 2;
        String[] data = {"M~C<2", "C~M>1"};
        int result = kakaoTakingGroupPhoto.solution(n, data);
        Assertions.assertThat(result).isEqualTo(0);
    }
}
