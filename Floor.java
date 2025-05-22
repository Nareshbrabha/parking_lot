import java.util.*;

public class Floor {
    int num_slots;

    List<Integer> freed;

    List<Integer> occupied;
    Map<String, Pair<Integer, Integer>> count;
    Map<String, Integer> tot_slots;
    List<Vehicle> slots;
    String [] prior = {"Car", "Bike", "Truck"};
    int num_Vehicles = 3;

    public Floor(int num_slots) {
        slots = new ArrayList<>(Collections.nCopies(num_slots, null));
        occupied = new ArrayList<>();
        freed = new ArrayList<>();
        tot_slots = new HashMap<>();
        count = new HashMap<>();
        this.num_slots = num_slots;
        int div = num_slots / num_Vehicles;
        int rem = num_slots % num_Vehicles;
        int cum = 0, add;
        for(String pri : prior){
            if(rem>0){
                add = div + 1;
                rem--;
            }
            else add = div;
            tot_slots.put(pri,add);
            cum += add;
            Pair<Integer,Integer> p = new Pair<>(add,cum);
            count.put(pri, p);
        }

    }

    public Floor() {}

    public int getNum_slots() {
        return num_slots;
    }

    public void setNum_slots(int num_slots) {
        this.num_slots = num_slots;
    }

    public Map<String, Pair<Integer, Integer>> getCount() {
        return count;
    }

    public void setCount(Map<String, Pair<Integer, Integer>> count) {
        this.count = count;
    }

    public List<Vehicle> getSlots() {
        return slots;
    }

    public void setSlots(List<Vehicle> slots) {
        this.slots = slots;
    }

    public int genSlotId(String type, String color, String reg ){

        int free = count.get(type).first;
        int cum =  count.get(type).second;

        if( free == 0){

            for (int i = cum+1; i <= tot_slots.get(type); i++) {
                if(freed.contains(i)){
                    freed.remove(Integer.valueOf(i));
                    Vehicle v = new Vehicle(type, color, reg);
                    slots.set(i-1, v);
                    occupied.add(i);
                    return i;
                }
            }
           return -1;
        }

        Vehicle v = new Vehicle(type, color, reg);
        slots.set(--count.get(type).second, v);
        count.get(type).first--;               //shifting index to free spot;
        occupied.add(cum);
        System.out.println(occupied);
        return cum;
    }
}
