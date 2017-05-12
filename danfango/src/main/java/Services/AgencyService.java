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
import Model.Genre;
import Model.Movie;
import Model.MovieGenre;
import Model.MovieTrailer;
import Model.Seat;
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
    @Autowired
    GenreService genreService;
    @Autowired
    MovieGenreService movieGenreService;
    @Autowired
    SeatService seatService;

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
        } else if (agency.equals("theatreRoom")) {
            parseTheatreRoomFile();
        } else if (agency.equals("showing")) {
            parseShowingFile();
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
        Document doc = prepareDoc("newTheatre.xml");

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
                String reserved = eElement.getElementsByTagName("reserved").item(0).getTextContent();

                theatre.setAgencyTheatreId(Integer.parseInt(agencyId));
                theatre.setName(name);
                theatre.setAddress(address);
                theatre.setCity(city);
                theatre.setState(state);
                theatre.setStateName(stateName);
                theatre.setZip(zipcode);

                if (reserved.equals("true")) {
                    theatre.setSeatingType(Theatre.SeatingType.Reserved);
                } else {
                    theatre.setSeatingType(Theatre.SeatingType.Nonreserved);
                }

                if (theatreService.getTheatreByAgencyTheatreId(theatre.getAgencyTheatreId()) == null) {
                    theatreService.addTheatre(theatre);

                    NodeList rooms = eElement.getElementsByTagName("rooms");

                    for (int i = 0; i < rooms.getLength(); i++) {
                        Node room = rooms.item(i);
                        Element roomElement = (Element) room;
                        String roomId = roomElement.getElementsByTagName("room").item(0).getTextContent();
                        TheatreRoom tr = new TheatreRoom();
                        tr.setTheatre(theatre);
                        tr.setRoomNumber(roomId);
                        theatreRoomService.addTheatreRoom(tr);
                    }

                } else {
                    theatre.setId(theatreService.getTheatreByAgencyTheatreId(theatre.getAgencyTheatreId()).getId());
                    theatreService.updateTheatre(theatre);
                }

            }
        }
    }

    public void parseTheatreRoomFile() throws ParserConfigurationException, SAXException, IOException, ParseException {
        Document doc = prepareDoc("ROOMS.xml");
        NodeList nList = doc.getElementsByTagName("rooms");

        for (int counter = 0; counter < nList.getLength(); counter++) {
            Node nNode = nList.item(counter);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String roomId = eElement.getElementsByTagName("roomId").item(0).getTextContent();
                String numSeats = eElement.getElementsByTagName("theatreId").item(0).getTextContent();
                TheatreRoom room = theatreRoomService.getTheatreRoomByRoomNumber(roomId);
                room.setTotalSeats(Integer.parseInt(numSeats));

                NodeList seatingLayout = eElement.getElementsByTagName("seatingLayout");
                // prevent adding duplicate seatss
                if (room.getLayout() == null && seatingLayout != null) {
                    String layoutString = "";
                    char seatRow = 'A';
                    int seatNum = 1;
                    for (int i = 0; i < seatingLayout.getLength(); i++) {
                        Node row = seatingLayout.item(i);
                        String rowContent = row.getTextContent();
                        // append this rows seats to the layoutString
                        layoutString += rowContent;
                        // don't add a "|" accidentally at the end
                        if (i < seatingLayout.getLength() - 1) {
                            layoutString += "|";
                        }
                        // create seat objects
                        String[] rowArray = rowContent.split(",");
                        for (int seatIndex = 0; seatIndex < rowArray.length; seatIndex++) {
                            int seatValue = Integer.parseInt(rowArray[seatIndex]);
                            if (seatValue == 1) {
                                Seat seat = new Seat();
                                seat.setRow(String.valueOf(seatRow));
                                seat.setSeatNumber(Integer.toString(seatNum));
                                seat.setTheatreRoom(room);
                                seatService.addSeat(seat);
                                seatNum++;
                            }
                        }
                        seatRow = (char) (seatRow + 1);
                        seatNum = 1;
                    }
                    room.setLayout(layoutString);
                    System.out.println("PRINTING THE LAYOUT STRING___________________");
                    System.out.println("layoutString: " + layoutString);
                }
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
                String genres = eElement.getElementsByTagName("genre").item(0).getTextContent();

                //NEED TO SET TRAILERS
                if (movieService.getMovieByAgencyMovieId(movie.getImdbID()) == null) {
                    movieService.addMovie(movie);
                } // if the movie does exist then we update that movie oobject
                else {
                    movie.setId(movieService.getMovieByAgencyMovieId(movie.getImdbID()).getId());
                    movieService.updateMovie(movie);
                }
                //set all genres on movie
                addMovieGenre(movie, genres);

            }
        }
    }

    public void addMovieGenre(Movie movie, String genres) {
        genres = genres.replaceAll(" ", "");
        String[] splitgenres = genres.split(",");
        for (String genre : splitgenres) {
            Genre g = genreService.getGenreByName(genre);
            // add to movie genre table
            MovieGenre pair = new MovieGenre();
            pair.setGenre(g);
            pair.setMovie(movie);

            if (movieGenreService.getMovieGenresByGenreAndMovie(g, movie) == null) {
                movieGenreService.addMovieGenre(pair);
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
        NodeList nList = doc.getElementsByTagName("theatre");

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
                        Movie mov = (Movie) movieService.getMovieByTitle(moviename);
                        String theatreRoomId = showingElement.getElementsByTagName("theatreRoomID").item(0).getTextContent();
                        TheatreRoom theatreRoom = (TheatreRoom) theatreRoomService.getTheatreRoomByRoomNumber(theatreRoomId);

                        String showtime = showingElement.getElementsByTagName("datetime").item(0).getTextContent();
                        showtime = showtime.replace("T", " ");
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        Date parsedDate = dateFormat.parse(showtime);
                        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

                        if (mov != null) {
                            showing.setTime(timestamp);
                            showing.setMovie(mov);
                            showing.setTheatre(theatre);
                            showing.setTheatreRoom(theatreRoom);
                        }

                        Showing existingShowing = showingService.getShowingByJoe(mov, theatre, timestamp);
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

}
