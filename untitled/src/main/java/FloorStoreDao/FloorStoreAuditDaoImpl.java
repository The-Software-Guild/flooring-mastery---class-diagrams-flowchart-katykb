package FloorStoreDao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class FloorStoreAuditDaoImpl implements FloorStoreAuditDao {

    public static final String AUDIT_FILE = "dataexport.txt";
    @Override
    public void writeAuditEntry(String entry) throws FloorStorePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new FloorStorePersistenceException("Could not persist audit information.", e);
        }

        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }
    }

