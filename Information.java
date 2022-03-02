package GAME.info;

import java.util.ArrayList;
import java.util.Collections;

public class Information {
    private final Credentials credentials;
    private final String nume;
    private final String tara;
    private final ArrayList<String> jocuri;

    private Information(InfoBuilder builder) {
        this.credentials = builder.credentials;
        this.jocuri = builder.jocuri;
        this.nume = builder.nume;
        this.tara = builder.tara;
        Collections.sort(jocuri);

    }

    public String getName() {
        return nume;
    }


    public Credentials getCredentials() {
        return credentials;
    }

    @Override
    public String toString() {
        return "Information{" +
                "credentials=" + credentials +
                ", jocuri=" + jocuri +
                ", nume='" + nume + '\'' +
                ", tara='" + tara + '\'' +
                '}';
    }

    public static class InfoBuilder {
        private Credentials credentials;
        private ArrayList<String> jocuri;
        private String nume;
        private String tara;

        public InfoBuilder nume(String nume) {
            this.nume = nume;
            return this;
        }

        public InfoBuilder tara(String tara) {
            this.tara = tara;
            return this;
        }

        public InfoBuilder jocuri(ArrayList<String> jocuri) {
            this.jocuri = jocuri;
            return this;
        }

        public InfoBuilder credentials(Credentials credentials) {
            this.credentials = credentials;
            return this;
        }

        public Information build() {
            return new Information(this);
        }

    }

}
