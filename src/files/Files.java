package files;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class Files {

    private String folderPath;

    public Files(String folderPath) {
        this.folderPath = "../" + folderPath;
    }

    public Files() {
        folderPath = "../src/resources";
    }

    public void listAllFileNames() {
        listAllFileNames("-1");
    }

    public void listAllFileNames(String extension) {
        try {
            File folder = new File(folderPath);
            File[] files = folder.listFiles();
            for (File file : files) {
                // extension equal to -1 => get all extensions. This way if extensions is empty
                // string we don't end up printing everything
                if (extension.equals("-1") || extension.equals(getFileExtension(file))) {
                    System.out.println("  " + getFileName(file));
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to read files. " + e.getMessage());
            System.out.println("folderPath: " + folderPath);
        }
    }

    public void listAllFileExtensions() {
        try {
            File folder = new File(folderPath);
            File[] files = folder.listFiles();
            Set<String> extensions = new HashSet<>();
            for (File file : files) {
                extensions.add(getFileExtension(file));
            }
            for (String extension : extensions) {
                System.out.println("  " + extension);
            }
        } catch (Exception e) {
            System.out.println("Failed to read files. " + e.getMessage());
            System.out.println("folderPath: " + folderPath);
        }
    }

    private String getFileName(File file) {
        String fileName = file.getName();
        String trimmedFileName = fileName.substring(0, fileName.lastIndexOf("."));
        return trimmedFileName;
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        String trimmedFileName = fileName.substring(1 + fileName.lastIndexOf("."));
        return trimmedFileName;
    }

    public void setFolderPath(String path) {
        this.folderPath = path;
    }

    public String getFolderPath() {
        return folderPath;
    }

    @Override
    public String toString() {
        return "Folderpath: " + getFolderPath();
    }
}
