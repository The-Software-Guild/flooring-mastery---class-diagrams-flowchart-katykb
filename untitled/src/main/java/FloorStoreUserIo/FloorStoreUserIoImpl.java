package FloorStoreUserIo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class FloorStoreUserIoImpl implements FloorStoreUserIo{

    final private Scanner console = new Scanner(System.in);
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }
    public void print(BigDecimal msg){
        System.out.println(msg);
    }

    public void print(LocalDate msg){
        System.out.println(msg);
    }
    public String readString(String msgPrompt) {
        System.out.println(msgPrompt);
        return console.nextLine();
    }

    @Override
    public LocalDate readLocalDate(String prompt) {
        System.out.println(prompt);
        return LocalDate.parse(console.nextLine());
    }

    @Override
    public double readDouble(String prompt) {
        return 0;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        return 0;
    }

    @Override
    public float readFloat(String prompt) {
        return 0;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        return 0;
    }

    @Override
    public int readInt(String prompt) {
        return 0;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        return 0;
    }

    @Override
    public long readLong(String prompt) {
        return 0;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        return 0;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        System.out.println(prompt);
        return console.nextBigDecimal();
    }
}
