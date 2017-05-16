package Controllers;

/**
 *
 * @author joeg332
 */
import Model.User;
import Configuration.MyServletContextListener;
import Services.AgencyService;
import Services.AuthenticationService;
import Services.UserService;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import javax.servlet.ServletContext;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Controller
public class SignInController {

    @Autowired
    AuthenticationService authenticationService;
    @Autowired

    UserService userService;
    @Autowired
    AgencyService agencyService;

    @RequestMapping(value = "/signinpage")
    protected ModelAndView getSignInPage(HttpServletRequest request) {

        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);

        ModelAndView modelandview = new ModelAndView("signinpage");
        return modelandview;
    }

    @RequestMapping(value = "/submitCredentials", method = RequestMethod.POST)
    protected ModelAndView submitCredentials(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest request) {
        ModelAndView modelandview;

        boolean authenticated = authenticationService.authenticate(email, password);
        if (authenticated) {
            User user = userService.getUserByEmail(email);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            modelandview = new ModelAndView("redirect:/index");
        } else {
            modelandview = new ModelAndView("signinpage");
            modelandview.addObject("signinError", "Incorrect credentials entered. Please try again.");
        }
        return modelandview;
    }

    //this is temporary we will make this function fully and not hard code it 
    @RequestMapping(value = "/submitAgencyCredentials")
    protected ModelAndView submitAgencyCredentials() throws Exception {
        ModelAndView modelandview;
//        agencyService.parseFile("movie");
//        agencyService.parseFile("trailers");
//        agencyService.parseFile("actor");
        //agencyService.parseFile("theatre");
        modelandview = new ModelAndView("index");
        return modelandview;

    }

    @RequestMapping(value = "/updateMovies")
    protected ModelAndView updateMovies() throws Exception {
        ModelAndView modelandview;
        agencyService.parseFile("movie");
        modelandview = new ModelAndView("index");
        return modelandview;
    }

    @RequestMapping(value = "/updateTheatres")
    protected ModelAndView updateTheatres() throws Exception {
        ModelAndView modelandview;
        agencyService.parseFile("theatre");
        modelandview = new ModelAndView("index");
        return modelandview;
    }

    @RequestMapping(value = "/updateShowings")
    protected ModelAndView updateShowings() throws Exception {
        ModelAndView modelandview;
        agencyService.parseFile("showing");
        modelandview = new ModelAndView("index");
        return modelandview;
    }

    @RequestMapping(value = "/updateTheatreRooms")
    protected ModelAndView updateTheatreRooms() throws Exception {
        ModelAndView modelandview;
        agencyService.parseFile("theatreRoom");
        modelandview = new ModelAndView("index");
        return modelandview;
    }

    @RequestMapping(value = "/logout")
    protected ModelAndView logout(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.invalidate();

        ModelAndView modelandview = new ModelAndView("redirect:/index");
        return modelandview;
    }
    
    @RequestMapping(value = "/forgotpassword")
    protected ModelAndView forgotPassword(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);
        return new ModelAndView("forgotpassword");
    }
    
    @RequestMapping(value = "/submitEmail", method = RequestMethod.POST)
    protected ModelAndView forgotPassword(@RequestParam("email") String email, HttpServletRequest request)
    {
        String contextPath = request.getContextPath();
        System.out.println("Path: " + contextPath);
        request.setAttribute("contextPath", contextPath);
        User user = userService.getUserByEmail(email);
        if(user != null)
        {
            String newPassword = getSaltString();
            user.setPassword(authenticationService.hash(newPassword));
            userService.updateUser(user);
            try{
                sendEmail(email, newPassword);
            }
            catch(Exception e)
            {
                System.out.println("email failed");
            }
            request.setAttribute("sentEmail", "An email has been sent with your temporary password");
        }
        else
        {
            request.setAttribute("nonEmail", "The email you have selected is not  attached to an account");
        }
        return new ModelAndView("forgotpassword");
    }
    
    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
    
    protected void sendEmail(String email, String password) throws AddressException, MessagingException
    {
        Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("aspencse308@gmail.com", "aspen308team");
			}
		  });
        // Recipient's email ID needs to be mentioned.
        String to = email;
        // Sender's email ID needs to be mentioned
        String from = "aspencse308@gmail.com";
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // Set Subject: header field
            message.setSubject("Temporary Password");
            // Now set the actual message
            System.out.println("password: " + password);
            message.setText("Your temporary password is: " + password);
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }  
    }
    
}
