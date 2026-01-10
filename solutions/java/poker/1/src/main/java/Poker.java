import java.util.*;
import java.util.stream.Collectors;

class Poker {

    private final List<Hand> hands;

    Poker(List<String> hands) {
        this.hands = hands.stream().map(Hand::new).collect(Collectors.toList());
    }

    List<String> getBestHands() {
        List<Hand> sorted = new ArrayList<>(hands);
        Collections.sort(sorted);
        Hand best = sorted.get(sorted.size() - 1);

        return sorted.stream()
                .filter(h -> h.compareTo(best) == 0)
                .map(h -> h.original)
                .collect(Collectors.toList());
    }

    private static class Card {
        int rank;
        char suit;

        Card(String s) {
            String rankStr = s.length() == 3 ? s.substring(0, 2) : s.substring(0, 1);
            this.rank = parseRank(rankStr);
            this.suit = s.charAt(s.length() - 1);
        }

        private int parseRank(String r) {
            switch (r) {
                case "A": return 14;
                case "K": return 13;
                case "Q": return 12;
                case "J": return 11;
                case "10": return 10;
                default: return r.charAt(0) - '0';
            }
        }
    }

    private static class Hand implements Comparable<Hand> {

        final String original;
        final List<Card> cards;
        final HandRank rank;

        Hand(String hand) {
            this.original = hand;
            this.cards = Arrays.stream(hand.split(" "))
                    .map(Card::new)
                    .sorted(Comparator.comparingInt(c -> c.rank))
                    .collect(Collectors.toList());

            this.rank = evaluate();
        }

        @Override
        public int compareTo(Hand o) {
            return this.rank.compareTo(o.rank);
        }

        private HandRank evaluate() {
            boolean isFlush = cards.stream().map(c -> c.suit).distinct().count() == 1;
            List<Integer> ranks = cards.stream().map(c -> c.rank).sorted().collect(Collectors.toList());
            boolean isStraight = isStraight(ranks);

            Map<Integer, Long> freq = cards.stream().collect(Collectors.groupingBy(
                    c -> c.rank, Collectors.counting()
            ));

            List<Map.Entry<Integer, Long>> sortedFreq = freq.entrySet().stream()
                    .sorted((a, b) -> {
                        int cmp = Long.compare(b.getValue(), a.getValue());
                        if (cmp != 0) return cmp;
                        return Integer.compare(b.getKey(), a.getKey());
                    })
                    .collect(Collectors.toList());

            List<Integer> kickerList = buildKickerList(sortedFreq);

            if (isStraight && isFlush)
                return new HandRank(8, getStraightHigh(ranks));

            if (sortedFreq.get(0).getValue() == 4)
                return new HandRank(7, sortedFreq.get(0).getKey(), kickerList);

            if (sortedFreq.get(0).getValue() == 3 && sortedFreq.get(1).getValue() == 2)
                return new HandRank(6, sortedFreq.get(0).getKey(), sortedFreq.get(1).getKey());

            if (isFlush)
                return new HandRank(5, ranks);

            if (isStraight)
                return new HandRank(4, getStraightHigh(ranks));

            if (sortedFreq.get(0).getValue() == 3)
                return new HandRank(3, sortedFreq.get(0).getKey(), kickerList);

            if (sortedFreq.get(0).getValue() == 2 && sortedFreq.get(1).getValue() == 2)
                return new HandRank(2,
                        Math.max(sortedFreq.get(0).getKey(), sortedFreq.get(1).getKey()),
                        Math.min(sortedFreq.get(0).getKey(), sortedFreq.get(1).getKey()),
                        kickerList);

            if (sortedFreq.get(0).getValue() == 2)
                return new HandRank(1, sortedFreq.get(0).getKey(), kickerList);

            return new HandRank(0, ranks);
        }

        private List<Integer> buildKickerList(List<Map.Entry<Integer, Long>> sortedFreq) {
            List<Integer> out = new ArrayList<>();
            for (Map.Entry<Integer, Long> e : sortedFreq) {
                if (e.getValue() == 1)
                    out.add(e.getKey());
            }
            out.sort(Collections.reverseOrder());
            return out;
        }

        private boolean isStraight(List<Integer> ranks) {
            if (ranks.equals(Arrays.asList(2, 3, 4, 5, 14)))
                return true;

            for (int i = 0; i < 4; i++)
                if (ranks.get(i + 1) - ranks.get(i) != 1)
                    return false;

            return true;
        }

        private int getStraightHigh(List<Integer> ranks) {
            if (ranks.equals(Arrays.asList(2, 3, 4, 5, 14)))
                return 5;
            return ranks.get(4);
        }
    }

    private static class HandRank implements Comparable<HandRank> {

        final int category;
        final List<Integer> tiebreakValues;

        HandRank(int category, List<Integer> cardValues) {
            this.category = category;
            this.tiebreakValues = new ArrayList<>(cardValues);
            this.tiebreakValues.sort(Collections.reverseOrder());
        }

        HandRank(int category, int highCard) {
            this.category = category;
            this.tiebreakValues = List.of(highCard);
        }

        HandRank(int category, int mainValue, Object... rest) {
            this.category = category;
            this.tiebreakValues = new ArrayList<>();
            this.tiebreakValues.add(mainValue);

            for (Object o : rest) {
                if (o instanceof Integer) tiebreakValues.add((Integer) o);
                else if (o instanceof List) tiebreakValues.addAll((List<Integer>) o);
            }
        }

        @Override
        public int compareTo(HandRank o) {
            if (this.category != o.category)
                return Integer.compare(this.category, o.category);

            int n = Math.min(this.tiebreakValues.size(), o.tiebreakValues.size());
            for (int i = 0; i < n; i++) {
                int cmp = Integer.compare(this.tiebreakValues.get(i), o.tiebreakValues.get(i));
                if (cmp != 0) return cmp;
            }
            return 0;
        }
    }
}
