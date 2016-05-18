package dugin.irent.domain;


/**
 * Created by Rodrigo on 18/05/2016.
 */
public class FacebookInfo {

    private String nome, faixa_idade, email, sexo, id, imgURL;


    public FacebookInfo(String nome, String faixa_idade, String email, String sexo, String id, String imgURL) {
        this.nome = nome;
        this.faixa_idade = faixa_idade;
        this.email = email;
        this.sexo = sexo;
        this.id = id;
        this.imgURL = imgURL;
    }

    public String getNome() {
        return nome;
    }

    public String getFaixa_idade() {
        return faixa_idade;
    }

    public String getEmail() {
        return email;
    }

    public String getSexo() {
        return sexo;
    }

    public String getId() {
        return id;
    }

    public String getImgURL() {
        return imgURL;
    }
}
