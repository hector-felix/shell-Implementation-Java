/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc341assn1;

/**
 *
 * @author Hector Felix
 */
import java.io.*;
import java.util.*;

public class jsh {

    public static void main(String[] args) throws java.io.IOException {

        String commandLine;
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        ProcessBuilder pb = new ProcessBuilder();
        String parseSpace = "\\";

        // we break out with <control><C>
        while (true) {
            // read what they entered
            System.out.print("jsh>");
            commandLine = console.readLine();

            // If they entered a return, just loop again
            if (commandLine.equals("")) {
                continue;
            }

            StringTokenizer st = new StringTokenizer(commandLine);
            ArrayList<String> commandList = new ArrayList<>();

            while (st.hasMoreElements()) {
                commandList.add(st.nextToken());
            }

            if (!commandList.contains("cd")) {
                String argument;
                argument = commandList.get(commandList.size() - 1);
                System.out.println("argument is: " + argument);
                String makePath = pb.directory() + parseSpace + argument;
                System.out.println("path created is " + makePath);
                File newPath = new File(makePath);
                pb.directory(newPath);
                continue;
            } else {
                if (commandList.get(commandList.size() - 1).equals("cd")) {

                    File home = new File(System.getProperty("user.home"));
                    System.out.println("Home Directory:  " + home);
                    pb.directory(home);
                    continue;
                }
                

            }

            pb.command(commandList);
            Process process = pb.start();
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            try (BufferedReader br = new BufferedReader(isr)) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
                br.close();
            }
        }
    }
}
