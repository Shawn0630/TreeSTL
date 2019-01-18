package model;

import model.BinaryTreeNode;
import model.TraversalAction;
import model.TreeNode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotEquals;

public class BinaryTreeNodeTest extends TreeTest{

    /*
     * Test tree structure
     *
     *   +- root(ROOT_DATA)
     *      +- node_1(NODE_DATA_1)
     *      |  +- node_3(NODE_DATA_3)
     *      |  |  +- node_5(NODE_DATA_1)
     *      |  |  +- node6 (NODE_DATA4)
     *      |  +- node_4(NODE_DATA_4)
     *      |  |  +- node_7(null)
     *      |     |  +- node9(NODE_DATA_4)
     *               |  +- node11(null)
     *      |     |  +- node10(null)
     *      |  |  +- node_8(NODE_DATA_1)
     *      +- node_2(NODE_DATA_2)
     *
     */

    private static final String ROOT_DATA           =   "ROOT_DATA";
    private static final String NODE_DATA_1         =   "DATA_1";
    private static final String NODE_DATA_2         =   "DATA_2";
    private static final String NODE_DATA_3         =   "DATA_3";
    private static final String NODE_DATA_4         =   "DATA_4";
    private static final String NODE_DATA_5         =   "DATA_5";


    private TreeNode<String> root;
    private TreeNode<String> node1;
    private TreeNode<String> node2;
    private TreeNode<String> node3;
    private TreeNode<String> node4;
    private TreeNode<String> node5;
    private TreeNode<String> node6;
    private TreeNode<String> node7;
    private TreeNode<String> node8;
    private TreeNode<String> node9;
    private TreeNode<String> node10;
    private TreeNode<String> node11;

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
        node6 = createTreeNode(NODE_DATA_4);
        node7 = createTreeNode(null);
        node8 = createTreeNode(NODE_DATA_1);
        node9 = createTreeNode(NODE_DATA_4);
        node10 = createTreeNode(null);
        node11 = createTreeNode(null);

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
        assertEquals(message, node1, node4.parent());
        assertEquals(message, node3, node6.parent());
        assertEquals(message, node7, node10.parent());
        assertEquals(message, node9, node11.parent());

        // Test if non parent tree node is non parent tree node
        assertNotEquals(message, node2, node5.parent());
        assertNotEquals(message, root, node3.parent());
        assertNotEquals(message, node7, root.parent());
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

    }

    @Override
    public void testHeight() {

    }

    @Override
    public void testLevel() {

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

}
