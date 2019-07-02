/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package initialization;

import java.util.ArrayList;

/**
 *
 * @author AbdulAli
 */
public class Initialization {
    
    String variableName;
    String variableValue;

    public Initialization() {
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getVariableValue() {
        return variableValue;
    }

    public void setVariableValue(String variableValue) {
        this.variableValue = variableValue;
    }

    public void writeToArray(ArrayList<String> dataArray) {
        dataArray.add(variableName+" db "+variableValue);
    }  
}
