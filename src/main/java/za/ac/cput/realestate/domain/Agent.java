/* Agent.java
   Agent model class using Builder Pattern
   Author: Sinoxolo Kobeni (230801846)
   Date: 20 March 2026
*/

package za.ac.cput.realestate.domain;

public class Agent {

    private String agentId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String licenseNumber;
    private double commissionRate;

    private Agent(Builder builder) {
        this.agentId = builder.agentId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phone = builder.phone;
        this.email = builder.email;
        this.licenseNumber = builder.licenseNumber;
        this.commissionRate = builder.commissionRate;
    }

    public String getAgentId() { return agentId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getLicenseNumber() { return licenseNumber; }
    public double getCommissionRate() { return commissionRate; }

    @Override
    public String toString() {
        return "Agent{" +
                "agentId='" + agentId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", commissionRate=" + commissionRate +
                '}';
    }

    public static class Builder {
        private String agentId;
        private String firstName;
        private String lastName;
        private String phone;
        private String email;
        private String licenseNumber;
        private double commissionRate;

        public Builder setAgentId(String agentId) {
            this.agentId = agentId;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setLicenseNumber(String licenseNumber) {
            this.licenseNumber = licenseNumber;
            return this;
        }

        public Builder setCommissionRate(double commissionRate) {
            this.commissionRate = commissionRate;
            return this;
        }

        public Builder copy(Agent agent) {
            this.agentId = agent.agentId;
            this.firstName = agent.firstName;
            this.lastName = agent.lastName;
            this.phone = agent.phone;
            this.email = agent.email;
            this.licenseNumber = agent.licenseNumber;
            this.commissionRate = agent.commissionRate;
            return this;
        }

        public Agent build() {
            return new Agent(this);
        }
    }
}