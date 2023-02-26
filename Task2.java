import java.util.*;


public class Task2 {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // n = Number of days & m = Number of houses
        int n = sc.nextInt();
        int m = sc.nextInt();

        //houses list is used to store the start and end day of each house.
        ArrayList<ArrayList<Integer>> houses = new ArrayList<>();
        ArrayList<Integer> house;
        PriorityQueue<House> priorityQueue = new PriorityQueue<>(new smallestIntervalComparator());


        //Input of m houses start day and end day
        for (int i = 0; i < m; i++) {
            int start_day = sc.nextInt();
            int end_day = sc.nextInt();
            house = new ArrayList<>();
            house.add(start_day);
            house.add(end_day);
            house.add(i + 1);
            houses.add(house);
        }

        //List of painted houses
        ArrayList<Integer> result = new ArrayList<>();

        int house_ind = 0;
        for (int i = 1; i <= n; i++) {
            while (house_ind < m) {
                int start_day = houses.get(house_ind).get(0);
                int end_day = houses.get(house_ind).get(1);
                int house_number = houses.get(house_ind).get(2);
                if (i == start_day) {
                    priorityQueue.add(new House(start_day, end_day, house_number));
                    house_ind += 1;
                } else {
                    break;
                }
            }

            while (!priorityQueue.isEmpty()) {
                House h = priorityQueue.peek();
                if (i >= h.getStart_day() && i <= h.getEnd_day()) {
                    priorityQueue.poll();
                    result.add(h.getHouse_number());
                    break;
                } else if (i > h.getEnd_day()) {
                    priorityQueue.poll();
                }
            }
        }

        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i) + " ");
        }
        System.out.println();
    }

    static class House {
        int start_day;
        int end_day;
        int house_number;

        public House(int start_day, int end_day, int house_number) {
            this.start_day = start_day;
            this.end_day = end_day;
            this.house_number = house_number;
        }

        public int getEnd_day() {
            return end_day;
        }

        public int getStart_day() {
            return start_day;
        }

        public int getHouse_number() {
            return house_number;
        }
    }

    /**
     * Sort the priority queue in descending order of the start day (which is the available
     * the latest)
     */
    static class smallestIntervalComparator implements Comparator<House> {
        public int compare(House a, House b) {
            return ((b.getStart_day() - a.getStart_day()));
        }
    }
}
