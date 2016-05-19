package dugin.irent.json;

/**
 * Created by Rodrigo on 19/05/2016.
 */

public class Facebook {
    private String id;

    private String email;

    private String imgURL;

    private String sexo;

    private String nome;

    private String faixa_idade;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFaixa_idade() {
        return faixa_idade;
    }

    public void setFaixa_idade(String faixa_idade) {
        this.faixa_idade = faixa_idade;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", email = " + email + ", imgURL = " + imgURL + ", sexo = " + sexo + ", nome = " + nome + ", faixa_idade = " + faixa_idade + "]";
    }
}

