/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Service
public class AgencyService {

    @Autowired
    MemberService memberService;
    public AgencyService(){
    }
    
    
    public static void parseMovieFile(){
        try{
            File inputFile = new File("movieAgency.xml");
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(inputFile);
         doc.getDocumentElement().normalize();
         System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
         NodeList nList = doc.getElementsByTagName("student");

        }
    }
}
