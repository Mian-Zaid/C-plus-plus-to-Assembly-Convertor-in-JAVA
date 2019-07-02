/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab09;
import decision.Decision;
import java.util.ArrayList;
import loop.Convergence;
/**
 *
 * @author DELL
 */
public class While {
    
    int labelCount;
    Decision d;
    Convergence c;

    public While() {
    }

    public Decision getD() {
        return d;
    }

    public int getLabelCount() {
        return labelCount;
    }

    public Convergence getC() {
        return c;
    }

    public void setD(Decision d) {
        this.d = d;
    }

    public void setLabelCount(int labelCount) {
        this.labelCount = labelCount;
    }

    public void setC(Convergence c) {
        this.c = c;
    }
    
    public void writeToArray(int labelCount, ArrayList<String> codeArray, int insideScope) {

        ArrayList<String> newArray = new ArrayList<String>(0);
        int count = -1;
        boolean found = false;
        if (insideScope>0) 
        {
            for(String tokens : codeArray){
                if(tokens.contains("###")){
                    count++;
                    found = true;
                    break;
                }else{
                    count++;
                    newArray.add(tokens);
                }
            }
        }else{
            Utility.copyArrayList(codeArray, newArray);
        }

        String newLabelName = "L" + labelCount;
        
        newArray.add("\n" + newLabelName + ":");
        c.writeToArray(newArray);
        d.writeToArray(newArray, newLabelName);
        newArray.add("\n");
        
        if (insideScope>0) {
            for(int i=count; i<codeArray.size()-1; i++ ){
                newArray.add(codeArray.get(i+1));
            }
        }
        
        codeArray.clear();
        Utility.copyArrayList(newArray, codeArray);

    }
}
