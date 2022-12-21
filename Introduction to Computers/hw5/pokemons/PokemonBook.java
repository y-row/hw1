package pokemons;
import IOhelper.*;
import java.util.ArrayList;
public class PokemonBook {
    protected  ArrayList<String> pokeList=new ArrayList<String>();
    public  PokemonBook(){
        pokeList.add("Vaporeon");
        pokeList.add("Flareon");
        pokeList.add("Espeon");
        pokeList.add("Umbreon");
        pokeList.add("Leafeon");
            
    }

    public void call(String s){
        switch(s){
            case "Vaporeon":
                Vaporeon();
                break;
            case "Flareon":
                Flareon();
                break;
            case "Espeon":
                Espeon();
                break;
            case "Umbreon":
                Umbreon();
                break;
            case "Leafeon":
                Leafeon();
                break;
        }
    }
    

    public  void help(){
        
        System.out.println("type number to read the Pokedex.");
        System.out.println("0: ALL of them.");
        System.out.println("134: Vaporeon.");
        System.out.println("136: Flareon.");
        System.out.println("196: Espeon.");
        System.out.println("197: Umbreon.");
        System.out.println("470: Leafeon.");
        System.out.println("-1:  exit.");
        System.out.println();
        int x=ConsoleIn.readLineInt();
        switch(x){
            case 0:
                all();
                break;
            case 134:
                Vaporeon();
                break;
            case 136:
                Flareon();
                break;
            case 196:
                Espeon();
                break;
            case 197:
                Umbreon();
                break;
            case 470:
                Leafeon();
                break;
        }


    }
    public  void all(){
        Espeon();
        Umbreon();
        Vaporeon();
        Flareon();
        Leafeon();

    }
    
    public  void allName(){
       for(int i=0;i<pokeList.size();i++){
        System.out.println(pokeList.get(i));
       }
    }
    public  void Vaporeon(){
        System.out.println("#134水伊布 水屬性神奇寶貝。");
        System.out.println("當水伊布開始微微顫動牠全身上下的鰭，就表示幾個小時之後要下雨了。");
        System.out.println("HP:70 atk:28 speed:2");
        System.out.println("技能");
        System.out.println("1.水槍(watergun): 威力40 pp5");
        System.out.println("2.水炮(hydropump): 威力110 pp2");
        System.out.println();
    }

    public  void Flareon(){
        System.out.println("#136火伊布 火屬性神奇寶貝。");
        System.out.println("囊狀的內臟中燃燒著火焰。能藉吸入空氣強化其火力，溫度甚至可達１７００度之高。");
        System.out.println("HP:75 atk:30 speed:7");
        System.out.println("技能");
        System.out.println("1.火花(ember): 威力40 pp5");
        System.out.println("2.閃焰衝鋒(flareblitz): 威力120 pp2");
        System.out.println();
    }

    public  void Espeon(){
        System.out.println("#196太陽伊布 超能力神奇寶貝。");
        System.out.println("當沐浴在日光下時，額上紅珠會發亮，令牠活力大增。");
        System.out.println("HP:75 atk:29 speed:5");
        System.out.println("技能");
        System.out.println("1.念力(confusion):  威力50  pp5");
        System.out.println("2.幻象光線(psybeam): 威力120 pp2");
        System.out.println();
    }

    public  void Umbreon(){
        System.out.println("#197月亮伊布 惡屬性神奇寶貝。");
        System.out.println("他們活躍於月光灑落大地的夜半時分。大大的眼睛即使是在黑暗之中也能牢牢捕捉獵物的身影。");
        System.out.println("HP:55 atk:30 speed:3");
        System.out.println("技能");
        System.out.println("1.大聲咆哮(snarl): 威力40 pp5");
        System.out.println("2.惡之波動(darkpulse): 威力110 pp2");
        System.out.println();
    }
    
    public  void Leafeon(){
        System.out.println("#470葉伊布 草屬性神奇寶貝。");
        System.out.println("從體毛中可檢出近似植物的細胞。堅硬的尾部能將大樹一刀兩斷，鋒利程度更勝名刀一籌。");
        System.out.println("HP:80 atk:27 speed:5");
        System.out.println("技能");
        System.out.println("1.飛葉快刀(razorleaf):  威力40  pp5");
        System.out.println("2.刀葉(leafblade): 威力90 pp2");
        System.out.println();
    }
}
