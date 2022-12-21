package pokemons;
public class Espeon extends BasePokemon {
    {
        name="Espeon";
        type="Psychic";
        skill1="confusion";
        skill2="psybeam";
        drop="kingsrock";
        s1Atk=50;
        s2Atk=120;
        HPstep=35;
        atkstep=3;
        speedstep=2;
        baseAtk=10;
    }
    public Espeon(int lv,int hp,int s1, int s2, int exp, String s){
        super(lv, lv*35+75, lv*3+29, lv*2+5,exp,s);
        HP=hp;
        S1=s1;
        S2=s2;
    }
    public Espeon(int lv){
        super(lv, lv*35+75, lv*3+29, lv*2+5,0,"");
        this.HP=maxHP;
        this.S1=maxS1;
        this.S2=maxS2;
    }
    protected int Attack(BasePokemon p1,int damage){
        double power=0;
        switch(p1.getType()){
            case"Dark":
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
