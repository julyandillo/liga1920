package ligafx.modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Jugador extends Entidad {

    private String nombre;

    private String apodo;

    private String nacionalidad;

    private Date fechaNacimiento;

    private int dorsal;

    private Posicion posicion;

    private int peso;

    private int altura;

    // en lugar de definirlo como ArrayList se define con la interfaz List, cuando se instancie sera un ArrayList
    private List<Gol> goles;

    private List<Tarjeta> tarjetas;

    public Jugador(int id) {
        super(id);

        this.nombre = "";
        this.apodo = "";
        this.nacionalidad = "";
        this.fechaNacimiento = null;
        this.dorsal = 0;
        this.posicion = null;
        this.peso = 0;
        this.altura = 0;

        this.goles = new ArrayList<>();
        this.tarjetas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public List<Gol> getGoles() {
        return goles;
    }

    public List<Tarjeta> getTarjetas() {
        return tarjetas;
    }

}
