import LRA.LRATest;
import org.apache.commons.mail.EmailException;

import java.io.IOException;

public class EntryPoint {
    public static void main(String[] args) throws EmailException, IOException, InterruptedException {
        LRATest test = new LRATest();
        test.login22();
    }
}
