import java.util.ArrayList;
import java.util.List;

class Dominoes {

    List<Domino> formChain(List<Domino> inputDominoes) throws ChainNotFoundException {
        if (inputDominoes.isEmpty()) {
            return new ArrayList<>();
        }

        List<Domino> chain = new ArrayList<>();

        for (int i = 0; i < inputDominoes.size(); i++) {
            boolean[] used = new boolean[inputDominoes.size()];
            Domino first = inputDominoes.get(i);
            chain.add(first);
            used[i] = true;
            if (backtrack(chain, used, inputDominoes, first.getLeft(), first.getRight(), 1)) {
                return chain;
            }

            chain.clear();
            used = new boolean[inputDominoes.size()];
            Domino rev = new Domino(first.getRight(), first.getLeft());
            chain.add(rev);
            used[i] = true;
            if (backtrack(chain, used, inputDominoes, rev.getLeft(), rev.getRight(), 1)) {
                return chain;
            }
        }

        throw new ChainNotFoundException("No domino chain found.");
    }

    private boolean backtrack(List<Domino> chain, boolean[] used, List<Domino> stones,
                              int startValue, int currentValue, int depth) {

        if (depth == stones.size()) {
            return currentValue == startValue;
        }

        for (int i = 0; i < stones.size(); i++) {
            if (used[i]) continue;

            Domino d = stones.get(i);

            if (d.getLeft() == currentValue) {
                used[i] = true;
                chain.add(d);
                if (backtrack(chain, used, stones, startValue, d.getRight(), depth + 1)) {
                    return true;
                }
                chain.remove(chain.size() - 1);
                used[i] = false;
            }

            if (d.getRight() == currentValue) {
                Domino rev = new Domino(d.getRight(), d.getLeft());
                used[i] = true;
                chain.add(rev);
                if (backtrack(chain, used, stones, startValue, rev.getRight(), depth + 1)) {
                    return true;
                }
                chain.remove(chain.size() - 1);
                used[i] = false;
            }
        }
        return false;
    }
}
