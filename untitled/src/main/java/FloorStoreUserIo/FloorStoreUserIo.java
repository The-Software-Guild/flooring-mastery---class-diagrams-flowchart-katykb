package FloorStoreUserIo;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface FloorStoreUserIo {


    void print(String msg);
    void print(BigDecimal msg);
    void print(LocalDate msg);

    String readString(String prompt);

       LocalDate readLocalDate(String prompt);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);


    BigDecimal readBigDecimal(String prompt);

}
