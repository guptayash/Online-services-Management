import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.net.URISyntaxException;
import java.util.Random;
public class SmsSender {

    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC2588d628b1ee82c6e9e65bca4d6fc0fb";
     public static final String AUTH_TOKEN = "ae20afa0c49a7fc25baaf8abdefed6b8";

    public static void main(String[] args) throws URISyntaxException {
        Random rand=new Random();
        int n=rand.nextInt(10000)+1000;
        
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String s="Your OTP is "+ n;
       Message message;
        message = Message.creator(new PhoneNumber("+919790625878"),  // to
                new PhoneNumber("+18305429222"),  // from
                s ).create();
        
    }
}