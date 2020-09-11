/*
Daniel Pelepelin
112096007
Rec 30 Section 2
*/

/**
 * ItemInfo Class which contains various information about a specific
 * item that can or has been sold in a given department store.
 */
public class ItemInfo {
    private String name;
    private double price;
    private String rfidTagNumber;
    private String originalLocation;
    private String currentLocation;

    public ItemInfo(String name, double price, String rfidTagNumber, String location) {
        this.name = name;
        this.price = price;
        setRfidTagNumber(rfidTagNumber);
        setOriginalLocation(location);
        setCurrentLocation(location);
    }

    /**
     * Gets the name of the item
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the item
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price of the item
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the item
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the RFID number
     * @return
     */
    public String getRfidTagNumber() {
        return rfidTagNumber;
    }

    /**
     * Sets the RFID number and throws an exception if the length of the number does not equal 9
     * @param rfidTagNumber
     * @throws IllegalArgumentException
     */
    public void setRfidTagNumber(String rfidTagNumber) throws IllegalArgumentException {
        if(rfidTagNumber.length() != 9){
            throw new IllegalArgumentException("rfid number must be 9 characters long.");
        }
        else if(!rfidTagNumber.matches("^[a-fA-F0-9]+$")){
            throw new IllegalArgumentException("Not a hexadecimal number.");
            }
        this.rfidTagNumber = rfidTagNumber.toLowerCase();
    }

    /**
     * Gets the original location of the item
     * @return
     */
    public String getOriginalLocation() {
        return originalLocation;
    }

    /**
     * Sets the orignal location of the item and throws an exception if the length is less than 6.
     * @param originalLocation
     * @throws IllegalArgumentException
     */
    public void setOriginalLocation(String originalLocation) {
        if(originalLocation.length() > 6){
            throw new IllegalArgumentException("orignalLocation must be less than 7 characters.");
        }
        this.originalLocation = originalLocation.toLowerCase();
    }

    /**
     * Gets the current location of an item
     * @return
     */
    public String getCurrentLocation() {
        return currentLocation;
    }

    /**
     * Sets the current location of an item
     * @param currentLocation
     */
    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation.toLowerCase();
    }
}
