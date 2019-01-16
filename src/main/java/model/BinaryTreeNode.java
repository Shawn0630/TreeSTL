package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class BinaryTreeNode<T> extends LinkedTreeNode<T> {

    /**
     * Creates an instance of this class
     *
     * @param data data to store in the current tree node
     */
    public BinaryTreeNode(T data) {
        super(data);
    }

    @Override
    public boolean add(TreeNode<T> subtree) throws TreeNodeException {
       if (leftMostNode != null && rightSiblingNode != null) {
           throw new TreeNodeException("Binary tree node not allow more than two descendants");
       }
       return super.add(subtree);
    }

    public static class BinaryTreeNodeBuilder<T> {
        private List<T> preorder;
        private List<T> inorder;
        private List<T> postorder;

        private BinaryTreeNodeBuilder() {}

        public static <T> BinaryTreeNodeBuilder<T> newBuilder() {
            return new BinaryTreeNodeBuilder<>();
        }
        public BinaryTreeNodeBuilder setPreorder(Collection<T> preorder) {
            this.preorder = new ArrayList<>(preorder);
            return this;
        }
        public BinaryTreeNodeBuilder setInorder(Collection<T> inorder) {
            this.inorder = new ArrayList<>(inorder);
            return this;
        }
        public BinaryTreeNodeBuilder setPostorder(Collection<T> postorder) {
            this.postorder = new ArrayList<>(postorder);
            return this;
        }
        /**
         *  construct the binary tree via inorder and preorder/postorder
         *
         *  @return the root of the binary tree
         *  @throws TreeNodeException an exception that is thrown in case if
         *  fails to construct a binary tree
         */
        public BinaryTreeNode build() throws TreeNodeException {
            BinaryTreeNode<T> root = null;

            if (inorder == null) {
                throw new TreeNodeException(
                        "Fail to construct binary tree caused by without specifying inorder sequence");
            }
            if (preorder == null && postorder == null) {
                throw new TreeNodeException(
                        "Fail to construct binary tree caused by without specifying preorder sequence or postorder sequence");
            }
            if (preorder != null && postorder != null && preorder.size() != postorder.size()) {
                throw new TreeNodeException(
                        "Fail to construct binary tree caused by preorder sequence and postorder sequence not same");
            }
            if (preorder != null && new HashSet<>(preorder).size() != preorder.size()) {
                throw new TreeNodeException(
                        "Fail to construct binary tree caused by duplication in preorder sequence");
            }
            if (postorder != null && new HashSet<>(postorder).size() != postorder.size()) {
                throw new TreeNodeException(
                        "Fail to construct binary tree caused by duplication in postorder sequence");
            }
            if (preorder != null) { // use inorder
                root = buildUtilViaPreorder(preorder.subList(0, preorder.size()), inorder);
            } else if (postorder != null) {
                root = buildUtilViaPostorder(postorder.subList(0, postorder.size()), inorder);
            }

            return root;
        }

        private BinaryTreeNode<T> buildUtilViaPreorder(List<T> preorder, List<T> inorder) throws TreeNodeException {
            if (preorder.isEmpty()) {
                return null;
            }

            BinaryTreeNode<T> root = new BinaryTreeNode<>(preorder.get(0));

            int inIndex = inorder.indexOf(root.data);
            if (inIndex < 0) {
                throw new TreeNodeException("Fail to construct binary tree caused by inconsistent pre/post order sequence and inorder sequence");
            }

            List<T> leftSubTreeInorder = inorder.subList(0, inIndex);
            List<T> rightSubTreeInorder = inorder.subList(inIndex + 1, preorder.size());
            List<T> leftSubTreePreorder = preorder.subList(1, 1 + leftSubTreeInorder.size());
            List<T> rightSubTreePreorder = preorder.subList(1 + leftSubTreeInorder.size(), preorder.size());

            root.add(buildUtilViaPreorder(leftSubTreePreorder, leftSubTreeInorder));
            root.add(buildUtilViaPreorder(rightSubTreePreorder, rightSubTreeInorder));

            return root;
        }

        private BinaryTreeNode<T> buildUtilViaPostorder(List<T> postorder, List<T> inorder) throws TreeNodeException {
            if (postorder.isEmpty()) {
                return null;
            }

            BinaryTreeNode<T> root = new BinaryTreeNode<>(postorder.get(postorder.size() - 1));

            int inIndex = inorder.indexOf(root.data);
            if (inIndex < 0) {
                throw new TreeNodeException("Fail to construct binary tree caused by inconsistent pre/post order sequence and inorder sequence");
            }

            List<T> leftSubTreeInorder = inorder.subList(0, inIndex);
            List<T> rightSubTreeInorder = inorder.subList(inIndex + 1, postorder.size());
            List<T> leftSubTreePostorder = postorder.subList(0, leftSubTreeInorder.size());
            List<T> rightSubTreePostorder = postorder.subList(leftSubTreeInorder.size(), postorder.size() - 1);

            root.add(buildUtilViaPreorder(leftSubTreePostorder, leftSubTreeInorder));
            root.add(buildUtilViaPreorder(rightSubTreePostorder, rightSubTreeInorder));

            return root;
        }


    }
}
