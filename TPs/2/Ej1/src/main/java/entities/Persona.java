package entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Persona {
    @Id
    private int id;
    @Column (nullable = false)
    private int anios;
    @Column(nullable = false)
    private String nombre;
    @ManyToOne
    private Direccion direccion;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "personas")
    private List<Turno> Turnos;

    public Persona() {
    }

    public Persona(int id, int anios, String nombre, Direccion direcion, List<Turno> Turnos) {
        this.id = id;
        this.anios = anios;
        this.nombre = nombre;
        this.direccion = direcion;
        this.Turnos = Turnos;
    }

    public int getId() {
        return id;
    }

    public int getAnios() {
        return anios;
    }

    public void setAnios(int anios) {
        this.anios = anios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Direccion getDirecion() {
        return direccion;
    }

    public void setDirecion(Direccion direcion) {
        this.direccion = direcion;
    }

    public List<Turno> getTurnos() {
        return Turnos;
    }

    public void setTurnos(List<Turno> Turnos) {
        this.Turnos = Turnos;
    }
}
