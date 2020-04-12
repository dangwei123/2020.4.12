给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

说明：你不能倾斜容器，且 n 的值至少为 2
class Solution {
    public int maxArea(int[] height) {
        int res=0;
        int left=0;
        int right=height.length-1;
        while(left<right){
            res=Math.max(res,(right-left)*Math.min(height[left],height[right]));
            if(height[left]<height[right]){
                left++;
            }else{
                right--;
            }
        }
        return res;
    }
}

给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。

为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer,Integer> map=new HashMap<>();
        int res=0;
        for(int i=0;i<A.length;i++){
            for(int j=0;j<B.length;j++){
                int sum=A[i]+B[j];
                map.put(sum,map.getOrDefault(sum,0)+1);
            }
        }
        for(int i=0;i<C.length;i++){
            for(int j=0;j<D.length;j++){
                int sum=C[i]+D[j];
                    res+=map.getOrDefault(-sum,0);
            }
        }
        return res;
    }
}

根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。

给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：

如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。

 
 class Solution {
    int[] dx={-1,-1,-1,0,0,1,1,1};
    int[] dy={-1,0,1,-1,1,-1,0,1};
    public void gameOfLife(int[][] board) {
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                int count=alive(board,i,j);
                if(board[i][j]==0){
                    if(count==3){
                        board[i][j]=2;
                    }
                }else{
                    if(count==2||count==3){
                        board[i][j]=3;
                    }
                }
            }
        }
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                board[i][j]>>=1;
            }
        }
    }
    private int alive(int[][] board,int i,int j){
        int count=0;
        for(int k=0;k<8;k++){
            int x=i+dx[k];
            int y=j+dy[k];
            if(x<0||x==board.length||y<0||y>=board[0].length){
                continue;
            }
            if((board[x][y]&1)==1)
              count++;
        }
        return count;
    }
}

给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
class Solution {
    public int firstMissingPositive(int[] nums) {
        int n=nums.length;
        for(int i=0;i<n;i++){
            while(nums[i]>0&&nums[i]<=n&&nums[i]-1!=i&&nums[nums[i]-1]!=nums[i]){
                swap(nums,nums[i]-1,i);
            }
        }
        for(int i=0;i<n;i++){
            if(nums[i]-1!=i){
                return i+1;
            }
        }
        return n+1;
    }
    private void swap(int[] nums,int left,int right){
        int tmp=nums[left];
        nums[left]=nums[right];
        nums[right]=tmp;
    }
}

给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回滑动窗口中的最大值。
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res=new int[nums.length-k+1];
        int max=maxindex(nums,0,k-1);
        int j=0;
        res[j++]=nums[max];
        int left=1;
        for(int i=k;i<nums.length;i++){
            if(max<left){
                max=maxindex(nums,left,i);
            }else if(nums[i]>=nums[max]){
                max=i;
            }
            left++;
            res[j++]=nums[max];
        }
        return res;
    }
    private int maxindex(int[] nums,int left,int right){
        int max=left;
        for(int i=left+1;i<=right;i++){
            if(nums[i]>=nums[max]){
                max=i;
            }
        }
        return max;
    }
}