package Model;

public class Usuario {
    private int idUsuario;
    private String nome;
    private String email;
    private String tipo; // aluno ou professor

    public Usuario() {}

    public Usuario(int idUsuario, String nome, String email, String tipo) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.tipo = tipo;
    }


    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}

