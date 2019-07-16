package ca.jrvs.challenge;

import java.util.HashSet;
import java.util.Set;

public class DupNum {

    /*Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
    prove that at least one duplicate number must exist. Assume that there is only one duplicate number,
    find the duplicate one.
     */
    public int findDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<Integer>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return num;
            }
            seen.add(num);
        }

        return -1;
    }
}
