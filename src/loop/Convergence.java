/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loop;

import java.util.ArrayList;

/**
 *
 * @author AbdulAli
 */
public class Convergence {
    //i++
    
    String variableName;
    String convergenceType;

    public Convergence() {
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getConvergenceType() {
        return convergenceType;
    }

    public void setConvergenceType(String convergenceType) {
        this.convergenceType = convergenceType;
    }

    public void writeToArray(ArrayList<String> codeArray) {
        codeArray.add(convergenceType + " " + variableName);
    }
    
}
