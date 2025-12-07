import java.util.*;

class DnDCharacter {
  private final int strength = ability(rollDice());
    private final int dexterity = ability(rollDice());
    private final int constitution = ability(rollDice());
    private final int intelligence = ability(rollDice());
    private final int wisdom  = ability(rollDice());
    private final int charisma = ability(rollDice());

    int ability(List<Integer> scores) {
    List<Integer> sorted = new ArrayList<>(scores);
    Collections.sort(sorted);
    return sorted.get(1) + sorted.get(2) + sorted.get(3);
    }

    List<Integer> rollDice() {
        List<Integer> rollDice = new ArrayList<Integer>();
        int val = 0;
        for(int i=0;i<4;i++)
            {
                val =(int) (Math.random()*6)+1;
                rollDice.add(val);
            }
        return rollDice;
    }

    int modifier(int input) {
        return (int) Math.floor((input-10)/2.0);
    }

    int getStrength() {
        return this.strength;
    }

    int getDexterity() {
       return this.dexterity;
    }

    int getConstitution() {
        return this.constitution;
    }

    int getIntelligence() {
       return this.intelligence;
    }

    int getWisdom() {
       return this.wisdom;
    }

    int getCharisma() {
        return this.charisma;
    }

    int getHitpoints() {
        return 10+modifier(getConstitution());
    }
}
