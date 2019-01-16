package io;

import model.TreeNode;
import org.junit.Test;

import java.io.IOException;
import java.util.stream.Collectors;

public class PennTreeBankReaderTest {

    @Test
    public void testTreeBankReader() throws IOException{
        PennTreeBankReader reader = new PennTreeBankReader("test.ptb");
        TreeNode<String> tree;
        while ((tree = reader.readPtbTree()) != null) {
            System.out.println(tree.leaves().stream().map(TreeNode::data).collect(Collectors.toList()));
        }
        reader.close();
    }
}
