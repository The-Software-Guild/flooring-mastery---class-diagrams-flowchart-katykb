package FloorStoreDao;

import FloorStoreDto.Orders;
import FloorStoreDto.StateTax;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;

public class FloorStoreTaxDaoImpl implements FloorStoreTaxDao {

    public final String STATE_TAX_FILE = "stateTax.txt";
    public static final String DELIMITER = ",";

    private Map<String, StateTax> stateTaxMap = new HashMap<>();
    //List<StateTax> taxesList = new ArrayList<>();

    @Override
    //CA,Calfornia,25.00
    public StateTax unmarshallStateTax(String stateTaxAsText) {
        String[] storedTaxRates = stateTaxAsText.split(DELIMITER);
        String stateAbbreviation = storedTaxRates[0];
        StateTax stateTaxFromFile = new StateTax();
        stateTaxFromFile.setTrStateName(storedTaxRates[1]);
        stateTaxFromFile.setStateAbbreviation(stateAbbreviation);
        BigDecimal taxRate = new BigDecimal(storedTaxRates[2]);
        stateTaxFromFile.setTrTaxRate(taxRate);
        return stateTaxFromFile;

    }

    @Override
    public List<StateTax> loadStateTax() {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(STATE_TAX_FILE)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String currentLine;
        StateTax currentTaxInfo;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTaxInfo = unmarshallStateTax(currentLine);
            stateTaxMap.put(String.valueOf(currentTaxInfo.getTrTaxRate()), currentTaxInfo);


        }
        scanner.close();
        return new ArrayList<StateTax>(stateTaxMap.values());

    }
}

