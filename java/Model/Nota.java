package Model;

public class Nota {
    private int idNota;
    private double valor;
    private int idTarefa; // FK

    public Nota() {}

    public Nota(int idNota, double valor, int idTarefa) {
        this.idNota = idNota;
        this.valor = valor;
        this.idTarefa = idTarefa;
    }

    public int getIdNota() { return idNota; }
    public void setIdNota(int idNota) { this.idNota = idNota; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    public int getIdTarefa() { return idTarefa; }
    public void setIdTarefa(int idTarefa) { this.idTarefa = idTarefa; }
}