package controllers;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import views.html.*;

import models.*;

public class Application extends Controller {

    /**
     * This result directly redirect to application home.
     */
    public static Result GO_HOME = redirect(
        routes.Application.list(0, "name", "asc", "")
    );

    /**
     * Handle default path requests, redirect to list.
     */
    public static Result index() {
        return GO_HOME;
    }

    /**
     * Display the paginated list.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on node name
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                Node.page(page, 10, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }

    /**
     * Display the 'edit form'.
     *
     * @param id Id of the node to edit
     */
    public static Result edit(Long id) {
        Form<Node> nodeForm = form(Node.class).fill(
            Node.find.byId(id)
        );
        return ok(
            editForm.render(id, nodeForm)
        );
    }

    /**
     * Handle the 'edit form' submission.
     *
     * @param id Id of the node to edit
     */
    public static Result update(Long id) {
        Form<Node> nodeForm = form(Node.class).bindFromRequest();
        if(nodeForm.hasErrors()) {
            return badRequest(editForm.render(id, nodeForm));
        }
        nodeForm.get().update(id);
        flash("success", "Node " + nodeForm.get().name + " has been updated");
        return GO_HOME;
    }

    /**
     * Display the 'new form'.
     */
    public static Result create() {
        Form<Node> nodeForm = form(Node.class);
        return ok(
            createForm.render(nodeForm)
        );
    }

    /**
     * Handle the 'new form' submission.
     */
    public static Result save() {
        Form<Node> nodeForm = form(Node.class).bindFromRequest();
        if(nodeForm.hasErrors()) {
            return badRequest(createForm.render(nodeForm));
        }
        nodeForm.get().save();
        flash("success", "Node " + nodeForm.get().name + " has been created");
        return GO_HOME;
    }

    /**
     * Handle deletion.
     */
    public static Result delete(Long id) {
        Node.find.ref(id).delete();
        flash("success", "Node has been deleted");
        return GO_HOME;
    }

}
