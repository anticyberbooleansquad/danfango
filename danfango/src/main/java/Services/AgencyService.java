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
import Model.CrewMemberMovie;
import Model.Movie;
import Model.MovieTrailer;
import Model.Showing;
import Model.Theatre;
import Model.TheatreRoom;
import java.util.ArrayList;
import java.util.Random;
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
    @Autowired
    CrewMemberMovieService crewMemberMovieService;
    @Autowired
    ShowingService showingService;
    @Autowired
    TheatreRoomService theatreRoomService;
    @Autowired
    MovieTrailerService movieTrailerService;
    @Autowired
    LocationService locationService;

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
        } else if (agency.equals("trailers")) {
            parseTrailersFile();
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
                String stateName = locationService.getFullNameValue(state.toLowerCase());
                String zipcode = eElement.getElementsByTagName("zipcode").item(0).getTextContent();
                theatre.setAgencyTheatreId(Integer.parseInt(agencyId));
                theatre.setName(name);
                theatre.setAddress(address);
                theatre.setCity(city);
                theatre.setState(state);
                theatre.setStateName(stateName);
                theatre.setZip(zipcode);

                if (theatreService.getTheatreByAgencyTheatreId(theatre.getAgencyTheatreId()) == null) {
                    theatreService.addTheatre(theatre);
                    //createTheatreRoom(theatre);
                } else {
                    theatre.setId(theatreService.getTheatreByAgencyTheatreId(theatre.getAgencyTheatreId()).getId());
                    theatreService.updateTheatre(theatre);
                }

                // use the theatre service to add the theatre here V 
            }
        }
    }

    public void parseMovieFile() throws ParserConfigurationException, SAXException, IOException, ParseException {
        Document doc = prepareDoc("newMovie.xml");
        NodeList nList = doc.getElementsByTagName("movie");

        for (int counter = 0; counter < nList.getLength(); counter++) {
            Node nNode = nList.item(counter);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                System.out.println("MOVIE ELEMT : " + eElement);
                Movie movie = new Movie();

                if (!eElement.getElementsByTagName("released").item(0).getTextContent().equals("N/A") && !eElement.getElementsByTagName("released").item(0).getTextContent().equals("")) {
                    System.out.println("RELEASE: " + eElement.getElementsByTagName("released").item(0).getTextContent());
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
                String genres= eElement.getElementsByTagName("genre").item(0).getTextContent();

                //NEED TO SET TRAILERS
                if (movieService.getMovieByAgencyMovieId(movie.getImdbID()) == null) {
                    movieService.addMovie(movie);
                } // if the movie does exist then we update that movie oobject
                else {
                    movie.setId(movieService.getMovieByAgencyMovieId(movie.getImdbID()).getId());
                    movieService.updateMovie(movie);
                }
//                //populate movie_genre table
//                if(genres.contains("")){
//                
//                }
                

            }
        }
    }

    public void parseTrailersFile() throws ParserConfigurationException, SAXException, IOException, ParseException {
        Document doc = prepareDoc("newMovie.xml");
        NodeList nList = doc.getElementsByTagName("movie");

        for (int counter = 0; counter < nList.getLength(); counter++) {
            Node nNode = nList.item(counter);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                NodeList trailers = eElement.getElementsByTagName("trailer");
                String imdbID = (eElement.getElementsByTagName("imdbID").item(0).getTextContent());

                System.out.println("TRAILERS NODE LIST " + trailers);
                for (int i = 0; i < trailers.getLength(); i++) {
                    Node trailer = trailers.item(i);
                    Element trailerElement = (Element) trailer;
                    System.out.println("Trailer Element: " + trailerElement);
                    String trailerId = trailerElement.getElementsByTagName("id").item(0).getTextContent();
                    System.out.println("Trailer ID: " + trailerId);
                    String trailerKey = trailerElement.getElementsByTagName("key").item(0).getTextContent();
                    System.out.println("Trailer KEY: " + trailerKey);

                    MovieTrailer mt = new MovieTrailer();
                    mt.setAgencyId(trailerId);
                    Movie m = movieService.getMovieByAgencyMovieId(imdbID);
                    if (m != null) {
                        mt.setMovie(m);
                    }
                    mt.setYoutubeKey(trailerKey);
                    movieTrailerService.addMovieTrailer(mt);
                }

            }
        }
    }

    public void parseCrewFile() throws ParserConfigurationException, SAXException, IOException, ParseException {
        Document doc = prepareDoc("newCrew.xml");
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
                //String imdbId = eElement.getElementsByTagName("imdbID").item(0).getTextContent();
                //actor.setAgencyCrewId(imdbId);

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

                ArrayList<Movie> crewMember_movies = new ArrayList();
                for (int i = 0; i < movies.getLength(); i++) {
                    Node movie = movies.item(i);
                    Element movieElement = (Element) movie;
                    String movieid = movieElement.getTextContent();
                    System.out.println("MOVIEID: " + movieid);
                    Movie m = movieService.getMovieByAgencyMovieId(movieid);
                    // list of movies per crew member
                    if (m != null) {
                        crewMember_movies.add(m);
                        //actor.getMovies().add(m); 
                    }
                }
//                  crewService.addCrewMember(actor);

                if (crewService.getCrewMemberByNameAndDOB(actor.getFullName(), actor.getDob()) == null) {
                    crewService.addCrewMember(actor);

                } // if the movie does exist then we update that movie oobject
                else {
                    System.out.println("$$$$$$$$$$$$$$$$$$$$ " + actor.getFullName());
                    actor.setId(crewService.getCrewMemberByNameAndDOB(actor.getFullName(), actor.getDob()).getId());
                    crewService.updateCrewMember(actor);
                }

                // check if relation exists if not add 
                for (int i = 0; i < crewMember_movies.size(); i++) {
                    CrewMemberMovie relation = new CrewMemberMovie();
                    if (crewMemberMovieService.getCrewMemberMovieByJoe(crewMember_movies.get(i), actor) == null) {
                        relation.setMovie(crewMember_movies.get(i));
                        relation.setCrewMember(actor);
                        crewMemberMovieService.addCrewMemberMovie(relation);
                    } 
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

                        Movie mov = (Movie) movieService.getMovieByTitle(moviename);

                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm");
                        Date parsedDate = dateFormat.parse(showtime);
                        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                        showing.setTime(timestamp);
                        TheatreRoom room = theatreRoomService.getTheatreRoomByTheatre(theatre);
                        Showing existingShowing = showingService.getShowingByJoe(mov, theatre, room, timestamp);
                        if (existingShowing != null) {
                            showing.setId(existingShowing.getId());
                            showingService.updateShowing(showing);
                        } else {
                            showingService.addShowing(showing);
                        }
                    }

                }

            }
        }
    }

    public void createTheatreRoom(Theatre theatre) {
        TheatreRoom room = new TheatreRoom();
        double seatingType = Math.random();
        boolean[][] layout = new boolean[2][2];

        // >25 reservation type 1 >.5 reservation type 2>.75 reservationtype 3 <.75 no reservation
        if (seatingType <= .25) {
            // 132 seats
            room.setTotalSeats(2);
            room.setTotalSeatsRemaining(2);
            room.setSeatingType(TheatreRoom.SeatingType.Reserved);
            // basic seats at first 
            layout[0][0] = true;
            layout[0][1] = true;
            layout[1][0] = false;
            layout[1][1] = false;
            room.setLayout(layout);
        } else if (seatingType <= .50 && seatingType > .25) {
            // 132 seats
            room.setTotalSeats(2);
            room.setTotalSeatsRemaining(2);
            room.setSeatingType(TheatreRoom.SeatingType.Reserved);
            // basic seats at first 
            layout[0][0] = true;
            layout[0][1] = false;
            layout[1][0] = true;
            layout[1][1] = false;
            room.setLayout(layout);

        } else if (seatingType <= .75 && seatingType > .5) {
            // 132 seats
            room.setTotalSeats(2);
            room.setTotalSeatsRemaining(2);
            room.setSeatingType(TheatreRoom.SeatingType.Reserved);
            // basic seats at first 
            layout[0][0] = false;
            layout[0][1] = true;
            layout[1][0] = false;
            layout[1][1] = true;
            room.setLayout(layout);

        } else {
            room.setTotalSeats(200);
            room.setTotalSeatsRemaining(200);
        }
        theatreRoomService.addTheatreRoom(room);

    }

}
