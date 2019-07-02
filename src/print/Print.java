/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package print;

import java.util.ArrayList;
import lab09.Utility;

/**
 *
 * @author AbdulAli
 */
public class Print {

    String printStatement;
    String var1;
    boolean newLine;
    boolean before;
    

    public String getPrintStatement() {
        return printStatement;
    }

    public String getVar1() {
        return var1;
    }

    public boolean isNewLine() {
        return newLine;
    }

    public boolean isBefore() {
        return before;
    }

    public void setPrintStatement(String printStatement) {
        this.printStatement = printStatement;
    }

    public void setVar1(String var1) {
        this.var1 = var1;
    }

    public void setNewLine(boolean newLine) {
        this.newLine = newLine;
    }

    public void setBefore(boolean before) {
        this.before = before;
    }
    
    public void writeToArray(String codeLine ,int stringNumber, ArrayList<String> codeArray, ArrayList<String> dataArray, int insideScope) {

        //dataArray.add("str"+stringNumber+" db "+printStatement+",\"$\"");
        ArrayList<String> newArray = new ArrayList<String>(0);
        int count = -1;
        boolean found = false;
        if (insideScope > 0) {

            for (String tokens : codeArray) {
                if (tokens.contains("###")) {
                    count++;
                    found = true;
                    break;
                } else {
                    count++;
                    newArray.add(tokens);
                }
            }
        }
        else {
            Utility.copyArrayList(codeArray, newArray);
        }
        
            String tokens[]=codeLine.split("<<"); 
            int len = tokens.length;
            for (int i=1; i<len-1; i++)
            {
                if (tokens[i].contains("\""))
                {
                    dataArray.add("str"+stringNumber+" db "+tokens[i]+",\"$\"");
                                       
                    newArray.add("MOV DX, Offset str"+stringNumber);
                    newArray.add("MOV AH,09");
                    newArray.add("INT 21H");
                    newArray.add(" ");    
                    stringNumber++;
                } else if (tokens[i].contains("endl")) {
                        
                    newArray.add("MOV AH,02H");
                    newArray.add("MOV DX, 10");
                    newArray.add("INT 21H");
                    newArray.add(" ");    
                } else {
                    newArray.add("MOV si,0");
                    newArray.add("MOV BL, 10");
                        
                    newArray.add("MOV AH, 0");
                    newArray.add("MOV AL,"+tokens[i]);
                    newArray.add("DIV BL");
                        
                    newArray.add("MOV CL,AH");
                    newArray.add("MOV AH,02H");
                    newArray.add("MOV DL,AL");
                    newArray.add("ADD DL,48");
                    newArray.add("INT 21H");
                        
                    newArray.add("MOV AH,02H");
                    newArray.add("MOV DL,CL");
                    newArray.add("ADD DL,48");
                    newArray.add("INT 21H");    
                    newArray.add(" ");
                }
            }
            if (tokens[len-1].contains("\""))
                {   
                    tokens=tokens[len-1].split(";");
                    System.out.println(tokens[0]);
                    dataArray.add("str"+stringNumber+" db "+tokens[0]+",\"$\"");
                    
                    newArray.add("MOV DX, Offset str"+stringNumber);
                    newArray.add("MOV AH,09");
                    newArray.add("INT 21H");
                    newArray.add(" ");    
                    stringNumber++;
                } else if (tokens[len-1].contains("endl")) {
                        
                    newArray.add("MOV AH,02H");
                    newArray.add("MOV DX, 10");
                    newArray.add("INT 21H");
                    newArray.add(" ");    
                } else  {
                    newArray.add("MOV si,0");
                    newArray.add("MOV BL, 10");
                    
                    newArray.add("\n");
                    newArray.add("MOV AH, 0");
                    newArray.add("MOV AL,"+tokens[0]);
                    newArray.add("DIV BL");
                    
                    newArray.add("\n");    
                    newArray.add("MOV CL,AH");
                    newArray.add("MOV AH,02H");
                    newArray.add("MOV DL,AL");
                    newArray.add("ADD DL,48");
                    newArray.add("INT 21H");
                    
                    newArray.add("\n");
                    newArray.add("MOV AH,02H");
                    newArray.add("MOV DL,CL");
                    newArray.add("ADD DL,48");
                    newArray.add("INT 21H");    
                    newArray.add(" ");
                }
        
        if (insideScope > 0) {
            for (int i = count; i < codeArray.size() - 1; i++) {
                newArray.add(codeArray.get(i + 1));
            }
        }

        codeArray.clear();
        Utility.copyArrayList(newArray, codeArray);
    }
    int findPlz(String STRING)
    {
        int nonSpace=0;
        for (int i=0; i<STRING.length(); ++i)
        {
            if (STRING.charAt(i)!=(' '))
            {
                nonSpace++;
            }
        }
        return nonSpace;
    }
}
