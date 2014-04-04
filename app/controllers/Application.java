package controllers;

import models.Project;
import models.Task;
import models.User;
import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import views.html.*;

public class Application extends Controller {

    public static class Login {

        public String email;
        public String password;

        public String validate(){
            if (User.authenticate(email, password) == null) {
                return "Invalid user or password";
            }
            return null;
        }
    }

    public static Result authenticate() {
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();

        if (loginForm.hasErrors()){
            return badRequest(login.render(loginForm));
        } else {
            session().clear();
            session("email", loginForm.get().email);
            return redirect(
                    routes.Application.index()
            );
        }
    }

    public static Result index() {
        return ok(index.render(
                Project.find.all(),
                Task.find.all()
        ));
    }

    public static Result login() {
        return ok(
                login.render(form(Login.class))
        );
    }

}
