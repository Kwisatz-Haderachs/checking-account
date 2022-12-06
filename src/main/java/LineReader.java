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
        String input = scanner.nextLine();
        try{
            return Integer.parseInt(input);
        } catch (NumberFormatException numberFormatException){
            System.out.println(numberFormatException);
            return 0;
        }
    }
}
