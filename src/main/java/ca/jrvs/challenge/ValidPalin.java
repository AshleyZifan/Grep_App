package ca.jrvs.challenge;

class ValidPalin {

    //Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        int i = 0, j = s.length() - 1;
        char[] arr = s.toCharArray();
        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(arr[i])) i++; //only consider letter and digits
            while (i < j && !Character.isLetterOrDigit(arr[j])) j--;
            if (Character.toLowerCase(arr[i++]) != Character.toLowerCase(arr[j--])) return false; //not palindrome
        }
        return true;
    }
}
