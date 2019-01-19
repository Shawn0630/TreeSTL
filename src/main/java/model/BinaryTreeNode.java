package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeNode<T> extends TreeNode<T> {

    /**
     * Current UID of this object used for serialization
     */
    private static final long serialVersionUID = 1L;

    /**
     * A reference to the first subtree tree node of the current tree node
     */
    protected BinaryTreeNode<T> leftNode;

    /**
     * A reference to the right sibling tree node of the current tree node
     */
    protected BinaryTreeNode<T> rightNode;

    private Deque<BinaryTreeNode<T>> deque = new LinkedList<>();

    /**
     * Creates an instance of this class
     */
    public BinaryTreeNode() {
        super();
        deque.addLast(this);
    }

    @Override
    public Collection<TreeNode<T>> subtrees() {
        Collection<TreeNode<T>> subtrees = new LinkedHashSet<>();
        if (leftNode != null) {
            subtrees.add(leftNode);
        }
        if (rightNode != null) {
            subtrees.add(rightNode);
        }

        return subtrees;
    }

    /**
     * Creates an instance of this class
     *
     * @param data data to store in the current tree node
     */
    public BinaryTreeNode(T data) {
        super(data);
        deque.addLast(this);
    }

    /**
     * Insert tree node in level order
     * <p>
     * {@code null} subtree cannot be added, in this case return result will
     * be {@code false}
     * <p>
     * Checks whether this tree node was changed as a result of the call
     *
     * @param subtree subtree to add to the current tree node
     * @return {@code true} if this tree node was changed as a
     *         result of the call; {@code false} otherwise
     */
    @Override
    public boolean add(TreeNode<T> subtree) throws TreeNodeException {
       if(!deque.isEmpty()) {
           BinaryTreeNode<T> node = deque.peekFirst();
           deque.pollFirst();
           if (node.leftNode == null) {
               node.leftNode = (BinaryTreeNode<T>) subtree;
               linkParent(subtree, node);
               deque.addFirst(node);
               deque.addLast(node.leftNode);
               return true;
           } else {
               node.rightNode = (BinaryTreeNode<T>) subtree;
               linkParent(subtree, node);
               deque.addLast(node.rightNode);
               return true;
           }
       }

       return false;
    }

    @Override
    public boolean dropSubtree(TreeNode<T> subtree) {
        if (subtree == null
            || isLeaf()
            || subtree.isRoot()) {
            return false;
        }
        if (leftNode != null && leftNode.equals(subtree)) {
            unlinkParent(subtree);
            leftNode = null;
            return true;
        }

        if (rightNode != null && rightNode.equals(subtree)) {
            unlinkParent(subtree);
            rightNode = null;
            return true;
        }

        return false;
    }

    @Override
    public void clear() {
        if (!isLeaf()) {
           if (leftNode != null) {
                unlinkParent(leftNode);
               leftNode = null;
            }
            if (rightNode != null) {
               unlinkParent(rightNode);
               rightNode = null;
            }
        }
    }

    /**
     * Returns an iterator over the elements in this tree in proper sequence
     * <p>
     * The returned iterator is <b>fail-fast</b>
     *
     * @return an iterator over the elements in this tree in proper sequence
     */
    @Override
    public TreeNodeIterator iterator() {
        return new TreeNodeIterator() {

            /**
             * Returns the leftmost node of the current tree node if the
             * current tree node is not a leaf
             *
             * @return leftmost node of the current tree node if the current
             *         tree node is not a leaf
             * @throws TreeNodeException an exception that is thrown in case
             *                           if the current tree node is a leaf
             */
            @Override
            protected TreeNode<T> leftMostNode() {
                return leftNode;
            }

            /**
             * Returns the right sibling node of the current tree node if the
             * current tree node is not root
             *
             * @return right sibling node of the current tree node if the current
             *         tree node is not root
             * @throws TreeNodeException an exception that may be thrown in case if
             *                           the current tree node is root
             */
            @Override
            protected TreeNode<T> rightSiblingNode() {
                return rightNode;
            }

        };
    }

    /**
     * Returns the in ordered collection of nodes of the current tree
     * starting from the current tree node
     *
     * @return in ordered collection of nodes of the current tree starting
     *         from the current tree node
     */
    public Collection<TreeNode<T>> inOrdered() {
        if (isLeaf()) {
            return Collections.singleton(this);
        }
        final Collection<TreeNode<T>> mInOrdered = new ArrayList<>();
        TraversalAction<TreeNode<T>> action = populateAction(mInOrdered);
        traverseInOrder(action);
        return mInOrdered;
    }

    public void traverseInOrder(TraversalAction<TreeNode<T>> action) {
        if (!action.isCompleted()) {
            if (leftNode != null) {
                leftNode.traverseInOrder(action);
            }
            action.perform(this);
            if (rightNode != null) {
               rightNode.traverseInOrder(action);
            }
        }
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
