package src;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */ 

public class Astat
{
    parser parser = new parser();
    String statementType;
    String ID;
    Type type;
    Aexp e;
    Astat body;
    Astat elsebody;
    Lstat statementList;
    int calculatorResult;
    float calculatorFloatResult;
    String ID2;

    //for logical
    Aexp left,right;

    public Astat(Type t, String ID, Aexp e)
    {
        this.ID = ID;
        this.e = e;
        this.type = t;
    }
    
    public Astat(Type t, String ID)
    {
        this.ID = ID;
        this.e = null;
        this.type = t;
    }

    public Astat (Integer i)
    {
        this.calculatorResult = i;
    }
     public Astat (Float i)
    {
        this.calculatorFloatResult = i;
    }
     
    
    public Astat(String ID, Aexp e)
    {
        this.ID = ID;
        this.e = e;
    }
    
    public Astat(String ID1,String ID2 )
    {
        this.ID = ID1;
        this.ID2 = ID2;
    }

    public Astat(Aexp e, Astat body)
    {
        this.e = e;
        this.body = body;
    }
    
    public Astat(Aexp e, Astat body1, Astat body2) {
        this.e = e;

       
            this.body = body1;

            this.elsebody = body2;

    }

    public Astat(Aexp e)
    {
        this.e = e;
    }

    private Astat(Lstat l)
    {
        statementList = l;
    }

    
    public static Astat logic (Aexp logical)
    {
        Astat logic = new Astat(logical);
        logic.statementType="logic";

        return logic;
    }
    
    public static Astat assignment(String ID, Aexp e)
    {
        Astat assignment = new Astat(ID, e);

        assignment.statementType = "assignment";

        return assignment;

    }

    public static Astat assignment(Type t, String ID, Aexp e)
    {
        Astat assignment = new Astat(t, ID, e);
       
        assignment.statementType = "instantiation";

        return assignment;

    }
    
    public static Astat assignment(Type t, String ID)
    {
        Astat assignment = new Astat(t, ID);
     
        assignment.statementType = "declaration";

        return assignment;

    }

    public static Astat whileloop(Aexp e, Astat whileBody)
    {
        Astat loop = new Astat(e, whileBody);
        loop.statementType = "whileloop";
        return loop;

    }

    public static Astat ifthen(Aexp e, Astat ifbody)
    {
        
        Astat ifthen = new Astat(e, ifbody);
        ifthen.statementType = "ifthen";
        return ifthen;
    }
    
    public static Astat ifthenelse(Aexp e, Astat ifbody, Astat elsebody) {
       
        Astat ifthenelse = new Astat(e, ifbody,elsebody);
        ifthenelse.statementType = "ifthenelse";
        return ifthenelse;
        
    }

    public static Astat print(Aexp e)
    {

        Astat p = new Astat(e);
        p.statementType = "print";
        return p;

    }

    public static Astat list(Lstat l)
    {

        Astat p = new Astat(l);
        p.statementType = "list";
        return p;

    }
    
    public static Astat functioncall(String id)
    {
        Lstat l;
        Astat p = null;
        l = FunctionList.funTable.get(id);
        if(l != null){
           p = new Astat(l); 
           p.statementType = "function";
        }
        return p;

    }
    
    public static Astat functionReturn(String id1, String id2)
    {
       //Astat fc = functioncall(id2);         
       Astat p = new Astat(id1,id2);
       p.statementType = "function-return";
       
       return p;


    }

    public String getstat()
    {
        System.out.println(statementType);
        
        if (statementType.equals("assignment"))
        {
            return ID + "=" + e.getexp();
        } else if (statementType.equals("intantiation"))
        {
            return type.getCode() + " " + ID + "=" + e.getexp();
        } else if (statementType.equals("ifthen"))
        {
            return "if " + e.getexp() + " " + body.getstat();
        } else if (statementType.equals("ifthenelse"))
        {
            return "else " + e.getexp() + " " + body.getstat();
        }else if (statementType.equals("print"))
        {
            return "print " + e.getexp();
        } else if (statementType.equals("whileloop"))
        {
            return "while " + e.getexp() + " do " + body.getstat();
        } else if (statementType.equals("list"))
        {
            return "list";
        }else if (statementType.equals("function-return"))
        {
            return "function-return";
        } else
        {
            return "unknown";
        }
        
    }

    public void execute()
    {
        /* Retreive identifier from table, check its type, then assign */
        if (statementType.equals("assignment"))
        {
             if(Env.envTable.get(ID).getType().isInteger())
             {
                Env.envTable.put(ID, new TypeValue((Integer) e.getTypeValue().getValue()));
             }
              if(Env.envTable.get(ID).getType().isString())
             {
                Env.envTable.put(ID, new TypeValue((String) e.getTypeValue().getValue()));
             }
             if(Env.envTable.get(ID).getType().isFloating_point())
             {
                Env.envTable.put(ID, new TypeValue((Float) e.getTypeValue().getValue()));
             }
             if (Env.envTable.get(ID).getType().isBool())
             {
                 Env.envTable.put(ID, new TypeValue((Boolean) e.getTypeValue().getValue()));
             }
        }
        
        else if (statementType.equals("instantiation"))
        {
           
                             
            if (type.isInteger())
            {
                Env.envTable.put(ID, new TypeValue((Integer) e.getTypeValue().getValue()));
            }
           
            if (type.isFloating_point())
            {
                Env.envTable.put(ID, new TypeValue((Float) e.getTypeValue().getValue()));
            }
              if (type.isString())
            {
                   
                Env.envTable.put(ID, new TypeValue((String) e.getTypeValue().getValue()));
                
               
            }
            if (type.isBool())
            {
                Env.envTable.put(ID, new TypeValue((Boolean) e.getTypeValue().getValue()));
            }
           
        }
        else if (statementType.equals("ifthen"))
        {
            if (e.getTypeValue().getType().isBool()) //expr must be boolean
            {
                boolean b = (Boolean)e.getTypeValue().getValue();
                if ((Boolean) e.getTypeValue().getValue())
                {
                    body.execute();
                }
            }
            else
            {
                System.out.println("Error,if expression must be boolean.");
            }
        }
        else if (statementType.equals("ifthenelse"))
        {
            if (e.getTypeValue().getType().isBool()) //expr must be boolean
            {
                if ((Boolean) e.getTypeValue().getValue())
                {
                    body.execute();
                }else{
                    elsebody.execute();
                }
            }
            else
            {
               System.out.println("Error,if expression must be boolean.");
            }
        }

        else if (statementType.equals("whileloop"))
        {
            if (e.getTypeValue().getType().isBool())
            {
                for (;;)
                {
                    if ((Boolean) e.getTypeValue().getValue())
                    {
                        body.execute();
                    }
                    else
                    {
                        break;
                    }
                }
            }
            else
            {
                //type error
                System.out.println("while expression must be boolean.");
            }
        }
        
        else if (statementType.equals("print"))
        {
            //need to check type for casting from TypeValue
            if (e.getType().isInteger())
            {
                System.out.println((Integer) e.getTypeValue().getValue());
            }
             if (e.getType().isFloating_point())
            {
                System.out.println((Float) e.getTypeValue().getValue());
            }
             if (e.getType().isString())
            {
                System.out.println((String) e.getTypeValue().getValue());
            }
            if (e.getType().isBool())
            {
                System.out.println((Boolean) e.getTypeValue().getValue());
            }         

        }

        else if (statementType.equals("list"))
        {
            for (Astat s : statementList.statementLists)
            {
                s.execute();
            }
        }

        else if (statementType.equals("calculator"))
        {         
            if (type.isInteger())
            {
               System.out.println(calculatorResult);
            }
            if (type.isFloating_point())
            {
               System.out.println(calculatorFloatResult);
            }
            
        } 
        else if (statementType.equals("function"))
        {
            for (Astat s : statementList.statementLists)
            {
                s.execute();
            }
        }else if (statementType.equals("function-return"))
        {

                Lstat l;
                Astat p = null;
                l = FunctionList.funTable.get(ID2);
                if(l != null){
                   for (Astat s : l.statementLists)
                    {
                        s.execute();
                    }
                   
                   Env.envTable.put(ID, Env.envTable.get(ID2));

            }
            System.out.println("ID = "+Env.envTable.get(ID).getValue().toString()+"ID2 = "+Env.envTable.get(ID2).getValue().toString());
        }
    }
}
