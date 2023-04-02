package org.example;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Editor {

    public Editor() throws IOException {
    }

    public void start(){
        Scanner userInterface = new Scanner(System.in);
        System.out.println("type in the file name on your desktop");
        String fileName = userInterface.nextLine();

        try(Scanner scanner = new Scanner(Paths.get("/Users/ryanoh/desktop/" + fileName + ".txt"))){
            System.out.println("Found the file, what would you like to do?");
            System.out.println("Typing \"remove\" will remove dupe lines");
            

        }catch(Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong");
        }
    }
    public void read(Scanner scanner){
        while(scanner.hasNext()){
            System.out.println(scanner.nextLine());
        }
    }



}
