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
@Named("bol")


public class BolsaDAO implements Serializable{
    
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
    
    
    
    private static Collection<Bolsa> arraybolsa = new ArrayList<Bolsa>();
    
    
    
    public Collection<Bolsa> getArraybolsa() {
                return arraybolsa;
        }
    
   
    
    public void buscarBolsa() throws SQLException{
    
            Connection conexao = abrir();
            
            arraybolsa.clear();
            
            try{
               PreparedStatement sql = conexao.prepareStatement("SELECT * FROM bolsa");
               ResultSet rs = sql.executeQuery();
            while(rs.next()){
                      Bolsa bolsabusc = new Bolsa();
                      bolsabusc.setIdbolsa(rs.getInt("idbolsa"));
                      bolsabusc.setNomeOrientador(rs.getString("nomeorientador"));
                      bolsabusc.setEnderecoOrientador(rs.getString("enderecoorientador"));
                      bolsabusc.setDuracao(rs.getString("duracao"));
                      arraybolsa.add(bolsabusc);
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
    private Bolsa bolsa = new Bolsa();
    
    public Bolsa getBolsa() {
                return bolsa;
        }

    public void setBolsa(Bolsa bolsa) {
                this.bolsa = bolsa;
        }
    
    public Vaga getVaga() {
            return vaga;
    }

    public void setVaga(Vaga vaga) {
            this.vaga = vaga;
    }
    
    
    
    
    public void inserirBolsa() throws SQLException{
        
    Connection conexao = abrir();
                  
    try{                                
       PreparedStatement ps;
       ps = conexao.prepareStatement("INSERT INTO vaga (loc, tipo, descricao, titulo, contato, cargahoraria, valor) VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
       ps.setString(1, vaga.getLoc());
       ps.setString(2, "Bolsa");
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


       ps = conexao.prepareStatement("INSERT INTO bolsa (idbolsa, nomeorientador, enderecoorientador, duracao ) VALUES (?, ?, ?, ?)");
       ps.setInt(1, id);
       ps.setString(2, bolsa.getNomeOrientador());
       ps.setString(3, bolsa.getEnderecoOrientador());
       ps.setString(4, bolsa.getDuracao());
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
    bolsa.setNomeOrientador("");
    bolsa.setEnderecoOrientador("");
    bolsa.setDuracao("");
    
    // Para atualizar a tabela sem precisar clicar em Mostrar de novo.
    buscarBolsa();
    
}
    
    public void excluir(Bolsa b) throws SQLException{
            Connection conexao = abrir();
            
            try{
                PreparedStatement ps = conexao.prepareStatement("DELETE FROM bolsa WHERE idbolsa = "+ b.getIdbolsa() +"");
                ps.execute();
                ps.close();
                
                PreparedStatement ps1 = conexao.prepareStatement("DELETE FROM vaga WHERE id = "+ b.getIdbolsa() +"");
                ps1.execute();
                ps1.close();
                
                conexao.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
          
            
            // Para atualizar a tabela sem precisar clicar em Mostrar de novo.
            buscarBolsa();
        }

    
    public void editarBolsa() throws SQLException{
            Connection conexao = abrir();
            
            int id = vaga.getId();
            
            try{                                
               PreparedStatement ps;
               ps = conexao.prepareStatement("UPDATE vaga SET loc=?, descricao=?, titulo=?, contato=?, cargahoraria=?, valor=? WHERE id = "+ id +"");
               ps.setString(1, vaga.getLoc());
               ps.setString(2, vaga.getDescricao());
               ps.setString(3, vaga.getTitulo());
               ps.setString(4, vaga.getContato());
               ps.setInt(5, vaga.getCargahoraria());
               ps.setInt(6, vaga.getValor());
               ps.executeUpdate();
               ps.close();


               ps = conexao.prepareStatement("UPDATE bolsa SET nomeorientador=?, enderecoorientador=?, duracao=? WHERE idbolsa = "+ id +"");
               ps.setString(1, bolsa.getNomeOrientador());
               ps.setString(2, bolsa.getEnderecoOrientador());
               ps.setString(3, bolsa.getDuracao());
               ps.executeUpdate();
               ps.close();

               conexao.close();

            }catch(SQLException e){
                e.printStackTrace();
            }
    
            // Apenas para limpar os campos após serem inseridos no banco de dados.
            vaga.setId(0);
            vaga.setLoc("");
            vaga.setDescricao("");
            vaga.setTitulo("");
            vaga.setContato("");
            vaga.setCargahoraria(0);
            vaga.setValor(0);
            bolsa.setNomeOrientador("");
            bolsa.setEnderecoOrientador("");
            bolsa.setDuracao("");
            
            // Para atualizar a tabela sem precisar clicar em Mostrar de novo.
            buscarBolsa();
        }
}
