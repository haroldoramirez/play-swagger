package controllers;

import com.wordnik.swagger.annotations.*;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.index;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Api(value = "/aplicacao", description = "renderizar pagina index e sistema")
public class Application extends Controller {


    public static Result index(){
        return ok(index.render());
    }

    @GET
    @Path("/aplicacao/swagger")
    @ApiOperation(value = "Return woot hopefully!", response = Boolean.class, httpMethod = "GET")
    @ApiResponses(value = {@ApiResponse(code = Http.Status.BAD_REQUEST, message = "Invalid status value")})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public static Result swagger(
            @ApiParam(value = "ou falso ou verdadeiro",
                    required = true)
            @PathParam("status") String status){
        if (status.equals("true")) {
            return ok(views.html.swagger.render("teste swagger"));
            
        } else {
            return TODO;
            
        }
    }

}
