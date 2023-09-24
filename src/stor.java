import java.util.*;

class Laptop {
    private String model;
    private int ram; // ОЗУ в ГБ
    private int storage; // Объем жесткого диска в ГБ
    private String os; // Операционная система
    private String color;

    public Laptop(String model, int ram, int storage, String os, String color) {
        this.model = model;
        this.ram = ram;
        this.storage = storage;
        this.os = os;
        this.color = color;
    }

    // Геттеры для доступа к полям

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
        // Создаем множество ноутбуков
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Dell XPS", 16, 512, "Windows 10", "Silver"));
        laptops.add(new Laptop("MacBook Pro", 8, 256, "macOS", "Space Gray"));
        laptops.add(new Laptop("HP Spectre", 16, 512, "Windows 10", "Copper"));

        // Создаем критерии фильтрации
        Map<String, Object> filters = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите критерии фильтрации:");
        System.out.println("1 - ОЗУ (ГБ)");
        System.out.println("2 - Объем ЖД (ГБ)");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Очищаем буфер после ввода числа

        switch (choice) {
            case 1:
                System.out.print("Минимальное количество ОЗУ (ГБ): ");
                int minRam = scanner.nextInt();
                filters.put("ram", minRam);
                break;
            case 2:
                System.out.print("Минимальный объем ЖД (ГБ): ");
                int minStorage = scanner.nextInt();
                filters.put("storage", minStorage);
                break;
            case 3:
                System.out.print("Операционная система: ");
                String os = scanner.nextLine();
                filters.put("os", os);
                break;
            case 4:
                System.out.print("Цвет: ");
                String color = scanner.nextLine();
                filters.put("color", color);
                break;
            default:
                System.out.println("Неверный выбор критерия.");
                return;
        }

        // Фильтруем и выводим ноутбуки
        filterAndPrintLaptops(laptops, filters);
    }

    private static void filterAndPrintLaptops(Set<Laptop> laptops, Map<String, Object> filters) {
        System.out.println("Результаты фильтрации:");

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
                System.out.println("Модель: " + laptop.getModel());
                System.out.println("ОЗУ: " + laptop.getRam() + " ГБ");
                System.out.println("Объем ЖД: " + laptop.getStorage() + " ГБ");
                System.out.println("Операционная система: " + laptop.getOs());
                System.out.println("Цвет: " + laptop.getColor());
                System.out.println();
            }
        }
    }
}
