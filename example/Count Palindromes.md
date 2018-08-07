Find the number of palindromes in a string.
```
#include <iostream>

using namespace std;

int count_palindromes(string s);
int ifPalindrome(string s, int start, int end);

int main()
{
    string s = "wowpurerocks";
    int ans = count_palindromes(s);
    cout << ans << endl;
}

int count_palindromes(string s) {
    int ans = 0;
    if (s.size() == 0) return 0;
    for (int i = 0; i < s.size(); i++) {
        ans += ifPalindrome(s, i, i);
        if (i != s.size() - 1)
            ans += ifPalindrome(s, i, i + 1);
    }
    return ans;
}

int ifPalindrome(string s, int start, int end) {
    int cnt = 0;
    int lo = start, hi = end;
    while (lo <= hi && lo >= 0 && hi < s.size()) {
        if (s[lo] == s[hi]) {
            cnt++;
            lo--;
            hi++;
        } else {
            break;
        }
    }
    return cnt;
}

```
