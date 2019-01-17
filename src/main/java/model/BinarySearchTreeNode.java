package model;

public class BinarySearchTreeNode<T> extends BinaryTreeNode<T> {
    public BinarySearchTreeNode() {
        super();
    }
    public BinarySearchTreeNode(T data) {
        super(data);
    }

    @Override
    public boolean add(TreeNode<T> subtree) throws TreeNodeException {
        return false;
    }
}
