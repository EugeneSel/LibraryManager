package model;

public class Member {
    public enum SubscriptionType {
        BASIC(0), PREMIUM(1), VIP(0);
        private int index;

        private SubscriptionType(int index) {
            this.index = index;
        };



        /**
         * returns the index
         * @return index
         */
        public int getIndex() { return this.index; };

        /**
         * sets the index to the given value
         * @param index index to change to
         */
        public void setIndex(int index) { this.index = index; };
    }

    private static int autoincrement = 0;

    private int id;
    private String lastName;
    private String firstName;
    private String address;
    private String email;
    private String phoneNumber;
    private SubscriptionType subscription;

    public Member() {
        this.id = autoincrement++;
    };

    public Member(String lastName, String firstName, String address, String email, String phoneNumber, SubscriptionType subscription) {
        this.id = autoincrement++;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.subscription = subscription;
    };

    public int getId() { return this.id; };
    public String getLastName() { return this.lastName; };
    public String getFirstName() { return this.firstName; };
    public String getAddress() { return this.address; };
    public String getEmail() { return this.email; };
    public String getPhoneNumber() { return this.phoneNumber; };
    public SubscriptionType getSubscription() { return this.subscription; };

    public void setId(int id) { this.id = id; };
    public void setLastName(String lastName) { this.lastName = lastName; };
    public void setFirstName(String firstName) { this.firstName = firstName; };
    public void setAddress(String address) { this.address = address; };
    public void setEmail(String email) { this.email = email; };
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; };
    public void setSubscription(SubscriptionType subscription) { this.subscription = subscription; };

    public String toString() {
        return "\nId: " + this.id +
            "\nLast name: " + this.lastName +
            "\nFirst name: " + this.firstName +
            "\nAddress: " + this.address +
            "\nEmail: " + this.email +
            "\nPhone number: " + this.phoneNumber +
            "\nSubscription: " + this.subscription;
    }
}