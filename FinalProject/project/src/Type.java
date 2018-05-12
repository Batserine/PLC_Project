/* 
 * A utility class to compare types. Created for convenience.
 * Type object of integer type or boolean type can be returned.
 * This class is used in CUP.
 */
package src;

import java.util.HashMap;

public class Type {

    public static final int INTEGER = 1;
    public static final int BOOL = 2;
    public static final int ERRORTYPE = 3;
    public static final int FLOATING_POINT = 4;
    public static final int STRING = 5;

    static HashMap<String, Type> types = new HashMap<String, Type>();
    int tag;
    String code;

    public Type() {
        initTypes();
    }

    public Type(int t, String c) {
        tag = t;
        code = c;
    }

    public String getCode() {
        return code;
    }

    public static void initTypes() {
        types.put(INTEGER + "", new Type(INTEGER, "int"));
        types.put(BOOL + "", new Type(BOOL, "bool"));
        types.put(FLOATING_POINT + "", new Type(FLOATING_POINT, "float"));
        types.put(STRING + "", new Type(STRING, "String"));
        types.put(ERRORTYPE + "", new Type(ERRORTYPE, "error"));
    }

    public static Type string() {
        return (Type) types.get("" + STRING);
    }

    public boolean isString() {
        if (tag == STRING) {
            return true;
        } else {
            return false;
        }
    }

    public static Type integer() {
        return (Type) types.get("" + INTEGER);
    }

    public boolean isInteger() {
        if (tag == INTEGER) {
            return true;
        } else {
            return false;
        }
    }

    public static Type floating_point() {
        return (Type) types.get("" + FLOATING_POINT);
    }

    public boolean isFloating_point() {
        if (tag == FLOATING_POINT) {
            return true;
        } else {
            return false;
        }
    }

    public static Type bool() {
        return (Type) types.get("" + BOOL);
    }

    public boolean isBool() {
        if (tag == BOOL) {
            return true;
        } else {
            return false;
        }
    }

    public static Type errortype() {
        return (Type) types.get("" + ERRORTYPE);
    }

    public boolean isErrortype() {
        if (tag == ERRORTYPE) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean equals(Type t1, Type t2){
        boolean result = false;
        
        if (t1.getCode().equals(t2.getCode())){
            result = true;
        }
        
        return result;     
        
        
    }
}
