package PMgame;
import pokemons.BasePokemon;
import pokemons.Espeon;
import pokemons.Flareon;
import pokemons.Leafeon;
import pokemons.Umbreon;
import pokemons.Vaporeon;

public class players {
    public static final int backpack=4;
    public static final int itempack=3;
    protected String name;

    /* 戰鬥用 */
    public int usingPokemon=0;//使用中的寶可夢
    public int []battleEXP={0,0,0,0}; //紀錄戰鬥時寶可夢的經驗值
    public int potion=0,elixir=0; //藥品
    protected boolean active;//能不能出戰

    /* 物品 */
    protected int PokemonNumber=0;//先記錄第i個pokemon進i-1,再把數值提升至i。
    protected int itemNumber=0;
    protected BasePokemon[] pm=new BasePokemon[backpack];//寶可夢背包
    protected String[] item= new String[itempack];

    /* New game的時候 */
    public players(String name,String firstPm){
        this.name =name;
        this.active=true;//一定可以出戰
        switch (firstPm){
            case "Vaporeon":
                pm[PokemonNumber]=new Vaporeon(1);
                PokemonNumber++;
                break;
            case "Flareon":
                pm[PokemonNumber]=new Flareon(1);
                PokemonNumber++;
                break;
            case "Leafeon":
                pm[PokemonNumber]=new Leafeon(1);
                PokemonNumber++;
                break;
            case "Umbreon":
                pm[PokemonNumber]=new Umbreon(1);
                PokemonNumber++;
                break;
            case "Espeon":
                pm[PokemonNumber]=new Espeon(1);
                PokemonNumber++;
                break;
        }
        
    }
    
    public players(String name){
        this.name=name;
        this.active=false;//不知道能不能出戰
    }
    public boolean addPokemon(BasePokemon p){
        if(PokemonNumber<backpack){
            pm[PokemonNumber]=p;
            PokemonNumber++;
            return true;
        }
        else{
            System.out.println("背包已滿。");
            return false;
        }
    }

    /* 顯示背包狀態 */
    public void showStats(){
        /*顯示寶可夢 */
        System.out.println();
        System.out.println(name + "'s pokemon pack has " + PokemonNumber + " pokemons.");
        for(int i=0;i<PokemonNumber;i++){
            System.out.printf((i+1) + ". ");
            pm[i].showStatus();
        }
        /*顯示背包item */
        System.out.println(name + "'s itempack has " + itemNumber + " items.");
        for(int i=0;i<itempack;i++){
            System.out.printf((i+1) + ". ");
            if(item[i]==null){
                System.out.println("empty");
                continue;
            }
            System.out.println(item[i]);
        }
        System.out.println();
    }
    public String showItems(int x){//回傳背包的一個item
        return item[x];
    }
    public void showItems(){
        for(int i=0;i<itempack;i++){
            System.out.println(i + ". " + item[i]);
        }
    }
    /*拿參數 */
    public int getPokemonNumber(){
        return PokemonNumber;
    }
    public String getName(){
        return this.name;
    }
    public BasePokemon getPokemon(int i){
        return pm[i];
    }
    public boolean getActive(){
        return this.active;
    }
    public void setActive(boolean x){
        this.active=x;
    }
    /* 回復 */
    public boolean restore(){
        active=true;//沒加到
        boolean effective = false;
        for(int i=0;i<PokemonNumber;i++){
            boolean x=pm[i].JOEY();
            if(x){
                effective=x;
            }
        }
        return effective;//如果狀態全滿則是false
    }

    /* 戰鬥時的換隻
     * 切換到x-1號的寶可夢。
     * 換隻成功會回傳true,失敗會回傳false
    */
    public boolean switchPokemon(int x){
        x--;//輸入1應該要對應到0
        if(x==usingPokemon){
            System.out.println("You are already using this pokemon. Please try again.");
            return false;
        }
        if(pm[x].active()){
            this.usingPokemon=x;
            System.out.println(this.name + " now change to " + pm[usingPokemon].getName() + " !");
            return true;
        }
        System.out.println(pm[x].getName() + " is unable to battle! Please try again.");
        return false;
    }

    /* 出戰寶可夢被打敗時使用的換隻(自動換最小的)
     * 如果沒有可戰鬥的寶可夢 則玩家的狀態(active)將被設為false。 
     * 結束時回傳決鬥者的狀態。
    */
    public boolean autoChangePokemon(){
        for(int i=0;i<PokemonNumber;i++){
            if(pm[i].active()){
                usingPokemon=i;
                System.out.println(this.name + " now change to " + pm[usingPokemon].getName() + " !");
                return this.active;
            }
        }
        this.active=false;
        return this.active;
    }

    /*戰鬥結束開始結算經驗值
     * 1.將對應的經驗值加進去
     * 2.該經驗值設為0
     * 3.顯示獲得多少經驗、幸運蛋結算在basepokemon中。
     */
    public void gainEXP(){
        for(int i=0;i<PokemonNumber;i++){
            pm[i].gainEXP(battleEXP[i]);
            battleEXP[i]=0;
        }
    }

    /* 非戰鬥狀態的換隻。
     * 沒有檢查輸入 因為在hw5中檢查了。
     * 新增一個臨時寶可夢ptemp,再把before跟after指過去。
     */
    public void switchPokemonNumber(int before, int after){
        before--;
        after--;
        BasePokemon ptmp= pm[before];
        pm[before]=pm[after];
        pm[after]=ptmp;
    }
    
    /* 捕捉時掉落道具
     * 因建構檔案時會用到 所以在這裡統一不放掉落物的提示
     * 1.用switch檢查是哪種道具
     * 2.放在最小的空編號裡面。
     * 3.boolean:如果成功就回傳true,失敗就回傳false
     *   用來檢查包包有沒有滿的，並且不接受null
     * 4.void:讀檔專屬的。null也會放。
     */
    public boolean getItem(String s){
        switch(s){
            case "quickclaw":
            case "protector":
            case "leftovers":
            case "kingsrock":
            case "luckyegg":
            int i=0;
            for(;i<itempack;i++){
                if(item[i].equals("")){
                    this.item[i]=s;
                    itemNumber++;
                    return true;
                }
            }
            default:
            return false;
        }
    }
    public void getItem(String s ,int x){//讀檔案時的建構數據
        this.item[x]=s;
        if(!s.equals("")){
            itemNumber++;
        }
    }

    /*裝備或替換道具
     * 為pnumber裝備inumber道具
     * 0.pNumber和iNUmber要合法:先減1
     * 1.先判斷是否有裝備道具
     * 2.如果有 就swap(同寶可夢換隻)
     * 3. 如果沒有就裝備。背包中拔掉的那格設為null
     */
    public void equipItem(int pNumber, int iNumber ){
        pNumber--;
        iNumber--;
        if(pNumber>this.PokemonNumber){
            System.out.println("Invalid pokemon number. Try again.");
            return;
        }
        else if(itempack<=iNumber || iNumber<0){
            System.out.println("Invalid item number. Try again.");
            return;
        }
        else if(this.item[iNumber].equals("")){
            System.out.println("Invalid item number. Try again.");
            return;
        }

        /* 開始替換
         * 在這裡顯示文字版的提示，在pm中顯示加乘效果
         * 裝備完後 將他拿掉
        */
        if(pm[pNumber].getEquippingItem().equals("")){//沒裝備 直接放給他
            System.out.println(this.name + " equips" + pm[pNumber].getName() + " with " + this.item[iNumber] + "...");
            pm[pNumber].equipItem(this.item[iNumber]);
            itemNumber--;
            this.item[iNumber]="";
        }
        else{//有裝備 用替換的
            System.out.println(this.name + " switch the equips for " + pm[pNumber].getName() + " with " + this.item[iNumber] + "...");
            String tmpItem=pm[pNumber].getEquippingItem();
            pm[pNumber].equipItem(this.item[iNumber]);
            this.item[iNumber]=tmpItem;
        }

        /* 替換成功顯示資訊 */
        this.showStats();
    }

    /* 拆下道具
     * 1.檢查背包滿了就不給拆
     * 2.放到最小編號的道具中
     * 3.呼叫寶可夢remove
     */
     public void removeItem(int pNumber){
        
        if(this.itemNumber==itempack){
            System.out.println("Your backpack is full. Try again.");
            return;
        }
        else if(pNumber>backpack||pNumber<=0){
            System.out.println("Invalid Pokemon number. Try again.");

        }
        /* 2. */
        pNumber--;
        System.out.println(this.name + " removes" +  pm[pNumber].getName() + "'s item...");

        String s= pm[pNumber].getEquippingItem();
        if(s.equals("")){
            System.out.println(pm[pNumber].getName() + " has not equipped any item!");
            return;
        }
        else{
            for(int i=0;i<itempack;i++){
                if(item[i].equals("")){
                    item[i]=s;
                    pm[pNumber].removeItem();
                    break;
                }
            }
        }

         /* 替換成功顯示資訊 */
         this.showStats();
     }
}
