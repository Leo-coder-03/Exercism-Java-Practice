class Fighter {

    boolean isVulnerable() {
        return true;
    }

    int getDamagePoints(Fighter fighter) {
        return 1;
    }
}

// TODO: define the Warrior class
class Warrior extends Fighter{
    public String toString()
    {
        return "Fighter is a Warrior";
    }
    public boolean isVulnerable()
    {
        return false;
    }
    public int getDamagePoints(Fighter fighter)
    {
        if(fighter.isVulnerable())
        {
            return 10;
        }
        else
            return 6;
    }
}

// TODO: define the Wizard class
class Wizard extends Fighter{
    boolean isSpellPrepared = false;
    public String toString()
    {
        return "Fighter is a Wizard";
    }
    public void prepareSpell()
    {
        this.isSpellPrepared = true;
    }
    public boolean isVulnerable()
    {
        if(this.isSpellPrepared)
        return false;
        else
            return true;
    }
    public int getDamagePoints(Fighter fighter)
    {
        if(this.isSpellPrepared)
        {
            return 12;
        }
        else
            return 3;
    }
}

