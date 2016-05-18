package dugin.irent.eventBus;

/**
 * Created by Rodrigo on 18/05/2016.
 */
public class MessageEB {

    private String nomeClasse;

    public MessageEB(String nomeClasse) {
        this.nomeClasse = nomeClasse;
    }

    public String getNomeClasse() {
        return nomeClasse;
    }

    public void setNomeClasse(String nomeClasse) {
        this.nomeClasse = nomeClasse;
    }
}
