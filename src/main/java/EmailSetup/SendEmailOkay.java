package EmailSetup;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendEmailOkay {
    public static void sendingEmailToUser() throws EmailException, IOException {

        //GETTING RESPONSE OF SITE
        //URL connection
        HttpURLConnection cont=
                (HttpURLConnection)new URL("https://portal.efd.lra.gov.lr/app/#main/0")
                        .openConnection();
        // pass HEAD as parameter to setRequestMethod
        cont.setRequestMethod("HEAD");
        // obtain Response code
        cont.connect();
        int rs = cont.getResponseCode();



        Email email = new SimpleEmail();
        email.setHostName("mail.tracom.dev");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("catchall@tracom.dev", "Tr@com2021"));
        email.setSSLOnConnect(true);
        email.setFrom("lra_report@catchall.tracom.dev");
        email.setSubject("LRA Current State");
        email.setMsg("Hi, The report status is: " + rs + " the LRA General report is Okay");
        email.addTo("edward.muchogu@tracom.co.ke");
        email.addTo("marvinkamwenjih@gmail.com");
        email.addTo("karorimesh@gmail.com");
        email.addTo("mainabonface4@gmail.com");

        email.send();


    }
}
