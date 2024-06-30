 class Character {
    String name;
    int health;
    int physicalDamage;
    int elementalDamage;
    int physicalDefence;
    int resilience;

    public Character(String name, int health, int physicalDamage,int elementalDamage, int physicalDefence, int resilience) {
        this.name = name;
        this.health=health;
        this.physicalDamage = physicalDamage;
        this.elementalDamage = elementalDamage;
        this.physicalDefence=physicalDefence;
        this.resilience=resilience;
    }


    @Override
    public String toString() {
        return name +
                ", health=" + health +
                ", physicalDamage=" + physicalDamage +
                ", elementalDamage=" + elementalDamage +
                ", physicalDefence=" + physicalDefence +
                ", resilience=" + resilience;
    }
}
