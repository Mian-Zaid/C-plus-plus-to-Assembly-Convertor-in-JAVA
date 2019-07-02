/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loop;

import initialization.Initialization;
import decision.Decision;
import java.util.ArrayList;
import lab09.Utility;

/**
 *
 * @author AbdulAli
 */
public class Loop {

    int labelCount;

    Initialization i;
    Decision d;
    Convergence c;

    public Loop() {
    }

    public Initialization getI() {
        return i;
    }

    public void setI(Initialization i) {
        this.i = i;
    }

    public Decision getD() {
        return d;
    }

    public void setD(Decision d) {
        this.d = d;
    }

    public Convergence getC() {
        return c;
    }

    public void setC(Convergence c) {
        this.c = c;
    }

    public int getLabelCount() {
        return labelCount;
    }

    public void setLabelCount(int labelCount) {
        this.labelCount = labelCount;
    }

    public void writeToArray(int labelCount, ArrayList<String> codeArray, ArrayList<String> dataArray, int insideScope) {

         ArrayList<String> newArray = new ArrayList<String>(0);
         int count = -1;
         boolean found = false;
        if (insideScope>0) {

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
        } else {
            Utility.copyArrayList(codeArray, newArray);
        }

        String newLabelName = "L" + labelCount;
        i.writeToArray(dataArray);
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
