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

public class BinaryTreeNodeTest {

    @Test
    public void testBinaryTreeNodeBuilder() {
        String messageContainNotExpected =
                "The binary tree was not expected to the tree builder";
        List<String> inorder = Arrays.asList("D", "B", "E", "A", "F", "C");
        List<String> preorder = Arrays.asList("A", "B", "D", "E", "C", "F");
        List<String> postorder = Arrays.asList("D", "E", "B", "F", "C", "A");
        BinaryTreeNode.BinaryTreeNodeBuilder<String> builder = BinaryTreeNode.BinaryTreeNodeBuilder.newBuilder();

        BinaryTreeNode<String> root_via_preorder = builder.setInorder(inorder).setPreorder(preorder).build();
        assertEquals(messageContainNotExpected, preorder, root_via_preorder.preOrdered().stream().map(TreeNode::data).collect(Collectors.toList()));
        assertEquals(messageContainNotExpected, postorder, root_via_preorder.postOrdered().stream().map(TreeNode::data).collect(Collectors.toList()));

        BinaryTreeNode<String> root_via_postorder = builder.setInorder(inorder).setPostorder(postorder).build();
        assertEquals(messageContainNotExpected, preorder, root_via_postorder.preOrdered().stream().map(TreeNode::data).collect(Collectors.toList()));
        assertEquals(messageContainNotExpected, postorder, root_via_postorder.postOrdered().stream().map(TreeNode::data).collect(Collectors.toList()));
    }

}
