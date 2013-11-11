package models;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;

import com.avaje.ebean.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity managed by Ebean.
 */
@Entity
public class Node extends Model {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Id
    public Long id;

    @Constraints.Required
    public String uuid;

    @Constraints.Required
    public String name;

    public String info;

    /**
     * Generic query helper for entity with id Long.
     */
    public static Finder<Long,Node> find = new Finder<Long,Node>(Long.class, Node.class);

    /**
     * Return a page.
     *
     * @param page Page to display
     * @param pageSize Number of nodes per page
     * @param sortBy Node property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Node> page(int page, int pageSize, String sortBy, String order, String filter) {
        return
            find.where()
                .ilike("name", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }

}
