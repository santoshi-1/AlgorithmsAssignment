import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;

public class GenerateDataSet {

        public static void main(String[] args) throws IOException {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int m = sc.nextInt();
            sc.close();
            Random rand = new Random();
            FileWriter f = new FileWriter("dataset.txt");
            ArrayList<Integer> startDates = new ArrayList<>();
            ArrayList<Integer> endDates = new ArrayList<>();
            f.write(n + " " + m + "\r\n");

            for(int i=1; i<=m; i++) {
                int x = rand.nextInt(n) + 1;
                int y = rand.nextInt(n) + 1;
                if (x <= y) {
                    startDates.add(x);
                    endDates.add(y);
                }
                else {
                    startDates.add(y);
                    endDates.add(x);
                }
            }

            List<int[]> dateVector = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                int[] temp = { startDates.get(j), endDates.get(j) };
                dateVector.add(temp);
            }

            Collections.sort(dateVector, new Comparator<int[]>() {
                public int compare(int[] a, int[] b)
                {
                    if (a[0] != b[0]) {
                        return a[0] - b[0];
                    }
                    else {
                        return a[1] - b[1];
                    }
                }
            });

            for (int j = 0; j < m; j++) {
                f.write(dateVector.get(j)[0] + " " + dateVector.get(j)[1]);
                f.write("\r\n");
            }

            f.close();
        }
}
