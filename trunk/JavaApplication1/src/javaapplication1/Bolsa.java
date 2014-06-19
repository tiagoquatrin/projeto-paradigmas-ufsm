package javaapplication1;


public class Bolsa extends Vaga {
    
    private int idbolsa;
    private String nomeOrientador;
    private String enderecoOrientador;
    private String duracao;
    
    
    public int getIdbolsa() {
        return idbolsa;
    }

    public void setIdbolsa(int idbolsa) {
        this.idbolsa = idbolsa;
    }
    
    
    public String getNomeOrientador() {
        return nomeOrientador;
    }

    public void setNomeOrientador(String nomeOrientador) {
        this.nomeOrientador = nomeOrientador;
    }
    
    
    public String getEnderecoOrientador() {
        return enderecoOrientador;
    }

    public void setEnderecoOrientador(String enderecoOrientador) {
        this.enderecoOrientador = enderecoOrientador;
    }
    
    
    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }
    
}
