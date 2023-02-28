import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Task5 {

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
        ArrayList<Integer> result = new ArrayList<>();


        int house_ind = 0;
        int current_day = 1;

        /**
         * This outer loop runs for at max O(m) times (Iterating over the houses) and
         * the operations inside it cost a maximum of 2mlogm
         * (logm for inserting into priority queue and logm for removing
         * from priority queue)
         *
         * so total complexity = m + mlogm = theta(mlogm)
         */
        long startTime = System.nanoTime();
        while (house_ind < m) {
            int start_day = houses.get(house_ind).get(0);
            int end_day = houses.get(house_ind).get(1);
            int index = houses.get(house_ind).get(2);

            if (priorityQueue.isEmpty()) {
                current_day = start_day;
            }

            if (start_day > n) break;

            if (start_day > current_day || house_ind == m) {
                while (!priorityQueue.isEmpty() && start_day > current_day) {
                    House h = priorityQueue.peek();

                    if (h.getEnd_day() >= current_day) {
                        h = priorityQueue.peek();
                        result.add(h.getHouse_number());
                        priorityQueue.poll();
                        current_day += 1;
                    } else if (h.getEnd_day() < current_day) {
                        priorityQueue.poll();
                    } else {
                        break;
                    }

                }
                current_day = start_day;
            }


            while (house_ind < m && start_day <= current_day) {
                priorityQueue.add(new House(start_day, end_day, index));
                house_ind += 1;
                if (house_ind < m) {
                    start_day = houses.get(house_ind).get(0);
                    end_day = houses.get(house_ind).get(1);
                    index = houses.get(house_ind).get(2);
                }

            }
        }

        /**
         * This loop runs for atmost m + mlogm (remove atmost m items from priority queue)
         */
        while (!priorityQueue.isEmpty()) {
            House h = priorityQueue.peek();

            if (h.getStart_day() > current_day) {
                current_day += 1;
                continue;
            } else if (current_day > n || h.getStart_day() > n) {
                break;
            }

            if (h.getStart_day() > n) break;

            if (h.getEnd_day() >= current_day) {
                h = priorityQueue.peek();
                result.add(h.getHouse_number());
                priorityQueue.poll();
                current_day += 1;
            } else if (h.getEnd_day() < current_day) {
                priorityQueue.poll();
            } else {
                break;
            }

        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i) + " ");
        }
        System.out.println("");
        System.out.print("The result size is " + result.size());
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

    static class smallestIntervalComparator implements Comparator<House> {
        public int compare(House a, House b) {
            return ((a.getEnd_day() - b.getEnd_day()));
        }
    }
}