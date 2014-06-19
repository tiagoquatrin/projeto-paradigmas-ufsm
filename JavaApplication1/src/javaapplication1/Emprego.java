package javaapplication1;


public class Emprego extends Vaga {
    
    private int idemprego;
    private String beneficios;
    
    
    public int getIdemprego() {
        return idemprego;
    }

    public void setIdemprego(int idemprego) {
        this.idemprego = idemprego;
    }
    
    
    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }

}
