package algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LastVisitedIntegers {
    public List<Integer> lastVisitedIntegers(List<String> words) {
        String PREV = "prev";
        int[] nums = new int[words.size()];
        List<Integer> result = new ArrayList<>();
        int totalVisitedIntegers = 0;
        int currentPrev = 0;
        for(String word: words){
            if(word.equals(PREV)){
                currentPrev++;
                if(currentPrev > totalVisitedIntegers){
                    result.add(-1);
                }
                else{
                    result.add(nums[totalVisitedIntegers-currentPrev]);
                }
            }
            else{
                nums[totalVisitedIntegers++] = Integer.parseInt(word);
                currentPrev = 0;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"1","prev","2","prev","prev"};
        LastVisitedIntegers lvi = new LastVisitedIntegers();
        System.out.println((lvi.lastVisitedIntegers(Arrays.asList(words))));
    }
}
