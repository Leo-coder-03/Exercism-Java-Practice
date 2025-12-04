class Badge {
    public String print(Integer id, String name, String department) {
       if(id!=null)
       {
           if(department!=null)
               return "["+id+"]"+" - "+name+" - "+department.toUpperCase();
           else
               return "["+id+"]"+" - "+name+" - OWNER";
       }
        else{
            if(department!=null)
               return name+" - "+department.toUpperCase();
           else
               return name+" - OWNER";
        }
    }
}
