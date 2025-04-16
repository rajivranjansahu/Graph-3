// TC: O(n)
// SC: O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

public int findCelebrity(int n) {
    int start = 0;
    int end = n - 1;
    
    // Narrow down the candidate
    while (start < end) {
        if (knows(start, end)) {
            start++;
        } else {
            end--;
        }
    }
    
    // Verify the candidate
    for (int i = 0; i < n; i++) {
        if (start != i && (knows(start, i) || !knows(i, start))) {
            return -1;
        }
    }
    
    return start;
}
