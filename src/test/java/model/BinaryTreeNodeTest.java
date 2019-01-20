package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BinaryTreeNodeTest extends TreeTest{

    /*
     * Test tree structure
     *
     *   +- root(ROOT_DATA)
     *      +- node_1(NODE_DATA_1)
     *      |  +- node_3(NODE_DATA_3)
     *      |  |  +- node_7(NODE_DATA_7)
     *      |  |  +- node_8(NODE_DATA_8)
     *      |  +- node_4(NODE_DATA_4)
     *      |  |  +- node_9(NODE_DATA_9)
     *      |     +- node_10(NODE_DATA_10)
     *      +- node_2(NODE_DATA_2)
     *      |  +- node_5(NODE_DATA_5)
     *      |  |  +- node_11(NODE_DATA_11)
     *      |  +- node_6(NODE_DATA_6
     *
     */

    private static final String ROOT_DATA           =   "ROOT_DATA";
    private static final String NODE_DATA_1         =   "DATA_1";
    private static final String NODE_DATA_2         =   "DATA_2";
    private static final String NODE_DATA_3         =   "DATA_3";
    private static final String NODE_DATA_4         =   "DATA_4";
    private static final String NODE_DATA_5         =   "DATA_5";
    private static final String NODE_DATA_6         =   "DATA_6";
    private static final String NODE_DATA_7         =   "DATA_7";
    private static final String NODE_DATA_8         =   "DATA_8";
    private static final String NODE_DATA_9         =   "DATA_9";
    private static final String NODE_DATA_10        =   "DATA_10";
    private static final String NODE_DATA_11        =   "DATA_11";


    private BinaryTreeNode<String> root;
    private BinaryTreeNode<String> node1;
    private BinaryTreeNode<String> node2;
    private BinaryTreeNode<String> node3;
    private BinaryTreeNode<String> node4;
    private BinaryTreeNode<String> node5;
    private BinaryTreeNode<String> node6;
    private BinaryTreeNode<String> node7;
    private BinaryTreeNode<String> node8;
    private BinaryTreeNode<String> node9;
    private BinaryTreeNode<String> node10;
    private BinaryTreeNode<String> node11;

    protected  <T> BinaryTreeNode<T> createTreeNode(T data) {
        return new BinaryTreeNode<>(data);
    }

    @Before
    public void buildTree() {
        // Create tree nodes
        root = createTreeNode(ROOT_DATA);
        node1 = createTreeNode(NODE_DATA_1);
        node2 = createTreeNode(NODE_DATA_2);
        node3 = createTreeNode(NODE_DATA_3);
        node4 = createTreeNode(NODE_DATA_4);
        node5 = createTreeNode(NODE_DATA_5);
        node6 = createTreeNode(NODE_DATA_6);
        node7 = createTreeNode(NODE_DATA_7);
        node8 = createTreeNode(NODE_DATA_8);
        node9 = createTreeNode(NODE_DATA_9);
        node10 = createTreeNode(NODE_DATA_10);
        node11 = createTreeNode(NODE_DATA_11);

        // Populate tree
        root.add(node1);
        root.add(node2);
        root.add(node3);
        root.add(node4);
        root.add(node5);
        root.add(node6);
        root.add(node7);
        root.add(node8);
        root.add(node9);
        root.add(node10);
        root.add(node11);
    }

    @Override
    public void testData() {

    }

    @Override
    public void testSetData() {

    }

    @Test
    public void testRoot() {
        // Test root tree node is correctly determined
        String message = "Root node was incorrectly determined";
        assertEquals(message, root, root);
        assertEquals(message, root, node1.root());
        assertEquals(message, root, node4.root());
        assertEquals(message, root, node7.root());
        assertEquals(message, root, node10.root());
        assertEquals(message, root, node11.root());
    }

    @Test
    public void testIsRoot() {
        // Test if root tree node is root tree node
        String messageRootExpected = "The tree node was expected to be root, but actually was not";
        assertTrue(messageRootExpected, root.isRoot());

        // Test if non root tree node is non root tree node
        String messageRootNotExpected = "The tree node was not expected to be root, but actually was";
        assertFalse(messageRootNotExpected, node2.isRoot());
        assertFalse(messageRootNotExpected, node6.isRoot());
        assertFalse(messageRootExpected, node11.isRoot());
    }

    @Test
    public void testParent() {
        // Test if parent tree node is correctly determined
        String message = "Parent node was incorrectly determined";

        assertNull(message, root.parent());
        assertEquals(message, root, node1.parent());
        assertEquals(message, root, node2.parent());
        assertEquals(message, node1, node3.parent());
        assertEquals(message, node1, node4.parent());
        assertEquals(message, node2, node5.parent());
        assertEquals(message, node2, node6.parent());
        assertEquals(message, node3, node7.parent());
        assertEquals(message, node3, node8.parent());
        assertEquals(message, node4, node9.parent());
        assertEquals(message, node4, node10.parent());
        assertEquals(message, node5, node11.parent());

        // Test if non parent tree node is non parent tree node
        assertNotEquals(message, root, node7.parent());
        assertNotEquals(message, root, node11.parent());
        assertNotEquals(message, node7, root.parent());
    }

    @Test
    public void testBinaryTreeNodeBuilder() {
        String messageContainNotExpected =
                "The binary tree was not expected to the tree from builder";
        List<String> inorder = Arrays.asList("D", "B", "E", "A", "F", "C");
        List<String> preorder = Arrays.asList("A", "B", "D", "E", "C", "F");
        List<String> postorder = Arrays.asList("D", "E", "B", "F", "C", "A");
        BinaryTreeNode.BinaryTreeNodeBuilder<String> builder = BinaryTreeNode.BinaryTreeNodeBuilder.newBuilder();

        BinaryTreeNode<String> root_via_preorder = builder.setInorder(inorder).setPreorder(preorder).build();
        assertEquals(messageContainNotExpected, preorder, root_via_preorder.preOrdered().stream().map(TreeNode::data).collect(Collectors.toList()));
        assertEquals(messageContainNotExpected, postorder, root_via_preorder.postOrdered().stream().map(TreeNode::data).collect(Collectors.toList()));
        assertEquals(messageContainNotExpected, inorder, root_via_preorder.inOrdered().stream().map(TreeNode::data).collect(Collectors.toList()));

        BinaryTreeNode<String> root_via_postorder = builder.setInorder(inorder).setPostorder(postorder).build();
        assertEquals(messageContainNotExpected, preorder, root_via_postorder.preOrdered().stream().map(TreeNode::data).collect(Collectors.toList()));
        assertEquals(messageContainNotExpected, postorder, root_via_postorder.postOrdered().stream().map(TreeNode::data).collect(Collectors.toList()));
        assertEquals(messageContainNotExpected, inorder, root_via_postorder.inOrdered().stream().map(TreeNode::data).collect(Collectors.toList()));
    }

    @Override
    public void testSubtrees() {

    }

    @Override
    public void testIsLeaf() {

    }

    @Override
    public void testFind() {

    }

    @Override
    public void testFindAll() {

    }

    @Override
    public void testHasSubtree() {

    }

    @Override
    public void testDropSubtree() {

    }

    @Override
    public void testContains() {

    }

    @Override
    public void testContainsAll() {

    }

    @Override
    public void testAdd() {

    }

    @Override
    public void testClear() {

    }

    @Override
    public void testRemove() {

    }

    @Override
    public void testRemoveAll() {

    }

    @Override
    public void testTraversePreOrder() {

    }

    @Override
    public void testTraversePostOrder() {

    }

    @Override
    public void testPreOrdered() {

    }

    @Override
    public void testPostOrdered() {

    }

    @Override
    public void testPath() {

    }

    @Override
    public void testPathToRootException() {

    }

    @Override
    public void testPathToNonExistentNodeException() {

    }

    @Override
    public void testCommonAncestor() {

    }

    @Override
    public void testCommonAncestorNullNodeException() {

    }

    @Override
    public void testCommonAncestorNonExistentNodeException() {

    }

    @Override
    public void testCommonAncestorCurrentNodeIsRootException() {

    }

    @Override
    public void testCommonAncestorNodeIsRootException() {

    }

    @Override
    public void testIsSiblingOf() {

    }

    @Override
    public void testIsAncestorOf() {

    }

    @Override
    public void testIsDescendantOf() {

    }

    @Override
    public void testSize() {
        // Test the specified tree node size is correctly calculated
        String message = "Tree node size was incorrectly calculated";
        assertSame(message, 1L, node6.size());
        assertSame(message, 1L, node9.size());
        assertSame(message, 3L, node3.size());
        assertSame(message, 12L, root.size());
    }

    @Override
    public void testHeight() {
        // Test the specified tree node height is correctly calculated
        String message = "Tree node height was incorrectly calculated";
        assertSame(message, 0, node6.height());
        assertSame(message, 1, node5.height());
        assertSame(message, 2, node2.height());
        assertSame(message, 1, node3.height());
        assertSame(message, 3, root.height());
    }

    @Override
    public void testLevel() {
        // Test the specified tree node level is correctly calculated
        String message = "Tree node level was incorrectly calculated";
        assertSame(message, 0, root.level());
        assertSame(message, 1, node1.level());
        assertSame(message, 1, node2.level());
        assertSame(message, 2, node3.level());
    }

    @Override
    public void testClone() {

    }

    @Override
    public void testEquals() {

    }

    @Override
    public void testIteratorNext() {

    }

    @Override
    public void testIteratorHasNext() {

    }

    @Override
    public void testIteratorNextNoSuchElementException() {

    }

    @Override
    public void testIteratorNextConcurrentModificationException() {

    }

    @Override
    public void testIteratorRemove() {

    }

    @Override
    public void testIteratorRemoveIllegalStateException() {

    }

    @Override
    public void testIteratorRemoveOnCurrentNodeTreeNodeException() {

    }

    @Override
    public void testIteratorRemoveOnRootTreeNodeException() {

    }

    @Override
    public void testIteratorRemoveConcurrentModificationException() {

    }

}
