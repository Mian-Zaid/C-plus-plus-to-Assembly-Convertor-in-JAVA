package lab09;

import initialization.Initialization;
import decision.Decision;
import java.util.ArrayList;

/**
 *
 * @author AbdulAli
 */
public class IfElse {

    int labelCount;

    Decision d;

    public IfElse() {
    }

    public Decision getD() {
        return d;
    }

    public void setD(Decision d) {
        this.d = d;
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

        String newLabelName = "LL" ;
        d.writeToArray(newArray, newLabelName);
           
        newArray.add("\n");
        newArray.add("\n" + newLabelName + ":");
        
        if (insideScope>0) {
            for(int i=count; i<codeArray.size()-1; i++ ){
                newArray.add(codeArray.get(i+1));
            }
        }
        codeArray.clear();
        Utility.copyArrayList(newArray, codeArray);
    }
}