package src;
public class Aexp
{

    private parser parser = new parser();
    private boolean[] tag = new boolean[6];
    private Integer NUMBER_INTEGER;
    private Float NUMBER_FLOAT;
    private String TEXT_LITERAL;
    private String ID;
    private Args Operands;
    private int Operator;
    private Boolean BOL;
    //type is determined from the constructor
    private Type type = new Type();
    //A variable to check if identifier hold boolean or not. If false, its integer.
    private boolean isBool;
 
    Aexp(Integer x)
    {
        int i;
        for (i = 0; i <= 5; i++)
        {
            if (i == 0)
            {
                tag[i] = true;
            } else
            {
                tag[i] = false;
            }
        }
        
        NUMBER_INTEGER = x;
        type = Type.integer();
        isBool = false;
        //working
        //System.out.println("Test Integet: "+x+" "+type.isInteger());
    }
    
     Aexp(Float x)
    {
        int i;
        for (i = 0; i <= 5; i++)
        {
            if (i == 1)
            {
                tag[i] = true;
            } else
            {
                tag[i] = false;
            }
        }
        
        NUMBER_FLOAT = x;
        type = Type.floating_point();
        isBool = false;        
    }

     
    Aexp(String a, String x)
    {
          int i;
        for (i = 0; i <= 5; i++)
        {
            if (i == 2)
            {
                tag[i] = true;
            } else
            {
                tag[i] = false;
            }
        }
        
        TEXT_LITERAL = x;
        type = Type.string();
        isBool = false;        
    }
    
    Aexp(String x)
    {
        
        int i;

        for (i = 0; i <= 5; i++)
        {
            if (i == 3)
            {
                tag[i] = true;
            } else
            {
                tag[i] = false;
            }
        }
        
       
        if (Env.envTable.get(x).getType().isInteger())
        {
            type = Type.integer();
            isBool = false;
           
        } else if (Env.envTable.get(x).getType().isFloating_point())
        {
            type = Type.floating_point();
            isBool = false;
        } else if (Env.envTable.get(x).getType().isString())
        {
            type = Type.string();
            isBool = false;
        } 
        else if (Env.envTable.get(x).getType().isBool())
        {
           
            type = Type.bool();
            isBool = true;
        } else
        {
          type = Type.errortype();
        }

        ID = x;
    }

    Aexp(Args x, int op)
    {
        int i;
        for (i = 0; i <= 5; i++)
        {
            if (i == 4)
            {
                tag[i] = true;
            } else
            {
                tag[i] = false;
            }
        }
       
        //set type here
        if (op == sym.PLUS || op == sym.MINUS || op == sym.TIMES || op == sym.DIVIDE || op == sym.MOD || op == sym.GT || op == sym.LT)
        {
            
             if (x.getse().getType().isFloating_point()){
                type = Type.floating_point();
            } else if (x.getse().getType().isInteger() ){
                type = Type.integer();
            }else if (x.getse().getType().isString()){
                type = Type.string();
            }             
              
            isBool = false;
        } else if (op == sym.OR || op == sym.AND)
        {
            type = Type.bool();
            isBool = true;
        }
        else
        {
            type = Type.errortype();
        }

        Operands = x;
        Operator = op;
    }

    Aexp(Boolean mutex)
    {
        int i;
        for (i = 0; i <= 5; i++)
        {
            if (i == 5)
            {
                tag[i] = true;
            } else
            {
                tag[i] = false;
            }
        }

        type = Type.bool();
        this.BOL = mutex;
       
        isBool = true;
    }
    
   
    public String getexp()
    {

        String s = "";
        if (tag[0])
        {
            s = "" + NUMBER_INTEGER;
        } else if (tag[1])                
        {
            s = "" + NUMBER_FLOAT;
        } else if (tag[2])                
        {
            s = "" + TEXT_LITERAL;
        } else if (tag[3])
        {
            s = ID;
        } else if (tag[4])
        {
            if (Operator == sym.PLUS)
            {
                s = "PLUS(" + Operands.getfi().getexp() + "," + Operands.getse().getexp() + ")";
            } else if (Operator == sym.MINUS)
            {
                s = "MINUS(" + Operands.getfi().getexp() + "," + Operands.getse().getexp() + ")";
            }
            if (Operator == sym.TIMES)
            {
                s = "TIMES(" + Operands.getfi().getexp() + "," + Operands.getse().getexp() + ")";
            }
            if (Operator == sym.DIVIDE)
            {
                s = "DIVIDE(" + Operands.getfi().getexp() + "," + Operands.getse().getexp() + ")";
            }
            if (Operator == sym.MOD)
            {
                s = "MOD(" + Operands.getfi().getexp() + "," + Operands.getse().getexp() + ")";
            }
        } else if (tag[5])
        {
            s = "" + BOL;
        }
        return s;
    }

    
    //Evaluation of expressions
    public TypeValue getTypeValue()
    {
        TypeValue typeVal = null;
        if (tag[0]) //number integer
        {
              typeVal = new TypeValue(NUMBER_INTEGER);
        }else if (tag[1]) //number float
        {
              typeVal = new TypeValue(NUMBER_FLOAT);
        }
        else if (tag[2]) //String
        {
              typeVal = new TypeValue(TEXT_LITERAL);
        }
        else if (tag[3]) //identifier
        {
            if (isBool)
            {
                typeVal = Env.envTable.get(ID);
            }
            else
            {
                 typeVal = Env.envTable.get(ID);
            }
        }
        else if (tag[4])//operation on two expressions fi and si
        {
            /*Make sure that operations work only on the given types.*/

            /*
             * Typecasts will throw a exception from java itself, when there
             * is a type error in the language
             */
                if (Operator == sym.PLUS)
                {
                  
                    if ( Operands.getfi().getType().isInteger() )
                    {
                        int val = (Integer) Operands.getfi().getTypeValue().getValue();
                        val = val + (Integer)Operands.getse().getTypeValue().getValue();
                        typeVal = new TypeValue(val);
                    } else if ( Operands.getfi().getType().isFloating_point())
                    {
                        float val = (Float) Operands.getfi().getTypeValue().getValue();
                        val = val + (Float)Operands.getse().getTypeValue().getValue();
                        typeVal = new TypeValue(val);
                    }
                }
                else if (Operator == sym.MINUS)
                {
                    int val = (Integer) Operands.getfi().getTypeValue().getValue();
                    val = val - (Integer)Operands.getse().getTypeValue().getValue();
                    typeVal = new TypeValue(val);
                }
                else if (Operator == sym.TIMES)
                {
                    int val = (Integer) Operands.getfi().getTypeValue().getValue();
                    val = val * (Integer)Operands.getse().getTypeValue().getValue();
                    typeVal = new TypeValue(val);
                }
                else if (Operator == sym.DIVIDE)
                {
                   
                    
                    if ( Operands.getfi().getType().isInteger() )
                    {
                        int val = (Integer) Operands.getfi().getTypeValue().getValue();
                        val = val / (Integer)Operands.getse().getTypeValue().getValue();
                        typeVal = new TypeValue(val);
                        
                    } else if ( Operands.getfi().getType().isFloating_point())
                    {
                        float val = (Float) Operands.getfi().getTypeValue().getValue();
                        val = val / (Float)Operands.getse().getTypeValue().getValue();
                        typeVal = new TypeValue(val);
                    }
                }
                else if (Operator == sym.MOD)
                {                    
                    if ( Operands.getfi().getType().isInteger() )
                    {
                        int val = (Integer) Operands.getfi().getTypeValue().getValue();
                        val = val % (Integer)Operands.getse().getTypeValue().getValue();
                        typeVal = new TypeValue(val);
                        
                    } else if ( Operands.getfi().getType().isFloating_point())
                    {
                        float val = (Float) Operands.getfi().getTypeValue().getValue();
                        val = val % (Float)Operands.getse().getTypeValue().getValue();
                        typeVal = new TypeValue(val);
                    }
                }
                else if (Operator == sym.GT)
                {
                   
                     if ( Operands.getfi().getType().isInteger() )
                    {
                         boolean val = (Integer) Operands.getfi().getTypeValue().getValue() > (Integer)Operands.getse().getTypeValue().getValue();
                         typeVal = new TypeValue(val);
                        
                    } else if ( Operands.getfi().getType().isFloating_point())
                    {
                        boolean val = (Float) Operands.getfi().getTypeValue().getValue() > (Float)Operands.getse().getTypeValue().getValue();
                        typeVal = new TypeValue(val);
                    }
                }
                else if (Operator == sym.LT)
                {
                    boolean val = (Integer) Operands.getfi().getTypeValue().getValue() < (Integer)Operands.getse().getTypeValue().getValue();
                    typeVal = new TypeValue(val);
                }
                else if (Operator == sym.GTE)
                {
                    boolean val = (Integer) Operands.getfi().getTypeValue().getValue() >= (Integer)Operands.getse().getTypeValue().getValue();
                    typeVal = new TypeValue(val);
                }
                else if (Operator == sym.LTE)
                {
                    boolean val = (Integer) Operands.getfi().getTypeValue().getValue() <= (Integer)Operands.getse().getTypeValue().getValue();
                    typeVal = new TypeValue(val);
                }
                else if (Operator == sym.OR)
                {
                    boolean val = (Boolean) Operands.getfi().getTypeValue().getValue() || (Boolean) Operands.getse().getTypeValue().getValue();
                    typeVal = new TypeValue(val);
                }
                else if (Operator == sym.AND)
                {
                    boolean val = (Boolean) Operands.getfi().getTypeValue().getValue() && (Boolean) Operands.getse().getTypeValue().getValue();
                    typeVal = new TypeValue(val);
                }
        }
        if (tag[5]) //for boolean expression (from constructor)
        {           
            typeVal = new TypeValue(BOL);
        }
        return typeVal;
    }
    
    public Type getType()
    {
        return type;
    }

}
