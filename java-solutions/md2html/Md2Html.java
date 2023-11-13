package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Stack;


public class Md2Html {
    public static int getHeader(String block) {
        int header = 0;
        while (header < block.length() && block.charAt(header) == '#') {
            header++;
        }
        if (header != block.length() && !Character.isWhitespace(block.charAt(header))) {
            header = 0;
        }
        return header;
    }

    public static String handleBlock(String block) {
        StringBuilder result = new StringBuilder();
        Stack<String> vals = new Stack<>();
        int header = getHeader(block);
        block = block.substring(header);
        result.append("<").append((header == 0) ? "p" : "h" + header).append(">");
        if (header != 0) block = block.stripLeading();

        for (int i = 0; i < block.length(); i++) {
            String tag = "?";
            int tagLn = 0;
            boolean slash = (i > 0 && block.charAt(i - 1) == '\\');

            if (i + 1 < block.length() && !slash) {
                switch (block.substring(i, i + 2)) {
                    case "__", "**" -> tag = "strong";
                    case "<<", ">>" -> tag = "ins";
                    case "--" -> tag = "s";
                    case "{{", "}}" -> tag = "del";
                }
                if (!tag.equals("?")) tagLn = 2;
            }

            boolean isConnected = i > 0 && !Character.isWhitespace(block.charAt(i - 1)) ||
                    i + 1 < block.length() && !Character.isWhitespace(block.charAt(i + 1));
            if (tagLn == 0 && !slash && isConnected) {
                tag = switch (block.charAt(i)) {
                    case '*', '_' -> "em";
                    case '`' -> "code";
                    default -> tag;
                };
                if (!tag.equals("?")) tagLn = 1;
            }

            if (tagLn == 0) {
                char v = block.charAt(i);
                switch (v) {
                    case '&':
                        result.append("&amp;");
                        break;
                    case '<':
                        result.append("&lt;");
                        break;
                    case '>':
                        result.append("&gt;");
                        break;
                    case '\\':
                        if (slash) result.append(v);
                        break;
                    default:
                        result.append(v);
                }
                continue;
            }

            result.append("<");
            if (!vals.empty() && vals.peek().equals(tag)) {
                vals.pop();
                result.append("/");
            } else {
                vals.push(tag);
            }
            result.append(tag).append(">");
            i += tagLn - 1;
        }
        result.append("</").append((header == 0) ? "p" : "h" + header).append(">");
        return result.toString();
    }

    public static void main(String[] args) {
        ArrayList<String> ans = new ArrayList<String>();
        try (BufferedReader in = new BufferedReader(new FileReader(args[0], StandardCharsets.UTF_8))) {
            StringBuilder block = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                if (line.trim().length() != 0) {
                    if (block.length() != 0) block.append("\n");
                    block.append(line);
                } else if (block.length() > 0) {
                    ans.add(handleBlock(block.toString()));
                    block.setLength(0);
                }
            }
            if (block.length() > 0) {
                ans.add(handleBlock(block.toString()));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Can`t open input file: " + e.getMessage());
            return;
        } catch (IOException e) {
            System.err.println("Error when reading input file: " + e.getMessage());
            return;
        }
        try (BufferedWriter out = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8))) {
            for (String v : ans) {
                out.write(v);
                out.newLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Can`t open output file: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error when writing to output file: " + e.getMessage());
        }
    }
}