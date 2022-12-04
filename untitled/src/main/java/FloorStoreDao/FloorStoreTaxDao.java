package FloorStoreDao;

import FloorStoreDto.StateTax;

import java.util.List;

public interface FloorStoreTaxDao {

    public StateTax unmarshallStateTax(String stateAbbreviation);

    public List<StateTax> loadStateTax();
}
