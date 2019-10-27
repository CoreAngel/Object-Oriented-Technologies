package filesystem.node.verify;

import filesystem.node.Node;
import filesystem.node.directory.Directory;
import filesystem.node.directory.RootDirectory;

import java.util.ArrayList;

public class Verifier {

    public static void verifyName(String name) throws VerifyException
    {
        if (name == null) {
            throw new VerifyException("Name cannot be null");
        }

        String trimmedName = name.trim();

        if (trimmedName.isEmpty() || trimmedName.length() > 12) {
            throw new VerifyException("Wrong length");
        }

        if (trimmedName.equals(".") || trimmedName.equals("..")) {
            throw new VerifyException("Node cannot have name . or ..");
        }

        for (int i = 0; i < trimmedName.length(); ++i) {
            int oneChar = trimmedName.charAt(i);
            if(!(oneChar >= '0' && oneChar <= '9' ||
                oneChar >= 'a' && oneChar <= 'z' ||
                oneChar >= 'A' && oneChar <= 'Z' ||
                oneChar == '_' || oneChar == '.' || oneChar == '-')) {
                throw new VerifyException("Wrong name. Unexpected char: " + (char)oneChar);
            }
        }
    }

    public static void verifyDuplicate(Node toAdd, Directory parent) throws VerifyException
    {
        ArrayList<Node> nodes = parent.getNodesList();
        for (Node node: nodes) {
            if (toAdd.getName().equals(node.getName())) {
                throw new VerifyException("Directory already contains node with name: " + toAdd.getName());
            }
        }

    }

    public static void verifyPath(Directory path) throws VerifyException
    {
        if (path == null) {
            throw new VerifyException("Path cannot be null");
        }

        if (Verifier.findCycle(path, path)) {
            throw new VerifyException("Cycle detected");
        }

    }

    private static boolean findCycle(Directory currentPath, Directory testedPath) {
        if (currentPath instanceof RootDirectory) {
            return false;
        } else {
            Directory node = currentPath.getPath();
            if (node == testedPath) {
                return true;
            } else {
                return Verifier.findCycle(node, testedPath);
            }
        }
    };

}
