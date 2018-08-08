```
#include <iostream>
#include <vector>
#include <stack>
#include <unordered_set>
using namespace std;

int lock_use_analyzer(vector<string> logs);
string* split(string s);

int main()
{
    vector<string> logs{"ACQUIRE 364",
                        "ACQUIRE 84",
                        "ACQUIRE 364",
                        "RELEASE 364"};
    int ans = lock_use_analyzer(logs);
    cout << ans << endl;
}

int lock_use_analyzer(vector<string> logs) {
    // judge if logs is a valid input
    // corner case
    if (logs.size() == 0) return 0;
    stack<string> stk;
    unordered_set<string> container;
    // iterate
    for (int i = 0; i < logs.size(); i++) {
        string *log = split(logs[i]);
        cout << log[0] << " " << log[1] << endl;
        if (log[0] == "ACQUIRE") {
            if (container.count(log[1]) > 0) return i+1;
            container.insert(log[1]);
            stk.push(log[1]);
        }
        else {
            string prev = stk.top();
            if (prev != log[1]) return i+1;
            stk.pop();
        }
    }
    if (!stk.empty()) return logs.size()+1;
    return 0;
}

string* split(string s) {
    string *log = new string[2];
    for (int i = 0; i < s.length(); i++) {
        if (s[i] == ' ') {
            log[0] = s.substr(0,i);
            log[1] = s.substr(i+1, s.length() - i - 1);
            return log;
        }
    }
    throw "No Valid Input";
}

```
