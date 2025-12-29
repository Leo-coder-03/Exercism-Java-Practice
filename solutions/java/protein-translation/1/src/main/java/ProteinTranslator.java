import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class ProteinTranslator {

    private static final Map<String, String> CODON_MAP = Map.ofEntries(
        Map.entry("AUG", "Methionine"),
        Map.entry("UUU", "Phenylalanine"),
        Map.entry("UUC", "Phenylalanine"),
        Map.entry("UUA", "Leucine"),
        Map.entry("UUG", "Leucine"),
        Map.entry("UCU", "Serine"),
        Map.entry("UCC", "Serine"),
        Map.entry("UCA", "Serine"),
        Map.entry("UCG", "Serine"),
        Map.entry("UAU", "Tyrosine"),
        Map.entry("UAC", "Tyrosine"),
        Map.entry("UGU", "Cysteine"),
        Map.entry("UGC", "Cysteine"),
        Map.entry("UGG", "Tryptophan"),
        Map.entry("UAA", "STOP"),
        Map.entry("UAG", "STOP"),
        Map.entry("UGA", "STOP")
    );

    List<String> translate(String rnaSequence) {
        List<String> proteins = new ArrayList<>();
        int flag = 0;

        // Read codons in steps of 3
        for (int i = 0; i <= rnaSequence.length()-3; i += 3) {
            String codon = rnaSequence.substring(i, i + 3);
            String protein = CODON_MAP.get(codon);

            if (protein == null) {
                throw new IllegalArgumentException("Invalid codon");
            }

            if (protein.equals("STOP")) {
                flag = 1;
                break;
            }

            proteins.add(protein);
        }
        if((flag == 0) && (rnaSequence.length()%3!=0))
          throw new IllegalArgumentException("Invalid codon");
        return proteins;
    }
}
