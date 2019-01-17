package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class BinarySearchTreeNodeTest {

    @Test
    public void testBinarySearchTreeBuilder() {
        String messageContainNotExpected =
                "The binary search tree was not expected to the tree from builder";
        BinarySearchTreeNode.BinarySearchTreeNodeBuilder<Integer> builder = BinarySearchTreeNode.BinarySearchTreeNodeBuilder.newBuilder();
        assertEquals(messageContainNotExpected, Collections.singletonList(1), builder.addAll(Collections.singletonList(1)).preOrdered().stream().map(
                TreeNode::data).collect(Collectors.toList()));
        assertEquals(messageContainNotExpected, Arrays.asList(1, 2), builder.addAll(Arrays.asList(1, 2)).preOrdered().stream().map(
                TreeNode::data).collect(Collectors.toList()));
    }
}
