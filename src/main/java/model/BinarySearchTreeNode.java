package model;

import java.util.Collection;

public class BinarySearchTreeNode<T extends Comparable<T>> extends BinaryTreeNode<T> {
    public BinarySearchTreeNode() {
        super();
    }
    public BinarySearchTreeNode(T data) {
        super(data);
    }

    @Override
    public boolean add(TreeNode<T> subtree) throws TreeNodeException {
        if (this.data().compareTo(subtree.data()) < 0) {
            return this.rightNode.add(subtree);
        }

        return this.leftNode.add(subtree);
    }

    public boolean add(T data) throws TreeNodeException {
        BinarySearchTreeNode<T> node = new BinarySearchTreeNode<>(data);
        return this.add(node);
    }

    public boolean addAll(Collection<T> data) throws TreeNodeException {
        return data.stream().allMatch(this::add);
    }

    @Override
    public TreeNode<T> find(T data) {
        if (this.data() == data) {
            return this;
        }

        if (this.data().compareTo(data) < 0) {
            return this.rightNode.find(data);
        }

        return this.leftNode.find(data);
    }

    public static class BinarySearchTreeNodeBuilder<T extends Comparable<T>> {
        private BinarySearchTreeNodeBuilder() {}

        public static <T> BinarySearchTreeNodeBuilder newBuilder() {
            return new BinarySearchTreeNodeBuilder();
        }

        public BinarySearchTreeNode<T> addAll(Collection<T> data) {
            T first = data.stream().findFirst().orElse(null);
            if (first == null) {
                return null;
            }
            BinarySearchTreeNode<T> root = new BinarySearchTreeNode<>(first);
            data.stream().skip(1).forEach(root::add);

            return root;
        }
    }
}
