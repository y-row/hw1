package pokemons;
public class Flareon extends BasePokemon{
    {
        type="Fire";
        name="Flareon";
        skill1="ember";
        skill2="flareblitz";
        drop="protector";
        s1Atk=40;
        s2Atk=120;
        HPstep=25;
        atkstep=5;
        speedstep=1;
        baseAtk=10;
    }
    public Flareon(int lv,int hp,int s1,int s2, int exp, String s){//讀檔的
        super(lv, lv*25+75, lv*5+30, lv*1+7, exp,s);
        this.HP=hp;
        S1=s1;
        S2=s2;
    }
    public Flareon(int lv){
        super(lv, lv*25+75, lv*5+30, lv*1+7, 0, "");
        this.HP=maxHP;
        this.S1=maxS1;
        this.S2=maxS2;
    }
    protected int Attack(BasePokemon p1,int damage){
        double power=0;
        switch(p1.getType()){
            case "Grass":
                power=1.2;
                break;
            case"Water":
                power=0.8;
                break;
            default:
                power=1;
                break;
        }
        int x=(int)Math.round((Atk+damage)*power);
        p1.damage(x);
        return x;
    }
}
