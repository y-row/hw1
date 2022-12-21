package PMgame;
import IOhelper.*;
import pokemons.*;

public class game {
    /* hw5中的help */
    public static void helpAttribute(){
        System.out.println("All players can have 4 pokemons and total 3 items.");
    }

    public static void help(){
        System.out.println("1:normal fight                Start a battle.");
        System.out.println("2:catch a pokemon             You ony can have 4 pokemons.");
        System.out.println("3:restore                     Restore all pokemon's PP&HP.");
        System.out.println("4:show status                 Show two player's status.");
        System.out.println("5:items                       Equip or remove a pokemon's item.");
        System.out.println("6:switch number               Switch a player's pokemon number.");
        System.out.println("7:exit                        End this game.");
        System.out.println("0:Book                        ReadPokedex.");
        System.out.println("\nyour input:");
    }
    public static void help6(){
        System.out.println("switch [playername] [N1] [N2] switch N1 and N2 in player's backpack.");
        System.out.println("\nyour input:");
    }
    public static void help5(){
        System.out.println("load [name] [pokemon number] [item number]     equips a pokemon with an item.");
        System.out.println("unload [name] [pokemon number]                 unequips a pokemon's item.");
        System.out.println("\nyour input:");
    }


    public static void helpFight(){
        System.out.println("Belows are valid input format for a battle:");
        System.out.println("attack                   normal attack");
        System.out.println("attack [skillName]       special attack. Use 1 pp.");
        System.out.println("run away                 exit and lose this battle.");
        System.out.println("super potion             restore this pokemon 50HP. Only 2 of each battle.");
        System.out.println("max elixir               restore this pokemon's PP. Only 2 of each battle.");
        System.out.println("switch [pokemonNumber]   switch pokemon and skip this turn.");


    }
    public static void helpCatch(){
        System.out.println("input[playerName] [pokemonName] [pokemonLV] to catch.");
        System.out.println("Wild pokemon will only use normal attack");
    }

    /* 比速度 
     * 傳入兩個寶可夢 回傳速度比較快的。若速度相等則回傳第一隻
    */
    public int first(BasePokemon p1, BasePokemon p2){
        if(p1.getSpeed()>=p2.getSpeed()){
            return 1;
        }
        else{
            return 2;
        }
    }

    /* 野生動物自動戰鬥 
     * 傳入兩隻寶可夢，野生的進行普攻攻擊另一隻寶可夢後，將傷害回傳
    */
    public int wildAttack(BasePokemon p1, BasePokemon wildpm){
        System.out.printf("Wild ");
        int damage=wildpm.normalAttack(p1);
        return damage;
    }

    /* 檢查玩家的寶可夢掛了沒
     * 檢查player正在使用的寶可夢是否為active，若沒有則幫他換隻
     * 若戰鬥可以繼續就會回傳false，當回傳true時代表被打敗了。
    */
    public boolean checkBeaten(players player){
        if(!player.getPokemon(player.usingPokemon).active()){
            System.out.println(player.name + "'s " + player.getPokemon(player.usingPokemon).getName() + " is unable to battle!");
            return !player.autoChangePokemon();//換隻成功會變成true 所以要反向
        }
        return false ;//這一隻還沒被打敗
    }

    /* 使玩家控制寶可夢的程式,傳入的參數為玩家與其對手寶可夢
     * 直到輸入有效之前不斷的進行迴圈
     * step1.印出 玩家的回合!
     * step2. 讀一行指令然後切割
     * step3. 若指令無效則印出無效指令的提示
     * step4. 迴圈結束後，將damage回傳。
     */
    public int controller(players player,BasePokemon p1){//回傳傷害 逃跑(-1)或其他(0) 
        boolean effective = false;
        BasePokemon p= player.getPokemon(player.usingPokemon);
        int damage = -1;
        while(!effective){
            System.out.println(player.name + "'s turn!");
            String command =ConsoleIn.readLine();
            String []Splitcommand=command.split(" ");

            /* case1:攻擊/使用技能
             * 呼叫玩家寶可夢中對應的攻擊指令，最後回傳傷害
             * 若沒有該技能 則印出 寶可夢沒有這招!然後繼續迴圈
             * 在match到指令後，將effective設為true。
             * 如果沒有match到指令，則顯示錯誤並提供幫助。
            */
            if(Splitcommand.length==1&&Splitcommand[0].equals("attack")){
                effective = true;
                damage=player.getPokemon(player.usingPokemon).normalAttack(p1);
            }
            else if(Splitcommand.length==2&&Splitcommand[0].equals("attack")){//或許在使用技能
                if(Splitcommand[1].equals(p.getSkill1())){
                    effective = true; //使用技能1
                    damage=p.skill1(p1);
                }
                else if(Splitcommand[1].equals(p.getSkill2())){
                    effective = true;  //使用技能2
                    damage=p.skill2(p1);
                }
                else{
                    System.out.println(p.getName() + " has no such skills."); //錯字
                }
            }

            /* 非戰鬥指令:逃跑與回復
             *  在match到指令後，將effective設為true。
            */
            else if(Splitcommand.length==2){
                
                /* 逃跑 */
                if(command.equals("run away")){
                    damage= -1;
                    return damage;//跑了
                }

                /* 好傷藥 */
                else if(command.equals("super potion")){
                    if(player.potion>0){
                        effective=true; //開始喝好傷藥
                        player.potion--;
                        p.restore();    //pokemon中的吃藥指令
                        damage= 0;
                    }
                    else{
                        System.out.println("No enough super potion!"); //沒辦法吃藥
                    }
                }
                
                /* 技能回復劑 */
                else if(command.equals("max elixir")){
                    if(player.elixir>0){
                        effective=true; //開始喝好傷藥
                        player.elixir--;
                        p.elixir();    //pokemon中的吃藥指令
                        damage= 0;
                    }
                    else{
                        System.out.println("No enough max elixir!"); //沒辦法吃藥
                    }
                }
                
                /* 換隻
                 * 錯誤訊息在player中
                 * 如果換隻成功，則換下一個人出擊
                 */
                else if(Splitcommand[0].equals("switch")){
                    int number=SplitDemo.stringToInt(Splitcommand[1]);
                    effective=player.switchPokemon(number);//執行成功會回傳true
                    if(effective){
                        damage=0;
                    }
                }
                else{
                    System.out.println("Invalid Syntax!\n");
                    helpFight();
                    System.out.println();
                }
            }
            else{
                System.out.println("Invalid Syntax!\n");
                helpFight();
                System.out.println();
            }
        }

        /* 至此結束:如果有裝備吃剩的東西,結算吃剩的東西(逃跑已經跳出迴圈了)
         * **換隻的時候，會回復新上來的生命值。
        */
        p = player.getPokemon(player.usingPokemon);
        if(p.getEquippingItem().equals("leftovers")){
            p.leftovers();
        }
        return damage;
    }

    /*抓取寶可夢
     * 1.判斷輸入格式是否正確
     * 2.檢查玩家可不可以出戰
     * 3.判斷寶可夢名稱格式是否正確
     * 上述步驟若無法順利進行，就跳出去重選。
     * 4.開始交戰
     */
    public void catchPokemon(players []playerlist){
        helpCatch(); //顯示提示
        String command =ConsoleIn.readLine();
        String []Splitcommand=command.split(" ");
        BasePokemon wildpm;
        int i=-1;//指示玩家
        int lv=-1;//指示pm等級
        
        /* 看背包
         * 1.將i設為對應的角色
         * 1.背包滿了沒
         * 2.背包要有活的寶可夢
         */
        if(Splitcommand[0].equals(playerlist[0].getName())){//第一位玩家
            i=0;     //*************//       
        }
        else if(Splitcommand[0].equals(playerlist[1].getName())){//第二位玩家
            i=1;
            
        }
        else{
            System.out.println("Syntax error. Please try again.");
            return;
        }

        /* 檢查玩家可不可以捕捉 */
        if(playerlist[i].getPokemonNumber()>players.backpack){//背包滿了
            System.out.println(playerlist[i].getName() + "'s backpack is full. Please try again.");
            return;
        }
        else if(!playerlist[i].active){//我沒了
            System.out.println(playerlist[i].name + " doesn't have active pokemon!");
            return;
        }

        /* 找pm
         * 1.格式要是三個字
         * 2.等級有沒有超過?
         * 3.名字有沒有正確?
         */
        if(Splitcommand.length!=3){
            System.out.println("Syntax error. Please try again.");
            return;
        }
        else{

            /* check等級 */
            lv=SplitDemo.stringToInt(Splitcommand[2]);
            if(lv>3){
                System.out.println("LV cannot greater than 3. Please Try again.");
                return;
            }
            else if(lv<=0){
                System.out.println("invalid pokemon level. Please Try again.");
                return; 
            }

            /* 生成野生寶可夢 */
            switch(Splitcommand[1]){
                case "Vaporeon":
                    wildpm = new Vaporeon(lv);
                    break;
                case "Flareon":
                    wildpm=new Flareon(lv);
                    break;
                case "Leafeon":
                    wildpm=new Leafeon(lv);
                    break;
                case "Umbreon":
                    wildpm= new Umbreon(lv);
                    break;
                case "Espeon":
                    wildpm= new Espeon(lv);
                    break;
                default:
                    System.out.println("Wrong pokemon name. Please try again.");
                    return;
            }
            
        }
        
        /* 戰鬥開始
         * 1. offset:2罐藥、第一回合、玩家有沒有被打敗
         * 2.檢查turn不可以超過4就進入迴圈
         * 3.戰鬥結束顯示相應訊息。
         * 4.計算經驗值
         * 
        */
        int turns =1;
        players player =playerlist[i];
        boolean catchPokemon =false;
        player.potion=2;
        boolean defeat=false;

        while(turns<5){
            if(!player.active){
                System.out.println("Defeat!");
                System.out.println(player.name + "'s result:");
                battleFinish(player, false);
                break;
            }else{
                System.out.println("Round " + turns + " : ");
            }
            int f= first(player.getPokemon(player.usingPokemon), wildpm);

            if(f==1){//玩家先動

                /*顯示狀態 */
                System.out.println(player.name);
                player.getPokemon(player.usingPokemon).battleStatus();
                System.out.println("Wildpokemon: ");
                wildpm.battleStatus();

                /*開始交戰 */
                int x=controller(player,wildpm);//x=0:回復,x=-1逃跑,-2輸入錯誤
                if(x>0){
                    player.battleEXP[player.usingPokemon]+=x;
                }
                if(x==-1){
                    System.out.println("Defeat!");
                    System.out.println(player.name + "'s result:");
                    battleFinish(player, true);
                    break; //結束
                }

                if(wildpm.active()){//野生寶可夢活著
                    System.out.println("Wild " + wildpm.getName() + "'s turn!\n");
                    wildAttack(player.getPokemon(player.usingPokemon), wildpm);//活著就打
                    if(checkBeaten(player)){
                        System.out.println("Defeat!");
                        System.out.println(player.name + "'s result:");
                        battleFinish(player, false);
                        break;
                    }
                }
                else{
                    catchPokemon=true;//抓到了
                    System.out.println("Catch success!");
                    System.out.println(player.name + "'s result:");
                    battleFinish(player, false);
                    break;
                }
            }

            /*野怪先動 */
            else{
                System.out.println("Wild " + wildpm.getName() + "'s turn!");
                wildAttack(player.getPokemon(player.usingPokemon), wildpm);//活著就打
                if(checkBeaten(player)){
                    System.out.println("Defeat!");
                    System.out.println(player.name + "'s result:");
                    battleFinish(player, false);
                    break;
                }

                /*顯示狀態 */
                System.out.println(player.name);
                player.getPokemon(player.usingPokemon).battleStatus();
                System.out.println("Wildpokemon: ");
                wildpm.battleStatus();
                
                /*開始交戰 */
                int x=controller(player,wildpm);//x=0:其他,x=-1逃跑
                if(x>0){
                    player.battleEXP[player.usingPokemon]+=x;
                }
                if(x==-1){
                    battleFinish(player, true);
                    break; //結束
                }

                /* 打贏了沒有 */
                if(!wildpm.active()){
                    catchPokemon=true;
                    battleFinish(player, false);
                    break;
                }
                
            }
            turns++;
        }
        //戰鬥結束
        if(catchPokemon){
            /* 掉落道具 */
            i=0;
            for(;i<player.PokemonNumber;i++){
                if(player.pm[i].getEquippingItem()!=null){
                    i++;
                    if(i>3);
                    break;
                }
            }
            if(i<3){
                player.getItem(wildpm.getDrop());
                System.out.println(player.name + " get a " + wildpm.getDrop() + " !");
            }
            player.showStats();
            wildpm.JOEY();
            player.addPokemon(wildpm);
            
        }
        else{
            if(turns==5){
                battleFinish(player, false);
            }
            System.out.println("Catch failed!");
            
            player.showStats();
        }
    }

    /*戰鬥結束的結算
     * 1.如果是逃跑則把經驗都扣50
     * 2.顯示獲得的經驗值
     * 3.呼叫結算經驗，把exp加進去
     */
    public void battleFinish(players player,boolean runAway){
        if(runAway){
            for(int i=0;i<2;i++){
                player.battleEXP[i]-=50;
                if(player.battleEXP[i]<0){
                    player.battleEXP[i]=0;
                }
            }
        }
        player.gainEXP();//增加經驗
    }



        /*玩家的戰鬥 */
    public void battle(players[]playerlist){
        /* 選出戰精靈*/
        for(int i=0;i<2;i++){
            for(int j=0;j<playerlist[i].PokemonNumber;j++){
                if(playerlist[i].getPokemon(j).active()){
                    playerlist[i].usingPokemon=j;
                    break;
                }
            }
        }

        /* 參數:
         * 1.回合、造成傷害、藥品
         * 2.玩家、pm的指標
         * 勝利者指示
        */
        int round=1;
        int damage;
        boolean runAway=false;
        players player1= playerlist[0];
        players player2= playerlist[1];
        player1.potion=2;
        player1.elixir=2;
        player2.potion=2;
        player2.elixir=2;
        BasePokemon p1;
        BasePokemon p2;
        boolean exitcode=false;
        int winner=0;
        while(!exitcode){
            System.out.println("Round " + round + " :");
            
            /*顯示狀態 */
            p1=player1.getPokemon(player1.usingPokemon);
            p2=player2.getPokemon(player2.usingPokemon);
            System.out.println(player1.name);
            p1.battleStatus();
            System.out.println(player2.name);
            p2.battleStatus();
            
            /* 比速度*/
            int f=first(p1,p2);//誰先

            /* 每次戰鬥結束執行checkBeaten 
             * 1.先把p1 p2指向對應的寶可夢
             * 2. 丟進去controller
             * 
             * 
            */
            if(f==1){ //1號玩家先動
                
                damage=controller(player1, p2);
                if(damage>0){
                    player1.battleEXP[player1.usingPokemon]+=damage;
                }
                else if(damage==-1){//1逃跑
                    runAway=true;
                    winner=2;
                    break;
                }
                
                if(checkBeaten(player2)){//2被打敗
                    runAway=false;
                    winner=1;
                    break;
                }

                /*2的回合 */
                
                /*顯示狀態 */
                p1=player1.getPokemon(player1.usingPokemon);
                p2=player2.getPokemon(player2.usingPokemon);
                System.out.println(player1.name);
                p1.battleStatus();
                System.out.println(player2.name);
                p2.battleStatus();
            
                damage=controller(player2, p1);
                if(damage>0){
                    player2.battleEXP[player2.usingPokemon]+=damage;
                }
                else if(damage==-1){//2逃跑
                    winner=1;
                    runAway=true;
                    break;
                }
                checkBeaten(player1);//1被打敗
                if(!player1.active){
                    winner=2;
                    runAway=false;
                    break;
                }
            }
            else{//2先動

                damage=controller(player2, p1);
                if(damage>0){
                    player2.battleEXP[player2.usingPokemon]+=damage;
                }
                else if(damage==-1){//2逃跑
                    winner=1;
                    runAway=true;
                    break;
                }
                checkBeaten(player1);//1被打敗
                if(!player1.active){
                    winner=2;
                    runAway=false;
                    break;
                }


                /*顯示狀態: 1的回合*/
                p1=player1.getPokemon(player1.usingPokemon);
                p2=player2.getPokemon(player2.usingPokemon);
                System.out.println(player1.name);
                p1.battleStatus();
                System.out.println(player2.name);
                p2.battleStatus();
                damage=controller(player1, p2);
                if(damage>0){
                    player1.battleEXP[player1.usingPokemon]+=damage;
                }
                else if(damage==-1){//1逃跑
                    winner=2;
                    runAway=true;
                    break;
                }
                checkBeaten(player2);//2被打敗
                if(!player2.active){
                    winner=1;
                    runAway=false;
                    break;
                }
            }
            round++;
        }

        /* 結算:顯示對應的贏家或輸家 */
        switch(winner){
            case 1:
                System.out.println(player1.name + " wins!");
                System.out.println(player2.name + " lose!");
                System.out.println("\n" +player1.name + "'s result:");
                battleFinish(player1, false);
                System.out.println("\n" +player2.name + "'s result:");
                battleFinish(player2, runAway);
                System.out.println("Winner's backpack:");
                player1.showStats();
                break;
            case 2:
                System.out.println(player2.name + " wins!");
                System.out.println(player1.name + " lose!");
                System.out.println("\n" +player2.name + "'s result:");
                battleFinish(player2, false);
                System.out.println("\n" +player1.name + "'s result:");
                battleFinish(player1, runAway);

                System.out.println("Winner's backpack:");
                player2.showStats();
                break;
        }
        
    }
}
