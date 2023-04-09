package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class EditorTest {
    Editor edit;
    private List<String> list;

    @BeforeEach
    void init() throws IOException {
        edit = new Editor();
        list = new ArrayList<>();
        String fileString = "test.txt";
        try(Scanner scanner = new Scanner(Paths.get(fileString))){
            while(scanner.hasNext()){
                list.add(scanner.nextLine());
            }
        }
    }
    @DisplayName("File does exist")
    @Test
    void fileExistTest(){
        String fileString = "test.txt";
        try(Scanner scanner = new Scanner(Paths.get(fileString))){
            assumeTrue(true);
        }catch(Exception e){
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
        try(Scanner scanner = new Scanner(Paths.get(fileString))){
            while(scanner.hasNext()){
                result.add(scanner.nextLine());
            }
        }catch(Exception e){

        }
        try(Scanner scanner = new Scanner(Paths.get(fileTestString))){
            while(scanner.hasNext()){
                actual.add(scanner.nextLine());
            }
        }catch (Exception e){

        }
        if(result.size() != result.size()){
            fail();
        }
        for(int i = 0; i < result.size();i++){
            if(!result.get(i).equals(actual.get(i))){
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
        try(Scanner scanner = new Scanner(Paths.get(fileString))){
            while(scanner.hasNext()){
                result.add(scanner.nextLine());
            }
        }catch(Exception e){

        }
        try(Scanner scanner = new Scanner(Paths.get(fileTestString))){
            while(scanner.hasNext()){
                actual.add(scanner.nextLine());
            }
        }catch (Exception e){

        }
        if(result.size() != result.size()){
            fail();
        }
        for(int i = 0; i < result.size();i++){
            if(!result.get(i).equals(actual.get(i))){
                fail();
            }
        }
        assertTrue(true);
    }
}
