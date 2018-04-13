package qsbk.app.model.relationship;

public enum Relationship {
    NO_REL_CHATED("no_rel_chated"),
    FRIEND("friend"),
    FOLLOW_REPLIED("follow_replied"),
    FOLLOW_UNREPLIED("follow_unreplied"),
    FAN("fan"),
    BLACK("black"),
    NO_REL("no_rel"),
    MYSELF("myself");
    
    public final String mRelationship;

    private Relationship(String str) {
        this.mRelationship = str;
    }

    public String getRelationship() {
        return this.mRelationship;
    }

    public String toString() {
        return this.mRelationship;
    }

    public static Relationship getRelationShipFromStr(String str) {
        if (NO_REL_CHATED.mRelationship.equals(str)) {
            return NO_REL_CHATED;
        }
        if (FRIEND.mRelationship.equals(str)) {
            return FRIEND;
        }
        if (FOLLOW_REPLIED.mRelationship.equals(str)) {
            return FOLLOW_REPLIED;
        }
        if (FOLLOW_UNREPLIED.mRelationship.equals(str)) {
            return FOLLOW_UNREPLIED;
        }
        if (FAN.mRelationship.equals(str)) {
            return FAN;
        }
        if (BLACK.mRelationship.equals(str)) {
            return BLACK;
        }
        if (NO_REL.mRelationship.equals(str)) {
            return NO_REL;
        }
        if (MYSELF.mRelationship.equals(str)) {
            return MYSELF;
        }
        return null;
    }
}
