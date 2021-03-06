package ligafx.modelos;

import java.util.*;

public class Jugador extends Entidad {

    private enum TipoCambio {
        ENTRA,
        SALE
    }

    private String nombre;

    private String apodo;

    private String paisNacimiento;

    private String nacionalidad;

    private Date fechaNacimiento;

    private int dorsal;

    private Posicion posicion;

    private int peso;

    private int altura;

    private String imagen;

    // en lugar de definirlo como ArrayList se define con la interfaz List, cuando se instancie sera un ArrayList
    private final List<Gol> goles;

    private final List<Tarjeta> tarjetas;

    private final Map<TipoCambio, Integer> cambios;

    // el id será el que tiene el jugador en la web resultados-futbol.com desde donde se rastrean los datos
    public Jugador(int id) {
        super(id);

        this.nombre = "";
        this.apodo = "";
        this.nacionalidad = "";
        this.paisNacimiento = "";
        this.fechaNacimiento = null;
        this.dorsal = 0;
        this.posicion = null;
        this.peso = 0;
        this.altura = 0;
        this.imagen = "";

        goles = new ArrayList<>();
        tarjetas = new ArrayList<>();
        cambios = new EnumMap<>(TipoCambio.class);
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

    public String getPaisNacimiento() {
        return paisNacimiento;
    }

    public void setPaisNacimiento(String paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getNumeroGolesDePenalti() {
        int ngoles = 0;

        for (Gol gol : this.goles) {
            if (gol.isPenalti()) {
                ngoles++;
            }
        }

        return ngoles;
    }

    public int getNumeroGolesPropiaPuerta() {
        int n = 0;

        for (Gol gol: goles) {
            if (gol.isPropiaMeta()) {
                n++;
            }
        }

        return n;
    }

    public int getVecesCambiado() {
        return cambios.get(TipoCambio.SALE);
    }

    public void setVecesCambiado(int veces) {
        cambios.put(TipoCambio.SALE, veces);
    }

    public int getVecesEntra() {
        return cambios.get(TipoCambio.ENTRA);
    }

    public void setVecesEntra(int veces) {
        cambios.put(TipoCambio.ENTRA, veces);
    }

    public int getNumeroTarjetasAmarillas() {
        return getNumeroTarjetas(TipoTarjeta.AMARILLA);
    }

    public int getNumeroTarjetasRojas() {
        return getNumeroTarjetas(TipoTarjeta.ROJA);
    }

    private int getNumeroTarjetas(TipoTarjeta tipo) {
        int n = 0;

        for (Tarjeta tarjeta : this.tarjetas) {
            if (tarjeta.getTipo() == tipo) {
                n++;
            }
        }

        return n;
    }
}
