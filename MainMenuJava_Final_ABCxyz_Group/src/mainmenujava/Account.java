package mainmenujava;

/**
 *
 * @author Nguyen  Pham Dinh Phuc Ce140024
 */
public class Account {
    private String acUser;//username
    private String acPass;//password

    public Account(String acUser, String acPass) {
        this.acUser = acUser;
        this.acPass = acPass;
    }

    public String getAcUser() {
        return acUser;
    }

    public void setAcUser(String acUser) {
        this.acUser = acUser;
    }

    public String getAcPass() {
        return acPass;
    }

    public void setAcPass(String acPass) {
        this.acPass = acPass;
    }

    @Override
    public String toString() {
        return "Account{" + "acUser=" + acUser + ", acPass=" + acPass + '}';
    }

    
    
    
}
