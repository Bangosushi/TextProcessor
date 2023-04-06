package org.example;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Editor {

    public Editor() throws IOException {
    }

    public void start(){
        Scanner userInterface = new Scanner(System.in);
        System.out.println("type in the file name on your desktop");
        String fileName = userInterface.nextLine();
        String fileString = "/Users/ryanoh/desktop/" + fileName + ".txt";
        try(Scanner scanner = new Scanner(Paths.get(fileString))){
            System.out.println("Found the file, what would you like to do?");
            List<String> list =  new ArrayList<>();
            while(scanner.hasNext()){
                list.add(scanner.nextLine());
            }
            String prompt = "";
            while(true){
                System.out.println("Typing \"remove\" will remove dupe lines");
                System.out.println("Typing \"replace\" will find and replace text");
                System.out.println("Typing \"read\" will remove dupe lines");
                System.out.println("Typing \"find\" will find text in the line");
                System.out.println("Typing \"sorted\" will sort the lines of text");
                System.out.println("Typing \"merge\" will merge the file with (a) new file(s)");
                System.out.println("Typing \"exit\" will exit the program");
                prompt = userInterface.nextLine();
                if(prompt.equals("read")){
                    read(list);
                }
                if(prompt.equals("exit")){
                    System.out.println("Thank you using the text processor. Good bye");
                    break;
                }
                if(prompt.equals("sorted")){
                    sortLines(list);
                }
                if(prompt.equals("remove")){
                    dupeLineRemover(list);
                }
                if(prompt.equals("find")){
                    findLine(userInterface,list);
                }
                if(prompt.equals("replace")){
                    findAndReplace(userInterface,list);
                }
                if(prompt.equals("merge")){
                    // TODO: figure out where files are saved/stored
                    mergeFiles(userInterface, fileName);
                }

            }

        }catch(Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong");
        }
    }

    private void mergeFiles(Scanner userInterface, String fileName) {
        FileHandler handler = new FileHandler();
        System.out.println("Which files would you like to merge this with?");
        System.out.println("Enter a new file on each line below or 'done' when no more files to merge");

        // Instantiate list of files to merge and read first input
        List<Path> files = new ArrayList<>();
        files.add(Paths.get(fileName));
        String filePath = userInterface.nextLine();

        // While
        while (!Objects.equals(filePath, "done")) {
            files.add(Paths.get(filePath));
            filePath = userInterface.nextLine();
        }

        // Merge files and output file
        handler.mergeFiles(files, "output.txt");
        System.out.println("Files have been merged into output.txt");
    }

    public void read(List<String> list){
        for(String text : list){
            System.out.println(text);
        }
    }
    public void findLine(Scanner userInterface, List<String> list){

        int counter = 1;
        System.out.println("Which line do you want to find?");
        String input = userInterface.nextLine();
        for(String text: list){
            if(text.contains(input)){
                System.out.println("The text was found on line: " + counter);
                System.out.println(text);
            }
            counter++;
        }
    }
    public void findAndReplace(Scanner userInterface, List<String> list){

        System.out.println("What string would you like to replace?");
        String input = userInterface.nextLine();
        System.out.println("What you like to replace it with?");
        String newString = userInterface.nextLine();
        try(PrintWriter printWriter = new PrintWriter(new File("output.txt"))){

            for(String text: list){
                boolean stat = input.equalsIgnoreCase(text);
                if(stat){
                    text = newString;
                    printWriter.println(text);
                }
                else if(text.contains(input)){
                    String[] brokenString = text.split(" ");
                    for(int i = 0; i < brokenString.length;i++){
                        if(brokenString[i].equals(input)){
                            brokenString[i] = newString;
                        }
                    }
                    text = String.join(" ", brokenString);
                    printWriter.println(text);
                }else{
                    printWriter.println(text);
                }
            }
        }catch (Exception e){
            System.out.println("Error:" + e);
        }
    }
    // dupe line
    public void dupeLineRemover(List<String> list){
        Set<String> set = new LinkedHashSet<>(list);
        try(PrintWriter printWriter = new PrintWriter(new File("output.txt"))){
            for(String text: set){
                printWriter.println(text);
                System.out.println(text);
            }
        }catch (Exception e){
            System.out.println("Error:" + e);
        }
        System.out.println("Dupe lines have been removed");
    }
    public void sortLines(List<String> list){

        Collections.sort(list);

        try(PrintWriter printWriter = new PrintWriter(new File("output.txt"))){
            for(String sortedText: list){
                printWriter.println(sortedText);
                System.out.println(sortedText);
            }
        }catch (Exception e){
            System.out.println("Error:" + e);
        }
    }



}
