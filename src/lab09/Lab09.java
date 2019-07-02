/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab09;

import java.io.IOException;
import java.util.ArrayList;
import loop.Convergence;
import decision.Decision;
import initialization.Initialization;
import loop.Loop;



/**
 *
 * @author AbdulAli
 */
public class Lab09 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        ArrayList<String> sentences;
        
        ArrayList<String> codeHeader = new ArrayList<String>(0);
        codeHeader.add(".model small");
        codeHeader.add(".stack 100h");        
        
        ArrayList<String> codeArray = new ArrayList<String>(0);
        ArrayList<String> dataArray = new ArrayList<String>(0);
        dataArray.add(".data");
        dataArray.add("arr db 2 dup('$')");
        codeArray.add(".code\nMOV AX,@data\nMOV DS,AX");
        
        FileReader fr = new FileReader();
        sentences = fr.readFile();
        fr.clearOutputFile();
        Handler handle = new Handler();
        
        for(String sentence : sentences){
            //System.out.println(sentence);
            handle.handleCode(sentence,codeArray,dataArray);            
        }

        handle.cleanCode(codeArray);
        fr.writeFile(codeHeader);
        fr.writeFile(dataArray);
        codeArray.add("MOV AH,4CH");
        codeArray.add("INT 21h");
        codeArray.add("END");
        fr.writeFile(codeArray);
        
    }
    
}
