package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Turno {
    @Id
    private int id;
    @Column
    private Date time;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Persona> personas;

    public Turno() {
    }
    public Turno(int id, Date time, List<Persona> personas) {
        this.id = id;
        this.time = time;
        this.personas = personas;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }


    public int getId() {
        return id;
    }
}
