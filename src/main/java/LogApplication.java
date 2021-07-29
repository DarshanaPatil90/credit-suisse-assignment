import helpers.DAOClass;
import helpers.ParserClass;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

/*
Author : Darshana Patil
*/

public class LogApplication {
    private final static Log logger = LogFactory.getLog(ParserClass.class);

    public static void main(String[] args) {
        if (args == null || args.length != 1) {
            logger.error("Arguments should be in the format --args='<File>'.");
            throw new IllegalArgumentException("Please check the arguments and run again.");
        }

        String file = args[0];

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            DAOClass dao = new DAOClass();
            dao.createEventsTable();

            logger.debug("Parsing file <" + file + "> for events.");
            ParserClass parser = new ParserClass();
            parser.parseLogs(reader, dao);

            dao.selectAll();
            dao.deleteAll();
            dao.closeDatabase();
        } catch (IOException e) {
            logger.error("Error while  parsing file < " + file + " >");
            e.printStackTrace();
        } catch (SQLException e) {
            logger.error("Error encountered in  DB");
            e.printStackTrace();
        }
    }
}
