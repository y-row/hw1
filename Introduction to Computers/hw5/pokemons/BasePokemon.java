package pokemons;
public class  BasePokemon {
	protected int LV, HP, Speed, Atk,S1,S2;//hp,s1,s2不設定,交給subclass設定 atk:基礎攻擊力
	protected int maxHP;
	protected int HPstep,atkstep,speedstep;
	protected int EXP=0;
	protected String type,name;
	protected final int maxS1=5,maxS2=2;
	protected String skill1,skill2;
	protected int baseAtk=0,s1Atk,s2Atk; //普攻攻擊力和技能攻擊力
	protected String drop=""; //捕捉掉落物
	protected String equip=""; //裝備中的物件
	public BasePokemon(int lv, int maxhp, int atk, int speed,int exp, String s){
		LV = lv;
		if(exp==0){//自動建立時幫他加上經驗值
			if(lv==2){
				EXP=200;
			}
			else if(lv==3){
				EXP=800;
			}
		}
		else{
			this.EXP=exp;
		}
		
		maxHP = maxhp;
		Speed = speed;
		
		this.equip=s;
		switch(s){//在這裡進行提升(讀檔的)
			case "": //空的item
				break;
			case "quickclaw":
				this.Speed+=3;
				break;
			case "protector":
				this.maxHP+=50;
				break;
			case "leftovers":
				break;
			case "kingsrock":
				this.Atk+=5;
			case "luckyegg":
				break;
		}
		Atk= atk;
	}
	/* 獲取參數 */
	public String getName(){
		return name;
	}
	public String getDrop(){
		return this.drop;
	}
	public String getType(){
		return type;
	}
	public String getSkill1(){
		return this.skill1;
	}
	public String getSkill2(){
		return this.skill2;
	}
	public int getLV(){
		return LV;
	}
	public int getHP(){
		return HP;
	}
	public int getSpeed(){
		return this.Speed;
	}
	public String getEquippingItem(){
		return this.equip;
	}
	public boolean active(){//是否可出戰
		if(HP==0){
			return false;
		}
		else{
			return true;
		}
	}
	public static void helpAdd(Boolean newPokemon){//要改!!
		if(newPokemon){//顯示新增格式
			System.out.println("Format: [playername] [first pokemon name]");
			System.out.println("note that there is always a space between your input.");
			System.out.println("A legal pokemon name starts with a capital. ");
			System.out.println("Example input:\nSatoshi Vaporeon ");
		}
	}
	
	/* 受傷*/
	public int skill1(BasePokemon p1){//第一招
        if(S1==0){
            System.out.println("No enough PP, please try again.");
            return -1;
        }
        else{
            int x=Attack(p1, s1Atk);
            System.out.println(this.name + " attacks " + p1.name + " by " +skill1 + " !");
		    System.out.println(p1.name + " takes " + x + " damage!");
            S1--;
            return x;
        }
    }
    public int skill2(BasePokemon p1){//第二招
        if(S2==0){
            System.out.println("No enough PP, please try again.");
            return -1;
        }
        else{
            int x=Attack(p1, s2Atk);
            System.out.println(this.name + " attacks " + p1.name + " by " +skill2 + " !");
		    System.out.println(p1.name + " takes " + x + " damage!");
            S2--;
            return x;
        }
    }
	protected int Attack(BasePokemon p1,int damage){//need to be override
		return -1;
	};
	public int normalAttack(BasePokemon p1){//普攻大家都一樣
        System.out.println(this.name + " Attacks " + p1.name+ " !");
		int x=Attack(p1, baseAtk);
		System.out.println(p1.name + " takes " + x + " damage!");
		return x;
    }
	public void damage(int x){
		this.HP-=x;
		if(HP<0){
			HP=0;
		}
	}

	/* 獲得經驗值&升級 */
	public void gainEXP(int exp){
		if(this.equip.equals("luckyegg")){//幸運蛋裝備中
			exp+=50;
			System.out.println("luckyegg activated!");
		}
		System.out.println(this.name + " gain " + exp + " EXP!");
		this.EXP+=exp;
		if(EXP>=200&&LV==1){
			levelUp();
		}
		else if(EXP>=800){
			EXP=800;
			if(this.LV<3){
				levelUp();
			}
		}
	}
	public void levelUp(){
		this.maxHP+=HPstep;
		this.Atk+=atkstep;
		this.Speed+=speedstep;
		this.LV++;
		System.out.println("Level up! " + this.getName() + " grew to LV." + this.LV + "!");
	}

	/*回復:喬伊小姐或吃藥 */
	public void elixir(){
		System.out.println(this.name + " use max elixir!");
		this.S1=this.maxS1;
		this.S2=this.maxS2;
	}
	public void restore(){
		System.out.println(this.name + " use super potion!");
		HP+=50;
		if(HP>=maxHP){
			HP=maxHP;
		}
		System.out.println(this.name + " now have " + this.HP + " HP!");
	}
	public boolean JOEY(){
		if(HP==maxHP&&S1==maxS1&&S2==maxS2){
			return false;
		}
		HP=maxHP;
		S1=maxS1;
		S2=maxS2;
		return true;
	}
	public void leftovers(){//專屬leftovers
		HP+=20;
		if(HP>=maxHP){
			HP=maxHP;
		}
		System.out.println("Leftovers effect! restore " + this.name + " 20HP!");
		System.out.println(this.name + " now have " + this.HP + " HP!");
	}
	
	/* 顯示數據*/
	public void showStatus(){
	     System.out.printf(name + " LV:" + LV + "/3 HP:" + HP + "/" + this.maxHP + " atk:" + this.Atk + " speed:" + Speed + " EXP:"+ this.EXP);
		 System.out.printf(" equip:");
		 if(this.equip.equals("")){
			System.out.println(" none");
		 }
		 else{
			System.out.println(this.equip);
		 }
		 
		 System.out.println( "   " + skill1 + ":" + S1 + "/" + maxS1);
		 System.out.println( "   " + skill2 + ":" + S2 + "/" + maxS2 );
		 System.out.println();

	}	
	public String recordStatus(){//結束時的紀錄
		return name + " " + (LV) + " " + (HP) +" " + (S1) + " " + (S2) + " " + (EXP) + " " + equip ;
	}
	public void battleStatus(){
		System.out.printf(this.name + " LV" + this.LV + " HP:" + HP + "/" + maxHP + " ");
		System.out.println(skill1 + ":" + S1 + "/" + maxS1 + " " + skill2 + ":" + S2 + "/" + maxS2);
	}

	/* 裝備or移除道具
	 * 1.除了幸運蛋和吃剩的東西，都在這裡就發揮效果
	 * 2.會覆蓋掉原本的道具所以在player中要先存
	 * 3.拆道具時顯示失去的加乘效果，然後把equip指向NULL
	 */
	public  void equipItem(String s){
		switch(s){
			case "quickclaw":
				this.equip=s;
				this.Speed+=3;
				System.out.println("Now " + this.name + "'s speed have increased by 3!");
				break;
            case "protector":
				this.equip=s;
				this.maxHP+=50;
				System.out.println("Now " + this.name + "'s max HP have increased by 50!");
				break;
            case "leftovers":
				this.equip=s;
				System.out.println("Now " + this.name + "'s HP will restore by 20 at the end of turn!");
				break;
            case "kingsrock":
				this.equip=s;
				this.Atk+=5; //atk是基礎攻擊力
				System.out.println("Now " + this.name + "'s atk have increased by 5!");
				break;
            case "luckyegg":
				this.equip=s;
				System.out.println("Now " + this.name + " will gain extra EXPs by 50 for every single battle!");
				break;
		}
	}
	public void removeItem(){
		switch(this.equip){
			case "quickclaw":
				this.equip="";
				this.Speed-=3;
				System.out.println("Now " + this.name + "'s speed have decreased by 3!");
				break;
            case "protector":
				this.equip="";
				this.maxHP-=50;
				if(this.HP>maxHP){
					this.HP=this.maxHP;
				}
				System.out.println("Now " + this.name + "'s max HP have decreased by 50!");
				break;
            case "leftovers":
				this.equip="";
				System.out.println("Now " + this.name + "'s HP will not restore by 20 at the end of turn!");
				break;
            case "kingsrock":
				this.equip="";
				this.Atk-=5; //atk是基礎攻擊力
				System.out.println("Now " + this.name + "'s atk have decreased by 5!");
				break;
            case "luckyegg":
				this.equip="";
				System.out.println("Now " + this.name + " will not gain extra EXPs by 50 for every single battle!");
				break;
		}
	}
}
