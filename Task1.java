import java.util.ArrayList;
import java.util.Scanner;

public class Task1 {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // n = Number of days & m = Number of houses
        int n = sc.nextInt();
        int m = sc.nextInt();

        //houses list is used to store the start and end day of each house.
        ArrayList<ArrayList<Integer>> houses = new ArrayList<>();
        ArrayList<Integer> house;

        //Input of m houses start day and end day
        for (int i = 0; i < m; i++) {
            int start_day = sc.nextInt();
            int end_day = sc.nextInt();
            house = new ArrayList<>();
            house.add(start_day);
            house.add(end_day);
            houses.add(house);

        }

        Task1 task1 = new Task1();
        //List of painted houses
        ArrayList<Integer> result = task1.paintCompatibleHouse(houses, n, m);

        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i) + " ");
        }
        System.out.print("The result size is " + result.size());
        System.out.println();
    }

    /**
     * Function that computes the houses that can be painted.
     */
    public ArrayList<Integer> paintCompatibleHouse(ArrayList<ArrayList<Integer>> houses, int n, int m) {

        /**
         * Keep track of the day that the last house is painted & check for the next compatible house
         * with this house that can be painted.
         */
        int recentDayPainted = 0;
        ArrayList<Integer> result = new ArrayList<>();


        int current_house = 0;
        for (int i = 1; i <= n; i++) {

            if (current_house >= m) break;

            int start_day = houses.get(current_house).get(0);
            int end_day = houses.get(current_house).get(1);

            if (start_day > i) {
                continue;
            }
            if (i >= start_day && i <= end_day) {
                current_house += 1;
                result.add(current_house);
            } else if (end_day < i) {
                current_house += 1;
                i -= 1;
            }
        }
        return result;
    }
}
