/*
Daniel Pelepelin
112096007
Rec 30 Section 2
*/

/**
 * ItemInfoNode Class which contains references to an ItemInfo object
 * as well as two other ItemInfoNode objects.
 */
public class ItemInfoNode {
    private ItemInfo item;
    private ItemInfoNode prev;
    private ItemInfoNode next;

    /**
     * Creates a new ItemInfoNode
     * @param item
     */
    public ItemInfoNode(ItemInfo item){
        this.item = item;
        prev = null;
        next = null;
    }

    /**
     * Sets the information
     * @param info
     */
    public void setInfo(ItemInfo info){
        item = info;
    }

    /**
     * Gets the information
     * @return
     */
    public ItemInfo getInfo(){
        return item;
    }

    /**
     * Links to the next node
     * @param node
     */
    public void setNext(ItemInfoNode node){
        this.next = node;
    }

    /**
     * Links the previous node
     * @param node
     */
    public void setPrev(ItemInfoNode node){
        this.prev = node;
    }

    /**
     * Gets the next node
     * @return
     */
    public ItemInfoNode getNext(){
        return next;
    }

    /**
     * Gets the previous node
     * @return
     */
    public ItemInfoNode getPrev(){
        return prev;
    }

    /**
     * toString method that formats each item
     * @return
     */
     public String toString(){
        return String.format("%-25s%-25S%-25s%-25s%.2f",item.getName(),item.getRfidTagNumber(),item.getOriginalLocation(),item.getCurrentLocation(),item.getPrice());
    }
}
