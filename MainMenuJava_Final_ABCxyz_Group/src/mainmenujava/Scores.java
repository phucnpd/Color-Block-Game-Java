package mainmenujava;

/**
 *
 * @author Nguyen Pham Dinh Phuc
 */
public class Scores {
    String sUser;
    String sScore;

    public Scores(String sUser, String sScore) {
        this.sUser = sUser;
        this.sScore = sScore;
    }

    public String getsUser() {
        return sUser;
    }

    public void setsUser(String sUser) {
        this.sUser = sUser;
    }

    public String getsScore() {
        return sScore;
    }

    public void setsScore(String sScore) {
        this.sScore = sScore;
    }

    @Override
    public String toString() {
        return "User:" + sUser + ":"+ "Score=" + sScore + '}'+"\n";
    }

    
    
}
