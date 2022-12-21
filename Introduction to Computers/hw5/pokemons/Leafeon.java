package pokemons;
public class Leafeon extends BasePokemon {
    {
        name="Leafeon";
        type="Grass";
        skill1="razorleaf";
        skill2="leafblade";
        drop="quickclaw";
        s1Atk=40;
        s2Atk=90;
        HPstep=40;
        atkstep=3;
        speedstep=2;
        baseAtk=12;
    }
    public Leafeon(int lv,int hp,int s1, int s2, int exp, String s){
        super(lv, lv*40+80, lv*3+27, lv*2+5,exp,s);
        this.HP=hp;
        this.S1=s1;
        this.S2=s2;

    }
    public Leafeon(int lv){
        super(lv, lv*40+80, lv*3+27, lv*2+5, 0, "");
        this.HP=maxHP;
        this.S1=maxS1;
        this.S2=maxS2;
    }
    protected int Attack(BasePokemon p1,int damage){
        double power=0;
        switch(p1.getType()){
            case "Fire":
                power=0.8;
                break;
            case"Water":
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
