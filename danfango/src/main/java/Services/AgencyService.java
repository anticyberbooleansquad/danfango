/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Model.CrewMember;
import Model.Movie;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dao.AgencyDAO;
import Model.Agency;
import Model.Movie;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Service
public class AgencyService {

    @Autowired
    MovieService movieService;
    @Autowired
    CrewMemberService crewService;

    private AgencyDAO agencyDAO;

    public AgencyService() {
    }

    public void setAgencyDAO(AgencyDAO agencyDAO) {
        this.agencyDAO = agencyDAO;
    }

    @Transactional
    public void addAgency(Agency a) {
        this.agencyDAO.addAgency(a);
    }

    @Transactional
    public void updateAgency(Agency a) {
        this.agencyDAO.updateAgency(a);
    }

    @Transactional
    public List<Agency> listAgencys() {
        return this.agencyDAO.listAgencys();
    }

    @Transactional
    public Agency getAgencyById(int id) {
        return this.agencyDAO.getAgencyById(id);
    }

    @Transactional
    public void removeAgency(int id) {
        this.agencyDAO.removeAgency(id);
    }

    public void parseFile(String agency) throws Exception {
        if (agency.equals("movie")) {
            parseMovieFile();
        } else if (agency.equals("actor")) {
            parseCrewFile();
        }
    }

    public void parseMovieFile() throws ParserConfigurationException, SAXException, IOException, ParseException {
        ClassLoader classLoader = getClass().getClassLoader();
        File inputFile = new File(classLoader.getResource("movieAgency.xml").getFile());
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
                if (!eElement.getElementsByTagName("released").item(0).getTextContent().equals("N/A")) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
                    Date parsedDate = dateFormat.parse(eElement.getElementsByTagName("released").item(0).getTextContent());
                    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                    movie.setReleaseDate(timestamp);
                }
                if (!eElement.getElementsByTagName("imdbRating").item(0).getTextContent().equals("N/A")) {
                    movie.setMovieScore(Double.parseDouble(eElement.getElementsByTagName("imdbRating").item(0).getTextContent()));
                }
                movie.setAgencyMovieId(eElement.getElementsByTagName("imdbID").item(0).getTextContent());
                movie.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
                movie.setSynopsis(eElement.getElementsByTagName("plot").item(0).getTextContent());
                movie.setRunTime(eElement.getElementsByTagName("runtime").item(0).getTextContent());
                movie.setPoster(eElement.getElementsByTagName("poster").item(0).getTextContent());

//                movieService.addMovie(movie);
                // if movie does not exist then we add the movie
                if (movieService.getMovieByAgencyId(movie.getAgencyMovieId()) == null) {
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

    public void parseCrewFile() throws ParserConfigurationException, SAXException, IOException, ParseException {
        ClassLoader classLoader = getClass().getClassLoader();
        File inputFile = new File(classLoader.getResource("actorAgency.xml").getFile());
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        org.w3c.dom.Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("actor");

        for (int counter = 0; counter < nList.getLength(); counter++) {
            Node nNode = nList.item(counter);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                CrewMember actor = new CrewMember();

                actor.setFullName(eElement.getElementsByTagName("name").item(0).getTextContent());

                if (!eElement.getElementsByTagName("birthday").item(0).getTextContent().equals("")) {
                    String dob = eElement.getElementsByTagName("birthday").item(0).getTextContent();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date parsedDate = dateFormat.parse(dob);
                    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                    actor.setDob(timestamp);
                }

                if (!eElement.getElementsByTagName("age").item(0).getTextContent().equals("")) {
                    actor.setAge(Integer.parseInt(eElement.getElementsByTagName("age").item(0).getTextContent()));
                }
                actor.setBiography(eElement.getElementsByTagName("biography").item(0).getTextContent());

                crewService.addCrewMember(actor);

            }
        }
    }
}
