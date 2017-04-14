/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Model.Movie;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Service
public class AgencyService {

    @Autowired
    MovieService movieService;

    public AgencyService() {
    }

    public void parseFile(String agency) throws Exception{
        if (agency == "movie"){
            parseMovieFile();
        }
    }
    
    public void parseMovieFile() throws ParserConfigurationException, SAXException, IOException, ParseException {
        File inputFile = new File("movieAgency.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        org.w3c.dom.Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("movie");

        for (int counter = 0; counter < nList.getLength(); counter++) {
            Node nNode = nList.item(counter);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                
                Movie movie = new Movie();

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                Date parsedDate = dateFormat.parse(eElement.getElementsByTagName("released").item(0).getTextContent());
                Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                movie.setReleaseDate(timestamp);
                movie.setAgencyMovieId(Integer.parseInt(eElement.getElementsByTagName("imdbID").item(0).getTextContent()));
                movie.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
                movie.setSynopsis(eElement.getElementsByTagName("plot").item(0).getTextContent());
                movie.setMovieScore(Integer.parseInt(eElement.getElementsByTagName("imdbRating").item(0).getTextContent()));
                movie.setRunTime(eElement.getElementsByTagName("runtime").item(0).getTextContent());
                movie.setPoster(eElement.getElementsByTagName("poster").item(0).getTextContent());
                
                

                // if movie does not exist then we add the movie
                if (movieService.getMovieByAgencyId(counter) == null) {
                    movieService.addMovie(movie);
                } // if the movie does exist then we update that movie oobject
                else {
                    movieService.updateMovie(movie);
                }

                System.out.println("title : " + eElement.getElementsByTagName("title").item(0).getTextContent());
                System.out.println("year : " + eElement.getElementsByTagName("year").item(0).getTextContent());
                System.out.println("rated : " + eElement.getElementsByTagName("rated").item(0).getTextContent());
                System.out.println("released : " + eElement.getElementsByTagName("released").item(0).getTextContent());
                System.out.println("imdbID : " + eElement.getElementsByTagName("imdbID").item(0).getTextContent());
                System.out.println("imdbRating : " + eElement.getElementsByTagName("imdbRating").item(0).getTextContent());
                System.out.println("genre : " + eElement.getElementsByTagName("genre").item(0).getTextContent());
                System.out.println("plot : " + eElement.getElementsByTagName("plot").item(0).getTextContent());
                System.out.println("poster : " + eElement.getElementsByTagName("poster").item(0).getTextContent());
                System.out.println("runtime : " + eElement.getElementsByTagName("runtime").item(0).getTextContent());
                System.out.println("actors : " + eElement.getElementsByTagName("actors").item(0).getTextContent());
                System.out.println("director : " + eElement.getElementsByTagName("director").item(0).getTextContent());
                System.out.println("writer : " + eElement.getElementsByTagName("writer").item(0).getTextContent());
                System.out.println("\n");

            }
        }
    }
}
