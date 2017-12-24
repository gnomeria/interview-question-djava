package sami.interview;

import java.io.File;

/**
 * Created by Sami on 22:15 - 30/03/17.
 *
 ********************************
 *  Question 2:                 *
 ********************************
 *
 * Write a solution that returns an Integer with the total number of files
 * in a given folder including any files in its’ sub-folders (if any exist)
 *
 */
public class Q2 {
    /**
     * This method will recursively find files based on the provided directory.
     * If the provided file/directory does not exist, it will return 0
     *
     * The first parameter passing of value file will be the directory to be
     * scanned. The parameter will also be used in the recursive stack to return
     * the number of files from a directory tree, similar to DFS.
     *
     * This reason this method is private is to preemptive guard against the
     * user's error in initializing the current files count in parameter passing.
     * Hence the overloaded public method will not require the count, as it will
     * always pass '0' as count initialization.
     *
     * @param file directory object to be recursively scanned for files
     * @param count will be used as current files count in recursive stack
     * @return
     */
    private int GetNumberOfFiles(File file, int count) {
        if(!file.exists()) return 0; //the provided file path does not exist
        int current = 0;
        if(file.isFile()) return 1;
        else {
            for (File f : file.listFiles()) {
                current = current + GetNumberOfFiles(f, ++count);
            }
        }
        return current;
    }

    /**
     * Overloaded method for public user's use, and will initialize value of count
     * to be '0' in first step
     * @param file
     * @return
     */
    public int GetNumberOfFiles(File file) {
        return GetNumberOfFiles(file, 0);
    }
}
