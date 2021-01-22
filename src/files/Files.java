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
    private File[] files;

    public Files(String folderPath) {
        this.folderPath = folderPath;
        logger = new Logger(folderPath);
        try {
            File folder = new File(folderPath);
            if (folder.isDirectory()) {
                files = folder.listFiles();
            } else {
                logger.error("Path is not a folder. Path:" + folderPath);
            }
        } catch (Exception e) {
            logger.error("Failed to read files " + e.getMessage() + "\n" + "  path:" + folderPath);
        }
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
        for (File file : files) {
            // extension equal to -1 => get all extensions
            // This way if extension is empty string we don't end up printing everything
            if (extension.equals("-1") || extension.equals(getFileExtension(file))) {
                fileNames.add(getFileName(file));
            }
        }
        return fileNames;
    }

    public Set<String> listAllFileExtensions() {
        Set<String> extensions = new HashSet<>();
        for (File file : files) {
            String extension = getFileExtension(file);
            extensions.add(extension);
            System.out.println(extension);
        }
        return extensions;
    }

    public Long size(String fileName) {
        long time = System.currentTimeMillis();

        File file = getFile(fileName);
        long fileSize = file.length();

        // logger inputs
        long totalTime = System.currentTimeMillis() - time;
        String text = fileName + " has size of " + fileSize + " bytes or ~" + (Math.round(fileSize / 1024.0))
                + " kilobytes.";
        logger.logAndPrint(text, totalTime);
        return fileSize;
    }

    public Long amountOfLines(String fileName) {
        long time = System.currentTimeMillis();
        long lines = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(folderPath + "/" + fileName))) {
            // read file line by line
            while (bufferedReader.readLine() != null) {
                lines++;
            }
        } catch (IOException e) {
            logger.error("IOException " + e.getMessage() + "\n" + "  path:" + folderPath + "\\" + fileName);
        } catch (Exception e) {
            logger.error("Failed to read files " + e.getMessage() + "\n" + "  path:" + folderPath + "\\" + fileName);
        }

        // logger inputs
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
                    // if the word is found, exit loop and return result
                    contains = true;
                    break;
                }
            }
        } catch (IOException e) {
            logger.error("IOException " + e.getMessage() + "\n" + "  path:" + folderPath + "\\" + fileName);
        } catch (Exception e) {
            logger.error("Failed to read files " + e.getMessage() + "\n" + "  path:" + folderPath + "\\" + fileName);
        }

        // logger inputs
        String text = fileName + " contains word " + word + ": " + contains + ".";
        long totalTime = System.currentTimeMillis() - time;
        logger.logAndPrint(text, totalTime);

        return contains;
    }

    public long countWord(String word, String fileName) {
        long time = System.currentTimeMillis();
        long count = 0;
        try (Scanner scanner = new Scanner(getFile(fileName))) {
            // divide file into small string pieces that contain only letters or numbers
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

        // logger inputs
        String text = "The word " + word + " was " + count + " times in the file " + fileName + ".";
        long totalTime = System.currentTimeMillis() - time;
        logger.logAndPrint(text, totalTime);

        return count;
    }

    private File getFile(String fileName) {
        // reads file from the folder
        try {
            File file = new File(folderPath + "/" + fileName);
            if (file.isFile()) {
                return file;
            }
        } catch (Exception e) {
            logger.error("Failed to read files " + e.getMessage() + "\n" + "  path:" + folderPath + "\\" + fileName);
        }
        return null;
    }

    private String getFileName(File file) {
        return getFileName(file, true);
    }

    private String getFileName(File file, boolean full) {
        String fileName = file.getName();
        if (full) {
            return fileName;
        }
        // trim the extension out of the file name
        String trimmedFileName = fileName.substring(0, fileName.lastIndexOf("."));
        return trimmedFileName;
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        String extension = fileName.substring(1 + fileName.lastIndexOf("."));
        return extension;
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
