package AcctMgmt;

import java.util.Scanner;

public class LineReader {
    private static Scanner scanner;

    public LineReader() {
        scanner = new Scanner(System.in);
    }

    public String readLine(){
        return scanner.nextLine();
    }

    public int readInt() {
        return Integer.parseInt(scanner.nextLine());
    }

    public double readDouble() {
        return Double.parseDouble(scanner.nextLine());
    }
}
