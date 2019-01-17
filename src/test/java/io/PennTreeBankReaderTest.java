package io;

import model.TreeNode;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

public class PennTreeBankReaderTest {

    @Test
    public void testTreeBankReader() throws IOException{
        String filename = "test.ptb";
        ClassLoader classLoader = new PennTreeBankReaderTest().getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        PennTreeBankReader reader = new PennTreeBankReader(file);
        TreeNode<String> tree;
        while ((tree = reader.readPtbTree()) != null) {
            System.out.println(tree.leaves().stream().map(TreeNode::data).collect(Collectors.toList()));
        }
        reader.close();
    }
}
