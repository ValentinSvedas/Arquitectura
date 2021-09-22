package entities;

import javax.persistence.*;

@Entity
public class Socio {

    @Id
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private Persona persona;

    @Column
    private String tipo;

    public Socio() {
    }

    public Socio(int id, Persona persona, String tipo) {
        this.id = id;
        this.persona = persona;
        this.tipo = tipo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getTipo() {
        return tipo;
    }

    public int getId() {
        return id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
