## Two methods
1. Recursive (DFS)
2. BFS

## String Stream in c++
An `ostringstream` object can be used to write a string.
```
#include <sstream>
int a = 1, b = 2, c = 3;
ostringstream out;
out << a << b << c;
cout << out.str();
```
An `istringstream` object can be used to read from a string.
```
string data = "1 # 2 3 4 5 #";
istringstream in(data);
string val;
while (in >> val) 
    cout << val << endl;
```


## Recursive
```
class Codec {
public:

    // Encodes a tree to a single string.
    string serialize(TreeNode* root) {
        ostringstream out;
        mySerialize(root, out);
        return out.str();
    }

    // Decodes your encoded data to tree.
    TreeNode* deserialize(string data) {
        istringstream in(data);
        return myDeserialize(in);
    }

private:
    void mySerialize(TreeNode* root, ostringstream& out) {
        if (!root) {
            out << "# ";
        }
        else {
            out << root->val << " ";
            mySerialize(root->left, out);
            mySerialize(root->right, out);
        }
    }
    
    TreeNode* myDeserialize(istringstream& in) {
        string val;
        in >> val;
        if (val == "#") return nullptr;
        TreeNode* root = new TreeNode(stoi(val));
        root->left = myDeserialize(in);
        root->right = myDeserialize(in);
        return root;
    }
};
```

## BFS
```
class Codec {
public:

    // Encodes a tree to a single string.
    string serialize(TreeNode* root) {
        ostringstream out;
        queue<TreeNode*> q;
        if(root) q.push(root);
        while (!q.empty()) {
            TreeNode* cur = q.front();q.pop();
            if (cur) {
                out << cur->val << " ";
                q.push(cur->left);
                q.push(cur->right);
            } else {
                out << "# ";
            }
        }
        return out.str();
    }

    // Decodes your encoded data to tree.
    TreeNode* deserialize(string data) {
        if (data.size() == 0) return nullptr;
        istringstream in(data);
        string val; in >> val;
        if (val == "#") return nullptr;
        
        queue<TreeNode*> q;
        TreeNode* root = new TreeNode(stoi(val));
        q.push(root);
        
        while(!q.empty()) {
            TreeNode* cur = q.front();q.pop();
            if (in >> val && val != "#") {
                cur->left = new TreeNode(stoi(val));
                q.push(cur->left);
            }
            if (in >> val && val != "#") {
                cur->right = new TreeNode(stoi(val));
                q.push(cur->right);
            }
        }
        
        return root;
    }
};

```
