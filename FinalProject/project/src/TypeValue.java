/* 
 * A class that encapsulates Type and Value.
 * Type class is used to encapsulate type. (convenience)
 */

package src;


public class TypeValue
{
    private Object value;
    private Type type;

    public TypeValue(int value)
    {
        type = Type.integer();
        this.value = (Object) value;
    }
    
    public TypeValue(float value)
    {
        type = Type.floating_point();
        this.value = (Object) value;
    }
    
     public TypeValue(String value)
    {
       
        type = Type.string();
        this.value = (Object) value;
        
       
    }

    public TypeValue (boolean mutex)
    {
        type = Type.bool();
        this.value = (Object) mutex;
    }

    public Type getType()
    {
        return type;
    }

    public Object getValue()
    {
        return value;
    }

}
