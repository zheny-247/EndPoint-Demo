package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class DirectoryManager {

    private Directory rootDirectory;

    public DirectoryManager() {
        this.rootDirectory = new Directory("");
    }

    public String create(List<String> args) {
        if (args.size() != 1) {
            return "Error: CREATE command requires 1 argument, e.g., CREATE fruits.";
        }

        String path = args.get(0);
        if (findNodeByPath(path) != null) {
            return "Cannot create " + path + " - directory already exists";
        }

        Directory currentNode = rootDirectory;
        String[] pathComponents = path.split("/");
        for (String component : pathComponents) {
            Directory childNode = currentNode.getChildren().get(component);
            if (childNode == null) {
                childNode = new Directory(component);
                childNode.setParent(currentNode);
                currentNode.getChildren().put(component, childNode);
            }
            currentNode = childNode;
        }

        return "CREATE " + path;
    }

    public String move(List<String> args) {
        if (args.size() != 2) {
            return "Error: MOVE command requires 2 arguments, e.g., MOVE fruits vegetables.";
        }

        String sourcePath = args.get(0);
        String targetPath = args.get(1);

        Directory sourceNode = findNodeByPath(sourcePath);
        if (sourceNode == null) {
            return "Cannot move " + sourcePath + " - source directory does not exist";
        }

        Directory targetNode = findNodeByPath(targetPath);
        if (targetNode == null) {
            return "Cannot move " + sourcePath + " - target directory does not exist";
        }

        if (targetNode.getChildren().containsKey(sourceNode.getName())) {
            return "Cannot move " + sourcePath + " - target directory already contains a directory with the same name";
        }

        Directory sourceParent = sourceNode.getParent();
        sourceParent.getChildren().remove(sourceNode.getName());

        targetNode.getChildren().put(sourceNode.getName(), sourceNode);
        sourceNode.setParent(targetNode);

        return "MOVE " + sourcePath + " " + targetPath;
    }

    public String delete(List<String> args) {
        if (args.size() != 1) {
            return "Error: DELETE command requires 1 argument, e.g., DELETE fruits.";
        }

        String path = args.get(0);
        Directory nodeToDelete = findNodeByPath(path);
        if (nodeToDelete == null) {
            return "Cannot delete " + path + " - directory does not exist";
        }

        Directory parent = nodeToDelete.getParent();
        parent.getChildren().remove(nodeToDelete.getName());

        return "DELETE " + path;
    }

    public String list() {
        StringBuilder output = new StringBuilder();
        listRecursively(rootDirectory, output, 0);
        return output.toString();
    }

    private void listRecursively(Directory node, StringBuilder output, int level) {
        List<Directory> sortedChildren = new ArrayList<>(node.getChildren().values());
        sortedChildren.sort(Comparator.comparing(Directory::getName));

        for (Directory child : sortedChildren) {
            for (int i = 0; i < level; i++) {
                output.append("  ");
            }
            output.append(child.getName()).append("\n");
            listRecursively(child, output, level + 1);
        }
    }

    private Directory findNodeByPath(String path) {
        String[] pathComponents = path.split("/");
        Directory currentNode = rootDirectory;

        for (String component : pathComponents) {
            if (component.isEmpty()) {
                continue;
            }

            Directory childNode = currentNode.getChildren().get(component);
            if (childNode == null) {
                return null;
            }
            currentNode = childNode;
        }

        return currentNode;
    }
}