import java.io.*;
import java.util.*;

import IOhelper.*;
import PMgame.*;
import PMgame.players;
import pokemons.*;
public class hw5 {
    public static void main(String args[]) {
        /* 第一部分:建立玩家資訊 */
        int i=-1;
        boolean illegalInput = true;
        Boolean effective=false;
        PokemonBook book= new PokemonBook();
        String command;
        String []splitcommand;
        players []playerlist=new players[2];
        System.out.println("Welcome");
        System.out.println("input 1 to start a new game \ninput 2 to load game\nyour input:");
        i=ConsoleIn.readLineInt();
        while(i!=2&&i!=1){
            System.out.println("input 1 to start a new game \ninput 2 to load game\nyour input:");
            i=ConsoleIn.readLineInt();
        }
        switch(i){
            case 2:  
                /* 建構時要把人物激活(一開始是false) */     
                try{
                    /* 讀檔案
                     * 如果長度超過6:pokemon
                     * 接著讀3行(用itemNumber變數來指示)
                     * 然後讀 下一個名字
                     * 
                     */
                    int p=0;
                    int itemNumber=0;
                    String name;
                    Scanner reader = new Scanner(new FileInputStream("record.txt"));
                    while(reader.hasNextLine()){
                        command = reader.nextLine();
                        splitcommand=command.split(" ");
                        if(splitcommand.length==1){//有可能是名字或item
                            switch(command){
                                case "": //空的item
                                case "quickclaw":
                                case "protector":
                                case "leftovers":
                                case "kingsrock":
                                case "luckyegg":
                                    playerlist[p-1].getItem(command,itemNumber);
                                    itemNumber++;
                                    break; 
                                default:
                                name=command;
                                playerlist[p]=new players(name);
                                p++;
                                itemNumber=0;//另一位角色
                                break;

                            }
                            
                        }
                        else{
                            int lv=SplitDemo.stringToInt(splitcommand[1]);
                            int hp=SplitDemo.stringToInt(splitcommand[2]);
                            int s1=SplitDemo.stringToInt(splitcommand[3]);
                            int s2=SplitDemo.stringToInt(splitcommand[4]);
                            int exp=SplitDemo.stringToInt(splitcommand[5]);
                            String s="";
                            if(splitcommand.length==7){
                                s=splitcommand[6];
                            }
                            if(hp>0){
                                playerlist[p-1].setActive(true); //有可以出戰的寶可夢
                            }
                            switch(splitcommand[0]){
                                case "Vaporeon":
                                    Vaporeon v =new Vaporeon(lv,hp,s1,s2,exp,s);
                                    playerlist[p-1].addPokemon(v);
                                    break;
                                case "Flareon":
                                    Flareon f=new Flareon(lv, hp, s1, s2,exp,s);
                                    playerlist[p-1].addPokemon(f);
                                    break;
                                case "Leafeon":
                                    Leafeon l = new Leafeon(lv,hp,s1,s2,exp,s);
                                    playerlist[p-1].addPokemon(l);
                                    break;
                                case "Espeon":
                                    Espeon e= new Espeon(lv,hp,s1,s2,exp,s);
                                    playerlist[p-1].addPokemon(e);
                                    break;
                                case "Umbreon":
                                    Umbreon u=new Umbreon(lv,hp,s1,s2,exp,s);
                                    playerlist[p-1].addPokemon(u);
                                    break;
                            }
                        }
                    }

                    /* 建立完畢 顯示雙方狀態 */
                    for(i=0;i<2;i++){
                        playerlist[i].showStats();
                    }
                    reader.close();
                    break;
                }
                catch(FileNotFoundException e){
                    System.out.println("NO such file. Now start a new game.");
            }
                
            case 1:
            /* 開始新遊戲 */
                int p=0;
                BasePokemon.helpAdd(true);
                System.out.println("Now this game have only 5 types of pokemon:");
                book.allName();
                while(p!=2){
                    System.out.println("\nCreating player" + (p+1) + "...");
                    command = ConsoleIn.readLine();
                    splitcommand= command.split(" ");
                    if(splitcommand.length!=2){//格式錯誤
                        BasePokemon.helpAdd(true);
                    }
                    else{
                        String s=splitcommand[1];
                        book.call(s);
                        switch(s){
                            /* 是寶可夢就新增，不是就不做*/
                            case "Vaporeon":
                            case "Flareon":
                            case "Leafeon":
                            case "Espeon":
                            case "Umbreon":
                            playerlist[p]=new players(splitcommand[0], s);
                            playerlist[p].showStats();
                            p++;
                            break;
                            default:
                            System.out.println("Wrong pokemon name. Please try again.");
                            break;
                        }
                    }
                }
                break;
        }

        /* 2.開始遊戲 */
        game Game= new game();
        System.out.println("\ngame start!");
        boolean exitcode=false;
        while(!exitcode){
            game.help();
            i=ConsoleIn.readLineInt();
            System.out.println();
            switch(i){
                /*終止迴圈 */
                case 7:
                    exitcode=true;
                    break;

                /* 更換編號
                 * 在這裡檢查輸入輸出
                 * 1.背包要有該寶可夢
                 * 2.名字要對
                 * 3.呼叫player的交換。
                 */
                case 6:
                    game.help6();
                    command=ConsoleIn.readLine();
                    splitcommand=command.split(" ");
                    if(splitcommand.length!=4){
                        System.out.println("invalid syntax. Try again.");
                    }
                    else{
                        int before= SplitDemo.stringToInt(splitcommand[2]);
                        int after = SplitDemo.stringToInt(splitcommand[3]);
                        
                        if(splitcommand[1].equals(playerlist[0].getName())){  //第一個玩家
                            
                            int max = playerlist[0].getPokemonNumber();  //寶可夢大小
                            if(before>max||before<1){//before太大
                                System.out.println(playerlist[0].getName() + " doesn't have number " + before + "'s pokemon." );
                                System.out.println("Try again.");
                            }
                            else if(after>max||after<1){//after太大
                                System.out.println(playerlist[0].getName() + " doesn't have number " + after + "'s pokemon." );
                                System.out.println("Try again.");
                            }
                            else if(after==before){
                                System.out.println("Try again.");
                            }
                            else{//開始交換
                                playerlist[0].switchPokemonNumber(before, after);
                                System.out.println("Switch complete.");
                                playerlist[0].showStats();
                            }

                        }
                        else if(splitcommand[1].equals(playerlist[1].getName())){  //第二個玩家
                            
                            int max = playerlist[1].getPokemonNumber();  //寶可夢大小
                            if(before>max||before<1){
                                System.out.println(playerlist[1].getName() + " doesn't have number " + before + "'s pokemon." );
                                System.out.println("Try again.");
                            }
                            else if(after>max||after<1){
                                System.out.println(playerlist[1].getName() + " doesn't have number " + after + "'s pokemon." );
                                System.out.println("Try again.");
                            }
                            else if(after==max){
                                System.out.println("Try again.");
                            }
                            else{
                                playerlist[1].switchPokemonNumber(before, after);
                                System.out.println("Switch complete.");
                                playerlist[1].showStats();
                            }

                        }
                        else{//不是玩家
                            System.out.println("invalid syntax. Try again.");
                        }
                    }
                    break;

                /*道具指令
                 * 1.先判斷指令長度是否為4(load)或3(unload)
                 * 2.判斷名字是否符合
                 * 3.呼叫對應player內的item系列指令
                 */
                case 5:
                    game.help5();
                    command=ConsoleIn.readLine();
                    splitcommand=command.split(" ");
                    switch (splitcommand.length){
                        case 4://load 
                            if(splitcommand[0].equals("load")){//一定要是load
                                int pnumber= SplitDemo.stringToInt(splitcommand[2]);
                                int inumber= SplitDemo.stringToInt(splitcommand[3]);
                                String name= splitcommand[1];
                                if(name.equals(playerlist[0].getName())){
                                    playerlist[0].equipItem(pnumber,inumber);
                                }
                                else if(name.equals(playerlist[1].getName())){
                                    playerlist[1].equipItem(pnumber,inumber);
                                }
                            }
                            else {
                                System.out.println("Invalid syntax. Try again.");
                            }
                            break;
                        case 3:
                            if(splitcommand[0].equals("unload")){//一定要是unload
                                String name= splitcommand[1];
                                int pnumber= SplitDemo.stringToInt(splitcommand[2]);
                                if(name.equals(playerlist[0].getName())){
                                    playerlist[0].removeItem(pnumber);
                                }
                                else if(name.equals(playerlist[1].getName())){
                                    playerlist[1].removeItem(pnumber);
                                }
                            }
                            else {
                                System.out.println("Invalid syntax. Try again.");
                            }
                            break;
                        default:
                            System.out.println("Invalid syntax. Try again.");
                    }
                    break;
                /* 顯示資訊 */
                case 4:
                    for(i=0;i<2;i++){
                        playerlist[i].showStats();
                    }
                    break;

                /* 回復站:把player的狀態回復 */
                case 3:
                effective=false;
                for(i=0;i<2;i++){
                    boolean x=playerlist[i].restore();
                    if(x){
                        effective=x;
                    }
                }
                if(effective){
                    System.out.println("Restore completed.");
                    for(i=0;i<2;i++){
                        playerlist[i].showStats();
                        playerlist[i].setActive(true);
                    }
                    System.out.println();
                }
                else{
                    System.out.println("All of the pokemons' PP&HP are full. Please Try again.\n");
                }
                break;

                case 2:
                Game.catchPokemon(playerlist);
                break;

                case 1:
                if(!playerlist[0].getActive()){
                    System.out.println(playerlist[0].getName() + " doesn't have active pokemon!");
                }
                else if(!playerlist[1].getActive()){
                    System.out.println(playerlist[1].getName() + " doesn't have active pokemon!");
                }
                else{
                    Game.battle(playerlist);
                }
                break;
                case 0:
                book.help();
                break;
            }
        }
        /* 結束時顯示 */
        System.out.println("Your records:");
        for(i=0;i<2;i++){
            playerlist[i].showStats();
        }
        /*存檔
         * 0. 清掉檔案(用writer.write印一個null)
         * 1.存名字
         * 2.存保可夢資訊
         * 3.存道具背包:如果showItems回傳null,就印一個換行
         * 4.flush+close
         */
        try{
            PrintWriter writer = new PrintWriter(new FileOutputStream("record.txt", false));
            writer.write("");
            for(i=0;i<2;i++){
                writer.println(playerlist[i].getName());
                for(int j=0;j<playerlist[i].getPokemonNumber();j++){
                    writer.println(playerlist[i].getPokemon(j).recordStatus());
                }
                for(int j=0;j<players.itempack;j++){
                    if(playerlist[i].showItems(j)==null){
                        writer.print("\n");
                    }
                    else{
                        writer.println(playerlist[i].showItems(j));
                    }
                    
                } 
            }
            writer.flush();
            writer.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File Not Found/Can't be Created");
        }

    }
    
}
