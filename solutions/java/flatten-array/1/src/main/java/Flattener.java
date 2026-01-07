import java.util.ArrayList;
import java.util.List;

class Flattener {

    List<Object> flatten(List<?> list) {
        List<Object> flat = new ArrayList<>();
        flattenInto(list, flat);
        return flat;
    }

    private void flattenInto(List<?> input, List<Object> output) {
        for (Object element : input) {
            if (element == null) {
                continue;
            }

            if (element instanceof List<?>) {
                flattenInto((List<?>) element, output);
            } else {
                output.add(element);
            }
        }
    }
}
