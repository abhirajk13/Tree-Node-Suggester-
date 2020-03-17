class TreeNode {

    String value;
    TreeNode left;
    TreeNode right;
    
    
    public TreeNode(String value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
    
    public TreeNode(String value, TreeNode left, TreeNode right) {
        
        this.value = value;
        this.left = left;
        this.right = right;
        
    
    
    }
    
    public TreeNode getLeft() {
        return left;
    }
    
    public TreeNode getRight() {
        return right;
    }
    
    public void setLeft(TreeNode newnode) {
        this.left = newnode;
    }
    public void setRight(TreeNode newnode) {
        this.right = newnode;
    }
    
    public boolean isLeaf() {
        if(left == null && right == null) {
            return true;
        }
        else { 
        return false; 
        }
    } 
}
        
        
    
    

