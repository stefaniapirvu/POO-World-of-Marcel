package GAME.info;

public class Credentials {
    private String email;
    private String parola;
    public Credentials(){
    }

    public Credentials(String email, String parola){
        this.email = email;
        this.parola = parola;
    }

    public String getEmail(){
        return email;
    }
    public String getParola(){
        return parola;
    }


    @Override
    public String toString() {
        return "Credentials{" +
                "email='" + email + '\'' +
                ", parola='" + parola + '\'' +
                '}';
    }

}
