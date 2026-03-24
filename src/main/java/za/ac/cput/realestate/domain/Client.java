/* Client.java
   Client model class using Builder Pattern
   Author: Khonzinkosi Khumalo (230231802)
   Date: 15 March 2026
*/
package za.ac.cput.realestate.domain;

public class Client {
    private final String clientId;
    private final String firstName;
    private final String lastName;
    private final String phone;
    private final String email;
    private final double budget;
    private final String preferredType;

    private Client(Builder builder) {
        this.clientId = builder.clientId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phone = builder.phone;
        this.email = builder.email;
        this.budget = builder.budget;
        this.preferredType = builder.preferredType;
    }

    public String getClientId() { return clientId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public double getBudget() { return budget; }
    public String getPreferredType() { return preferredType; }

    @Override
    public String toString() {
        return "Client{" +
                "clientId='" + clientId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", budget=" + budget +
                ", preferredType='" + preferredType + '\'' +
                '}';
    }

    public static class Builder {
        private String clientId, firstName, lastName, phone, email, preferredType;
        private double budget;

        public Builder setClientId(String clientId) { this.clientId = clientId; return this; }
        public Builder setFirstName(String firstName) { this.firstName = firstName; return this; }
        public Builder setLastName(String lastName) { this.lastName = lastName; return this; }
        public Builder setPhone(String phone) { this.phone = phone; return this; }
        public Builder setEmail(String email) { this.email = email; return this; }
        public Builder setBudget(double budget) { this.budget = budget; return this; }
        public Builder setPreferredType(String preferredType) { this.preferredType = preferredType; return this; }

        public Builder copy(Client client) {
            this.clientId = client.clientId;
            this.firstName = client.firstName;
            this.lastName = client.lastName;
            this.phone = client.phone;
            this.email = client.email;
            this.budget = client.budget;
            this.preferredType = client.preferredType;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }
}