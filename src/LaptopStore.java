import java.util.*;

class Laptop {
    private String model;
    private int ram; // RAM in GB
    private int storage; // Storage capacity in GB
    private String os; // Operating system
    private String color;

    public Laptop(String model, int ram, int storage, String os, String color) {
        this.model = model;
        this.ram = ram;
        this.storage = storage;
        this.os = os;
        this.color = color;
    }

    // Getters for accessing fields

    public String getModel() {
        return model;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }
}

public class LaptopStore {
    public static void main(String[] args) {
        // Create a set of laptops
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Dell XPS", 16, 512, "Windows 10", "Silver"));
        laptops.add(new Laptop("MacBook Pro", 8, 256, "macOS", "Space Gray"));
        laptops.add(new Laptop("HP Spectre", 16, 512, "Windows 10", "Copper"));

        // Create filtering criteria
        Map<String, Object> filters = new HashMap<>();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter filtering criteria:");
            System.out.println("1 - RAM (GB)");
            System.out.println("2 - Storage capacity (GB)");
            System.out.println("3 - Operating system");
            System.out.println("4 - Color");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer after entering the number

            switch (choice) {
                case 1:
                    System.out.print("Minimum RAM (GB): ");
                    int minRam = scanner.nextInt();
                    filters.put("ram", minRam);
                    break;
                case 2:
                    System.out.print("Minimum storage capacity (GB): ");
                    int minStorage = scanner.nextInt();
                    filters.put("storage", minStorage);
                    break;
                case 3:
                    System.out.print("Operating system: ");
                    String os = scanner.nextLine();
                    filters.put("os", os);
                    break;
                case 4:
                    System.out.print("Color: ");
                    String color = scanner.nextLine();
                    filters.put("color", color);
                    break;
                default:
                    System.out.println("Invalid criteria selection. Please choose a valid option.");
                    return;
            }
        }

        // Filter and print laptops
        filterAndPrintLaptops(laptops, filters);
    }

    private static void filterAndPrintLaptops(Set<Laptop> laptops, Map<String, Object> filters) {
        System.out.println("Filtering results:");

        for (Laptop laptop : laptops) {
            boolean passFilter = true;

            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                if ("ram".equals(key) && laptop.getRam() < (int) value) {
                    passFilter = false;
                    break;
                } else if ("storage".equals(key) && laptop.getStorage() < (int) value) {
                    passFilter = false;
                    break;
                } else if ("os".equals(key) && !laptop.getOs().equalsIgnoreCase((String) value)) {
                    passFilter = false;
                    break;
                } else if ("color".equals(key) && !laptop.getColor().equalsIgnoreCase((String) value)) {
                    passFilter = false;
                    break;
                }
            }

            if (passFilter) {
                System.out.println("Model: " + laptop.getModel());
                System.out.println("RAM: " + laptop.getRam() + " GB");
                System.out.println("Storage capacity: " + laptop.getStorage() + " GB");
                System.out.println("Operating system: " + laptop.getOs());
                System.out.println("Color: " + laptop.getColor());
                System.out.println();
            }
        }
    }
}
