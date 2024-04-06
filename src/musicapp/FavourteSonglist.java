
package musicapp;

import javax.swing.JOptionPane;
import static MusicApp.MusicAppGUI.artistTF;
import static MusicApp.MusicAppGUI.titleTF;
/**
 *
 * @author tanvi
 */

public class FavourteSonglist {
    
class FavourteSonglist implements FavourteSongInterface {
    
    private Node head;
    private boolean repeat;

    //constructor 
    public FavourteSonglist() {
        this.head = null;
        this.repeat = false;
    }

    @Override
    public void addSong() {
        
        //if the repeat is activated that means the last node is connected to the head and this might result to errors 
        if (repeat) {
            JOptionPane.showMessageDialog(null, "repeat is activated, deactivate the repeat first.");
            System.out.println("repeat is activated");
            return;
        } else {
            //taking user input 
            String title = titleTF.getText();
            String artist = artistTF.getText();

            // Check if title or artist is empty
            if (title.isEmpty() || artist.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter both title and artist.");
                return;
            }

            //adding the new song 
            Song newSong = new Song(title, artist);
            Node newNode = new Node(newSong);

            //if the head is null then the new song is the head 
            if (head == null) {
                head = newNode;
                JOptionPane.showMessageDialog(null, "First Song added successfully!");
                MusicApp.repeatTOGGLE2.setEnabled(true);
                if (repeat) {
                    head.setNext(head); // Pointing to itself to indicate a circular list
                }
            } else {
                Node last = getLastNode();//calling the method 
                if (last != null) {//which it should be not 
                    last.setNext(newNode);
                    JOptionPane.showMessageDialog(null, "Added to the list of liked songs.");
                    MusicApp.repeatTOGGLE2.setEnabled(true);
                    if (repeat) {
                        newNode.setNext(head); // Pointing the last node to the head to create a circular list
                        head = newNode; // Update the head to the newly added node to maintain circularity
                    }
                } else {
                    // Handle the case where last node is null
                    JOptionPane.showMessageDialog(null, "Error: Last node is null.");
                }
            }
        }
    }

    @Override
    public void printPlaylist() {
        //if the repeat is activated that means the last node is connected to the head and this might result to errors 
        if (repeat) {
            JOptionPane.showMessageDialog(null, "repeat is activated, deactivate the repeat first.");
            System.out.println("repeat is activated");
            return;
        } else {
            if (head == null) {
                JOptionPane.showMessageDialog(null, "There is nothing to print");
                return;
            }
            //start from the first  
            Node current = head;
            StringBuilder playlistContent = new StringBuilder();
            do {
                playlistContent.append("Title: ").append(current.getSong().getTitle()).append(" - Artist: ").append(current.getSong().getArtist()).append("\n");
                current = current.getNext();
                //if it takes the whole circle then stop the loop 
                if (current == head) {
                    break;
                }
            } while (current != null);

            JOptionPane.showMessageDialog(null, playlistContent.toString());
        }
    }

    @Override
    public void deleteSong() {
        //if the repeat is activated that means the last node is connected to the head and this might result to errors 
        if (repeat) {
            JOptionPane.showMessageDialog(null, "repeat is activated, deactivate the repeat first.");
            System.out.println("repeat is activated");
            return;
        } else {
            //checking if the list is empty first 
            if (head == null) {
                JOptionPane.showMessageDialog(null, "The liked list is empty");
                return;
            }

            String title = titleTF.getText();
            String artist = artistTF.getText();

            //start from the head 
            Node current = head;
            //assuming the node before head is null 
            //that means the repeat mode is deactivated 
            Node prev = null;
            boolean deletionSuccess = false;

            do {
                if (current.getSong().getTitle().equalsIgnoreCase(artist) || current.getSong().getTitle().equalsIgnoreCase(title)) {
                    if (prev == null) {
                        head = head.getNext();
                        JOptionPane.showMessageDialog(null, "Removed successfully.");
                        deletionSuccess = true;
                        //if it is on repeat we need to keep the cicle 
                        if (repeat) {
                            Node last = getLastNode();
                            last.setNext(head); // Updating the last node's next reference
                        }
                    } else {
                        prev.setNext(current.getNext());
                        JOptionPane.showMessageDialog(null, "Removed successfully.");
                        deletionSuccess = true;
                    }
                    break; // Exit the loop once deletion is successful
                }
                prev = current;
                current = current.getNext();
            } while (current != head && current != null); // Stop when we reach the head again or if current is null (end of list)

            if (!deletionSuccess) {
                JOptionPane.showMessageDialog(null, "Deletion failed! Check the spellings!");
            }
        }
    }
    
   @Override
   public int countSongs() {
       //if the repeat is activated that means the last node is connected to the head and this might result to errors 
       if (repeat) {
           JOptionPane.showMessageDialog(null, "repeat is activated, deactivate the repeat first.");
           System.out.println("repeat is activated");
           return 0;
       } else {
           //check if there is noting in the list 
           if (head == null) {
               JOptionPane.showMessageDialog(null, "The list is empty.");
               return 0;
           }

           //using a variable to track the number of the nodes 
           int count = 0;
           //start from the beginning 
           Node current = head;
           do {
               count++;
               current = current.getNext();
               //assuming the repeat mode is off, this will make the last node pointing to null in this case breaking the loop when it is null 
               if (current == null) { // Add a null check here
                   
                   break; // Exit the loop if current is null
                   
               }
           } while (current != head);

           JOptionPane.showMessageDialog(null, "Number of liked songs are: " + count);
           return count;
       }
   }

    @Override
    public void setRepeat() {
        //calling the method and assign it to the last 
        Node last = connectingLastNodeToFirst();
        // Updating the last node's next reference to make the list circular
        last.setNext(head); 
        JOptionPane.showMessageDialog(null, "The repeat mode is activated.");
        //keep track of the variable that it is settled to false by the constructor
        this.repeat = true;
        
    }
    
    private Node connectingLastNodeToFirst() {
       //check if the list is empty
       if (head == null) {
           System.out.println("List is empty!");
           return null; // Return null if the list is empty
       }

       //start from the beginning 
       Node current = head;
       while (current.getNext() != null && current.getNext() != head) {
           current = current.getNext();
       }
       System.out.println("Last song point to first song");
       return current;
   }
    
    @Override
    public void setNotRepeat () {
        
        // Disable repeat mode
        Node last = getOffRepeating();
        last.setNext(null); // Update the last node's next reference to null
        JOptionPane.showMessageDialog(null, "The repeat mode is deactivated.");
        this.repeat = false;
        
    }
    
    //getting of repeat 
    private Node getOffRepeating() {
        int count = 0;
        
        if (head == null) {
            System.out.println("List is empty!");
            return null; // Return null if the list is empty
        }
        //start from the beginning 
        Node current = head;
        while (current.getNext() != head ) {
            count ++ ;
            current = current.getNext();
        }
        System.out.println("last node point to null");
        return current;
    }
    
    @Override
    public void searchSong(String title, String artist) {
        //if the repeat is activated that means the last node is connected to the head and this might result to errors 
        if (repeat) {
            JOptionPane.showMessageDialog(null, "repeat is activated, deactivate the repeat first.");
            System.out.println("repeat is activated");
            return;
        } else {
            //checking if the list is empty 
            if (head == null) {
                JOptionPane.showMessageDialog(null, "The liked list is empty.");
                return;
            }

            boolean found = false;
            StringBuilder result = new StringBuilder();

            //start from the beginning 
            Node current = head;
            do {
                if (current != null && current.getSong() != null) {
                    Song currentSong = current.getSong();
                    if (currentSong.getTitle().equalsIgnoreCase(title) || currentSong.getArtist().equalsIgnoreCase(artist)) {
                        result.append("Title: ").append(currentSong.getTitle()).append(", Artist: ").append(currentSong.getArtist()).append("\n");
                        found = true;
                    }
                    current = current.getNext();
                }
            } while (current != head && current != null); // Stop when we reach the head again or if current is null (end of list)

            if (found) {
                JOptionPane.showMessageDialog(null, "Search results:\n" + result.toString());
            } else {
                JOptionPane.showMessageDialog(null, "No songs found matching the search criteria.");
            }
        }
    }

    private Node getLastNode() {
        if (head == null) {
            return null; // Return null if the list is empty
        }

        //start from the beginning 
        Node current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        return current;
    }
    
    @Override
    public void addLastSongToGenre() {
        
        // Take user input for the genre (piano/hiphop)
        String userInput = JOptionPane.showInputDialog(null, "Enter a genre (hiphop/piano):");
        //if the repeat is activated that means the last node is connected to the head and this might result to errors 
        if (repeat) {
            JOptionPane.showMessageDialog(null, "repeat is activated, deactivate the repeat first.");
            System.out.println("repeat is activated");
            return;
        } 
        
        // Find the last node in the list
        Node lastNode = getLastNode();
        if (userInput == null) {
            // User cancelled the operation
            return;
        }

        switch (userInput.toLowerCase()) {
            case "hiphop" -> {
                // Check if the playlist is empty
                if (head == null) {
                    JOptionPane.showMessageDialog(null, "Liked songs list is empty!");
                    return;
                }

                if (lastNode == null) {
                    // Something went wrong, handle error
                    JOptionPane.showMessageDialog(null, "Error: Last node (song) not found!");
                    return;
                }
                
                if (repeat) {
                    JOptionPane.showMessageDialog(null, "repeat is activated");
                    System.out.println("repeat is activated");
                } else {
                    // Add the last song to the Hip-Hop playlist
                    GenrePlaylist.getInstance().addHipHopSong(lastNode.getSong());
                    //deleting it after it is successfully added 
                    deleteLastSong();
                }
            }

            case "Countrymusic" -> {
                // Check if the playlist is empty
                if (head == null) {
                    JOptionPane.showMessageDialog(null, "Liked songs list is empty!");
                    return;
                }

                if (lastNode == null) {
                    // Something went wrong, handle error
                    JOptionPane.showMessageDialog(null, "Error: Last node not found!");
                    return;
                }

                if (repeat) {
                    JOptionPane.showMessageDialog(null, "repeat is activated");
                    System.out.println("repeat is activated");
                } else {
                    // Add the last song to the Piano playlist
                    GenrePlaylist.getInstance().addPianoSong(lastNode.getSong());
                    //deleting it after it is successfully added 
                    deleteLastSong();
                }
                
            }
            default -> JOptionPane.showMessageDialog(null, "Invalid genre!");
        }
    }
    
    // Method to delete the last song from the liked songs list
    private void deleteLastSong() {
        Node currentNode = head;
        Node previousNode = null;

        // Traverse to the last node
        while (currentNode.getNext() != null) {
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }

        // Remove the last node
        if (previousNode == null) {
            // If there's only one node in the list
            head = null;
        } else {
            previousNode.setNext(null);
        }
    }
}}

