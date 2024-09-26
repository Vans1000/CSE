/**
 * @author Vanshdeep Singh
 * Stonybrook ID: 116535948
 * vanshdeep.singh@stonybrook.edu
 * HW2
 * CSE214
 * Recitation: 01
 * Grading TA's: Rohan Sadawarte, Aditi Kulkarni
 */
public class Song {
    private String name;
    private String artist;
    private String album;
    private int length;

    /**
     * Contructs a song with no name,artists, album or length
     */
    public Song() {
        this.name = "";
        this.artist = "";
        this.album = "";
        this.length = 0;
    }

    /**
     * Constructs a song with atrributes
     * @param name
     * String to hold name of song
     * @param artist
     * String to hold artisti of song
     * @param album
     * String to hold album the song was found on
     * @param length
     * String to hold length of song
     */
    public Song(String name, String artist, String album, int length) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.length = length;
    }

    /**
     * Getter for name
     * @return
     * Returns string of the name of the song
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     * @param name
     * String name holds the name of song
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for artist name
     * @return
     * Returns String of artist name
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Setter for artist name
     * @param artist
     * String artist holds the name of the artist
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * Getter for the album name
     * @return
     * Returns the name of the album
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Setter for album name
     * @param album
     * String album holds the name of the album
     */
    public void setAlbum(String album) {
        this.album = album;
    }

    /**
     * Getter for length of song
     * @return
     * Returns the length of the song
     */
    public int getLength() {
        return length;
    }

    /**
     * Setter for the length of the song
     * @param length
     * Int length holds the length of the song
     */
    public void setLength(int length) {
        this.length = length;
    }

}
