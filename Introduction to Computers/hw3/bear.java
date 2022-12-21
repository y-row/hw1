import java.util.ArrayList;

public class bear {
    public int life;
    public bear(){
        this.life=1;
    }
 
    /* 時間推移
     * step1:看看果園中有沒有蜜蜂(check o.hive),如有則使hiveNumber-1。
     * step2:若沒有蜜蜂,則檢查果園中有沒有果樹。如有則使用cut指令將樹的offset設為empty。
     */
    public void next(orchard o,ArrayList<tree>trees){
        /* 找蜜蜂*/
        if(o.hiveNumber!=0){
            o.hiveNumber--;
        }
        else{
            for(int i=1;i<=9;i++){
                if((trees.get(i).name.equals("empty"))){
                    continue;
                }
                trees.get(i).cut();
                break;
            }
        }
        this.life--;
    }
}
