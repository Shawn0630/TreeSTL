/*
 * Copyright 2016 Scalified <http://www.scalified.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package model;

import org.junit.Test;

/**
 * @author shell
 * @version 1.0.0
 * @since 1.0.0
 */
public class ArrayTreeNodeTest extends TreeNodeImplementationTest {

    /*
     * Test tree structure
     *
     *   +- root(ROOT_DATA)
     *      +- node_1(NODE_DATA_1)
     *      +- node_2(NODE_DATA_2)
     *      |  +- node_3(NODE_DATA_3)
     *      |  |  +- node_4(NODE_DATA_4)
     *      |  |  +- node_5(NODE_DATA_1)
     *      |  |  |  +- node6 (NODE_DATA4)
     *      |  +- node_7(null)
     *      |  +- node_8(NODE_DATA_1)
     *      +- node9(NODE_DATA_4)
     *      |  +- node10(null)
     *
     */

    @Override
    protected <T> TreeNode<T> createTreeNode(T data) {
        return new ArrayTreeNode<>(data);
    }

    private static <T> TreeNode<T> createTreeNode(T data, int branchingFactor) {
        return new ArrayTreeNode<>(data, branchingFactor);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectBranchingFactor() {
        int incorrectBranchingFactor = -10;
        createTreeNode(TreeNodeTest.ROOT_DATA, incorrectBranchingFactor);
    }

}