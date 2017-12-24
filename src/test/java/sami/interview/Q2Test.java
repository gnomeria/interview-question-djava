package sami.interview;

import org.junit.After;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Sami on 22:38 - 30/03/17.
 */
@SuppressWarnings({"ResultOfMethodCallIgnored", "ConstantConditions"})
public class Q2Test {
    private static final Logger logger = LoggerFactory.getLogger(Q2Test.class);
    protected Q2 question2 = new Q2();
    //current folder of current java working directory
    protected final File currentDir = new File(System.getProperty("user.dir"));
    protected final File testFolder = new File(System.getProperty("user.dir") + File.separator + "testfolder");
    protected String assertTextFormat = "The scan for test folder should return: %d.";

    @After
    public void tearDown() throws Exception {
        logger.debug("Cleaning up test folder...");
        clearTestFolder();

    }

    @Test
    public void shouldReturn0FileCount() throws IOException {
        logger.debug("Running first test -- 0");
        createNumOfFilesTest(0);
        assertEquals(String.format(assertTextFormat, 0), 0, question2.GetNumberOfFiles(testFolder));
    }

    @Test
    public void shouldReturn10FileCount() throws IOException {
        logger.debug("Running second test -- 10");
        createNumOfFilesTest(10);
        assertEquals(String.format(assertTextFormat, 10), 10, question2.GetNumberOfFiles(testFolder));
    }

    @Test
    public void shouldReturn100FilesCount() throws Exception {
        logger.debug("Running third test -- 100");
        createNumOfFilesTest(100);
        assertEquals(String.format(assertTextFormat, 100), 100, question2.GetNumberOfFiles(testFolder));
    }

    @Test
    public void shouldReturn105FilesCountSubDir() throws Exception {
        logger.debug("Running third test -- 105");
        createNumOfFilesTest(100);
        File testSubdir = new File(testFolder.getAbsolutePath() + File.separator + "subdir");
        createNumOfFilesTest(testSubdir, 5);
        assertEquals(String.format(assertTextFormat, 105), 105, question2.GetNumberOfFiles(testFolder));
    }


    public void createNumOfFilesTest(int n) throws IOException {
        createNumOfFilesTest(testFolder, n);
    }
    public void createNumOfFilesTest(File folder, int n) throws IOException {
        logger.debug("Creating test folder in {}", folder);
        if (folder.mkdir()) {
            logger.debug("Test folder successfully created.");
            for (int i = 0; i < n; i++) {
                File.createTempFile("test", String.valueOf(i), folder);
            }
        } else {
            logger.error("Cannot create test folder at current directory.");
            assertTrue(false); //will fail the assert for the test case
        }
    }


    /**
     * This clear test will recursively deletes all files from a folder, along with files in its
     * subdirectories, before deleting the folder(s)
     *
     * @param folder folder to be deleted
     * @throws IOException if the files can't be deleted
     */
    public void clearTestFolder(File folder) throws IOException {
        if (folder != null) {
            //delete all files from test folder
            Arrays.stream(folder.listFiles()).forEach(
                    file -> {
                        if (file.isDirectory()) {
                            try {
                                clearTestFolder(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else file.delete();
                    }
            );

            if (folder.delete())
                logger.debug("Test temporary files have been successfully cleared up");
            else
                logger.warn("Failed to do test file cleanup. Manual deletion might be required");
        }
    }
    public void clearTestFolder() throws IOException {
        clearTestFolder(testFolder);
    }

}