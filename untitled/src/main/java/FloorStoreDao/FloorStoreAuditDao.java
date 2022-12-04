package FloorStoreDao;

public interface FloorStoreAuditDao {
    public void writeAuditEntry(String entry) throws FloorStorePersistenceException;
}
