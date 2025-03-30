package algo.programmers.lv4;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// https://school.programmers.co.kr/learn/courses/30/lessons/64063 lv4


public class KaKaoHotelRoomAllocation {
    public long[] solution(long k, long[] room_number) {
        HashMap<Long, Long> roomNext = new HashMap<>();
        long[] answer = new long[room_number.length];
        for(int i=0; i<room_number.length; i++){
            long nextRoom = room_number[i];
            List<Long> checkList = new ArrayList<>();
            while(roomNext.containsKey(nextRoom)){
                checkList.add(nextRoom);
                nextRoom = roomNext.get(nextRoom);
            }
            answer[i] = nextRoom;
            checkList.add(nextRoom);
            for (Long check : checkList) {
                roomNext.put(check, nextRoom + 1);
            }

        }
        return answer;
    }

    @Test
    public void ex1() {
        long[] result = solution(10, new long[]{1,3,4,1,3,1});
        Assertions.assertThat(result).isEqualTo(new long[]{1,3,4,2,5,6});
    }
}
