package pokemons;
public class Vaporeon extends BasePokemon{
    {
        name="Vaporeon";
        type="Water";
        skill1="watergun";
        skill2="hydropump";
        drop="leftovers";
        s1Atk=40;
        s2Atk=110;
        HPstep=40;
        atkstep=3;
        speedstep=2;
        baseAtk=13;
    }
    public Vaporeon(int lv,int hp,int s1, int s2, int exp, String s){
        super(lv, lv*35+70, lv*4+28, lv*3+2,exp,s);
        HP=hp;
        S1=s1;
        S2=s2;
    }
    public Vaporeon(int lv){
        super(lv, lv*35+70, lv*4+28, lv*3+2,0,"");
        this.HP=maxHP;
        this.S1=maxS1;
        this.S2=maxS2;
    }
    protected int Attack(BasePokemon p1,int damage){
        double power=0;
        switch(p1.getType()){
            case "Grass":
                power=0.8;
                break;
            case "Fire":
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
