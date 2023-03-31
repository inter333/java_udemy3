package churimon;

class Hitokake extends Monster3 {

    Hitokake(){
        super.setCharacter("ヒトカケ");
    }
    Hitokake(String tr, String nm){
        super(tr,nm);
        super.setCharacter("ヒトカケ");
    }
    //コンストラクタ3[引数1:トレーナー, 引数2:なまえ, 引数3:レベル]
    Hitokake(String tr, String nm, int lev){
        super(tr,nm,lev);
        super.setCharacter("ヒトカケ");
    }

    public void levelUp(int up){
        setLv(getLv() + 1 * up)  ;
        setHpMax(getHpMax() + 29* up)  ;
        setAtk(getAtk() + 8 * up)  ;
        setDef(getDef() + 5 * up)  ;
        setSpd(getSpd() + 9 * up)  ;
        setHp(getHpMax())             ;
    }
}