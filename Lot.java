import java.sql.SQLOutput;
import java.util.*;

public class Lot {
    List<Floor> floors;
    private static String pid;
    private static Lot lot;
    private static int num_slots;
    private Lot(){};
    public static Lot getInstance(int num_floors, int slots, String p){
        pid = p;
        num_slots = slots;
        if( lot != null) return lot;
        else{
            lot = new Lot();
            lot.floors = new ArrayList<>();
            for(int i = 0; i < num_floors; i++){
                Floor f = new Floor(num_slots);
                lot.floors.add(f);
            }
        }
        return lot;
    }

    public void freeCount( String veh ){

        for(int i = 0; i < floors.size(); i++){
            System.out.println("Number of free slots for "+veh+ " on Floor "+(i+1)+": ");
            int free = floors.get(i).count.get(veh).first;
            int cum =  floors.get(i).count.get(veh).second - free;
            int fc = 0;
            int tot = floors.get(i).tot_slots.get(veh);
            for(int k = 1; k <= tot; k++){
                if(!floors.get(i).occupied.contains(cum+k))
                    fc++;
            }
            System.out.println(fc);
        }
    }

    public void freeSlots( String veh ){
        for(int i = 0; i < floors.size(); i++){
            System.out.println("Number of free slots for "+veh+ " on Floor "+(i+1)+": ");
            int free = floors.get(i).count.get(veh).first;
            int cum =  floors.get(i).count.get(veh).second - free;

            int tot = floors.get(i).tot_slots.get(veh);
            for(int k = 1; k <= tot; k++){
                if(!floors.get(i).occupied.contains(cum+k))
                    System.out.println(cum+k);
            }
        }
    }

    public void occupiedSlots(String veh){

        for(int i = 0; i < floors.size(); i++){
            System.out.println("Number of occupied slots for "+veh+ " on Floor "+(i+1)+": ");
            int free = floors.get(i).count.get(veh).first;
            int cum =  floors.get(i).count.get(veh).second;

            int occ = floors.get(i).tot_slots.get(veh) - free;
            for(int k = 0; k <= occ; k++){
                if(floors.get(i).occupied.contains(cum+k))
                    System.out.println((cum+k)+" ");
            }
        }
    }

    public void parkVehicle(Scanner sc){
        String type, color, reg;
        String ticket = "full";
        System.out.println("Enter vehicle type Eg(Car, Bike, Truck): ");
        type = sc.next();
        System.out.println("Enter color: ");
        color = sc.next();
        System.out.println("Enter Regno: ");
        reg = sc.next();
        int slotid;
        for(int i = 0; i < floors.size(); i++){
            slotid = floors.get(i).genSlotId(type, color, reg);
            if(slotid != -1) {
                ticket = pid+"_"+(i+1)+"_"+slotid;
                break;
            }
        }
        if(ticket != "full") System.out.println("Ticket Id: " + ticket);
        else System.out.println("Parking lot full for vehicle type: " + type);
    }


    public void unparkVehicle(Scanner sc) {
        System.out.println("Enter Ticket ID");
        String ticket = sc.next();
        String id_floor_slot[] = ticket.split("_");
        int floor, slot;
        try {
            floor = Integer.parseInt(id_floor_slot[1]);
            slot = Integer.parseInt(id_floor_slot[2]);
        } catch (Exception e){
            System.out.println("Invalid Ticket");
            return;
        }
        System.out.println(Arrays.toString(id_floor_slot));
        if( floors.get(floor-1).occupied.contains(slot)) {

            Vehicle v = floors.get(floor-1).slots.get(slot-1);
            floors.get(floor-1).slots.set(slot-1,null);
            floors.get(floor-1).freed.add(slot);
            floors.get(floor-1).occupied.remove(Integer.valueOf(slot));
            System.out.println("Unparked Vehicle: " +v);
        }

        else {
            System.out.println("Invalid Ticket");
        }

    }
}

























