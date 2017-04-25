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
import Model.Showing;
import Model.Theatre;
import org.w3c.dom.Document;

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
    @Autowired
    TheatreService theatreService;
    //@Autowired
    //ShowingService showingService;


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
        } else if (agency.equals("theatre")) {
            parseTheatreFile();
        }

    }

    public Document prepareDoc(String fileName) throws SAXException, IOException, ParserConfigurationException {
        ClassLoader classLoader = getClass().getClassLoader();
        File inputFile = new File(classLoader.getResource(fileName).getFile());
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        return doc;
    }

    public void parseTheatreFile() throws ParserConfigurationException, SAXException, IOException, ParseException {
        Document doc = prepareDoc("theatreAgency.xml");

        NodeList nList = doc.getElementsByTagName("theatre");

        for (int counter = 0; counter < nList.getLength(); counter++) {
            Node nNode = nList.item(counter);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                Theatre theatre = new Theatre();

                String agencyId = eElement.getElementsByTagName("agencyID").item(0).getTextContent();
                String name = eElement.getElementsByTagName("name").item(0).getTextContent();
                String address = eElement.getElementsByTagName("address").item(0).getTextContent();
                String city = eElement.getElementsByTagName("city").item(0).getTextContent();
                String state = eElement.getElementsByTagName("state").item(0).getTextContent();
                String zipcode = eElement.getElementsByTagName("zipcode").item(0).getTextContent();
                theatre.setAgencyTheatreId(Integer.parseInt(agencyId));
                theatre.setName(name);
                theatre.setAddress(address);
                theatre.setCity(city);
                theatre.setState(state);
                theatre.setZip(zipcode);
                
                if(theatreService.getTheatreByAgencyTheatreId(theatre.getAgencyTheatreId()) == null){
                    theatreService.addTheatre(theatre);
                }
                else{
                    theatre.setId(theatreService.getTheatreByAgencyTheatreId(theatre.getAgencyTheatreId()).getId());
                    theatreService.updateTheatre(theatre);
                }

                // use the theatre service to add the theatre here V 
            }
        }
    }

    public void parseMovieFile() throws ParserConfigurationException, SAXException, IOException, ParseException {
        Document doc = prepareDoc("movieAgency3.xml");
        NodeList nList = doc.getElementsByTagName("movie");

        for (int counter = 0; counter < nList.getLength(); counter++) {
            Node nNode = nList.item(counter);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                Movie movie = new Movie();
                
                if (!eElement.getElementsByTagName("released").item(0).getTextContent().equals("N/A")) {
                    System.out.println("RELEASE: "+ eElement.getElementsByTagName("released").item(0).getTextContent());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date parsedDate = dateFormat.parse(eElement.getElementsByTagName("released").item(0).getTextContent());
                    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                    movie.setReleaseDate(timestamp);
                }
                if (!eElement.getElementsByTagName("imdbRating").item(0).getTextContent().equals("N/A")) {
                    movie.setMovieScore(Double.parseDouble(eElement.getElementsByTagName("imdbRating").item(0).getTextContent()));
                }

                String imdbID = (eElement.getElementsByTagName("imdbID").item(0).getTextContent());
                movie.setImdbID(imdbID);
                String tmdbID = (eElement.getElementsByTagName("tmbdID").item(0).getTextContent());
                movie.setTmdbID(tmdbID);
                String title = (eElement.getElementsByTagName("title").item(0).getTextContent());
                movie.setTitle(title);
                String rated = (eElement.getElementsByTagName("rated").item(0).getTextContent());
                movie.setRating(rated);
                String plot = (eElement.getElementsByTagName("plot").item(0).getTextContent());
                movie.setSynopsis(plot);
                String poster = (eElement.getElementsByTagName("poster").item(0).getTextContent());
                movie.setPoster(poster);
                String backdrop = (eElement.getElementsByTagName("backdrop").item(0).getTextContent());
                movie.setBackdrop(backdrop);
                String runtime = (eElement.getElementsByTagName("runtime").item(0).getTextContent());
                movie.setRunTime(runtime);
                
                //NEED TO SET TRAILERS
                
              

                if (movieService.getMovieByAgencyMovieId(movie.getImdbID()) == null) {
                    movieService.addMovie(movie);
                } // if the movie does exist then we update that movie oobject
                else {
//                    Movie mov = movieService.getMovieByAgencyMovieId(movie.getAgencyMovieId()) ;
//                    mov=movie;
                    movie.setId(movieService.getMovieByAgencyMovieId(movie.getImdbID()).getId());
                    movieService.updateMovie(movie);
                }

            }
        }
    }

    public void parseCrewFile() throws ParserConfigurationException, SAXException, IOException, ParseException {
        Document doc = prepareDoc("actorAgency2.xml");
        NodeList nList = doc.getElementsByTagName("actor");

        for (int counter = 0; counter < nList.getLength(); counter++) {
            Node nNode = nList.item(counter);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                CrewMember actor = new CrewMember();

                String fullName = eElement.getElementsByTagName("name").item(0).getTextContent();
                actor.setFullName(fullName);
                String biography = eElement.getElementsByTagName("biography").item(0).getTextContent();
                actor.setBiography(biography);
                String poster = eElement.getElementsByTagName("poster").item(0).getTextContent();
                actor.setPoster(poster);

                if (!eElement.getElementsByTagName("birthday").item(0).getTextContent().equals("") && eElement.getElementsByTagName("birthday").item(0).getTextContent().length() > 4) {
                    String dob = eElement.getElementsByTagName("birthday").item(0).getTextContent();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date parsedDate = dateFormat.parse(dob);
                    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                    actor.setDob(timestamp);
                }
                if (!eElement.getElementsByTagName("age").item(0).getTextContent().equals("")) {
                    int age = Integer.parseInt(eElement.getElementsByTagName("age").item(0).getTextContent());
                    actor.setAge(age);
                }
                NodeList movies = eElement.getElementsByTagName("movie");

                for (int i = 0; i < movies.getLength(); i++) {
                    Node movie = movies.item(i);
                    Element movieElement = (Element) movie;
                    String movieid = movieElement.getTextContent();
                    System.out.println("MOVIEID: " + movieid);
                    Movie m = movieService.getMovieByAgencyMovieId(movieid);
                    if (m != null) {
                        //actor.getMovies().add(m);
                    }
                }
//                  crewService.addCrewMember(actor);

                if (crewService.getCrewMemberByNameAndDOB(actor.getFullName(), actor.getDob()) == null) {
                    crewService.addCrewMember(actor);
                } // if the movie does exist then we update that movie oobject
                else {
                    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ " + actor.getFullName());
                    actor.setId(crewService.getCrewMemberByNameAndDOB(actor.getFullName(), actor.getDob()).getId());
                    crewService.updateCrewMember(actor);
                }

            }
        }
    }

    public void parseShowingFile() throws ParserConfigurationException, SAXException, IOException, ParseException {
        Document doc = prepareDoc("showingAgency.xml");
        NodeList nList = doc.getElementsByTagName("movie");

        for (int counter = 0; counter < nList.getLength(); counter++) {
            Node nNode = nList.item(counter);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                Showing showing = new Showing();

                int agencyTheatreId = Integer.parseInt(eElement.getAttribute("id"));
                String theatreName = eElement.getAttribute("name");
                Theatre theatre = theatreService.getTheatreByAgencyTheatreId(agencyTheatreId);
                if (theatre != null) {
                    NodeList showings = eElement.getElementsByTagName("showtime");

                    for (int i = 0; i < showings.getLength(); i++) {
                        Node showingelemnt = showings.item(i);
                        Element showingElement = (Element) showingelemnt;
                        String moviename = showingElement.getElementsByTagName("moviename").item(0).getTextContent();
                        String showtime = showingElement.getElementsByTagName("datetime").item(0).getTextContent();
                        
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm");
                        Date parsedDate = dateFormat.parse(showtime);
                        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                        showing.setTime(timestamp);
                        
//                        if(showingService.getshowingByRoomTheatreNameAndTime){
//                            showing.setId(showingservice.existing);
//                            crewService.updateCrewMember(actor);
//                        }
//                        else{
//                            showingService.addShowing(showing);
//                        }

                    }

                }

            }
        }
    }
}
