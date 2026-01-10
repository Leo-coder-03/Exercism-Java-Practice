class Markdown {

    public String parse(String markdown) {
        String[] lines = markdown.split("\n");
        StringBuilder html = new StringBuilder();

        boolean insideList = false;

        for (String line : lines) {

            String htmlLine = parseHeader(line);
            if (htmlLine == null) htmlLine = parseListItem(line);
            if (htmlLine == null) htmlLine = parseParagraph(line);

            boolean isListItem = htmlLine.startsWith("<li>");

            if (isListItem && !insideList) {
                insideList = true;
                html.append("<ul>");
            }
            if (!isListItem && insideList) {
                insideList = false;
                html.append("</ul>");
            }

            html.append(htmlLine);
        }

        if (insideList) {
            html.append("</ul>");
        }

        return html.toString();
    }


    private String parseHeader(String line) {
        int hashCount = 0;

        while (hashCount < line.length() && line.charAt(hashCount) == '#') {
            hashCount++;
        }

        if (hashCount == 0) return null;           // Not a header
        if (hashCount > 6) return wrapInParagraph(line);  // More than h6 → treat as paragraph

        // Valid header
        String content = line.substring(hashCount + 1); // Skip '# ' pattern
        return "<h" + hashCount + ">" + content + "</h" + hashCount + ">";
    }


    private String parseListItem(String line) {
        if (!line.startsWith("* ")) return null;

        String content = line.substring(2);
        return "<li>" + applyInlineFormatting(content) + "</li>";
    }


    private String parseParagraph(String line) {
        return wrapInParagraph(applyInlineFormatting(line));
    }

    private String wrapInParagraph(String text) {
        return "<p>" + text + "</p>";
    }


    private String applyInlineFormatting(String text) {
        text = text.replaceAll("__(.+)__", "<strong>$1</strong>");

        text = text.replaceAll("_(.+)_", "<em>$1</em>");

        return text;
    }
}
