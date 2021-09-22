package entities;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column (nullable = false)
    private String calle;
    @Column(nullable = false)
    private String ciudad;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="direccion")
    private ArrayList<Persona> personas;

    public Direccion() {
    }

    public Direccion(int id, String calle, String ciudad, ArrayList<Persona> personas) {
        this.id = id;
        this.calle = calle;
        this.ciudad = ciudad;
        this.personas = personas;
    }

    public int getId() {
        return id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(ArrayList<Persona> personas) {
        this.personas = personas;
    }
}
