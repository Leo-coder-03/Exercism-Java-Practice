import java.util.*;

class HighScores {

    List<Integer> highScores = new ArrayList<Integer>();
    public HighScores(List<Integer> highScores) {
        this.highScores = highScores;
    }

    List<Integer> scores() {
        return highScores;
    }

    Integer latest() {
        return highScores.get(highScores.size()-1);
    }

    Integer personalBest() {
        List<Integer> scores = new ArrayList<>(highScores);
        Collections.sort(scores);
        return scores.get(scores.size()-1);
    }

    List<Integer> personalTopThree() {
        List<Integer> personalTopThree = new ArrayList<>();
        List<Integer> scores = new ArrayList<>(highScores);
        Collections.sort(scores);
        try{
        personalTopThree.add(scores.get(scores.size()-1));
        personalTopThree.add(scores.get(scores.size()-2));
        personalTopThree.add(scores.get(scores.size()-3));
        }
        catch(IndexOutOfBoundsException e)
            {
                System.out.println("Index -1 out of bounds for length "+scores.size());
            }
        return personalTopThree;
    }

}
