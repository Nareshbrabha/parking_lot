import java.util.*;

public class Main {

    public static void displayMenu( Scanner sc, Lot lot) {

        boolean run = true;
        while (run) {
            System.out.println(" 1. Display Free Count\n 2. Display Free Slots\n 3. Occupied Slots\n 4. Park Vehicle\n 5. Unpark Vehicle\n 6. Exit Parking system   ");
            int op = sc.nextInt();
            switch (op) {
                case 1:
                    System.out.println("Enter vehicle to display free count");
                    System.out.println("Eg: 'Car'  'Truck'  'Bike'");
                    String veh = sc.next();
                    lot.freeCount(veh);
                    break;
                case 2:
                    System.out.println("Enter vehicle to display free slots");
                    System.out.println("Eg: 'Car'  'Truck'  'Bike'");
                    String vehi = sc.next();
                    lot.freeSlots(vehi);
                    break;

                case 3:
                    System.out.println("Enter vehicle to display occupied slots");
                    System.out.println("Eg: 'Car'  'Truck'  'Bike'");
                    String vehic = sc.next();
                    lot.occupiedSlots(vehic);
                    break;
                case 4:
                    lot.parkVehicle(sc);
                    break;
                case 5:
                    lot.unparkVehicle(sc);
                    break;
                case 6:
                    run = false;
                    break;
            }
        }
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Create a parking lot with ID, Floors and Slots");
        System.out.println("Enter Parking lot ID: Eg:'PR1234'");
        String pid = sc.next();

        System.out.println("Enter number of floors: ");
        int floors = sc.nextInt();
        System.out.println("Enter number of slots: ");
        int slots = sc.nextInt();

        Lot lot = Lot.getInstance(floors, slots, pid);
        displayMenu(sc, lot);
    }
}
