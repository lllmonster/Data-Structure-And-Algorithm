## Two Pointer
Case 1: Iterate the array from two ends to the middle.  
Case 2: One pointer starts from the beginning while the other pointer starts from the end.  
Case 3: One slow-runner and one fast-runner at the same time.  
And it is worth noting that this technique is often used in a `sorted` array.

## Two Pointer in Array
```
void reverse(int *v, int N) {
    int i = 0;
    int j = N - 1;
    while (i < j) {
        swap(v[i], v[j]);
        i++;
        j--;
    }
}
```

```
int removeElement(vector<int>& nums, int val) {
    int k = 0;
    for (int i = 0; i < nums.size(); ++i) {
        if (nums[i] != val) {
            nums[k] = nums[i];
            ++k;
        }
    }
    return k;
}
```

## Two Pointer in Linked List
```
// Initialize slow & fast pointers
ListNode* slow = head;
ListNode* fast = head;
/**
 * Change this condition to fit specific problem.
 * Attention: remember to avoid null-pointer error
 **/
while (slow && fast && fast->next) {
    slow = slow->next;          // move slow pointer one step each time
    fast = fast->next->next;    // move fast pointer two steps each time
    if (slow == fast) {         // change this condition to fit specific problem
        return true;
    }
}
return false;   // change return value to fit specific problem
```
