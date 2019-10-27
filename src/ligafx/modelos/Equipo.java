package ligafx.modelos;

import java.util.ArrayList;
import java.util.List;

public class Equipo extends Entidad {

    private String nombre;

    private String nombreClub;

    private String presidente;

    private String entrenador;

    private int fundacion;

    private String escudo;

    private String telefono;

    private String direccion;

    private String web;

    private Estadio estadio;

    private List<Jugador> jugadores;

    public Equipo(int id) {
        super(id);

        this.nombre = "";
        this.nombreClub = "";
        this.presidente = "";
        this.entrenador = "";
        this.fundacion = 0;
        this.escudo = "";
        this.telefono = "";
        this.direccion = "";
        this.web = "";

        this.estadio = null;
        this.jugadores = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreClub() {
        return nombreClub;
    }

    public void setNombreClub(String nombreClub) {
        this.nombreClub = nombreClub;
    }

    public String getPresidente() {
        return presidente;
    }

    public void setPresidente(String presidente) {
        this.presidente = presidente;
    }

    public String getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(String entrenador) {
        this.entrenador = entrenador;
    }

    public int getFundacion() {
        return fundacion;
    }

    public void setFundacion(int fundacion) {
        this.fundacion = fundacion;
    }

    public String getEscudo() {
        return escudo;
    }

    public void setEscudo(String escudo) {
        this.escudo = escudo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

}
