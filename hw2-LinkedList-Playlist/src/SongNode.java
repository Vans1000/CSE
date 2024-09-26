/**
 * @author Vanshdeep Singh
 * Stonybrook ID: 116535948
 * vanshdeep.singh@stonybrook.edu
 * HW2
 * CSE214
 * Recitation: 01
 * Grading TA's: Rohan Sadawarte, Aditi Kulkarni
 */
public class SongNode {
    private Song data;
    private SongNode prev;
    private SongNode next;

    /**
     * Creates SongNode with null for all data
     */
    public SongNode() {
        this.data = null;
        this.prev = null;
        this.next = null;
    }

    /**
     * Creates song node with data of next, previous, and current song
     * @param data
     * Stores data of current song
     * @param next
     * Stores the node of the next song
     * @param prev
     * Stores the node fo the previous song
     */
    public SongNode(Song data,SongNode next, SongNode prev) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    /**
     * Getter for current song data
     * @return
     * Returns data of current song
     */
    public Song getData() {
        return data;
    }

    /**
     * Setter for Song
     * @param data
     * Sets current node to the data of the song
     */
    public void setData(Song data) {
        this.data = data;
    }

    /**
     * Getter for the previous node
     * @return
     * Returns the data of the previous node
     */
    public SongNode getPrev() {
        return prev;
    }

    /**
     * Setter for the previous node
     * @param prev
     * Sets prev to the pervious node in the list
     */
    public void setPrev(SongNode prev) {
        this.prev = prev;
    }

    /**
     * Getter for the next node
     * @return
     * Returns the data of the next node
     */
    public SongNode getNext() {
        return next;
    }

    /**
     * Setter for the next node
     * @param next
     * Sets next to the next node in the list
     */
    public void setNext(SongNode next) {
        this.next = next;
    }

}
