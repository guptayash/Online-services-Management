import java.net.URI;

import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.CallCreator;
import com.twilio.twiml.Play;
import com.twilio.twiml.Say;
import com.twilio.twiml.VoiceResponse;
import com.twilio.type.PhoneNumber;



public class PhoneCalls {

    // find your Account SID & Auth Token in Twilio Console: twilio.com/console
    public static final String ACCOUNT_SID = "ACxxxxxxxxx"; // your Account SID found in the Twilio Console
    public static final String AUTH_TOKEN = "yyyy"; // your auth token also found in the Twilio Console

    // use the +12025551234 format for the value in the following constant
    public static final String TWILIO_NUMBER = "+12023359765"; // Twilio phone number for dialing outbound phone calls

    public static final String NGROK_BASE_URL = "https://0e64e563.ngrok.io"; // paste your ngrok Forwarding URL such as https://0e64e563.ngrok.io


    public static void main(String[] args) {

        // instantiate the TwilioRestClient helper library with our Twilio credentials set as constants
        TwilioRestClient client = new TwilioRestClient.Builder(ACCOUNT_SID, AUTH_TOKEN).build();

        // lets us know our app is up and running
        get("/", (request, response) -> "Spark app up and running!");

        // twiml endpoint
        post("/twiml", (request, response) -> {
            // generate the TwiML response to tell Twilio what to do
            Say sayHello = new Say.Builder("Hello from Twilio, Java 8 and Spark!").build();
            Play playSong = new Play.Builder("https://api.twilio.com/cowbell.mp3").build();
            VoiceResponse voiceResponse = new VoiceResponse.Builder().say(sayHello).play(playSong).build();
            return voiceResponse.toXml();
        });

        // this endpoint handles dialing outbound phone calls with the TwilioRestClient object
        get("/dial-phone/:number", (request, response) -> {
            String phoneNumber = request.params(":number");
            /* as long as the phone number is not blank or null, we'll attempt to dial it, but
               you can add more exception handling here */
            if (!phoneNumber.isEmpty()) {
                PhoneNumber to = new PhoneNumber(phoneNumber);
                PhoneNumber from = new PhoneNumber(TWILIO_NUMBER);
                URI uri = URI.create(NGROK_BASE_URL + "/twiml");

                // Make the call using the TwilioRestClient we instantiated
                Call call = new CallCreator(to, from, uri).create(client);
                return "Dialing " + phoneNumber + " from your Twilio phone number...";
            } else {
                return "Hey, you need to enter a valid phone number in the URL!";
            }
        });
    }
}