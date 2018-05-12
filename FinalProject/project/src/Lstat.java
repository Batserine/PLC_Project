package src;

import java.util.ArrayList;
/**
 *
 * @author Balu
 */

public class Lstat {

    ArrayList<Astat> statementLists;

    public Lstat(Astat s){
        statementLists  = new ArrayList<Astat>();
        statementLists.add(s);
    }

    public Lstat(Lstat l, Astat s){
        statementLists = l.statementLists;
        statementLists.add(s);
    }

    public void execute(){

        for (Astat astat : statementLists) {
            astat.execute();
        }
    }

    public void showStatements()
    {
        for (int i = 0; i < statementLists.size(); i++)
        {
           // System.out.println("****************");
           // System.out.println(statementLists.get(i));
           // System.out.println("****************");
            
        }
    }
}
