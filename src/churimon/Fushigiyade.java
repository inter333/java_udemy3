package churimon;

class Fushigiyade extends Monster3 {
    Fushigiyade(){
        super.setCharacter("フシギヤデ");
    }

    Fushigiyade(String tr, String nm){
        super(tr, nm);
        super.setCharacter("フシギヤデ");
    }

    Fushigiyade(String tr, String nm, int lev){
        super(tr,nm,lev);
        super.setCharacter("フシギヤデ");
    }

    public void levelUp(int up){
        setLv(getLv() + 1 * up)  ;
        setHpMax(getHpMax() + 31 * up)  ;
        setAtk(getAtk() + 6 * up)  ;
        setDef(getDef() + 7 * up)  ;
        setSpd(getSpd() + 8 * up)  ;
        setHp(getHpMax())             ;
    }

}
