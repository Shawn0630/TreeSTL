package io;

import model.LinkedTreeNode;
import model.TreeNode;

import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class PennTreeBankReader implements Closeable {
    Reader reader;
    int    currentChar;

    public PennTreeBankReader(Reader reader) throws IOException {
        this.reader = reader;
        currentChar = nextChar();
    }

    public PennTreeBankReader(File file)
            throws IOException {
        this(new FileReader(file));
    }

    public PennTreeBankReader(String filename)
            throws IOException {
        this(new FileReader(filename));
    }

    private int nextChar()
            throws IOException {
        return reader.read();
    }

    private String nextToken()
            throws IOException {
        if (currentChar == -1) {
            return null;
        }
        if (currentChar == '(' || currentChar == ')') {
            String s = Character.toString((char) currentChar);
            currentChar = nextChar();
            return s;
        }

        // white space
        while (Character.isWhitespace(currentChar)) {
            currentChar = nextChar();
        }

        if (currentChar == -1) {
            return null;
        }
        if (currentChar == '(' || currentChar == ')') {
            String s = Character.toString((char) currentChar);
            currentChar = nextChar();
            return s;
        }
        StringBuilder sb = new StringBuilder();
        sb.append((char) currentChar);
        currentChar = nextChar();
        while (currentChar != '('
                && currentChar != ')'
                && currentChar != -1
                && !Character.isWhitespace(currentChar)) {
            sb.append((char) currentChar);
            currentChar = nextChar();
        }
        return sb.toString();

    }

    /**
     * Read a single ptb tree.
     *
     * @return a ptb tree, or null if the end of the stream has been reached.
     * @throws IOException
     */
    public TreeNode<String> readPtbTree()
            throws IOException {

        TreeNode<String> root = new LinkedTreeNode<>("ROOT");
        TreeNode<String> current = root;

        int state = 0;
        while (true) {
            String s = nextToken();
            switch (state) {
                case 0:
                    if (s == null) {
                        return null;
                    } else if (s.equals("(")) {
                        TreeNode<String> child = new LinkedTreeNode<>();
                        current.add(child);
                        current = child;
                        state = 1;
                    } else {
                        throw new IllegalArgumentException("the ptb should start with [(]");
                    }
                    break;
                case 1:
                    if (s == null || s.equals("(") || s.equals(")")) {
                        throw new IllegalArgumentException("expecting [tag]");
                    } else {
                        current.setData(s);
                        state = 2;
                    }
                    break;
                case 2:
                    if (s == null || s.equals(")")) {
                        throw new IllegalArgumentException("expecting [(] or [word]");
                    } else if (s.equals("(")) {
                        TreeNode<String> child = new LinkedTreeNode<>();
                        current.add(child);
                        current = child;
                        state = 1;
                    } else {
                        TreeNode<String> child = new LinkedTreeNode<>(s);
                        current.add(child);
                        state = 3;
                    }
                    break;
                case 3:
                    if (s == null) {
                        throw new IllegalArgumentException("expecting [(] or [)]");
                    }
                    if (s.equals(")")) {
                        if (current == null) {
                            throw new IllegalArgumentException("too much [)]");
                        }
                        current = current.parent();
                        if (current.parent() == null) {
                            return root;
                        }
                    } else if (s.equals("(")) {
                        TreeNode<String> child = new LinkedTreeNode<>();
                        current.add(child);
                        current = child;
                        state = 1;
                    }
                    break;
            }
        }
    }

    @Override
    public void close()
            throws IOException {
        reader.close();
    }
}
