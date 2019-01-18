package model;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

public abstract class TreeTest {
    protected abstract <T> TreeNode<T> createTreeNode(T data);

    @Before
    public abstract void buildTree();

    @Test
    public abstract void testData();

    @Test
    public abstract void testSetData();

    @Test
    public abstract void testRoot();

    @Test
    public abstract void testIsRoot();

    @Test
    public abstract void testParent();

    @Test
    public abstract void testSubtrees();

    @Test
    public abstract void testIsLeaf();

    @Test
    public abstract void testFind();

    @Test
    public abstract void testFindAll();

    @Test
    public abstract void testHasSubtree();

    @Test
    public abstract void testDropSubtree();

    @Test
    public abstract void testContains();

    @Test
    public abstract void testContainsAll();

    @Test
    public abstract void testAdd();

    @Test
    public abstract void testClear();

    @Test
    public abstract void testRemove();

    @Test
    public abstract void testRemoveAll();

    @Test
    public abstract void testTraversePreOrder();

    @Test
    public abstract void testTraversePostOrder();

    @Test
    public abstract void testPreOrdered();

    @Test
    public abstract void testPostOrdered();

    @Test
    public abstract void testPath();

    @Test(expected = TreeNodeException.class)
    public abstract void testPathToRootException();

    @Test(expected = TreeNodeException.class)
    public abstract void testPathToNonExistentNodeException();

    @Test
    public abstract void testCommonAncestor();

    @Test(expected = TreeNodeException.class)
    public abstract void testCommonAncestorNullNodeException();

    @Test(expected = TreeNodeException.class)
    public abstract void testCommonAncestorNonExistentNodeException();

    @Test(expected = TreeNodeException.class)
    public abstract void testCommonAncestorCurrentNodeIsRootException();

    @Test(expected = TreeNodeException.class)
    public abstract void testCommonAncestorNodeIsRootException();

    @Test
    public abstract void testIsSiblingOf();

    @Test
    public abstract void testIsAncestorOf();

    @Test
    public abstract void testIsDescendantOf();

    @Test
    public abstract void testSize();

    @Test
    public abstract void testHeight();

    @Test
    public abstract void testLevel();

    @Test
    public abstract void testClone();

    @Test
    public abstract void testEquals();

    @Test
    public abstract void testIteratorNext();

    @Test
    public abstract void testIteratorHasNext();

    @Test(expected = NoSuchElementException.class)
    public abstract void testIteratorNextNoSuchElementException();

    @Test(expected = ConcurrentModificationException.class)
    public abstract void testIteratorNextConcurrentModificationException();

    @Test
    public abstract void testIteratorRemove();

    @Test(expected = IllegalStateException.class)
    public abstract void testIteratorRemoveIllegalStateException();

    @Test(expected = TreeNodeException.class)
    public abstract void testIteratorRemoveOnCurrentNodeTreeNodeException();

    @Test(expected = TreeNodeException.class)
    public abstract void testIteratorRemoveOnRootTreeNodeException();

    @Test(expected = ConcurrentModificationException.class)
    public abstract void testIteratorRemoveConcurrentModificationException();
}
