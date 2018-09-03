## Array
```
#include <iostream>
#include <algorithm>
using namespace std;

int main()
{
    // 1. initialize
    int a[5];
    int b[5] = {1, 2, 3}; // other ele will be set as the default value

    // 2. get length
    int size = sizeof(b) / sizeof(*b);

    // 3. iterative the element
    for (int& item : b) {
        cout << item << " ";
    }

    // 4. sort
    sort(b, b + size);
}
```
## Dynamic Array
```
#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int main()
{
    // 1. initialize
    vector<int> v0;
    vector<int> v1(5, 0);
    vector<int> v1{1,2,3};
    vector<vector<int>> matrix( n, vector<int>(n) );

    // 2. make a copy
    vector<int> v2(v1.begin(), v1.end());
    vector<int> v3(v2);

    // 3. cast an array to a vector
    int a[5] = {1, 2, 3, 4, 5};
    vector<int> v4(a, *(&a + 1));

    // 4. get length
    int size = v4.size();

    // 5. sort
    sort(v4.begin(), v4.end());

    // 6. add new ele at the end of the vector
    v4.push_back(-1);

    // 7.delete the last element
    v4.pop_back();
}
```
