import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.ActionSource;
import javax.inject.Named;

@SessionScoped
@Named("emp")

public class EmpregoDAO implements Serializable {
    
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
    
    
    private static Collection<Emprego> arrayemprego = new ArrayList<Emprego>();
    
    public Collection<Emprego> getArrayemprego() {
                return arrayemprego;
        }
    
    public void buscarEmprego() throws SQLException{
    
            Connection conexao = abrir();
            
            arrayemprego.clear();
            
            try{
               PreparedStatement sql = conexao.prepareStatement("SELECT * FROM emprego");
               ResultSet rs = sql.executeQuery();
            while(rs.next()){
                      Emprego empregobusc = new Emprego();
                      empregobusc.setIdemprego(rs.getInt("idemprego"));
                      empregobusc.setBeneficios(rs.getString("beneficios"));
                      arrayemprego.add(empregobusc);
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
    private Emprego emprego = new Emprego();
    
    public Emprego getEmprego() {
                return emprego;
        }

    public void setEmprego(Emprego emprego) {
                this.emprego = emprego;
        }
    
    public Vaga getVaga() {
            return vaga;
    }

    public void setVaga(Vaga vaga) {
            this.vaga = vaga;
    }
    
    
    
    
    public void inserirEmprego(){
        
    Connection conexao = abrir();
                  
    try{                                
       PreparedStatement ps;
       ps = conexao.prepareStatement("INSERT INTO vaga (loc, tipo, descricao, titulo, contato, cargahoraria, valor) VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
       ps.setString(1, vaga.getLoc());
       ps.setString(2, "Emprego");
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


       ps = conexao.prepareStatement("INSERT INTO emprego (idemprego, beneficios) VALUES (?, ?)");
       ps.setInt(1, id);
       ps.setString(2, emprego.getBeneficios());
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
    emprego.setBeneficios("");

}
    
    public void excluir(Emprego emp) throws SQLException{
            Connection conexao = abrir();
            
            try{
                PreparedStatement ps = conexao.prepareStatement("DELETE FROM emprego WHERE idemprego = "+ emp.getIdemprego() +"");
                ps.execute();
                ps.close();
                
                PreparedStatement ps1 = conexao.prepareStatement("DELETE FROM vaga WHERE id = "+ emp.getIdemprego() +"");
                ps1.execute();
                ps1.close();
                
                conexao.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
            
            // Para atualizar a tabela sem precisar clicar em Mostrar de novo.
            buscarEmprego();
        }
    
}
