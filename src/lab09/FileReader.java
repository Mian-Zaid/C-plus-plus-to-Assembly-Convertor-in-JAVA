/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab09;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author AbdulAli
 */
public class FileReader {

    public FileReader() {
        
        
    }
    
    public ArrayList<String> readFile() throws FileNotFoundException, IOException{
        
        FileInputStream fstream = new FileInputStream("input.cpp");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        ArrayList<String> sentences = new ArrayList<String>(0);
        String sentence;
        
        //Read File Line By Line
        while ((sentence = br.readLine()) != null)   {
        // Print the content on the console
        sentences.add(sentence);
        }

        //Close the input stream
        br.close();
        
        return sentences;
    }
    
    
    public void writeFile(ArrayList<String> sentences) throws FileNotFoundException, IOException{
        
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.asm", true));
        //PrintWriter out = new PrintWriter(new FileOutputStream("output.asm"));
        for(String temp : sentences){
           bw.write(temp);
           bw.newLine();
        }
        
        bw.flush();
        bw.close();
    }
    
    public void clearOutputFile() throws FileNotFoundException{
        PrintWriter writer = new PrintWriter(new FileOutputStream("output.asm"));
        writer.print("");
        writer.close();
    }
}