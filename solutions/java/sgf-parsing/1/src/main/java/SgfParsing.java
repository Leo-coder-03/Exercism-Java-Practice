import java.util.*;

public class SgfParsing {

    private String input;
    private int index;

    public SgfNode parse(String input) throws SgfParsingException {
        if (input == null || input.isEmpty()) {
            throw new SgfParsingException("tree missing");
        }

        this.input = input;
        this.index = 0;

        SgfNode root = parseTree();

        if (index != input.length()) {
            throw new SgfParsingException("unexpected content after tree");
        }

        return root;
    }

    // ---------------- TREE ----------------

    private SgfNode parseTree() throws SgfParsingException {
        expect('(');

        if (peek() == ')') {
            throw new SgfParsingException("tree with no nodes");
        }

        SgfNode root = parseSequence();

        expect(')');
        return root;
    }

    private SgfNode parseSequence() throws SgfParsingException {
        expect(';');

        SgfNode root = parseNode();
        SgfNode current = root;

        // Same variation (sequential nodes)
        while (peek() == ';') {
            consume();
            SgfNode next = parseNode();
            current.appendChild(next);
            current = next;
        }

        // Parallel variations
        while (peek() == '(') {
            SgfNode subtree = parseTree();
            root.appendChild(subtree);
        }

        return root;
    }

    // ---------------- NODE ----------------

    private SgfNode parseNode() throws SgfParsingException {
        Map<String, List<String>> props = new LinkedHashMap<>();
        SgfNode node = new SgfNode();

        while (isUpper(peek())) {
            String key = parseKey();

            if (props.containsKey(key)) {
                throw new SgfParsingException("duplicate property");
            }

            if (peek() != '[') {
                throw new SgfParsingException("properties without delimiter");
            }

            List<String> values = new ArrayList<>();
            while (peek() == '[') {
                values.add(parseValue());
            }

            props.put(key, values);
        }

        node.setProperties(props);
        return node;
    }

    private String parseKey() throws SgfParsingException {
        if (!isUpper(peek())) {
            throw new SgfParsingException("property must be in uppercase");
        }

        StringBuilder sb = new StringBuilder();
        while (isUpper(peek())) {
            sb.append(consume());
        }
        return sb.toString();
    }

    // ---------------- VALUE ----------------

    private String parseValue() throws SgfParsingException {
        expect('[');
        StringBuilder sb = new StringBuilder();

        while (peek() != ']') {
            char c = consume();

            if (c == '\\') {
                char next = consume();

                if (next == '\n') continue;          // remove escaped newline
                if (isWhitespace(next)) sb.append(' ');
                else sb.append(next);

            } else if (c == '\n') {
                sb.append('\n');

            } else if (isWhitespace(c)) {
                sb.append(' ');

            } else {
                sb.append(c);
            }
        }

        expect(']');
        return sb.toString();
    }

    // ---------------- UTIL ----------------

    private char peek() throws SgfParsingException {
        if (index >= input.length()) {
            throw new SgfParsingException("unexpected end of input");
        }
        return input.charAt(index);
    }

    private char consume() throws SgfParsingException {
        char c = peek();
        index++;
        return c;
    }

    private void expect(char expected) throws SgfParsingException {
        if (peek() != expected) {
            throw new SgfParsingException("expected '" + expected + "'");
        }
        consume();
    }

    private boolean isUpper(char c) {
        return c >= 'A' && c <= 'Z';
    }

    private boolean isWhitespace(char c) {
        return c == ' ' || c == '\t' || c == '\f' || c == '\r';
    }
}
