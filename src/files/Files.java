package files;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class Files {

    private String folderPath;

    public Files(String folderPath) {
        this.folderPath = folderPath;
    }

    public Files() {
        folderPath = "C:/Users/nicolas.valentine/Documents/Java/FileIOProject/src/resources";
    }

    public void listAllFileNames() {
        listAllFileNames("-1");
    }

    public Set<String> listAllFileNames(String extension) {
        Set<String> fileNames = new HashSet<>();
        try {
            File folder = new File(folderPath);
            File[] files = folder.listFiles();
            for (File file : files) {
                // extension equal to -1 => get all extensions
                // This way if extension is empty string we don't end up printing everything
                if (extension.equals("-1") || extension.equals(getFileExtension(file))) {
                    String fileName = getFileName(file);
                    fileNames.add(getFileName(file));
                    System.out.println("  " + fileName);
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to read files. " + e.getMessage());
            System.out.println("folderPath: " + folderPath);
        }
        return fileNames;
    }

    public Set<String> listAllFileExtensions() {
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
            return extensions;
        } catch (Exception e) {
            System.out.println("Failed to read files. " + e.getMessage());
            System.out.println("folderPath: " + folderPath);
            return null;
        }
    }

    private String getFileName(File file) {
        return getFileName(file, true);
    }

    private String getFileName(File file, boolean full) {
        String fileName = file.getName();
        if (full) {
            return fileName;
        }
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
