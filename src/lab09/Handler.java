/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab09;

import decision.Decision;
import initialization.Initialization;
import java.util.ArrayList;
import loop.Convergence;
import loop.Loop;
import print.Print;

/*
@author AbdulAli
 */
public class Handler {
    
    int labelCount = 0;
    int ifLabelCount=0;
    int stringCount = 0;
    
    Loop loop = new Loop();
    While whileLoop = new While();
    IfElse ifelse=new IfElse();
    Initialization init = new Initialization();
    Print print = new Print();
    Expression exp=new Expression();
    
    int insideScope = -1;
    boolean openingLine = false;
    
    void handleCode(String codeLine, ArrayList<String> codeArray, ArrayList<String> dataArray) {
        
        checkScope(codeLine,codeArray);
        if(codeLine.contains("{")){
            openingLine = true;
        }
        if(codeLine.contains("if(")){
            ifLabelCount++;
            ifelse.setLabelCount(ifLabelCount);
            ifelse = handleIfStatement(codeLine);
            ifelse.writeToArray(ifLabelCount, codeArray, dataArray, insideScope);
            
        } else if((codeLine.contains("=") && codeLine.contains(";") && !codeLine.contains("for(")) && (codeLine.contains("+") || codeLine.contains("-")
                || codeLine.contains("*") || codeLine.contains("/") || codeLine.contains("%") || codeLine.contains("--")
                || codeLine.contains("++"))) {
            exp=handleExpression(codeLine);
            exp.writeToArray(codeArray);            
            
        } else if(codeLine.contains("for(")){
            labelCount++;
            loop.setLabelCount(labelCount);
            loop = handleForStatement(codeLine);
            loop.writeToArray(labelCount, codeArray, dataArray, insideScope);
            
        } else if (codeLine.contains("while(") && !codeLine.contains(";")){
            labelCount++;
            whileLoop.setLabelCount(labelCount);
            whileLoop=handleWhileStatement(codeLine);
            whileLoop.writeToArray(labelCount, codeArray, insideScope);
            
        } else if (codeLine.contains("do {")){
            labelCount++;
            whileLoop.setLabelCount(labelCount);
            Decision d=new Decision();
            Convergence c=new Convergence();
            
            d.setComparisonValue("20");
            d.setConditon("jb");
            d.setVariableName("a");
            c.setConvergenceType("inc");
            c.setVariableName(d.getVariableName());
            
            whileLoop.setC(c);
            whileLoop.setD(d);
            //whileLoop=handleWhileStatement(codeLine);
            whileLoop.writeToArray(labelCount, codeArray, insideScope);
            
        }else if(codeLine.contains("int ") && codeLine.contains("=")){
            init = handleInitialization(codeLine);
            init.writeToArray(dataArray);
        
        } else if(codeLine.contains("int ") && !codeLine.contains("(")) {
            //System.out.println(codeLine);
            String temp[];
            temp=codeLine.split("\\ ");
            init.setVariableName(temp[4]);
            init.setVariableValue("0");
            init.writeToArray(dataArray);
            
        }else if(codeLine.contains("cout")){
            stringCount++;    
            print.writeToArray(codeLine, stringCount, codeArray, dataArray, insideScope);
        }
    }
    
    private IfElse handleIfStatement(String line) {
        
        IfElse ifelse=new IfElse();
        Decision d=new Decision();
        
        String tokens[];
        String[] temp;
        tokens=line.split("\\(");
        //System.out.println(temp[1]);
        temp=tokens[1].split("\\)");
        
       if(temp[0].contains("<")){
           temp = temp[0].split("<");
           d.setConditon("jnb");
       } else if(temp[0].contains(">")){
           temp = temp[0].split(">");
           d.setConditon("jna");
       } else if(temp[0].contains(">=")){
           temp = temp[0].split(">=");
           d.setConditon("jnae");
       } else if(temp[0].contains("<=")){
           temp = temp[0].split("<=");
           d.setConditon("jnbe");
       } else if(temp[0].contains(">=")){
           temp = temp[0].split(">=");
           d.setConditon("jnae");
       } else if(temp[0].contains("==")){
           temp = temp[0].split("==");
           d.setConditon("jne");
       } else{
           temp = temp[1].split("!=");
           d.setConditon("je");
       }
       
        d.setVariableName(temp[0]);
        d.setComparisonValue(temp[1]);
       
        System.out.println(d.getVariableName());
        System.out.println(d.getConditon());
        System.out.println(d.getComparisonValue());
        
        ifelse.setD(d);
        return ifelse;
    }

    private Loop handleForStatement(String line) {
        Loop loop = new Loop();
        Decision d = new Decision();
        Convergence c = new Convergence();
        Initialization i = new Initialization();
              
        //for(int i=0;i<10;i++)
        
        String tokens[];
        tokens = line.split("\\)"); // tokens[0] = for(int i=0;i<10;i++
        tokens = tokens[0].split("\\("); // tokens[1] = int i=0;i<10;i++
        tokens = tokens[1].split("\\;"); // tokens[0]=int i=0   tokens[1]=i<10   tokens[2]=i++
        
        i = handleInitialization(tokens[0]);
        loop.setI(i);
        
        d = handleDecision(tokens[1]);
        loop.setD(d);
        
        c = handleConvergence(tokens[2]);
        loop.setC(c);
        
        return loop;
        
   }
    
    private While handleWhileStatement(String line) {
        While w=new While();
        Decision d=new Decision();
        Convergence c=new Convergence();
        
        String tokens[];
        tokens=line.split("\\(");
        tokens=tokens[1].split("\\)");
        d=handleDecision(tokens[0]);
        c.setConvergenceType("inc");
        c.setVariableName(d.getVariableName());

        w.setD(d);
        w.setC(c);
        
        return w;
    }

    private Initialization handleInitialization(String line) {
        
        Initialization i = new Initialization();
        //int i=0;
        String tokens[];
        String temp[];
        tokens = line.split("\\;"); //tokens[0]=int i=0
        tokens = tokens[0].split("\\="); // tokens[0]=int tokens[1]=i=0
        temp = tokens[0].split("\\ ");
        i.setVariableName(temp[temp.length-1]);
        temp=tokens[1].split("\\ ");
        i.setVariableValue(tokens[1]);

        return i;
    }

    private Expression handleExpression (String line) {
        
        Expression i = new Expression();
     
        String tokens[];
        String temp[];
        
        tokens = line.split("\\;"); 
        tokens = tokens[0].split("\\=");
        temp = tokens[0].split("\\ ");
        exp.setResult(temp[3]);
        
        if (tokens[1].contains("++"))
        {
            exp.setOperation(4);
            tokens=tokens[1].split("\\ ");
            tokens=tokens[1].split("");
            exp.setOperator1(tokens[0]);
            
            return exp;
        }
        else if (tokens[1].contains("--"))
        {
            exp.setOperation(5);
            tokens=tokens[1].split("\\ ");
            tokens=tokens[1].split("");
            exp.setOperator1(tokens[0]);
            
            return exp;
        }
              
        tokens=tokens[1].split("\\ ");
        if (tokens[2].equals("+")){
            exp.setOperation(1);
            exp.setOperator1(tokens[1]);
            exp.setOperator2(tokens[3]);
        }
        else if (tokens[2].equals("-")){
            exp.setOperation(2);
            exp.setOperator1(tokens[1]);
            exp.setOperator2(tokens[3]);
        }
        else if (tokens[2].equals("*")){
            exp.setOperation(3);
            exp.setOperator1(tokens[1]);
            exp.setOperator2(tokens[3]);
        }
        else if (tokens[2].equals("/")){            
            exp.setOperation(6);
            exp.setOperator1(tokens[1]);
            exp.setOperator2(tokens[3]);
        }
        else if (tokens[2].equals("%")){
            exp.setOperation(7);
            exp.setOperator1(tokens[1]);
            exp.setOperator2(tokens[3]);
        }
        return exp;
    }
    
    private Decision handleDecision(String line) {
       
        Decision d = new Decision();
       //i<10
       String tokens[];
       
       if(line.contains("<")){
           tokens = line.split("<");
           d.setConditon("jb");
       } else if(line.contains(">")){
           tokens = line.split(">");
           d.setConditon("ja");
       } else if(line.contains(">=")){
           tokens = line.split(">=");
           d.setConditon("jae");
       } else if(line.contains("<=")){
           tokens = line.split("<=");
           d.setConditon("jbe");
       } else if(line.contains(">=")){
           tokens = line.split(">=");
           d.setConditon("jae");
       } else if(line.contains("==")){
           tokens = line.split("==");
           d.setConditon("je");
       } else{
           tokens = line.split("!=");
           d.setConditon("jne");
       }
           
       //tokens[0]=i  tokens[1]=10
       
       d.setVariableName(tokens[0]);
       d.setComparisonValue(tokens[1]);
       
       return d;
           
    }

    private Convergence handleConvergence(String line) {
      Convergence c = new Convergence();
      //i++ 
      String tokens[];
      if(line.contains("+")){
          c.setConvergenceType("inc");
          tokens = line.split("");
      }else{
          c.setConvergenceType("dec");
          tokens = line.split("");
      }
      //tokens[0]=i
      //System.out.println(tokens[1]);
      c.setVariableName(tokens[1]);
      
      return c;
    }

    private void checkScope(String codeLine, ArrayList<String> codeArray) {
        
        if(codeLine.contains("{")){
            insideScope++;
            createSpaceInArray(codeArray);
        }else if(codeLine.contains("}")){
            insideScope--;
        }else if(!codeLine.contains("{") && insideScope>0){
            createSpaceInArray(codeArray);
        }
        
    }

    private void createSpaceInArray(ArrayList<String> codeArray) {
       
       ArrayList<String> newArray = new ArrayList<String>(0);
       int count = 0;
       
       
       for(String token : codeArray){
           if(token.contains("L"+labelCount+":")){
               newArray.add(codeArray.get(count));
               newArray.add("###");
           }else{
               newArray.add(codeArray.get(count));
           }
           count++;
       }
       codeArray.clear();
       Utility.copyArrayList(newArray, codeArray);
    }

    private Print handlePrintStatement(String codeLine) {
        
        Print p = new Print();
        if (codeLine.contains("endl") || codeLine.contains("\n")) {
            p.setNewLine(true);
        }
        else {
            p.setNewLine(false);
        }
        String tokens[] = codeLine.split("<<");
        String temp[];
        
        int len = tokens.length;
        
        for (int i=0; i<len; i++)
        {
            if (tokens[i].contains("\""));
            {
                //.add("str"+stringCount+" db "+tokens[i]+",\"$\"");
            }
        }
        
        if (tokens[1].contains("\"")) {
            p.setBefore(false);
        }
        else {
            p.setBefore(true);
        }
        
        /*if (p.isBefore()==false) {
            p.setPrintStatement(tokens[1]);
            p.setVar1(tokens[2]);
            
            //System.out.print(p.getPrintStatement());
            //System.out.println(p.getVar1());
        }
        else {
            p.setPrintStatement(tokens[2]);
            p.setVar1(tokens[1]);
            
            //System.out.print(p.getPrintStatement());
            //System.out.println(p.getVar1());
        } */      
        return p;
    }

    void cleanCode(ArrayList<String> codeArray) {
        int count = -1;
        for(String token: codeArray){
            count++;
            if(token.equals("###")){
                break;
            }
        }
        codeArray.remove(count);
        
        count = -1;
        int check=0;
        for(String token: codeArray){
            count++;
            if(token.contains("LL:")){
                check++;
                //break;
            }
            if(check==2){
                break;
            }
        }
        codeArray.remove(count);
    }
    
}
