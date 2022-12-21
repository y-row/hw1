/* 要寫的東西:歡迎訊息、show日期類別、add錯誤(O)、update 、help */


public class hw2
{
    public static void main(String args[]) 
    {
        Accounts []list =new Accounts[101];
        for(int i=1;i<=100;i++)
        {
            list[i]=new Accounts();
        }
        String command="";
        String [] splitCommand;
        int date=0;
        int money=0;
        int index=0;
        boolean exitCode=false;
        
        /* 歡迎訊息 */
        System.out.println("歡迎使用 記帳系統");
        Accounts.helpAll();
        while(!exitCode)
        {
            System.out.println("\n輸入指令:");
            command=ConsoleIn.readLine();
            splitCommand=command.split(" ");
            switch(splitCommand[0])
            {
                /* 加法 */
                case "add":
                {
                    if(splitCommand.length!=4)//判斷格式是不是錯了
                    {
                        Accounts.helpAdd();
                        break;
                    }

                    if(Accounts.getTotalAccount()==100)//判斷有沒有超過上限
                    {
                        System.out.println("帳目已達100筆 若要新增請先刪除其他筆帳目");
                        break;
                    }

                    date=SplitDemo.stringToInt(splitCommand[1]);//日期轉int
                    if(date<=0||date>31)
                    {
                        Accounts.wrongAdd(splitCommand[1], 1);//日期錯誤編號為1
                        break;
                    }

                    money=SplitDemo.stringToInt(splitCommand[3]);
                    if(money<=0)
                    {
                        Accounts.wrongAdd(splitCommand[3], 2);//金額錯誤編號為2
                        break;
                    }

                    char type='0';
                    if(splitCommand[2].length()!=1)//長度錯誤
                    {
                        Accounts.wrongAdd(splitCommand[2],0); 
                        break;
                    }
                    
                    type=splitCommand[2].charAt(0);
                    if(type<'a'||type>'c')
                    {
                        Accounts.wrongAdd(splitCommand[2],3); 
                        break;
                    }

                    Accounts.addAccounts(list, date, type, money);
                    break; //add finish
                }

                 /* show */
                case "show":
                {
                    if(splitCommand[1].equals("all")&&splitCommand.length==2)
                    {       
                        if(Accounts.getTotalAccount()==0)
                        {
                            System.out.println("無資料。");
                            
                        }
                        else
                        {
                            Accounts.showAll(list,false);
                        }
                    }
                    else if(splitCommand[1].charAt(0)>='0'&&splitCommand[1].charAt(0)<='9')
                    {
                        date=SplitDemo.stringToInt(splitCommand[1]);
                        if(date>31||date==0)
                        {
                            System.out.println("無效的日期。");
                        }
                        else
                        {
                            Accounts.showDate(list, date);
                        }
                        break;
                    }
                    else if(splitCommand[1].charAt(0)>='a'&&splitCommand[1].length()==1)
                    {
                        switch(splitCommand[1].charAt(0))
                        {
                            case 'a':
                                Accounts.showType(list, 'a');
                                break;
                            case 'b':
                                Accounts.showType(list, 'b');
                                break;
                            case 'c':
                                Accounts.showType(list, 'c');
                                break;
                            case 't':
                                System.out.println("目前共有" + Accounts.getTotalAccount() + "筆資料。");
                                break;
                            case 'm':
                                System.out.println("本月支出" + Accounts.getTotalmoney() + "元");
                                break;
                            default:
                                System.out.println("invalid type");
                                Accounts.helpType();
                                break;
                        }
                    }
                    else
                    {
                        System.out.println("invalid syntax.");
                        Accounts.helpShow();
                    }        
                    break;//case show finish
                }
                
                case "help":
                {
                    if(splitCommand.length==1)
                    {
                        Accounts.helpAll();
                    }
                    else if(splitCommand.length==2)
                    {
                        switch(splitCommand[1])
                        {
                            case "type":
                                Accounts.helpType();
                                break;
                            case "add":
                                Accounts.helpAdd();
                                break;
                            case "show":
                                Accounts.helpShow();
                                break;
                            case "update":
                                Accounts.helpUpdate();
                                break;
                            case "U":
                                Accounts.helpU();
                                break;
                            case "delete":
                                Accounts.helpDelete();
                                break;
                            default:
                                Accounts.helpAll();
                                break;
                        }
                    }
                    
                    break;
                }
                    
                case "update":
                {
                    Accounts.showAll(list, true);
                    System.out.println("輸入想要變更的帳目");
                    command=ConsoleIn.readLine();
                    splitCommand=command.split(" ");
                    
                    if(splitCommand.length!=4)//格式錯誤
                    {
                        System.out.println("格式錯誤,變更失敗");
                        break;
                    }

                    /* 轉換各資料*/
                    index = SplitDemo.stringToInt(splitCommand[0]);
                    date = SplitDemo.stringToInt(splitCommand[1]);
                    money = SplitDemo.stringToInt(splitCommand[3]);
                    char type=splitCommand[2].charAt(0);

                    /* 檢查錯誤 */
                    if(index<1||index>Accounts.getTotalAccount())//編號錯誤
                    {
                        System.out.println("無效的編號,變更失敗");
                    }
                    else if(date<1||date>31)//日期錯誤
                    {
                        System.out.println("無效的日期,變更失敗");
                    }
                    else if(money<=0)//金額錯誤
                    {
                        System.out.println("無效的金額,變更失敗");
                    }
                    else if(type>'c'||type<'a'||splitCommand[2].length()!=1)//類別錯誤
                    {
                        System.out.println("無效的類別,變更失敗");
                        Accounts.helpType();
                    }
                    else//開始變更
                    {
                        Accounts.updateAccounts(list, date, type, money, index);
                    }
                    break;
                }
                
                /* delete功能 */
                case "delete":
                {
                    Accounts.showAll(list, true);
                    System.out.println("輸入想要刪除的帳目");
                    index = ConsoleIn.readLineInt();
                    if(index<0||index>Accounts.getTotalAccount())
                    {
                        System.out.println("無效的編號");
                    }
                    else
                    {
                        Accounts.delete(list,index);
                    }
                    break;
                }

                /* 額外功能:show more than */
                case "u":
                {
                    if(splitCommand.length==2)
                    {
                        money=SplitDemo.stringToInt(splitCommand[1]);
                        if(money<0)
                        {
                            System.out.println("無效的金額。");
                        }
                        else
                        {
                            Accounts.showUpper(list, money);
                        }
                    }
                    else
                    {
                        System.out.println("invalid syntax.");
                    }
                    break;
                }
                case "exit":
                    exitCode=true;
                    break;
                default:
                    System.out.println("invlid syntax. 若要取得說明,請輸入help\n");
            }   
        }
        /* 結束訊息。*/
        System.out.println("帳本紀錄完成，共" + Accounts.getTotalAccount() + "筆資料。");
        Accounts.showAll(list, true);
        System.out.println("本月支出共" + Accounts.getTotalmoney() + "元");
    }
}