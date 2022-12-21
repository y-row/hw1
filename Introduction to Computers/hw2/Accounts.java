public class Accounts 
{
    
    /* static variable */
    private static int totalAccounts=0 ;
    private static int totalmoney=0;


    /* operation : get static variable*/
    public static int getTotalAccount ()
    {
        return totalAccounts;
    }
    public static int getTotalmoney ()
    {
        return totalmoney;
    }


    /* Non-static variable */
    public int money;
    public int date ; 
    public char type;


    /* operation : help */
    public static void helpType()
    {
        System.out.println("本系統所識別的類別有娛樂、飲食、交通三種,以a b c為代號。");
        System.out.println("a:娛樂類 b:飲食類 c:交通類\n");
    }
    public static void helpAdd()
    {
        System.out.println("add [日期] [類別] [金額]    增加一筆帳目到list中。最多只能有100筆資料。\n");
    }
    public static void helpShow()
    {
        System.out.println("show all                    顯示所有帳目。");
        System.out.println("show [日期]                 依照加入順序顯示該日所有帳目。");
        System.out.println("show [類別]                 依照日期順序列出該類別的帳目。");
        System.out.println("show t                      顯示帳目數。");
        System.out.println("show m                      顯示總金額。\n");
    }
    public static void helpUpdate()
    {
        System.out.println("update                      進入update模式。");
        System.out.println("[編號] [日期] [類別] [金額] (update)變更編號的帳目內容。\n");
    }
    public static void helpU()//額外功能
    {
        System.out.println("u [金額]                    顯示輸入金額以上的所有帳目。");
    }
    public static void helpDelete()
    {
        System.out.println("delete                      進入刪除模式");
        System.out.println("[編號]                      (delete)刪除編號的帳目。");
    }
    public static void helpHelp()
    {
        System.out.println("\nhelp                        顯示所有指令。");
        System.out.println("help type                   顯示系統支援的帳目類別。");
        System.out.println("help add                    顯示add的指令。");
        System.out.println("help show                   顯示show的指令。");
        System.out.println("help update                 顯示update的指令。");
        System.out.println("help delete                 顯示delete的指令。");
    }
    public static void helpAll()
    {
        Accounts.helpType();
        Accounts.helpAdd();
        Accounts.helpShow();
        Accounts.helpUpdate();
        Accounts.helpU();
        Accounts.helpDelete();
        Accounts.helpHelp();
        System.out.println("\nexit                        結束記帳");
    }
    

    /* operation : add account list */
    public static void addAccounts(Accounts []list,int date ,char type, int money)
    {
        int index=1;
        for(;index<=totalAccounts;index++)
        {
            
            if(date<list[index].date)
            {
                break;
            }
        }
        for(int i=totalAccounts;i>=index;i--)
        {
            list[i+1].date=list[i].date;
            list[i+1].type=list[i].type;
            list[i+1].money=list[i].money;
        }
        list[index].date=date;
        list[index].type=type;
        list[index].money=money;
        switch(type)
        {
            case 'a':
                System.out.println("新增一筆" + date + " 號 娛樂類 " + money + " 元 的項目");
            break;
            case 'b':
                System.out.println("新增一筆" + date + " 號 飲食類 " + money + " 元 的項目");
                break;
            case 'c':
                System.out.println("新增一筆" + date + " 號 交通類 " + money + " 元 的項目");
                break;
        }
        totalmoney+=money;
        totalAccounts++;//新增成功
    }

    public static void wrongAdd(String a , int x)
    {
        String errortype[]= {"類別長度錯誤 " , "日期錯誤 " , "金額錯誤 " , "類別錯誤 "};
        System.out.println(errortype[x]);
        Accounts.helpAdd();
    }


    /*operation : show all、show單項完成， 還要做show日期跟show類別*/
    private static void showAttribute(Accounts listIndex,int index,boolean showNumber)
    {
        if(showNumber)
        {
            System.out.println(index + "     " + listIndex.date + "    " + listIndex.type + "   " + listIndex.money);
        }
        else
        {
            System.out.println(listIndex.date + "     " + listIndex.type + "   " + listIndex.money);
        }
        
    }
    
    public static void showAll(Accounts [] list,boolean showNumber)
    {
        if(showNumber)
        {
            System.out.println("編號 日期 類別 金額");
            for(int i=1;i<=totalAccounts;i++)
            {
               showAttribute(list[i],i,showNumber);
            }
        }
        else
        {
            System.out.println("日期 類別 金額");
            for(int i=1;i<=totalAccounts;i++)
            {
               showAttribute(list[i],i,showNumber);
            }
        }    
    }

    public static void showType(Accounts [] list,char type)
    {
        int counter=0;//紀錄有幾筆資料傳出
        
        for(int i=1;i<=totalAccounts;i++)
        {
            if(list[i].type==type)
            {
                counter++;
                if(counter==1)
                {
                    System.out.println("日期 類別 金額");
                }
                showAttribute(list[i],i,false);//不用show num
            }
        }
        if(counter==0)
        {
            System.out.println("查無資料");
        }
        else
        {
            System.out.println("共" + counter + "筆資料");
        }
    }

    public static void showDate(Accounts [] list,int date)
    {
        int counter=0;//紀錄有幾筆資料傳出
        for(int i=1;i<=totalAccounts;i++)
        {
            if(list[i].date==date)
            {
                counter++;
                if(counter==1)
                {
                    System.out.println("日期 類別 金額");
                }
                showAttribute(list[i],i,false);//不用show num
            }
        }
        if(counter==0)
        {
            System.out.println("查無資料");
        }
        else
        {
            System.out.println("共" + counter + "筆資料");
        }
    }
    
    public static void showUpper(Accounts [] list,int money)
    {
        int counter=0;//紀錄有幾筆資料傳出
        for(int i=1;i<=totalAccounts;i++)
        {
            if(list[i].money>=money)
            {
                counter++;
                if(counter==1)
                {
                    System.out.println("日期 類別 金額");
                }
                showAttribute(list[i],i,false);//不用show num
            }
        }
        if(counter==0)
        {
            System.out.println("查無資料");
        }
        else
        {
            System.out.println("共" + counter + "筆資料");
        }
    }

    /* operation : update */
    public static void  updateAccounts(Accounts []list,int date ,char type, int money,int index)
    {
        totalmoney-=list[index].money;//扣掉原本的金額
        int finialIndex=1;
        for(;finialIndex<totalAccounts;finialIndex++)//找出最後的位置
        {
            if(date<list[finialIndex].date)
            {
                break;
            }
        }        
        /* 如果新位置在舊位置前面:把介於之間的index右移,反之則將介於之間的index左移;一樣的話就不用改*/
        if(finialIndex<index)
        {
            for(int i=index;i>finialIndex;i--)
            {
                list[i].date=list[i-1].date;
                list[i].type=list[i-1].type;
                list[i].money=list[i-1].money;
            }
        }
        else if(finialIndex>index)
        {
            if(finialIndex!=totalAccounts)
            {
                finialIndex--;//若新位置不在最後,則真實的位置會在fin-1的地方，因為fin多算了一個自己
            }
            for(int i=index;i<finialIndex;i++)
            {
                list[i].date=list[i+1].date;
                list[i].type=list[i+1].type;
                list[i].money=list[i+1].money;
            }
        }
        list[finialIndex].date=date;
        list[finialIndex].type=type;
        list[finialIndex].money=money;
        
        /* 新增完成*/
        totalmoney+=money;
        switch(type)
        {
            case 'a':
                System.out.println("變更編號 " + index + "的帳目為 " + date + " 號 娛樂類 " + money + " 元 的項目");
            break;
            case 'b':
                System.out.println("變更編號 " + index + "的帳目為 " + date + " 號 飲食類 " + money + " 元 的項目");
                break;
            case 'c':
                System.out.println("變更編號 " + index + "的帳目為 " + date + " 號 交通類 " + money + " 元 的項目");
                break;
        }
        System.out.println("下為變更後的帳本");
        showAll(list, true);
    }

    /* operation : delete */
    public static void delete(Accounts[]list,int index)
    {
        for(int i=index;i<totalAccounts;i++)
        {
            list[i].date=list[i+1].date;
            list[i].type=list[i+1].type;
            list[i].money=list[i+1].money;
        }
        list[totalAccounts]=list[0];
        totalAccounts--;
        System.out.println("delete complete.");
        showAll(list, true);
    }    
}
