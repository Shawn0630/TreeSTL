package io;

import model.TreeNode;
import org.junit.Test;

import java.io.IOException;

public class PennTreeBankReaderTest {

    @Test
    public void testTreeBankReader() throws IOException{
        PennTreeBankReader reader = new PennTreeBankReader("test.ptb");
        TreeNode tree;
        while ((tree = reader.readPtbTree()) != null) {
            tree.preOrdered().stream().
        }
        reader.close();
    }
}
