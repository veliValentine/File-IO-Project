package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import logger.Logger;

public class Files {

    private String folderPath;
    private Logger logger;

    public Files(String folderPath) {
        this.folderPath = folderPath;
        logger = new Logger(folderPath);
    }

    public Files() {
        this("C:/Users/nicolas.valentine/Documents/Java/FileIOProject/src/resources");
    }

    public Set<String> listAllUniqueFileNames() {
        return listAllUniqueFileNames("-1");
    }

    public void printAllFileNames() {
        printAllFileNames("-1");
    }

    public void printAllFileNames(String extension) {
        for (String fileName : listAllUniqueFileNames(extension)) {
            System.out.println("  " + fileName);
        }
    }

    public Set<String> listAllUniqueFileNames(String extension) {
        Set<String> fileNames = new HashSet<>();
        try {
            File folder = new File(folderPath);
            File[] files = folder.listFiles();
            for (File file : files) {
                // extension equal to -1 => get all extensions
                // This way if extension is empty string we don't end up printing everything
                if (extension.equals("-1") || extension.equals(getFileExtension(file))) {
                    fileNames.add(getFileName(file));
                }
            }
        } catch (Exception e) {
            logger.error("Failed to read files " + e.getMessage() + "\n" + "  path:" + folderPath);
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
            logger.error("Failed to read files " + e.getMessage() + "\n" + "  path:" + folderPath);
            return null;
        }
    }

    public Long size(String fileName) {
        long time = System.currentTimeMillis();
        long fileSize = -1;
        try {
            File file = new File(folderPath + "/" + fileName);
            fileSize = file.length();
        } catch (Exception e) {
            logger.error("Failed to read files " + e.getMessage() + "\n" + "  path:" + folderPath + "\\" + fileName);
        }
        long totalTime = System.currentTimeMillis() - time;
        String text = fileName + " has size of " + fileSize + " bytes or " + (fileSize / 1024.0) + " kilobytes.";
        logger.logAndPrint(text, totalTime);
        return -1L;
    }

    public Long amountOfLines(String fileName) {
        long time = System.currentTimeMillis();
        long lines = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(folderPath + "/" + fileName))) {
            while (bufferedReader.readLine() != null) {
                lines++;
            }
        } catch (IOException e) {
            logger.error("IOException " + e.getMessage() + "\n" + "  path:" + folderPath + "\\" + fileName);
        } catch (Exception e) {
            logger.error("Failed to read files " + e.getMessage() + "\n" + "  path:" + folderPath + "\\" + fileName);
        }

        String text = fileName + " contains " + lines + " lines.";
        long totalTime = System.currentTimeMillis() - time;
        logger.logAndPrint(text, totalTime);

        return lines;
    }

    public boolean containsWord(String word, String fileName) {
        long time = System.currentTimeMillis();
        boolean contains = false;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(folderPath + "/" + fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.toLowerCase().contains(word.toLowerCase())) {
                    contains = true;
                    break;
                }
            }
        } catch (IOException e) {
            logger.error("IOException " + e.getMessage() + "\n" + "  path:" + folderPath + "\\" + fileName);
        } catch (Exception e) {
            logger.error("Failed to read files " + e.getMessage() + "\n" + "  path:" + folderPath + "\\" + fileName);
        }

        String text = fileName + " contains word " + word + ": " + contains + ".";
        long totalTime = System.currentTimeMillis() - time;
        logger.logAndPrint(text, totalTime);

        return contains;
    }

    public long countWord(String word, String fileName) {
        long time = System.currentTimeMillis();
        long count = 0;
        try (Scanner scanner = new Scanner(new File(folderPath + "/" + fileName))) {
            scanner.useDelimiter("[^a-zA-Z0-0]+");
            String fileWord;
            while (scanner.hasNext()) {
                fileWord = scanner.next().toLowerCase().trim();
                if (word.toLowerCase().equals(fileWord)) {
                    count++;
                }
            }
        } catch (IOException e) {
            logger.error("IOException " + e.getMessage() + "\n" + "  path:" + folderPath + "\\" + fileName);
        } catch (Exception e) {
            logger.error("Failed to read files " + e.getMessage() + "\n" + "  path:" + folderPath + "\\" + fileName);
        }

        String text = "The word " + word + " was " + count + " times in the file " + fileName + ".";
        long totalTime = System.currentTimeMillis() - time;
        logger.logAndPrint(text, totalTime);

        return count;
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
