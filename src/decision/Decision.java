/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decision;

import java.util.ArrayList;

/**
 *
 * @author AbdulAli
 */
public class Decision {
    
    String variableName;
    String comparisonValue;
    String conditon; // 

    public Decision() {
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getComparisonValue() {
        return comparisonValue;
    }

    public void setComparisonValue(String comparisonValue) {
        this.comparisonValue = comparisonValue;
    }   

    public String getConditon() {
        return conditon;
    }

    public void setConditon(String conditon) {
        this.conditon = conditon;
    }

    public void writeToArray(ArrayList<String> codeArray, String labelName) {
        codeArray.add("MOV al,"+variableName);
        codeArray.add("CMP al,"+comparisonValue);
        codeArray.add(conditon + " " + labelName);
    }
       
}
