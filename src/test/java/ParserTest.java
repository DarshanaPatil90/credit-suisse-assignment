import helpers.DAOClass;
import helpers.ParserClass;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;


public class ParserTest {

    @InjectMocks private ParserClass mockParser;
    @Mock private BufferedReader mockBufferedReader;
    @Mock private DAOClass mockDAO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void parseLogs() throws IOException, SQLException {
        String startEvent = "{\"id\":\"scsmbstgra\", \"state\":\"STARTED\", \"timestamp\":1491377495212}";
        String finishEvent = "{\"id\":\"scsmbstgra\", \"state\":\"FINISHED\", \"timestamp\":1491377495217}";
        Mockito.when(mockBufferedReader.readLine())
                .thenReturn(startEvent)
                .thenReturn(finishEvent)
                .thenReturn(null);

        mockParser.parseLogs(mockBufferedReader, mockDAO);

        Mockito.verify(mockDAO, Mockito.times(1));
    }
}
