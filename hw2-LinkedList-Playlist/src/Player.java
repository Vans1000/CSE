/**
 * @author Vanshdeep Singh
 * Stonybrook ID: 116535948
 * vanshdeep.singh@stonybrook.edu
 * HW2
 * CSE214
 * Recitation: 01
 * Grading TA's: Rohan Sadawarte, Aditi Kulkarni
 */
import java.util.Scanner;
public class Player {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        SongLinkedList playlist = new SongLinkedList();
        boolean quit = false;
        System.out.println("Menu: \n" +
                "(A) Add Song to Playlist " +
                "\n(F) Go to Next Song " +
                "\n(B) Go to Previous Son " +
                "\n(R) Remove Song from Playlist " +
                "\n(L) Play a Song " +
                "\n(C) Clear the Playlist " +
                "\n(S) Shuffle Playlist " +
                "\n(Z) Random Song " +
                "\n(P) Print Playlist " +
                "\n(T) Get the total amount of songs in the playlist " +
                "\n(Q) Exit the playlist");
        while (quit == false) {
            System.out.print("Enter a command: ");
            String command = input.nextLine();
            switch (command.toUpperCase()) {
                case "A":
                    String name = input.nextLine();
                    String artist = input.nextLine();
                    String album = input.nextLine();
                    int length = Integer.parseInt(input.nextLine());
                    Song song = new Song(name,artist,album,length);
                    playlist.insertAfterCursor(song);
                    System.out.println("'"+name+"'" +" by "+artist+"is added to your playlist");
                    break;
                case "F":
                    playlist.cursorForwards();
                    break;
                case "B":
                    playlist.cursorBackwards();
                    break;
                case "R":
                    if(playlist.getSize()!=0) {
                        Song removed = playlist.removeCursor();
                        System.out.println("'" + removed.getName() + "'" + " by " + removed.getArtist() + " was removed from the playlist");
                    }else{
                        System.out.println("Your playlist is empty");
                    }
                    break;
                case "L":
                    String names = input.nextLine();
                    if(playlist.findSongNode(names)!= null) {
                        playlist.play(names);
                        System.out.println("'" + names + "'" + " by " + playlist.findSongNode(names).getData().getArtist() + " is now playing");
                    }else{
                        System.out.println("'"+names+"' not found");
                    }
                    break;
                case "C":
                    playlist.deleteAll();
                    System.out.println("Playlist cleared");
                    break;
                case "S":
                    playlist.shuffle();
                    System.out.println("Playlist shuffled");
                    break;
                case "Z":
                    playlist.play(playlist.random().getName());
                    System.out.println("Playing a random song");
                    break;
                case "P":
                    playlist.printPlaylist();
                    break;
                case "T":
                    if(playlist.getSize()!=0) {
                        System.out.println("Your playlist contains " + playlist.getSize() + " songs.");
                    }else{
                        System.out.println("Your playlist is empty");
                    }
                    break;
                case "Q":
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }
}
