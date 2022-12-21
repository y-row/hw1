import java.util.ArrayList;

public class monkey {
    public int life;
    public monkey(){
        this.life=3;
    }

    /* 時間推移
     * step1:看看果園中有沒有香蕉樹。
     * step2:若最小編號的香蕉樹上超過三根香蕉,則偷走三根;若不足三根,則偷至0根後搜尋下一棵樹。
     * step3:行動完畢,壽命減少一歲。
     */
    public void next(ArrayList<tree> trees){
        int s=3;
        for(int i=1;i<=9&&s>0;i++){
            if(trees.get(i).name=="香蕉樹"){
                if(trees.get(i).fruitNumber>=s){
                    trees.get(i).fruitNumber-=s;
                    break;
                }
                else{ //繼續偷
                    s-=trees.get(i).fruitNumber;
                    trees.get(i).fruitNumber=0;
                }
            }
        }
        life--;
    }
    
}
