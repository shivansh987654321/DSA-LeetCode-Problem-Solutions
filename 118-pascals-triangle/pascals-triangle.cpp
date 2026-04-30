class Solution {
public:
    vector<vector<int>> generate(int n) {
        vector<vector<int>> ans(n);
        ans[0].push_back(1);

        for (int i = 1; i < n; i++) {
            int size = ans[i - 1].size() + 1;

            ans[i].push_back(1);

            for (int j = 1; j < size - 1; j++) {
                ans[i].push_back(ans[i - 1][j - 1] + ans[i - 1][j]);
            }  

            ans[i].push_back(1);
        }

        return ans;
    }
};