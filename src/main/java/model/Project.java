
package model;

import java.util.Date;


public class Project {
    
    private int id ;
    private String name ;
    private String description;
    private Date createdAt ;
    private Date updaedAt ;

    public Project(int id, String name, String description, Date createdAt, Date updaedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updaedAt = updaedAt;
    }

    @Override
    public String toString() {
        return "Project{" + "id=" + id + ", name=" + name + ", description=" + description + ", createdAt=" + createdAt + ", updaedAt=" + updaedAt + '}';
    }
    
    
    
    
    
    
}
