public class tree {
    public  String name ;         //樹的名字
    public  int    life ;         //壽命
    private int    activeLife;    //會結果的壽命上限
    public  int    fruitNumber;   //果實數量
    private int    maxFruitNumber;//樹上可持有的果實數量
    public int     growStep;      //一天生幾顆

    /* 生成樹洞:empty */
    private void emptyTree(){
        this.name="empty";
        this.life=-1;
        this.activeLife=-1;
        this.fruitNumber=-1;
        this.maxFruitNumber=-1;
        this.growStep=0;
    
    }

    /* 生成柚子樹:pomeloTree */
    public void pomeloTree(){
        this.name="柚子樹";
        this.life=0;
        this.activeLife=30;
        this.fruitNumber=0;
        this.maxFruitNumber=20;
        this.growStep=2;
    }

    /* 生成香蕉樹:bananaTree */
    public void bananaTree(){
        this.name="香蕉樹";
        this.life=0;
        this.activeLife=60;
        this.fruitNumber=0;
        this.maxFruitNumber=100;
        this.growStep=5;
    }

    /* 生成空的樹:初始化時或是砍樹時 */
    public tree(){
        emptyTree();
    }
    public void cut()
    {
        emptyTree();
    } 

    /* 時間推移:樹木生長 */
    /* step1:判斷壽命是否超過上限?
     * step2:結果
     * step3:果實數量是否超過,超過的話將數量設為max
     */
    public void grow(orchard o){
        this.life++;
        if(this.activeLife>=this.life&&this.maxFruitNumber>fruitNumber){
            this.fruitNumber+=(this.growStep+2*o.hiveNumber);
            if(this.fruitNumber>this.maxFruitNumber){
                this.fruitNumber=this.maxFruitNumber;
            }
        }
    }

    /* prune */
    public void nourish(){
        if(!this.name.equals("empty")){
            this.activeLife+=5;
        }
    }

    /* harvest */
    public int harvest(){
        int fruit=fruitNumber;
        fruitNumber=0;
        return fruit;
    }
    /* show 每個block是16字元 */
    public void showName(){
        System.out.printf("%s          ",this.name);
    }
    public void showFruit(){
        System.out.printf("有%-3d個果實     ",this.fruitNumber);
    }
    public void showLife(){
        System.out.printf("壽命為%-3d歲     ",this.life);
    }
}
