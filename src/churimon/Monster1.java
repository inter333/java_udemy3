package churimon;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Monster1 {

    //フィールド
    String character = "(unknown)" ; //種族
    String trainer = "(wild)" ; //トレーナー
    String name = "(noname)" ; //なまえ
    int lv = 1 ; //レベル
    int hp = 80 ; //HP
    int atk = 15 ; //こうげき
    int def = 10 ; //ぼうぎょ
    int spd = 10 ; //すばやさ
    int hpMax = 80 ; //HP初期値
    String wazaNm = "たいあたり" ; //わざ(なまえ)
    String wazaDmgRate = "1.0" ; //わざ(ダメージ倍率)

    //定数
    final String WAZA_DMG_RATE_REGEXP = "^[0-9]+¥.[0-9]$"; //バリデーションで使う正規表現
    final String DMG_CORRECTION_1 = "1" ;
    final String DMG_CORRECTION_120 = "120" ;


    //toStringメソッド

    public String toString(){
        String status = "[ " +  name  + " lv" + lv  + " HP" + hp + "/" + hpMax
                + " ] (status) character:" + character + " trainer:" + trainer
                + " atk:" + atk + " def:" + def + " spd:" + spd + " wazaNm:"
                + wazaNm + " wazaDmgRate:" + wazaDmgRate ;
        return status;
    }

    //levelUpメソッド
    void levelUp(int up){
        lv += 1 * up ;
        hpMax += 30 * up ;
        atk += 5 * up ;
        def += 5 * up ;
        spd += 5 * up ;
        hp = hpMax ;
    }

    //setWazaメソッド
    void setWaza(String nm, String dmr){
        if(dmr.matches(WAZA_DMG_RATE_REGEXP)){
            wazaNm = nm ;
            wazaDmgRate = dmr ;
        }
        else {
            System.out.println("[ERROR]わざの設定に失敗しました");
        }
    }

    //getStatusメソッド
    String getStatus(){
        String status = "[ " +  name  + " lv" + lv  + " HP" + hp + "/" + hpMax + " ]" ;
        return status ;
    }

    //useWazaメソッド
    int useWaza(){
        BigDecimal bdAtk = new BigDecimal(atk) ;
        BigDecimal bdDmrt = new BigDecimal(wazaDmgRate) ;
        int dmg = ( bdAtk.multiply(bdDmrt) ).intValue() ;
        return dmg ;
    }

    //damagedメソッド
    int damaged(int passDmg){
        BigDecimal bdPassDmg = new BigDecimal(passDmg);
        BigDecimal bdDef = new BigDecimal(def);
        BigDecimal bdDmCr1 = new BigDecimal(DMG_CORRECTION_1);
        BigDecimal bdDmCr120 = new BigDecimal(DMG_CORRECTION_120);

        BigDecimal dmRate = bdDmCr1.divide(bdDmCr1.add(bdDef.divide(bdDmCr120,2, RoundingMode.DOWN)),2,RoundingMode.DOWN);

        int dmg = (bdPassDmg.multiply(dmRate)).intValue();

        if (hp>dmg){
            hp -= dmg ;
        }else{
            hp = 0 ;
        }

        return dmg;
    }

}
