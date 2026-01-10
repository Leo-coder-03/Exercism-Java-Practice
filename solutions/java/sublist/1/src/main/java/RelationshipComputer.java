import java.util.List;

class RelationshipComputer<T> {

    Relationship computeRelationship(List<T> firstList, List<T> secondList) {

        if (firstList.isEmpty() && secondList.isEmpty()) {
            return Relationship.EQUAL;
        }

        if (isSublist(secondList, firstList)) {
            if (firstList.equals(secondList)) return Relationship.EQUAL;
            return Relationship.SUPERLIST;
        }

        if (isSublist(firstList, secondList)) {
            return Relationship.SUBLIST;
        }

        return Relationship.UNEQUAL;
    }

    private boolean isSublist(List<T> small, List<T> big) {
        if (small.isEmpty()) return true;

        int smallSize = small.size();
        int bigSize = big.size();

        if (smallSize > bigSize) return false;

        for (int i = 0; i <= bigSize - smallSize; i++) {
            if (big.subList(i, i + smallSize).equals(small)) {
                return true;
            }
        }
        return false;
    }
}
