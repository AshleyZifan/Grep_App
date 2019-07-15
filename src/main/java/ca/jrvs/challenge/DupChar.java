package ca.jrvs.challenge;

public class DupChar {

    //Find duplicate characters in a String
    public char[] findDup(String str){
        char result[] = null;
        char[] inp = str.toCharArray();
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < str.length(); j++) {
                if (inp[i] == inp[j]) {
                    result[cnt] = inp[i];
                    cnt++;
                }
            }
        }
        return result;
    }
}
