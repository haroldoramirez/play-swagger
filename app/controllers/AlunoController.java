package controllers;

import models.Aluno;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import static play.data.Form.form;

import java.util.List;

public class AlunoController extends Controller {
    
    private static final Form<Aluno> alunoForm = Form.form(Aluno.class);
    
    public static Result cria() {
        return ok(views.html.alunos.novo.render(alunoForm));
    }
 
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
    
    public static Result detalha(Long id) {
        Form<Aluno> formAluno = form(Aluno.class).fill(Aluno.find.byId(id));
        return ok(views.html.alunos.edita.render(id,formAluno));
    }

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

    public static Result lista() {
        List<Aluno> alunos = Aluno.find.findList();
        return ok(views.html.alunos.lista.render(alunos));
    }

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
