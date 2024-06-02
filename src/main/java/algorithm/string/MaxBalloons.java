package algorithm.string;

public class MaxBalloons {
    public static void main(String[] args) {
        System.out.println(maxNumberOfBalloons("leetcode"));
    }
    public static int maxNumberOfBalloons(String text) {
        int[] charArray = new int[256];
        for(int i=0;i<charArray.length;i++){
            charArray[i]=0;
        }
        for(int i=0;i<text.length();i++){
            charArray[text.charAt(i)] += 1;
        }
        int countB = charArray['b'];
        int countA = Math.min(countB,charArray['a']);
        int countL = Math.min(countA,charArray['l']/2);
        int countO = Math.min(countL,charArray['o']/2);
        int countN = Math.min(countO,charArray['n']);
        return countN;

    }
}
