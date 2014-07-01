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
@Named("vag")

public class VagaDAO implements Serializable {
    
    private Vaga vaga = new Vaga(); 
    
    
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
    
    private static Collection<Vaga> arrayvaga = new ArrayList<Vaga>();
    
    
    public Collection<Vaga> getArrayvaga() {
                return arrayvaga;
    }
    
    public Vaga getVaga() {
            return vaga;
    }

    public void setVaga(Vaga vaga) {
            this.vaga = vaga;
    }
    
    
    public void buscarTituloDescricao(){
    
            Connection conexao = abrir();

            try{                                
                Statement s = conexao.createStatement();
                ResultSet rs = s.executeQuery("SELECT * FROM vaga WHERE titulo || descricao LIKE '%"+ vaga.getDescricao() +"%'");
                arrayvaga.clear();
                while(rs.next()){
                               Vaga vagabusc = new Vaga();
                               vagabusc.setId(rs.getInt("id"));
                               vagabusc.setDescricao(rs.getString("descricao"));
                               vagabusc.setTitulo(rs.getString("titulo"));
                               vagabusc.setLoc(rs.getString("loc"));
                               vagabusc.setTipo(rs.getString("tipo"));
                               vagabusc.setContato(rs.getString("contato"));
                               vagabusc.setCargahoraria(rs.getInt("cargahoraria"));
                               vagabusc.setValor(rs.getInt("valor"));
                               arrayvaga.add(vagabusc);  
                }

              rs.close();

              conexao.close();

            }catch(SQLException e){
                e.printStackTrace();
            }
    }
    
    
    public void excluir(Vaga v) throws SQLException{
            
            Connection conexao = abrir();
            
            Statement s = conexao.createStatement();
            ResultSet rs = s.executeQuery("SELECT idbolsa FROM bolsa");
            
            while(rs.next()){

            int idbolsa = rs.getInt("idbolsa");
            
            
            if (v.getId() == idbolsa){
                
                try{
                PreparedStatement ps = conexao.prepareStatement("DELETE FROM bolsa WHERE idbolsa = "+ v.getId() +"");
                ps.execute();
                ps.close();
                
                PreparedStatement ps1 = conexao.prepareStatement("DELETE FROM vaga WHERE id = "+ v.getId() +"");
                ps1.execute();
                ps1.close();

                }catch(SQLException e){
                    e.printStackTrace();
                }
            
            }

            }
            
            rs.close();
            
            
            rs = s.executeQuery("SELECT idestagio FROM estagio");
            
            while(rs.next()){
           
            int idestagio = rs.getInt("idestagio");
            
            
            if (v.getId() == idestagio){
                
                try{
                PreparedStatement ps = conexao.prepareStatement("DELETE FROM estagio WHERE idestagio = "+ v.getId() +"");
                ps.execute();
                ps.close();
                
                PreparedStatement ps1 = conexao.prepareStatement("DELETE FROM vaga WHERE id = "+ v.getId() +"");
                ps1.execute();
                ps1.close();

                }catch(SQLException e){
                    e.printStackTrace();
                }
            
            }

            }
            
            
            rs.close();
            
            
            rs = s.executeQuery("SELECT idemprego FROM emprego");
            
            while(rs.next()){
           
            int idemprego = rs.getInt("idemprego");
            
            
            if (v.getId() == idemprego){
                
                try{
                PreparedStatement ps = conexao.prepareStatement("DELETE FROM emprego WHERE idemprego = "+ v.getId() +"");
                ps.execute();
                ps.close();
                
                PreparedStatement ps1 = conexao.prepareStatement("DELETE FROM vaga WHERE id = "+ v.getId() +"");
                ps1.execute();
                ps1.close();

                }catch(SQLException e){
                    e.printStackTrace();
                }
            
            }

            }

            rs.close();
            conexao.close();
            
            //Para atualizar a tabela sem precisar clicar em Mostrar de novo.
            buscarTituloDescricao();
            
            
        }
    
    
    
}
