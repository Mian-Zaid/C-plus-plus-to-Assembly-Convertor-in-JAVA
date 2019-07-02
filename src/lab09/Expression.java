/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab09;

import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class Expression {
    String result;
    String operator1;
    String operator2;
    int operation;

    public Expression() {
    }

    public String getResult() {
        return result;
    }

    public String getOperator1() {
        return operator1;
    }

    public String getOperator2() {
        return operator2;
    }

    public int getOperation() {
        return operation;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setOperator1(String operator1) {
        this.operator1 = operator1;
    }

    public void setOperator2(String operator2) {
        this.operator2 = operator2;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    
     public void writeToArray( ArrayList<String> codeArray) {
         
         if (operation==1) //a=b+c
         {
             codeArray.add("MOV al, "+operator1);
             codeArray.add("ADD al, "+operator2);
             codeArray.add("MOV "+result+", al");
         }
         else if (operation==2) //a=b-c
         {
             codeArray.add("MOV al, "+operator1);
             codeArray.add("SUB al, "+operator2);
             codeArray.add("MOV "+result+", al");
         }
         else if (operation==3) //a=b*c
         {
             codeArray.add("MOV al, "+operator1);
             codeArray.add("MOV bl, "+operator2);
             codeArray.add("MUL bl");
             codeArray.add("MOV "+result+", al");
         }
         else if (operation==4) //a=b++
         {
             codeArray.add("MOV al, "+operator1);
             codeArray.add("ADD al, 1");
             codeArray.add("MOV "+result+", al");
         }
         else if (operation==5) //a=b--
         {
    
             codeArray.add("MOV al, "+operator1);
             codeArray.add("SUB al, 1");
             codeArray.add("MOV "+result+", al");
         }
         else if (operation==6) //a=b/c
         {
             codeArray.add("MOV al, "+operator1);
             codeArray.add("MOV bl, "+operator2);
             codeArray.add("DIV al");
             codeArray.add("MOV "+result+", ah");
         }
         else if (operation==7) //a=b%c
         {
             codeArray.add("MOV al, "+operator1);
             codeArray.add("MOV bl, "+operator2);
             codeArray.add("DIV al");
             codeArray.add("MOV "+result+", dl");
        }
         codeArray.add("\n");
    }
}
