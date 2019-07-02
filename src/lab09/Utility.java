/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab09;

import java.util.ArrayList;

/**
 *
 * @author AbdulAli
 */
public class Utility {
    
    public static void copyArrayList(ArrayList<String> source, ArrayList<String> destination){
        for(String token: source){
            destination.add(token);
        }
    }
    
}
