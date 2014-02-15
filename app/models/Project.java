package models;

import play.db.ebean.Model;
import javax.persistence.*;
import java.util.*;

/**
 * @Author: Mukund Bhudia
 */
@Entity
public class Project extends Model {

    @Id
    public Long id;
    public String name;
    public String folder;
    @ManyToMany(cascade = CascadeType.REMOVE)
    public List<User> members = new ArrayList<User>();

    public Project(String name, String folder, User owner) {
        this.name = name;
        this.folder = folder;
        this.members.add(owner);
    }

    public static Model.Finder<Long, Project> find = new Model.Finder(Long.class, Project.class);

    public static List<Project> findInvolving(String user){
        return find.where().eq("members.email", user).findList();
    }
}
