package model;

import static org.junit.Assert.assertEquals;

import model.BinaryTreeNode;
import model.TraversalAction;
import model.TreeNode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BinaryTreeNodeTest extends TreeNodeTest{

    /*
     * Test tree structure
     *
     *   +- root(ROOT_DATA)
     *      +- node_1(NODE_DATA_1)
     *      |  +- node_3(NODE_DATA_3)
     *      |  |  +- node_5(NODE_DATA_1)
     *      |  |  |  +- node6 (NODE_DATA4)
     *      |  +- node_4(NODE_DATA_4)
     *      |  |  +- node_7(null)
     *      |  |  +- node_8(NODE_DATA_1)
     *      +- node_2(NODE_DATA_2)
     *      |  +- node9(NODE_DATA_4)
     *      |  +- node10(null)
     *
     */

    protected static final String ROOT_DATA           =   "ROOT_DATA";
    protected static final String NODE_DATA_1         =   "DATA_1";
    protected static final String NODE_DATA_2         =   "DATA_2";
    protected static final String NODE_DATA_3         =   "DATA_3";
    protected static final String NODE_DATA_4         =   "DATA_4";

    protected static final String ANOTHER_NODE_DATA   =   "ANOTHER_NODE_DATA";

    protected TreeNode<String> root;
    protected TreeNode<String> node1;
    protected TreeNode<String> node2;
    protected TreeNode<String> node3;
    protected TreeNode<String> node4;
    protected TreeNode<String> node5;
    protected TreeNode<String> node6;
    protected TreeNode<String> node7;
    protected TreeNode<String> node8;
    protected TreeNode<String> node9;
    protected TreeNode<String> node10;

    protected final TreeNode<String> anotherNode = createTreeNode(ANOTHER_NODE_DATA);

    protected <T> BinaryTreeNode<T> createTreeNode(T data) {
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
        node5 = createTreeNode(NODE_DATA_1);
        node6 = createTreeNode(NODE_DATA_4);
        node7 = createTreeNode(null);
        node8 = createTreeNode(NODE_DATA_1);
        node9 = createTreeNode(NODE_DATA_4);
        node10 = createTreeNode(null);

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
    }

    @Test
    public void testAdd() {
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
