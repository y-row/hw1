

public class hw01
{
    public static void main(String[] args) 
    {
        
        
        
        /*介紹支援的服務*/
        System.out.println("歡迎光臨!\n現在兩人同行享9折優惠\n三人以上享8折優惠!\n\n");
        System.out.println("服務項目     男   女");
        System.out.println("指定設計師  500  500");
        System.out.println("--------剪髮--------");
        System.out.println("精緻剪髮    100  150");
        System.out.println("修剪瀏海     X    50");
        System.out.println("--------染髮--------");
        System.out.println("一般染髮    499  499");
        System.out.println("天然護髮染  999  999");
        System.out.println("--------燙髮--------");
        System.out.println("髮根蓬蓬燙 1000 1000");
        System.out.println("局部燙      500  500");
        System.out.println("哥德式閃亮  350  350");
        System.out.println("--------其他--------");
        System.out.println("護髮        360  720");
        System.out.println("洗髮         50   50\n\n");

        /*主要&輸出*/
        int np=0;//人數
        int exit=0;//終止信號
        int total=0;//總金額
        while(exit!=1)
        {
            boolean n=false; //有沒有選擇服務
            boolean e=false;//是否強制退出
            int money_t=0;//單人花費
            System.out.println("您的性別\n男性:m    女性:f");
            char gender=ConsoleIn.readLineNonwhiteChar();//性別
            char choice_c;//字元形選項
            int choice_i=0;
            /*選擇服務區段 */ 
            switch(gender)
            {
                /*女性 */
                case 'f':         
                    System.out.println("性別：女"); 
                    
                    /*1.指定設計師*/
                    System.out.println("\n是否要指定設計師($500)?\n是:y    否:n    取消:c");
                    choice_c=ConsoleIn.readLineNonwhiteChar();
                    if(choice_c=='y'||choice_c=='Y')
                    {
                        money_t+=500;
                        System.out.println("您選擇：指定設計師\n目前花費:"+money_t);
                    }
                    else if(choice_c=='n'||choice_c=='N')
                    {
                        System.out.println("您選擇：不指定設計師\n目前花費:"+money_t);
                    }
                    else 
                    {
                        e=true;//強退
                        break;
                    }

                    /*2.剪頭髮*/
                    System.out.println("\n是否要剪髮($150)?\n是:y    否:n    隨機:r    取消:c");
                    choice_c=ConsoleIn.readLineNonwhiteChar();
                    if(choice_c=='r')//隨機called
                    {
                        choice_i=(int)Math.floor((Math.random()*2)+1);
                        choice_c=choice_i==1?'y':'n';
                    }
                    if(choice_c=='y'||choice_c=='Y')//要剪頭髮
                    {
                        System.out.println("您選擇：剪髮\n\n是否只剪瀏海(150->50)?\n是:y    否:n    隨機:r    取消:c");
                        choice_c=ConsoleIn.readLineNonwhiteChar();
                        if(choice_c=='r')//隨機called
                        {
                            choice_i=(int)Math.floor((Math.random()*2)+1);
                            choice_c=choice_i==1?'y':'n';
                        }
                        if(choice_c=='y'||choice_c=='Y')
                        {
                            n=true;
                            money_t+=50;
                            System.out.println("您選擇：剪瀏海\n目前花費:"+money_t);
                        }
                        else if(choice_c=='n'||choice_c=='N')
                        {
                            n=true;
                            money_t+=150;
                            System.out.println("您選擇：精緻剪髮\n目前花費:"+money_t);

                        }
                        else 
                        {
                            e=true;//強退
                            break;
                        }
                    }
                    else if(choice_c=='n'||choice_c=='N')//不剪頭髮
                    {
                        System.out.println("您選擇：不剪髮\n目前花費:"+money_t);
                    }
                    else 
                    {
                        e=true;//強退
                        break;
                    }

                    /*3.染髮*/
                    System.out.println("\n是否要染髮?\n是:y    否:n    隨機:r    取消:c");
                    choice_c=ConsoleIn.readLineNonwhiteChar();
                    if(choice_c=='r')//隨機called
                        {
                            choice_i=(int)Math.floor((Math.random()*2)+1);
                            choice_c=choice_i==1?'y':'n';
                        }
                    if(choice_c=='y'||choice_c=='Y')//要染頭髮
                    {
                        System.out.println("您選擇：染髮");
                        System.out.println("\n請選擇染劑\n一般($499):1    天然護膚($999):2    隨機:9    取消:0");
                        choice_i=ConsoleIn.readLineInt();
                        if(choice_i==9)//隨機called
                        {
                            choice_i=(int)Math.floor((Math.random()*2)+1);
                        }
                        if(choice_i==1)
                        {
                            n=true;
                            money_t+=499;
                            System.out.println("您選擇：一般染劑\n目前花費:"+money_t);
                        }
                        else if(choice_i==2)
                        {
                            n=true;
                            money_t+=999;
                            System.out.println("您選擇：天然護膚染\n目前花費:"+money_t);
                        }
                        else 
                        {
                            e=true;//強退
                            break;
                        }
                    }
                    else if(choice_c=='n'||choice_c=='N')//不染頭髮
                    {
                        System.out.println("您選擇：不染髮\n目前花費:"+money_t);
                    }
                    else 
                    {
                        e=true;//強退
                        break;
                    }

                    /*4.護髮*/
                    System.out.println("\n是否要護髮($720)?\n是:y    否:n    隨機:r    取消:c");
                    choice_c=ConsoleIn.readLineNonwhiteChar();
                    if(choice_c=='r')//隨機called
                        {
                            choice_i=(int)Math.floor((Math.random()*2)+1);
                            choice_c=choice_i==1?'y':'n';
                        }
                    if(choice_c=='y'||choice_c=='Y')
                    {
                        n=true;
                        money_t+=720;
                        System.out.println("您選擇：護髮\n目前花費:"+money_t);
                    }
                    else if(choice_c=='n'||choice_c=='N')
                    {
                        System.out.println("您選擇：不護髮\n目前花費:"+money_t);
                    }
                    else 
                    {
                        e=true;//強退
                        break;
                    }
                    
                    /*5.燙髮*/
                    System.out.println("\n是否要燙髮?\n是:y    否:n    隨機:r    取消:c");
                    choice_c=ConsoleIn.readLineNonwhiteChar();
                    if(choice_c=='r')//隨機called
                        {
                            choice_i=(int)Math.floor((Math.random()*2)+1);
                            choice_c=choice_i==1?'y':'n';
                        }
                    if(choice_c=='y'||choice_c=='Y')//要燙頭髮
                    {
                        System.out.println("您選擇：燙髮");
                        System.out.println("\n請選擇樣式");
                        System.out.println("髮根澎澎燙($1000):1    局部燙($500):2    哥德式閃亮($350):3    隨機:9    取消:0");
                        choice_i=ConsoleIn.readLineInt();
                        if(choice_i==9)//隨機called
                        {
                            choice_i=(int)Math.floor((Math.random()*3)+1);
                        }
                        if(choice_i==1)
                        {
                            n=true;
                            money_t+=1000;
                            System.out.println("您選擇：髮根澎澎燙\n目前花費:"+money_t);
                        }
                        else if(choice_i==2)
                        {
                            n=true;
                            money_t+=500;
                            System.out.println("您選擇：局部燙\n目前花費:"+money_t);
                        }
                        else if(choice_i==3)
                        {
                            n=true;
                            money_t+=350;
                            System.out.println("您選擇：哥德式閃亮\n目前花費:"+money_t);
                        }
                        else 
                        {
                            e=true;//強退
                            break;
                        }
                    }
                    else if(choice_c=='n'||choice_c=='N')//不燙頭髮
                    {
                        System.out.println("您選擇：不燙髮\n目前花費:"+money_t);
                    }
                    else 
                    {
                        e=true;//強退
                        break;
                    }

                    /*6.洗髮*/
                    System.out.println("\n是否要洗髮($50)?\n是:y    否:n    隨機:r    取消:c");
                    choice_c=ConsoleIn.readLineNonwhiteChar();
                    if(choice_c=='r')//隨機called
                        {
                            choice_i=(int)Math.floor((Math.random()*2)+1);
                            choice_c=choice_i==1?'y':'n';
                        }
                    if(choice_c=='y'||choice_c=='Y')
                    {
                        n=true;
                        money_t+=50;
                        System.out.println("您選擇：洗髮");
                    }
                    else if(choice_c=='n'||choice_c=='N')
                    {
                        System.out.println("您選擇：不洗髮");
                    }
                    else 
                    {
                        e=true;//強退
                        break;
                    }


                /*結束*/
                if(n)
                {
                    np++;
                }
                break;//女性結束
                        
                /*男性*/
                case 'm':
                    System.out.println("性別：男"); 

                    /*1.指定設計師*/
                    System.out.println("\n是否要指定設計師($500)?\n是:y    否:n    取消:c");
                    choice_c=ConsoleIn.readLineNonwhiteChar();
                    if(choice_c=='y'||choice_c=='Y')
                    {
                        money_t+=500;
                        System.out.println("您選擇：指定設計師\n目前花費:"+money_t);
                    }
                    else if(choice_c=='n'||choice_c=='N')
                    {
                        System.out.println("您選擇：不指定設計師\n目前花費:"+money_t);
                    }
                    else 
                    {
                        e=true;//強退
                        break;
                    }

                    /*2.剪頭髮*/
                    System.out.println("\n是否要剪髮($100)?\n是:y    否:n    隨機:r    取消:c");
                    choice_c=ConsoleIn.readLineNonwhiteChar();
                    if(choice_c=='r')//隨機called
                    {
                        choice_i=(int)Math.floor((Math.random()*2)+1);
                        choice_c=choice_i==1?'y':'n';
                    }
                    if(choice_c=='y'||choice_c=='Y')//要剪頭髮
                    {
                        n=true;
                        money_t+=100;
                        System.out.println("您選擇：精緻剪髮\n目前花費:"+money_t);
                    }
                    else if(choice_c=='n'||choice_c=='N')//不剪頭髮
                    {
                        System.out.println("您選擇：不剪髮\n目前花費:"+money_t);
                    }
                    else 
                    {
                        e=true;//強退
                        break;
                    }

                    /*3.染髮*/
                    System.out.println("\n是否要染髮?\n是:y    否:n    隨機:r    取消:c");
                    choice_c=ConsoleIn.readLineNonwhiteChar();
                    if(choice_c=='r')//隨機called
                        {
                            choice_i=(int)Math.floor((Math.random()*2)+1);
                            choice_c=choice_i==1?'y':'n';
                        }
                    if(choice_c=='y'||choice_c=='Y')//要染頭髮
                    {
                        System.out.println("您選擇：染髮");
                        System.out.println("\n請選擇染劑\n一般($499):1    天然護膚($999):2    隨機:9    取消:0");
                        choice_i=ConsoleIn.readLineInt();
                        if(choice_i==9)//隨機called
                        {
                            choice_i=(int)Math.floor((Math.random()*2)+1);
                        }
                        if(choice_i==1)
                        {
                            n=true;
                            money_t+=499;
                            System.out.println("您選擇：一般染劑\n目前花費:"+money_t);
                        }
                        else if(choice_i==2)
                        {
                            n=true;
                            money_t+=999;
                            System.out.println("您選擇：天然護膚染\n目前花費:"+money_t);
                        }
                        else 
                        {
                            e=true;//強退
                            break;
                        }
                    }
                    else if(choice_c=='n'||choice_c=='N')//不染頭髮
                    {
                        System.out.println("您選擇：不染髮\n目前花費:"+money_t);
                    }
                    else 
                    {
                        e=true;//強退
                        break;
                    }

                    /*4.護髮*/
                    System.out.println("\n是否要護髮($360)?\n是:y    否:n    隨機:r    取消:c");
                    choice_c=ConsoleIn.readLineNonwhiteChar();
                    if(choice_c=='r')//隨機called
                        {
                            choice_i=(int)Math.floor((Math.random()*2)+1);
                            choice_c=choice_i==1?'y':'n';
                        }
                    if(choice_c=='y'||choice_c=='Y')
                    {
                        n=true;
                        money_t+=360;
                        System.out.println("您選擇：護髮\n目前花費:"+money_t);
                    }
                    else if(choice_c=='n'||choice_c=='N')
                    {
                        System.out.println("您選擇：不護髮\n目前花費:"+money_t);
                    }
                    else 
                    {
                        e=true;//強退
                        break;
                    }
                    
                    /*5.燙髮*/
                    System.out.println("\n是否要燙髮?\n是:y    否:n    隨機:r    取消:c");
                    choice_c=ConsoleIn.readLineNonwhiteChar();
                    if(choice_c=='r')//隨機called
                        {
                            choice_i=(int)Math.floor((Math.random()*2)+1);
                            choice_c=choice_i==1?'y':'n';
                        }
                    if(choice_c=='y'||choice_c=='Y')//要燙頭髮
                    {
                        System.out.println("您選擇：燙髮");
                        System.out.println("\n請選擇樣式");
                        System.out.println("髮根澎澎燙($1000):1    局部燙($500):2    哥德式閃亮($350):3    隨機:9    取消:0");
                        choice_i=ConsoleIn.readLineInt();
                        if(choice_i==9)//隨機called
                        {
                            choice_i=(int)Math.floor((Math.random()*3)+1);
                        }
                        if(choice_i==1)
                        {
                            n=true;
                            money_t+=1000;
                            System.out.println("您選擇：髮根澎澎燙\n目前花費:"+money_t);
                        }
                        else if(choice_i==2)
                        {
                            n=true;
                            money_t+=500;
                            System.out.println("您選擇：局部燙\n目前花費:"+money_t);
                        }
                        else if(choice_i==3)
                        {
                            n=true;
                            money_t+=350;
                            System.out.println("您選擇：哥德式閃亮\n目前花費:"+money_t);
                        }
                        else 
                        {
                            e=true;//強退
                            break;
                        }
                    }
                    else if(choice_c=='n'||choice_c=='N')//不燙頭髮
                    {
                        System.out.println("您選擇：不燙髮\n目前花費:"+money_t);
                    }
                    else 
                    {
                        e=true;//強退
                        break;
                    }

                    /*6.洗髮*/
                    System.out.println("\n是否要洗髮($50)?\n是:y    否:n    隨機:r    取消:c");
                    choice_c=ConsoleIn.readLineNonwhiteChar();
                    if(choice_c=='r')//隨機called
                        {
                            choice_i=(int)Math.floor((Math.random()*2)+1);
                            choice_c=choice_i==1?'y':'n';
                        }
                    if(choice_c=='y'||choice_c=='Y')
                    {
                        n=true;
                        money_t+=50;
                        System.out.println("您選擇：洗髮");
                    }
                    else if(choice_c=='n'||choice_c=='N')
                    {
                        System.out.println("您選擇：不洗髮");
                    }
                    else 
                    {
                        e=true;//強退
                        break;
                    }


                /*結束*/
                if(n)
                {
                    np++;
                }
                break;//男性結束
            }
            
            /*若失敗*/
            if(gender!='f'&&gender!='m')continue;//(不支援性別光譜)
            if(e)continue;//中斷型
            if(!n)//沒選擇服務型
            {
                System.out.println("請至少選擇一種項目!");
                continue;
            }

            /*成功後詢問是否繼續*/
            total+=money_t; 
            System.out.println("您的花費為:"+money_t+"元\n目前已預約"+np+"位客人\n共"+total+"元");
            switch(np)
            {
                case 1:
                System.out.println("再預約1位可享全體9折優惠,再預約2位可享8折優惠");
                break;
                case 2:
                System.out.println("打9折後為"+total*9/10+"元\n再預約1位可享全體8折優惠");
                break;
            }
            if(np>=3)
            {
                System.out.println("打8折後為"+total*8/10+"元");
            }
            System.out.println("\n是否繼續預約下一位顧客的服務項目?\n是:y 否:n");
            choice_c=ConsoleIn.readLineNonwhiteChar();
            if(choice_c=='y'||choice_c=='Y')continue;
            if(np>=3)
            {
                total=total*8/10;
            }
            else if(np==2)
            {
                total=total*9/10;
            }
            System.out.println("\n總共需付"+total+"元\n謝謝光臨 歡迎下次再來!");
            exit=1;
        }

    }
}