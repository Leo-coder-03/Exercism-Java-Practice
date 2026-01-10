import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class BuildTree {

    TreeNode buildTree(ArrayList<Record> records) throws InvalidRecordsException {

        if (records.isEmpty()) {
            return null;
        }
        records.sort(Comparator.comparing(Record::getRecordId));

        int n = records.size();

        for (int i = 0; i < n; i++) {
            if (records.get(i).getRecordId() != i) {
                throw new InvalidRecordsException("Invalid Records");
            }
        }

        Record rootRecord = records.get(0);
        if (rootRecord.getParentId() != 0) {
            throw new InvalidRecordsException("Invalid Records");
        }

        List<TreeNode> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nodes.add(new TreeNode(i));
        }

        for (Record record : records) {
            int id = record.getRecordId();
            int parentId = record.getParentId();

            if (id < parentId || (id == parentId && id != 0)) {
                throw new InvalidRecordsException("Invalid Records");
            }

            if (id != 0) {
                nodes.get(parentId).getChildren().add(nodes.get(id));
            }
        }

        return nodes.get(0); 
    }
}
