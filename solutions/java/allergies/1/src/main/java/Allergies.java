import java.util.ArrayList;
import java.util.List;

class Allergies {

    private final int score;
    private final List<Allergen> allergens = new ArrayList<>();

    Allergies(int score) {
        this.score = score;

        for (Allergen allergen : Allergen.values()) {
            if ((score & allergen.getScore()) != 0) {
                allergens.add(allergen);
            }
        }
    }

    boolean isAllergicTo(Allergen allergen) {
        return allergens.contains(allergen);
    }

    List<Allergen> getList() {
        return new ArrayList<>(allergens);
    }
}
