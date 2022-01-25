
/**
 * A binary search tree data structure that has the structure of binary search rules. 
 * 
 * @author yossef-elmahdy
 * 
 */
public class BinaryTree {
    TreeNode root;
    private int size;
    private int depth;

    public BinaryTree(int rootValue) {
        this.root = new TreeNode(rootValue);
        this.size = 1;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return false;
    }

    public int size() {
        return this.size;
    }

    public int depth() {
        return this.depth + 1;
    }

    public boolean add(int value) {
        TreeNode node = new TreeNode(value); //
        TreeNode current = this.root;
        int d = 0;
        while (current != null) {
            if (current.value < value) {
                if (current.right == null) {
                    current.right = node;
                    ++this.size;
                    this.depth = (d > this.depth) ? d : this.depth;
                    return true;
                } else {
                    current = current.right;
                    ++d;
                }
            } else if (current.value > value) {
                if (current.left == null) {
                    current.left = node;
                    ++this.size;
                    this.depth = (d > this.depth) ? d : this.depth;
                    return true;
                } else {
                    current = current.left;
                    ++d;
                }
            } else {
                System.out.println("Value " + value + " exists in the tree. ");
                return false;
            }
        }
        return false;
    }
    
    private TreeNode smallestNode(TreeNode node) {
        if (node.left == null) {
            return node;
        }
        return this.smallestNode(node.left);
    }

    private TreeNode delete(TreeNode node, int value) {
        if (node == null) {
            return null;
        }
        if (node.value == value) {
            if (node.left == null && node.right == null) // has no children 
            {
                return null;
            }
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            TreeNode tmp = smallestNode(node.right);
            node.value = tmp.value;
            node.right = this.delete(node.right, tmp.value);
            return node;
        } else if (node.value > value) {
            node.left = this.delete(node.left, value);
            return node;
        } else {
            node.right = this.delete(node.right, value);
            return node;
        }
    }
    
    public void delete(int value) {
        this.root = this.delete(this.root, value);
    }

    private boolean searchRecursive(int value, TreeNode node) {
        if (node == null) {
            return false;
        }

        if (node.value == value) {
            return true;
        } else if (node.value < value) {
            return this.searchRecursive(value, node.right);
        } else {
            return this.searchRecursive(value, node.left);
        }
    }

    public boolean searchRecursive(int value) {
        return this.searchRecursive(value, this.root);
    }

    public boolean search(int value) {
        TreeNode current = this.root;
        while (current != null) {
            if (current.value == value) {
                return true;
            } else if (current.value < value) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return false;
    }

    private void inOrder(TreeNode node) {
        if (node == null)
            return;

        inOrder(node.left);
        System.out.println(node.value);
        inOrder(node.right);
    }

    public void printInOrder() {
        this.inOrder(this.root);
    }

    private void preOrder(TreeNode node) {
        if (node == null)
            return;

        if (node.left != null)
            System.out.println(node.left.value);
        if (node.right != null)
            System.out.println(node.right.value);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void printPreOrder() {
        this.preOrder(this.root);
    }

    private void postOrder(TreeNode node) {
        if (node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.value);
    }

    public void printPostOrder() {
        this.postOrder(this.root);
    }
    
    public int max() {
    	if (this.root == null) {
    		return Integer.MIN_VALUE; 
    	} else {
    		TreeNode current = this.root; 
    		while (current.right != null) {
    			current = current.right; 
    		}
    		return current.value; 
    	}
    }
    
    public int min() {
    	if (this.root == null) {
    		return Integer.MAX_VALUE; 
    	} else {
    		TreeNode current = this.root; 
    		while (current.left != null) {
    			current = current.left; 
    		}
    		return current.value; 
    	}
    }

}