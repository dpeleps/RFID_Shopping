/*
Daniel Pelepelin
112096007
Rec 30 Section 2
*/


/**
 * ItemList Class that contains references to the head and tail nodes
 */
public class ItemList {
    private ItemInfoNode head;
    private ItemInfoNode tail;
    private ItemInfoNode cursor;

    /**
     * ItemList constructor
     */
    public ItemList() {
        head = null;
        tail = null;
        cursor = null;
    }

    /**
     * Inserts an item into the list.
     * The order of complexity is O(n) because there is only one while loop iteration within this method.
     *
     * @param name
     * @param rfidTag
     * @param price
     * @param initPosition
     */
    public void insertInfo(String name, String rfidTag, double price, String initPosition) {
        ItemInfo newInfo = new ItemInfo(name, price, rfidTag, initPosition);
        ItemInfoNode newNode = new ItemInfoNode(newInfo);

        if (head == null) { //Checks to see if the list is empty
            head = newNode;
            tail = newNode;
        } else {
            cursor = head;
            while (cursor != null) {
                ItemInfo currentInfo = cursor.getInfo();
                if (cursor == head) {
                    if (newInfo.getRfidTagNumber().compareToIgnoreCase(currentInfo.getRfidTagNumber()) <= 0) {
                        newNode.setNext(head);
                        head.setPrev(newNode);
                        head = newNode;
                        break;
                    } else if (head == tail) {
                        head.setNext(newNode);
                        newNode.setPrev(head);
                        tail = newNode;
                        break;
                    }
                } else if (cursor == tail) {
                    if (newInfo.getRfidTagNumber().compareToIgnoreCase(currentInfo.getRfidTagNumber()) >= 0) {
                        tail.setNext(newNode);
                        newNode.setPrev(tail);
                        tail = newNode;
                        cursor = cursor.getNext();
                    } else {
                        ItemInfoNode prevNode = cursor.getPrev();

                        newNode.setNext(cursor);
                        newNode.setPrev(prevNode);
                        cursor.setPrev(newNode);
                        prevNode.setNext(newNode);
                    }
                } else {
                    if (newInfo.getRfidTagNumber().compareToIgnoreCase(currentInfo.getRfidTagNumber()) <= 0) {
                        ItemInfoNode prevNode = cursor.getPrev();

                        newNode.setNext(cursor);
                        newNode.setPrev(prevNode);
                        cursor.setPrev(newNode);
                        prevNode.setNext(newNode);
                        break;
                    }
                }
                cursor = cursor.getNext();
            }
        }
    }

    /**
     * removeAllPurchased method that removes all nodes that have current location listed
     * as "out" and displays a list of items that have been removed.
     *
     * The order of complexity is O(n) because there is only one while loop iteration within this method.
     */
    public void removeAllPurchased() {
        cursor = head;

        while (cursor != null && cursor.getNext() != null) {
            if (cursor.getInfo().getCurrentLocation().equalsIgnoreCase("out")) {
                System.out.println(cursor.toString());
                if (cursor == head) {
                    head = cursor.getNext();
                    head.setPrev(null);
                } else if (cursor == tail) {
                    tail = tail.getPrev();
                    tail.setNext(null);
                } else {
                    ItemInfoNode nextNode = cursor.getNext();
                    ItemInfoNode prevNode = cursor.getPrev();

                    nextNode.setPrev(prevNode);
                    prevNode.setNext(nextNode);
                }
            }
            cursor = cursor.getNext();
        }
    }

    /**
     * Moves an item with a given rfidTagNumber from a source location to a dest location.
     * The order of complexity is O(n) because there is only one while loop iteration within this method.
     *
     * @param rfidTag
     * @param source
     * @param dest
     * @return
     * @throws InvalidLocationException
     * @throws IllegalArgumentException
     */
    public boolean moveItem(String rfidTag, String source, String dest) throws InvalidLocationException, IllegalArgumentException {
        if (source.equalsIgnoreCase("out")) {
            throw new IllegalArgumentException("Can't move an item that has been checked out.");
        }

        else if (!dest.startsWith("s") && !dest.startsWith("c") && !dest.equalsIgnoreCase("out")) {
            throw new InvalidLocationException();
        }
        cursor = head;
        while (cursor != null) {
            if (cursor.getInfo().getRfidTagNumber().equalsIgnoreCase(rfidTag) && cursor.getInfo().getCurrentLocation().equalsIgnoreCase(source)) {
                cursor.getInfo().setCurrentLocation(dest);
                return true;
            }
            else {
                cursor = cursor.getNext();
            }
        }
        return false;
    }

    /**
     * Prints a neatly formatted list of all items currently in the list.
     * The order of complexity is also O(n) because the while loop iterates through all the items.
     */
    public void printAll() {
        cursor = head;

        while (cursor != null) {
            System.out.println(cursor.toString());
            cursor = cursor.getNext();
        }
    }

    /**
     * Prints a neatly formatted list of all items in a specified current location.
     * dThe order of complexity is O(n) because there is only one while loop iteration within this method.
     *
     * @param location
     */
    public void printByLocation(String location) {
        cursor = head;
        while (cursor != null) {
            if (cursor.getInfo().getCurrentLocation().equalsIgnoreCase(location)) {
                System.out.println(cursor.toString());
            }
            cursor = cursor.getNext();
        }
    }

    /**
     * Takes every item that is currently in the store and on the wrong shelf and places it back where it belongs
     * The order of complexity is O(n) because there is only one while loop iteration within this method.
     */
    public void cleanStore() {
        cursor = head;

        while (cursor != null) {
            if (cursor.getInfo().getCurrentLocation().startsWith("s")) {
                ItemInfo currentInfo = cursor.getInfo();

                if (!currentInfo.getCurrentLocation().equalsIgnoreCase(currentInfo.getOriginalLocation())) {
                    System.out.println(cursor.toString());
                    moveItem(currentInfo.getRfidTagNumber(), currentInfo.getCurrentLocation(), currentInfo.getOriginalLocation());
                }
            }
            cursor = cursor.getNext();
        }
    }

    /**
     * Goes through a given cart and checks out each item
     * The order of complexity is O(n) because there is only one while loop iteration within this method.
     *
     * @param cartNumber
     * @return
     * @throws IllegalArgumentException
     */
    public double checkOut(String cartNumber) throws IllegalArgumentException{
        double count = 0;
        cursor = head;

        if (!cartNumber.startsWith("c")) {
            throw new IllegalArgumentException("Wrong cart number.");
        }
        while (cursor != null) {
            if (cursor.getInfo().getCurrentLocation().equalsIgnoreCase(cartNumber)) {
                ItemInfo currentInfo = cursor.getInfo();
                System.out.println(cursor.toString());
                moveItem(currentInfo.getRfidTagNumber(), currentInfo.getCurrentLocation(), "out");
                count += currentInfo.getPrice();
            }
            cursor = cursor.getNext();
        }
        return count;
    }

    /**
     * Goes through the store's inventory and prints all items that have the given rfidTagNumber.
     *
     * @param rfidTagNumber
     */
    public void printRFID(String rfidTagNumber){
        cursor = head;

        while(cursor != null){
            if (cursor.getInfo().getRfidTagNumber().equalsIgnoreCase(rfidTagNumber)) {
                System.out.println(cursor.toString());
            }
            cursor = cursor.getNext();
        }
    }
}
