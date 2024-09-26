/**
 * @author Vanshdeep Singh
 * Stonybrook ID: 116535948
 * vanshdeep.singh@stonybrook.edu
 * HW2
 * CSE214
 * Recitation: 01
 * Grading TA's: Rohan Sadawarte, Aditi Kulkarni
 */
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Random;

public class SongLinkedList {
    private SongNode head;
    private SongNode tail;
    private SongNode cursor;
    private int size;

    /**
     * Creates SongLinkedList, which is a doubly linked list to represent playlist
     */
    public SongLinkedList() {
        this.head = null;
        this.tail = null;
        this.cursor = null;
        this.size = 0;
    }

    /**
     * Plays the song in the playlist that matches the name
     * @param name
     * String name of song that should be played
     * @throws IllegalArgumentException
     * If no song with matching name is found, throws IllegalArgumentException that says song is not found
     */
    public void play(String name) throws IllegalArgumentException {
        SongNode temp = head;
        while (temp != null) {
            if (temp.getData().getName().equals(name)) {
                try {
                    AudioInputStream AIS = AudioSystem.getAudioInputStream(new File(
                            temp.getData().getName()+".wav"));
                    Clip c = AudioSystem.getClip();
                    c.open(AIS);
                    c.start();
                } catch (Exception e) {}
                System.out.println("Playing: " + name + ".wav");

                return;
            }
            temp = temp.getNext();
        }
        throw new IllegalArgumentException("Song not found");
    }

    /**
     * Moves cursor to the next song
     */
    public void cursorForwards() {
        if (cursor != null && cursor.getNext() != null) {
            cursor = cursor.getNext();
            System.out.println("Cursor moved to the next song");
        } else {
            System.out.println("Already at the end of the playlist.");
        }
    }

    /**
     * Moves cursor to the previous song
     */
    public void cursorBackwards() {
        if (cursor != null && cursor.getPrev() != null) {
            cursor = cursor.getPrev();
            System.out.println("Cursor moved to the previous song");
        } else {
            System.out.println("Already at the beginning of the playlist.");
        }
    }

    /**
     * Inserts song after cursor
     * @param newSong
     * Song that should be added to the playlist
     */
    public void insertAfterCursor(Song newSong) {
        if (newSong == null) {
            throw new IllegalArgumentException("Song cannot be null.");
        }

        SongNode newNode = new SongNode(newSong, null, null);

        if (cursor == null) {//First Item
            head = newNode;
            tail = newNode;
            cursor = newNode;
        } else if (cursor.getNext() == null) {//Last Item
            cursor.setNext(newNode);
            newNode.setPrev(cursor);
            tail = newNode;
            cursor = newNode;
        } else {//Middle
            newNode.setNext(cursor.getNext());
            newNode.setPrev(cursor);
            cursor.getNext().setPrev(newNode);
            cursor.setNext(newNode);
            cursor = newNode;
        }
        size++;
    }

    /**
     * Removes the song that is at current cursor
     * @return
     * Returns the song that was removed
     */
    public Song removeCursor() {
        if (cursor == null) {
            throw new IllegalArgumentException("Cursor is null. Cannot remove.");
        }

        Song removedSong = cursor.getData();

        if (cursor == head && cursor == tail) {//If only one item
            head = null;
            tail = null;
            cursor = null;
        } else if (cursor == head) {//If cursor is at head
            head = cursor.getNext();
            head.setPrev(null);
            cursor = head;
        } else if (cursor == tail) {//If cursor is last item
            tail = cursor.getPrev();
            tail.setNext(null);
            cursor = tail;
        } else {//If cursor is in the middle
            cursor.getPrev().setNext(cursor.getNext());
            cursor.getNext().setPrev(cursor.getPrev());
            cursor = cursor.getNext();
        }

        size--;
        return removedSong;
    }

    /**
     * Returns size of the playlist
     * @return
     * Returns int which stores the amount of songs in the playlist
     */
    public int getSize() {
        return size;
    }

    /**
     * Gets random song from playlist
     * @return
     * Returns a random song from the playlist
     */
    public Song random() {
        if (size == 0) {
            return null;
        }
        Random rand = new Random();
        int randIndex = rand.nextInt(size);

        SongNode temp = head;
        for (int i = 0; i < randIndex; i++) {
            temp = temp.getNext();
        }

        play(temp.getData().getName());
        return temp.getData();
    }

    /**
     * Shuffles the playlist
     */
    public void shuffle() {
        SongLinkedList shuffledList = new SongLinkedList();
        Random rand = new Random();
        SongNode currentCursor = cursor;
        int shuffledSize = 0;
        while (size > 0) {
            Song randomSong = removeRandomSong();
            shuffledList.insertAfterCursor(randomSong);
            shuffledSize++;
        }

        head = shuffledList.head;
        tail = shuffledList.tail;
        cursor = findSongNode(currentCursor.getData().getName());
        size = shuffledList.getSize();
    }

    /**
     * Removes random song from playlist
     * @return
     * Returns the song that has been removed
     */
    private Song removeRandomSong() {
        Random rand = new Random();
        int randIndex = rand.nextInt(size);
        cursor = head;

        for (int i = 0; i < randIndex; i++) {
            cursor = cursor.getNext();
        }

        return removeCursor();
    }

    /**
     * Finds song with matching name
     * @param name
     * String name that you want to find a match with in the playlist
     * @return
     * Returns the song that matches the name of the song you want to find, or null if it isn't found
     */
    public SongNode findSongNode(String name) {
        SongNode temp = head;

        while (temp != null) {
            if (temp.getData().getName().equals(name)) {
                return temp;
            }
            temp = temp.getNext();
        }
        return null;
    }

    /**
     * Prints teh playlist in tablular form
     */
    public void printPlaylist() {
        System.out.printf("%-25s %-25s %-25s %-10s\n", "Song", "| Artist", "| Album", "| Length (s)");
        System.out.println("--------------------------------------------------------------------------------");
        SongNode temp = head;
        while (temp != null) {
            String isCurrent = (temp == cursor) ? "<-" : "";
            System.out.printf("%-25s %-25s %-25s %-10d%s\n",
                    temp.getData().getName(),
                    temp.getData().getArtist(),
                    temp.getData().getAlbum(),
                    temp.getData().getLength(),
                    isCurrent);
            temp = temp.getNext();
        }
    }

    /**
     * Deletes all elements in the playlist
     */
    public void deleteAll() {
        head = null;
        tail = null;
        cursor = null;
        size = 0;
    }

}
