package models;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
@ApiModel
public class Aluno extends Model {

    //private static final long serialVersionUID = 1L;

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Constraints.Required
    private String nome;
    
    //@Constraints.Required
    private String ra;

    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @ApiModelProperty(position = 1, required = true, value = "username containing only lowercase letters or numbers")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @ApiModelProperty(position = 2, required = true)
    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    @ApiModelProperty(position = 3, required = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static Model.Finder<Long,Aluno> find = new Model.Finder<Long,Aluno>(Long.class,Aluno.class);
}
