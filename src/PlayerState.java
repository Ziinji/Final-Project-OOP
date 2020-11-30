public abstract class PlayerState {
    Character character;

    PlayerState(Character character){
        this.character = character;
    }

    public abstract String idle();
    public abstract String moving();

    public abstract String comboX();
    public abstract String comboXX();
    public abstract String comboXXX();
    public abstract String comboXXZ();

    public abstract String comboZ();
    public abstract String comboZZ();
    public abstract String comboZZZ();
    public abstract String comboZZZZ();
    public abstract String comboZZX();

}
