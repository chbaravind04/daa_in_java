import java.util.*;

class Item {
    int weight;
    int value;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}

class Knapsack {
    public static double findMaxValue(List<Item> items, int capacity) {
        // Sort items based on value-to-weight ratio in descending order
        items.sort((i1, i2) -> (i2.value / i2.weight) - (i1.value / i1.weight));

        double totalValue = 0;
        int remainingCapacity = capacity;

        // Iterate over the sorted items and add them to the knapsack
        for (Item item : items) {
            if (item.weight <= remainingCapacity) {
                // Add the entire item
                totalValue += item.value;
                remainingCapacity -= item.weight;
            } else {
                // Add a fraction of the item to fill the remaining capacity
                double fraction = (double) remainingCapacity / item.weight;
                totalValue += fraction * item.value;
                break;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        items.add(new Item(10, 60));
        items.add(new Item(20, 100));
        items.add(new Item(30, 120));

        int capacity = 50;

        double maxValue = findMaxValue(items, capacity);
        System.out.println("Maximum value: " + maxValue);
    }
}
