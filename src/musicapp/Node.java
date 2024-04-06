
package musicapp;

/**
 *
 * @author tanvi
 */
public class Node {
    
    private final Songs song;
    private Node next;

    public Node(Songs song) {
        this.song = song;
        this.next = null;
    }

    //we do not nned a setter for the song 
    public Songs getSong() {
        return song;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

//this is mine 