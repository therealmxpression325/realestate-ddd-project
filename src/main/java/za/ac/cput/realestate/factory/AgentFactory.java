package za.ac.cput.realestate.factory;
/* AgentFactory.java
   Author: Sinoxolo Kobeni (230801846)
   Date: 20 March 2026
*/
import za.ac.cput.realestate.domain.Agent;
import za.ac.cput.realestate.util.Helper;

public class AgentFactory {

    @org.jetbrains.annotations.Nullable
    public static Agent createAgent(String agentId, String firstName, String lastName,
                                    String phone, String email, String licenseNumber,
                                    double commissionRate) {

        // Validate required fields
        if (Helper.isNullOrEmpty(agentId) ||
                Helper.isNullOrEmpty(firstName) ||
                Helper.isNullOrEmpty(lastName) ||
                Helper.isNullOrEmpty(phone) ||
                Helper.isNullOrEmpty(email) ||
                Helper.isNullOrEmpty(licenseNumber) ||
                !Helper.isPositive(commissionRate)) {
            return null;
        }

        return new Agent.Builder()
                .setAgentId(agentId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPhone(phone)
                .setEmail(email)
                .setLicenseNumber(licenseNumber)
                .setCommissionRate(commissionRate)
                .build();
    }
}
