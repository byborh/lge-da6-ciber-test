package eiffel.da6.basic_rest.dto;

import java.util.Date;

public class UserDTO {

    private int id;
    private String username;
    private Date date_naiss;

    public UserDTO(int id, String username, Date date_nais) {
        this.id = id;
        this.username = username;
        this.date_naiss = date_nais;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate_naiss() {
        return date_naiss;
    }

    public void setDate_naiss(Date date_naiss) {
        this.date_naiss = date_naiss;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", date_nais=" + date_naiss +
                '}';
    }

    public String toHTML() {
        return "<div class='user' id='"+id+"'>"+
              "<h1>Nom : "+username+"</h1>"+
              "<p>Ne(e) le "+date_naiss+"</p>"+
            "</div>";
    }
}
