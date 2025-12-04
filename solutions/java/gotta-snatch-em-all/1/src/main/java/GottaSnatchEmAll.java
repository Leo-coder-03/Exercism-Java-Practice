import java.util.List;
import java.util.Set;
import java.util.HashSet;

class GottaSnatchEmAll {

    static Set<String> newCollection(List<String> cards) {
       return new HashSet<>(cards);
    }

    static boolean addCard(String card, Set<String> collection) {
      return collection.add(card);
    }

    static boolean canTrade(Set<String> myCollection, Set<String> theirCollection) {
        boolean myCollectionContains = false;
        boolean theirCollectionContains = false;
        for(String card:myCollection)
            {
                 if(!theirCollection.contains(card)){
                     myCollectionContains = true;
                     break;
                 }
            }
        for(String card:theirCollection)
            {
                 if(!myCollection.contains(card)){
                     theirCollectionContains = true;
                     break;
                 }
            }
        return myCollectionContains && theirCollectionContains;
    }

    static Set<String> commonCards(List<Set<String>> collections) {
        Set<String>common = new HashSet<>(collections.get(0));
        for(Set<String> collection:collections)
            common.retainAll(collection);
        return common;
    }

    static Set<String> allCards(List<Set<String>> collections) {
       Set<String> uniqueCards = new HashSet<>();
        for(Set<String> collection:collections)
            uniqueCards.addAll(collection);
        return uniqueCards;
    }
}
