import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named("est")

public class EstagioDAO implements Serializable {
    
    public Connection abrir(){
            
            Connection c = null;
            try{
                Class.forName(BD.JDBC_DRIVER);
            }catch (ClassNotFoundException e){
                System.out.println("Não encontrou o driver" + BD.JDBC_DRIVER + " na memória");
            }
            try{
                c = DriverManager.getConnection(BD.URL_SERVER, BD.USUARIO, BD.SENHA);
            }catch(SQLException e){
                e.printStackTrace();
            }
            return c;
    }
    
    private static Collection<Estagio> arrayestagio = new ArrayList<Estagio>();
    
    public Collection<Estagio> getArrayestagio() {
                return arrayestagio;
    }
    
    public void buscarEstagio() throws SQLException{
    
            Connection conexao = abrir();
            
            arrayestagio.clear();
            
            try{
               PreparedStatement sql = conexao.prepareStatement("SELECT * FROM estagio");
               ResultSet rs = sql.executeQuery();
            while(rs.next()){
                      Estagio estagiobusc = new Estagio();
                      estagiobusc.setIdestagio(rs.getInt("idestagio"));
                      estagiobusc.setOrgao(rs.getString("orgao"));
                      arrayestagio.add(estagiobusc);
            }
        
                   rs.close();
                   conexao.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
        }
    
    
     // -----------------------------------------------------------
    // Parte do Admin:
    
    private Vaga vaga = new Vaga();
    private Estagio estagio = new Estagio();
    
    public Estagio getEstagio() {
                return estagio;
        }

    public void setEstagio(Estagio estagio) {
                this.estagio = estagio;
        }
    
    public Vaga getVaga() {
            return vaga;
    }

    public void setVaga(Vaga vaga) {
            this.vaga = vaga;
    }
    
    
    
    
    public void inserirEstagio(){
        
    Connection conexao = abrir();
                  
    try{                                
       PreparedStatement ps;
       ps = conexao.prepareStatement("INSERT INTO vaga (loc, tipo, descricao, titulo, contato, cargahoraria, valor) VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
       ps.setString(1, vaga.getLoc());
       ps.setString(2, "Estágio");
       ps.setString(3, vaga.getDescricao());
       ps.setString(4, vaga.getTitulo());
       ps.setString(5, vaga.getContato());
       ps.setInt(6, vaga.getCargahoraria());
       ps.setInt(7, vaga.getValor());
       ps.execute();


       int id = -1; // Só para inicializar mesmo. O valor será substituído no rset.next.
       ResultSet rset = ps.getGeneratedKeys();  //getGeneratedKeys pega os valores do último INSERT no banco.
       if (rset.next()){  
            id = rset.getInt(1);  
       }  
       rset.close();  
       ps.close();  


       ps = conexao.prepareStatement("INSERT INTO estagio (idestagio, orgao) VALUES (?, ?)");
       ps.setInt(1, id);
       ps.setString(2, estagio.getOrgao());
       ps.execute();
       ps.close();
       
       conexao.close();
       
    }catch(SQLException e){
        e.printStackTrace();
    }
    
    // Apenas para limpar os campos após serem inseridos no banco de dados.
    vaga.setLoc("");
    vaga.setDescricao("");
    vaga.setTitulo("");
    vaga.setContato("");
    vaga.setCargahoraria(0);
    vaga.setValor(0);
    estagio.setOrgao("");

}
    
    public void excluir(Estagio est) throws SQLException{
            Connection conexao = abrir();
            
            try{
                PreparedStatement ps = conexao.prepareStatement("DELETE FROM estagio WHERE idestagio = "+ est.getIdestagio() +"");
                ps.execute();
                ps.close();
                
                PreparedStatement ps1 = conexao.prepareStatement("DELETE FROM vaga WHERE id = "+ est.getIdestagio() +"");
                ps1.execute();
                ps1.close();
                
                conexao.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
          
            
            // Para atualizar a tabela sem precisar clicar em Mostrar de novo.
            buscarEstagio();
        }
    
    
}
