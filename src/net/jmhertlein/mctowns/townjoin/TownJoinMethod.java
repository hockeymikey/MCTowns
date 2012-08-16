package net.jmhertlein.mctowns.townjoin;

/**
 *
 * @author Joshua
 */
public enum TownJoinMethod {

    INVITATION,
    ECONOMY;

    public static TownJoinMethod parseMethod(String s) throws TownJoinMethodFormatException {
        if (s.equalsIgnoreCase(INVITATION.toString())) {
            return INVITATION;
        } else if (s.equalsIgnoreCase(ECONOMY.toString())) {
            return ECONOMY;
        } else {
            throw new TownJoinMethodFormatException("Error: " + s + " was not a valid String representation of a TownJoinMethod.");
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case INVITATION:
                return "invitation";
            case ECONOMY:
                return "economy";
            default:
                return null;
        }
    }


}