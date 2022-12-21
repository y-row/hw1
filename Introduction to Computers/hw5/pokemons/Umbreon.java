package pokemons;
public class Umbreon extends BasePokemon {
    {
        name="Umbreon";
        type="Dark";
        skill1="snarl";
        skill2="darkpulse";
        drop="luckyegg";
        s1Atk=40;
        s2Atk=110;
        HPstep=35;
        atkstep=4;
        speedstep=2;
        baseAtk=12;
    }
    public Umbreon(int lv,int hp,int s1, int s2, int exp, String s){
        super(lv, lv*35+55, 30+lv*4, 3+lv*3, exp, s);
        this.S1=s1;
        this.S2=s2;
        this.HP=hp;
    }
    public Umbreon(int lv){
        super(lv, lv*35+55, 30+lv*4, 3+lv*3, 0, "");
        this.S1=this.maxS1;
        this.S2=this.maxS2;
        this.HP=this.maxHP;
    }
    protected int Attack(BasePokemon p1,int damage){
        double power=0;
        switch(p1.getType()){
            case"Psychic":
                power=1.2;
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
