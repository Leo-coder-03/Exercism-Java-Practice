import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

class GrepTool {

    String grep(String pattern, List<String> flags, List<String> files) {

        boolean flagN = flags.contains("-n");
        boolean flagL = flags.contains("-l");
        boolean flagI = flags.contains("-i");
        boolean flagV = flags.contains("-v");
        boolean flagX = flags.contains("-x");

        if (flagI) {
            pattern = pattern.toLowerCase();
        }

        List<String> output = new ArrayList<>();
        boolean multipleFiles = files.size() > 1;

        for (String filename : files) {

            List<String> lines;
            try {
                lines = Files.readAllLines(Paths.get(filename));
            } catch (IOException e) {
                throw new RuntimeException("Failed to read file: " + filename, e);
            }

            boolean fileHasMatch = false;

            for (int i = 0; i < lines.size(); i++) {
                String originalLine = lines.get(i);
                String line = originalLine;

                if (flagI) {
                    line = line.toLowerCase();
                }

                boolean match = flagX ? line.equals(pattern) : line.contains(pattern);

                if (flagV) {
                    match = !match;
                }

                if (match) {
                    fileHasMatch = true;

                    if (flagL) break;
                    output.add(formatLine(filename, originalLine, i + 1, multipleFiles, flagN));
                }
            }

            if (flagL && fileHasMatch) {
                output.add(filename);
            }
        }

        return String.join("\n", output);
    }

    private String formatLine(String filename,
                              String line,
                              int lineNumber,
                              boolean multipleFiles,
                              boolean flagN) {

        StringBuilder sb = new StringBuilder();

        if (multipleFiles) {
            sb.append(filename).append(":");
        }

        if (flagN) {
            sb.append(lineNumber).append(":");
        }

        sb.append(line);
        return sb.toString();
    }
}
