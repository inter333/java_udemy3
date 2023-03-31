package churimon;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Monster2 {

    //フィールド
    String character  ; //種族
    String trainer  ; //トレーナー
    String name  ; //なまえ
    int lv  ; //レベル
    int hp  ; //HP
    int atk  ; //こうげき
    int def  ; //ぼうぎょ
    int spd  ; //すばやさ
    int hpMax  ; //HP初期値
    String wazaNm  ; //わざ(なまえ)
    String wazaDmgRate  ; //わざ(ダメージ倍率)

    //定数
    final String WAZA_DMG_RATE_REGEXP = "^[0-9]+¥.[0-9]$"; //バリデーションで使う正規表現
    final String DMG_CORRECTION_1 = "1" ;
    final String DMG_CORRECTION_120 = "120" ;

    //コンストラクタ
    Monster2(){
        this.character   = "(unknown)"  ; //初期化種族
        this.trainer     = "(wild)"     ; //初期化トレーナー
        this.name        = "(noname)"   ; //初期化なまえ
        this.lv          = 1            ; //初期化レベル
        this.hp          = 80           ; //初期化HP
        this.atk         = 15           ; //初期化こうげき
        this.def         = 10           ; //初期化ぼうぎょ
        this.spd         = 10           ; //初期化すばやさ
        this.hpMax       = 80           ; //初期化HP初期値
        this.wazaNm      = "たいあたり" ; //初期化わざ（なまえ）
        this.wazaDmgRate = "1.0"        ; //初期化わざ（ダメージ）
    }

    Monster2(String tr, String nm){
        this();                          //コンストラクタ1呼び出し
        this.trainer    = tr   ;         //初期化トレーナー
        this.name       = nm   ;         //初期化なまえ
    }

    Monster2(String tr, String nm, int lev){
        this(tr, nm);                //コンストラクタ2呼び出し

        if(lev > 1){
            levelUp(lev - 1);
        }
    }


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
