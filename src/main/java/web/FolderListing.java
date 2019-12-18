package web;

import javax.ejb.Stateless;
import java.io.File;

@Stateless
public class FolderListing implements IFolderListing {

    @Override
    public String getListing(String folderName) {
        final File file = new File(folderName);
        if(file.isDirectory()){
            return folderName + "\\<br>"+  getContentOfFolder(file.getAbsoluteFile(),0).toString();
        }
        else{
            return folderName + " - is not a directory <br>";
        }
    }

    private static StringBuffer getContentOfFolder(File currentFile, int level) {
        level++;
        final StringBuffer listOfFiles = new StringBuffer();
        if (currentFile != null) {
            File[] files = currentFile.getAbsoluteFile().listFiles();
            assert files != null;
            for (File file : files) {
                listOfFiles.append(addWhiteSpaces(level));
                listOfFiles.append(file.getName());
                if (file.isDirectory()) {
                    listOfFiles.append("\\");
                }
                listOfFiles.append("<br>").append(System.lineSeparator());
                if (file.isDirectory()) {
                    listOfFiles.append(getContentOfFolder(file, level));
                }
            }
        }
        return listOfFiles;
    }

    private static String addWhiteSpaces(int level) {
        final StringBuilder whiteSpace = new StringBuilder(new String());
        whiteSpace.append("&nbsp;".repeat(Math.max(0, level * 4)));
        return whiteSpace.toString();
    }
}
