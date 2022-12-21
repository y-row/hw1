import java.util.ArrayList;
public class orchard {
    private ArrayList<tree> trees = new ArrayList<tree>();
    private ArrayList<bear> bears = new ArrayList<bear>();
    private ArrayList<monkey> monkeys = new ArrayList<monkey>();
    public int hiveNumber=0;
    private int dogNumber=0;
    private int totalPomelo=0;
    private int totalBanana=0;
    
    /* 存取動物、收穫果實的數量 */
    public int getDogNumber(){
        return dogNumber;
    }
    public int getTotalPomelo(){
        return totalPomelo;
    }
    public int getTotalBanana(){
        return totalBanana;
    }

    /* 修改動物的數量(add指令)
     * step1.splitDemo中的strToInt會把非數字轉成-1,故判斷number是否大於0就可以排除掉[數量]錯誤的輸入。
     * step2.若數量正確,則新增動物。
     * step3.(只有hive)依新增的蜜蜂數量調整果園的offset
     */
    public void setHiveNumber(int number){
        if(number<=0){
            System.out.println("數量錯誤,請輸入大於0的整數。");
            return;
        }
        hiveNumber+=number;
        System.out.println("新增 " + number + " 隻蜜蜂到果園裡。");
    }
    public void setDogNumber(int number){
        if(number<=0){
            System.out.println("數量錯誤,請輸入大於0的整數。");
            return;
        }
        dogNumber+=number;
        System.out.println("新增 " + number + " 隻狗到果園裡。");
    }
    public void setBearNumber(int number){
        if(number<=0){
            System.out.println("數量錯誤,請輸入大於0的整數。");
            return;
        }
            
        for(int i=0;i<number;i++){
            bear b= new bear();
            bears.add(b);
        }
        System.out.println("新增 " + number + " 隻熊到果園裡。");
        
    }
    public void setMonkeyNumber(int number){
        if(number<=0){
            System.out.println("數量錯誤,請輸入大於0的整數。");
            return;
        }
        for(int i=0;i<number;i++){
            monkey m=new monkey();
            monkeys.add(m);
        }
        System.out.println("新增 " + number + " 隻猴子到果園裡。");
    }
    
    /*形成一個果園後,放入九個樹洞(1~9),不使用0 */
    public orchard(){    
        for(int i=0;i<10;i++)
        {
            trees.add(new tree());
        }
    }

    public void showOrchard(){
        /* show樹,每個樹都有16空格寬。 */
        int n=1;
        for(int i=0;i<3;i++)
        {
            /* 9個空格 */ 
            System.out.printf( n     + ":              ");
            System.out.printf((n+1) + ":              ");
            System.out.printf((n+2) + ":              ");
            System.out.println("");
            /* 空白要16個空格,中文是2字元 */
            for(int j=0;j<3;j++){
                if(trees.get(j+n).name.equals("empty")){
                    System.out.printf("無果樹          ");
                    continue;
                }
                trees.get(j+n).showName();
            }
            System.out.println("");
            for(int j=0;j<3;j++){
                if(trees.get(j+n).name.equals("empty")){
                    System.out.printf("                ");
                    continue;
                }
                trees.get(j+n).showFruit();
            }
            System.out.println("");
            for(int j=0;j<3;j++){
                if(trees.get(j+n).name.equals("empty")){
                    System.out.printf("                ");
                    continue;
                }
                trees.get(j+n).showLife();
            }
            System.out.println("\n");
            n+=3;
        }
        System.out.println("共已收穫香蕉 " + totalBanana + " 根,柚子 " + totalPomelo + " 個。\n");

        /* show動物 */
        System.out.println("果園中的動物有:");
        System.out.println(bears.size() + " 隻熊。");
        System.out.println(hiveNumber + " 隻蜜蜂。");
        System.out.println(dogNumber + " 隻狗。");
        System.out.println(monkeys.size() + " 隻猴子。");

    }

    /* 新增一棵樹
     * step1.判斷是否為系統植物的名稱,若不是則輸出錯誤。
     * step2.判斷編號是否為1~9,若不是則輸出錯誤。
     * step3.判斷編號上樹名是否為empty。若不是則代表已經在此位置種下了一棵樹。
     * step4.根據輸入的名稱呼叫tree.java中對應的設定函數。
     * step5.輸出新增成功的訊息。
     */
    public void addTree(int number, String name){
        if(!(name.equals("pomelo")||name.equals("banana"))){
            System.out.println("名字錯誤,請輸入正確的動植物名稱。");//進到這格有可能是動物或植物名稱的拼字錯誤
        }
        else if(number>9||number<1)
        {
            System.out.println("編號錯誤,請輸入1~9的數字。");
        }
        else if(!(trees.get(number).name.equals("empty"))){
            System.out.println("在編號 " + number + "處已有一棵樹,新增失敗。");
        }
        else{
            switch(name){
                case "pomelo":
                    trees.get(number).pomeloTree();
                    System.out.println("在編號 " + number + " 處種下一棵柚子樹。");
                    break;
                case"banana":
                    trees.get(number).bananaTree();
                    System.out.println("在編號 " + number + " 處種下一棵香蕉樹。");
                    break;
            }
        }
    }
    
    /*時間推移
     * step1.呼叫在bears中的所有熊,執行next指令。
     * step2.刪除壽命為0的熊。
     * step3.呼叫trees中名字非empty的樹,執行結果動作。
     * step4.依照dogNumber的值刪除monkeys中的monkey。由於add()會加在最後一格,所以刪除第0格的猴子。
     * step5.呼叫monkeys中所有的猴子,執行next指令。
     * step6.刪除壽命為0的猴子。
     */
    public void next(){
        /* 熊先動*/
        for(int i=0;i<bears.size();i++){
                bears.get(i).next(this,trees);
            }
            /* 檢查壽命 */
        for(int i=0;i<bears.size();i++){
            if(bears.get(i).life==0){
                bears.remove(i);
                i--;
            }
        }
        /* 樹木生長*/
        for(int i=1;i<=9;i++){
            if(!(trees.get(i).name.equals("empty"))){
                trees.get(i).grow(this);
            }
        }
        /* 狗趕走猴子*/
        for(int i=0;i<this.dogNumber*2;i++){
            if(monkeys.size()==0){
                break;
            }
            monkeys.remove(0);
        }
        /* 猴子偷香蕉*/
        for(int i=0;i<monkeys.size();i++){
                monkeys.get(i).next(trees);
        }
        /* 檢查壽命 */
        for(int i=0;i<monkeys.size();i++){
            if(monkeys.get(i).life==0){
                monkeys.remove(i);
                i--;
            }
        }
    }

    /* prune */
    public void prune(){
        boolean x=false;
        for(int i=1;i<=9;i++){
            if(!(trees.get(i).name.equals("empty"))){
                x=true;
                trees.get(i).nourish();
            }
        }
        if(!x){ //沒有果樹
            System.out.println("尚無果樹,修剪失敗。");
            return;
        }
        System.out.println("修剪完畢。");
    }

    /* harvest
     * step1.新增兩個tmp變數來存放此次收割的果實數量。
     * step2.判斷果樹名稱是否為empty,若不是則呼叫tree的harvest指令,並以臨時變樹fruit來代表此棵樹的收割數量。
     * step3.判斷收割的果實是香蕉還是柚子,放入正確的tmp變數中。
     * step4.輸出此次收穫結果,將對應的數量加入total中。
    */
    public void harvest(){
        int bananaGain=0;
        int pomeloGain=0;
        for(int i=1;i<=9;i++){
            if(!(trees.get(i).name.equals("empty"))){
                int fruit=trees.get(i).harvest();
                if((trees.get(i).name.equals("香蕉樹"))){
                    bananaGain+=fruit;
                }
                else{
                    pomeloGain+=fruit;
                }
            }
        }
        if(pomeloGain!=0&&bananaGain!=0){
            System.out.println("收穫結束。");
            System.out.println("共收穫香蕉 " + bananaGain + "根");
            System.out.println("共收穫柚子 " + pomeloGain + "個");
            totalBanana+=bananaGain;
            totalPomelo+=pomeloGain;
        }
        else{
            System.out.println("收穫結束,無收獲任何水果。");
        }
    }

    /* 額外功能:只收獲一棵樹 */
    public void harvest(int number){
        int gain=0;
        if(!(trees.get(number).name.equals("empty"))){
            int fruit=trees.get(number).harvest();
            if((trees.get(number).name.equals("香蕉樹"))){
                gain+=fruit;
                System.out.println("共收穫香蕉 " + gain + "根。");
            }
            else{
                gain+=fruit;
                System.out.println("共收穫柚子 " + gain + "個。");
            }
        }
        else{
            System.out.println("編號 " + number + " 處無果樹,收獲失敗。");
        }
    }

    /* 額外功能:砍樹*/
    public void cut(int number){
        if((trees.get(number).name.equals("empty"))){
            System.out.println("編號 " + number + "處無果樹,砍樹失敗。");
        }
        else{
            System.out.println("砍下編號 " + number + " 處的 " + trees.get(number).name + " 。");
            trees.get(number).cut();  
        }
    }

    /* 額外功能:只修剪一棵樹 */
    public int prune(int p){
        if(!(trees.get(p).name.equals("empty"))){
            trees.get(p).nourish();
            System.out.println("修剪 " + p + " 處的 " + trees.get(p).name + " 成功。");
            return 1;
        }
        else{
            return 0;//empty
        }
    }

    /* 額外功能:移除動物 */
    public void removeBears(int number){
        if(number<=0){
            System.out.println("請輸入正確的數量。");
        }
        else if(number>bears.size()){
            System.out.println("目前果園只有 " + bears.size() + " 隻熊,請重新輸入。");
        }
        else{
            for(int i=0;i<number;i++){
                bears.remove(0);
            }
            System.out.println("成功移除 " + number + " 隻熊。");
        }
    }
    public void removeMonkeys(int number){
        if(number<=0){
            System.out.println("請輸入正確的數量。");
        }
        else if(number>monkeys.size()){
            System.out.println("目前果園只有 " + monkeys.size() + " 隻猴子,請重新輸入。");
        }
        else{
            for(int i=0;i<number;i++){
                monkeys.remove(0);
            }
            System.out.println("成功移除 " + number + " 隻猴子。");
        }
    }
    public void removeDogs(int number){
        if(number>this.dogNumber){
            System.out.println("目前果園只有 " + this.dogNumber + " 隻狗,請重新輸入。");
        }
        else{
            this.dogNumber-=number;
            System.out.println("成功移除 " + number + " 隻狗。");
        }
    }
    public void removehives(int number){
        if(number>this.hiveNumber){
            System.out.println("目前果園只有 " + this.hiveNumber + " 隻蜜蜂,請重新輸入。");
        }
        else{
            this.hiveNumber-=number;
            System.out.println("成功移除 " + number + " 隻蜜蜂。");
        }
    }

   /* 額外功能:help指令 */
   public static void help(){
        System.out.println("本系統支援的果樹如下:");
        System.out.println("果樹名稱 指令名稱 每日結果數量 果實上限   壽命");
        System.out.println("    柚子  pomelo           2       20   30天");
        System.out.println("    香蕉  banana           5      100   60天");
        System.out.println("note:所有的果樹只能種在編號1~9的位置。");
        System.out.println("\n本系統支援的動物如下:");
        System.out.println("動物名稱     指令名稱     在果園停留天數                                  每天的行為");
        System.out.println("    猴子      monkey               3天                                     偷3根香蕉");
        System.out.println("      熊        bear               1天      吃掉1個蜂巢(有蜂巢);破壞一棵果樹(無蜂巢)");
        System.out.println("    蜂巢        hive  永久或直到被熊吃掉                            使每日結果數量+2");
        System.out.println("      狗         dog               永久                                  嚇跑2隻猴子");
        System.out.println("\n本系統支援的指令如下:");
        System.out.println("add[樹種][位置]            新增一棵樹到指定位置。");
        System.out.println("add[動物][數量]              新增指定數量的動物。");
        System.out.println("show                         顯示果園的發展情形。");
        System.out.println("cut[位置]                      砍掉指定位置的樹。");
        System.out.println("remove[動物][數量]         移除果園中的一個動物。");
        System.out.println("next[天數]         將時間依照給定的天數(>0)推移。");
        System.out.println("harvest                    收割果園中的所有果實。");
        System.out.println("harvest[位置]        收割指定位置果樹的所有果實。");
        System.out.println("prune         修剪果園中的果樹,使果樹的壽命+5天。");
        System.out.println("prune[位置]                  修剪指定位置的果樹。");
        System.out.println("exit                                   結束系統。");
   } 
}
