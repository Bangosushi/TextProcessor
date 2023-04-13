package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class EditorTest {
    Editor edit;
    private List<String> list;
    String programOptions;

    @BeforeEach
    void init() throws IOException {
        edit = new Editor();
        list = new ArrayList<>();
        String fileString = "test.txt";
        programOptions = "Typing \"remove\" will remove dupe lines" + System.lineSeparator()
                + "Typing \"replace\" will find and replace text" + System.lineSeparator()
                + "Typing \"read\" will remove dupe lines" + System.lineSeparator()
                + "Typing \"find\" will find text in the line" + System.lineSeparator()
                + "Typing \"sorted\" will sort the lines of text" + System.lineSeparator()
                + "Typing \"merge\" will merge the file with (a) new file(s)" + System.lineSeparator()
                + "Typing \"exit\" will exit the program" + System.lineSeparator();
        try (Scanner scanner = new Scanner(Paths.get(fileString))) {
            while (scanner.hasNext()) {
                list.add(scanner.nextLine());
            }
        }
    }

    @DisplayName("File does exist")
    @Test
    void fileExistTest() {
        String fileString = "test.txt";
        try (Scanner scanner = new Scanner(Paths.get(fileString))) {
            assumeTrue(true);
        } catch (Exception e) {
            assumeTrue(false);
        }
    }

    @DisplayName("sort the lines")
    @Test
    void sortLinesTest() throws Exception {
        edit.sortLines(this.list);
        String fileString = "output.txt";
        String fileTestString = "sortTest.txt";
        List<String> result = new ArrayList<>();
        List<String> actual = new ArrayList<>();
        try (Scanner scanner = new Scanner(Paths.get(fileString))) {
            while (scanner.hasNext()) {
                result.add(scanner.nextLine());
            }
        } catch (Exception e) {

        }
        try (Scanner scanner = new Scanner(Paths.get(fileTestString))) {
            while (scanner.hasNext()) {
                actual.add(scanner.nextLine());
            }
        } catch (Exception e) {

        }
        if (result.size() != result.size()) {
            fail();
        }
        for (int i = 0; i < result.size(); i++) {
            if (!result.get(i).equals(actual.get(i))) {
                fail();
            }
        }
        assertTrue(true);
    }

    @DisplayName("remove the dupe lines")
    @Test
    void dupeRemovalTest() throws Exception {
        edit.dupeLineRemover(this.list);
        String fileString = "output.txt";
        String fileTestString = "removeDupe.txt";
        List<String> result = new ArrayList<>();
        List<String> actual = new ArrayList<>();
        try (Scanner scanner = new Scanner(Paths.get(fileString))) {
            while (scanner.hasNext()) {
                result.add(scanner.nextLine());
            }
        } catch (Exception e) {

        }
        try (Scanner scanner = new Scanner(Paths.get(fileTestString))) {
            while (scanner.hasNext()) {
                actual.add(scanner.nextLine());
            }
        } catch (Exception e) {

        }
        if (result.size() != result.size()) {
            fail();
        }
        for (int i = 0; i < result.size(); i++) {
            if (!result.get(i).equals(actual.get(i))) {
                fail();
            }
        }
        assertTrue(true);
    }

    @DisplayName("Merge two files")
    @Test
    void mergeTwoFiles() throws Exception {
        String userInput = "test" + System.lineSeparator()
                + "merge" + System.lineSeparator()
                + "test.txt" + System.lineSeparator()
                + "done" + System.lineSeparator()
                + "exit";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        String expected = "type in the file name inside of the project" + System.lineSeparator()
                + "Found the file, what would you like to do?" + System.lineSeparator()
                + programOptions
                + "Which files would you like to merge this with?" + System.lineSeparator()
                + "Enter a new file on each line below or 'done' when no more files to merge" + System.lineSeparator()
                + "Files have been merged into output.txt" + System.lineSeparator()
                + programOptions
                + "Thank you using the text processor. Good bye";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        edit.start();

        // Compile program output
        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = String.join(System.lineSeparator(), lines);

        assertEquals(expected, actual);
    }

    @DisplayName("Merge file with large file")
    @Test
    void mergeBigFiles() throws Exception {
        String userInput = "test" + System.lineSeparator()
                + "merge" + System.lineSeparator()
                + "largeFile.txt" + System.lineSeparator()
                + "done" + System.lineSeparator()
                + "exit";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        String expected = "type in the file name inside of the project" + System.lineSeparator()
                + "Found the file, what would you like to do?" + System.lineSeparator()
                + programOptions
                + "Which files would you like to merge this with?" + System.lineSeparator()
                + "Enter a new file on each line below or 'done' when no more files to merge" + System.lineSeparator()
                + "Files have been merged into output.txt" + System.lineSeparator()
                + programOptions
                + "Thank you using the text processor. Good bye";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        edit.start();

        // Compile program output
        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = String.join(System.lineSeparator(), lines);

        assertEquals(expected, actual);
    }

    @DisplayName("Test Exit")
    @Test
    void exitTest() throws Exception {
        // Set the user input in the input stream
        String userInput = "test" + System.lineSeparator() + "exit";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        // Set the output stream to record program output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        // Start the application
        edit.start();

        // Compile program output
        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = String.join(System.lineSeparator(), lines);

        // Compile expected output
        String expected = "type in the file name inside of the project" + System.lineSeparator()
                + "Found the file, what would you like to do?" + System.lineSeparator()
                + programOptions
                + "Thank you using the text processor. Good bye";

        // Test output is expected
        assertEquals(expected, actual);
    }

    @AfterEach
    void cleanFiles() {
        File output = new File("output.txt");
        output.delete();
    }
}
