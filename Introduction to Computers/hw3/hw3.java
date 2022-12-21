public class hw3{
    public static void main (String args[]){
        int days=1;
        orchard o=new orchard();
        String command="";
        String [] splitCommand;
        boolean exitCode=false;
        System.out.println("歡迎使用 果園生態模擬系統。");
        orchard.help();
        while(!exitCode)
        {
            System.out.println("第 " + days + " 天:\n輸入指令:");
            command=ConsoleIn.readLine();
            splitCommand=command.split(" ");
            switch(splitCommand[0])
            {
                /* 顯示果園狀態 */
                case"show":
                    if(splitCommand.length==1){
                        o.showOrchard();
                    }
                    else{
                        System.out.println("格式錯誤。");
                    }
                    break;
                
                /* 新增動植物 */
                case "add":
                    if(splitCommand.length==3){
                        int number=SplitDemo.stringToInt(splitCommand[2]);
                        switch(splitCommand[1]){
                            
                            /* 動物 */
                            case"bear":
                                o.setBearNumber(number);
                                break;
                            case"hive":
                                o.setHiveNumber(number);    
                                break;
                            case"monkey":
                                o.setMonkeyNumber(number);
                                break;
                            case"dog":
                                o.setDogNumber(number);    
                                break;
                            /* 非動物 */
                            default:
                                o.addTree(number, splitCommand[1]);
                                break;
                        }     
                    }
                    else{
                        System.out.println("add格式錯誤");
                    }
                    break;

                /* 推移天數 */
                case"next":
                    if(splitCommand.length==2){
                        int d=SplitDemo.stringToInt(splitCommand[1]);
                        if(d>0){
                            for(int i=0;i<d;i++){
                                days++;
                                o.next();
                            }
                        }
                        else{
                            System.out.println("請輸入正確的天數。");
                        }
                    }
                    else{
                        System.out.println("格式錯誤。");
                    }
                    break;
                
                /* 修剪果園 */
                case"prune":
                    if(splitCommand.length==1){
                    o.prune();
                    }
                    else if(splitCommand.length==2){
                        int d=SplitDemo.stringToInt(splitCommand[1]);
                        if(d<=9&&d>=1){
                            int x=o.prune(d);
                            if(x==0){
                                System.out.println("該位置沒有果樹,修剪失敗。");
                            }
                        }
                        else{
                            System.out.println("位置錯誤,修剪失敗。");
                        }
                                
                    }
                    else{
                        System.out.println("格式錯誤,修剪失敗。");
                    }
                    break;
                
                /* 採收果實 */
                case"harvest":
                    if(splitCommand.length==1){
                        o.harvest();
                    }
                    else if(splitCommand.length==2){
                        int d=SplitDemo.stringToInt(splitCommand[1]);
                        if(d<=9&&d>=1){
                            o.harvest(d);
                        }
                        else{
                            System.out.println("位置錯誤,收穫失敗。");
                        }
                    }
                    else{
                        System.out.println("格式錯誤,收穫失敗。");
                    }
                    break;

                /* 砍樹 */
                case"cut":
                    if(splitCommand.length==2)
                    {
                        int d=SplitDemo.stringToInt(splitCommand[1]);
                        if(d<=9&&d>=1){
                            o.cut(d);
                        }
                        else{
                            System.out.println("位置錯誤,砍樹失敗。");
                        }
                    }
                    break;

                /* 趕走動物 */
                case"remove":
                    if(splitCommand.length==3){
                        int number=SplitDemo.stringToInt(splitCommand[2]);
                        switch(splitCommand[1]){
                            /* 熊 */
                            case"bear":
                                o.removeBears(number);
                                break;
                            /* 蜜蜂 */
                            case"hive":
                                o.removehives(number);
                                break;
                            
                            /* 猴子 */
                            case"monkey":
                                o.removeMonkeys(number);
                                break;

                            /* 狗 */
                            case"dog":
                                o.removeDogs(number);
                                break;
                            
                            /* 非動物 */
                            default:
                                System.out.println("格式錯誤,移除失敗。");
                                break;
                        }
                    }
                    else{
                        System.out.print("格式錯誤,移除失敗。");
                    }
                    break;

                /* 離開 */
                case"exit":
                    exitCode=true;
                    break;

                /* 取得幫助 */
                case"help":
                    orchard.help();
                    break;
                default:
                    System.out.println("無效的指令。輸入help獲取幫助。");
                    break;
            }
        }
        /*  結束運行 */
        System.out.println("模擬結束,最終共收穫 " + o.getTotalBanana() + "根香蕉 " + o.getTotalPomelo() + " 顆柚子。");
        System.out.println("感謝您的使用!。");
    }
}