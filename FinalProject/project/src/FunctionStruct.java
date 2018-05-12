/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;
/**
 *
 * @author Balu
 */
public class FunctionStruct {
    
    String name;
    String return_type;
    String return_id;
    String return_id_type;
    Object return_value;
    Lstat statements;

    public FunctionStruct(String name, String return_type, String return_id, String return_id_type, Object return_value, Lstat statements) {
        this.name = name;
        this.return_type = return_type;
        this.return_id = return_id;
        this.return_id_type = return_id_type;
        this.return_value = return_value;
        this.statements = statements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReturn_id() {
        return return_id;
    }

    public void setReturn_id(String return_id) {
        this.return_id = return_id;
    }

    public String getReturn_id_type() {
        return return_id_type;
    }

    public void setReturn_id_type(String return_id_type) {
        this.return_id_type = return_id_type;
    }

    public String getReturn_type() {
        return return_type;
    }

    public void setReturn_type(String return_type) {
        this.return_type = return_type;
    }

    public Object getReturn_value() {
        return return_value;
    }

    public void setReturn_value(Object return_value) {
        this.return_value = return_value;
    }

    public Lstat getStatements() {
        return statements;
    }

    public void setStatements(Lstat statements) {
        this.statements = statements;
    }
    
    
    
}
