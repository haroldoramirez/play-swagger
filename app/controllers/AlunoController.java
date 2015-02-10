package controllers;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import models.Aluno;
import org.h2.engine.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static play.data.Form.form;

@Api(value = "/aluno", description = "CRUD do Aluno")
public class AlunoController extends Controller {
    
    private static final Form<Aluno> alunoForm = Form.form(Aluno.class);
    
    public static Result cria() {
        return ok(views.html.alunos.novo.render(alunoForm));
    }
    
    @Path("/aluno")
    @ApiOperation(value = "Insere Aluno no banco de dados", response = Boolean.class, httpMethod = "POST")
    @Produces({MediaType.APPLICATION_JSON})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of user detail", response = Aluno.class),
            @ApiResponse(code = 404, message = "User with given username does not exist"),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    public static Result insere() {
        Form<Aluno> form = alunoForm.bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(views.html.alunos.novo.render(form));
        }
        Aluno aluno = form.get();
        aluno.save();
        flash("sucesso","Registro gravado com sucesso");
        return redirect(routes.AlunoController.lista());
    }

    @Path("/aluno")
    @ApiOperation(value = "detalha aluno", response = Boolean.class, httpMethod = "GET")
    @Produces({MediaType.APPLICATION_JSON})
    public static Result detalha(Long id) {
        Form<Aluno> formAluno = form(Aluno.class).fill(Aluno.find.byId(id));
        return ok(views.html.alunos.edita.render(id,formAluno));
    }

    @Path("/aluno")
    @ApiOperation(value = "atualiza os dados do aluno", response = Boolean.class, httpMethod = "POST")
    @Produces({MediaType.APPLICATION_JSON})
    public static Result atualiza(Long id) {
        form(Aluno.class).fill(Aluno.find.byId(id));
        
        Form<Aluno> alterarForm = form(Aluno.class).bindFromRequest();
        if (alterarForm.hasErrors()) {
            return badRequest(views.html.alunos.edita.render(id,alterarForm));
        }
        alterarForm.get().update(id);
        flash("sucesso","Aluno " + alterarForm.get().getNome() + " alterado com sucesso");
        return redirect(routes.AlunoController.lista());
    }
    public static Result buscaPorId(Long id) {
        return TODO;
    }

    @GET
    @Path("/aluno")
    @ApiOperation(value = "lista todos os alunos", response = Boolean.class, httpMethod = "GET")
    @Produces({MediaType.APPLICATION_JSON})
    public static Result lista() {
        List<Aluno> alunos = Aluno.find.findList();
        return ok(views.html.alunos.lista.render(alunos));
    }


    @Path("/aluno")
    @ApiOperation(value = "Remove o aluno", response = Boolean.class, httpMethod = "POST")
    @Produces({MediaType.APPLICATION_JSON})
    public static Result remove(Long id) {
        try {
            Aluno.find.ref(id).delete();
            flash("sucesso", "Diretor removido com sucesso");
        } catch (Exception e) {
            flash("erro", play.i18n.Messages.get("global.erro"));
            
        }
        return lista();
    }
}
