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

@Named("logar")
@SessionScoped
public class AlunoDAO implements Serializable{
   
   private Aluno aluno = new Aluno();

   
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
   
   
  
        public String login() throws SQLException{
            Connection conexao = abrir();
                
                
            try{
                Statement s = conexao.createStatement();
                ResultSet rs = s.executeQuery("SELECT * FROM aluno");
               
            while(rs.next()){
                int loginBanco = rs.getInt("matricula");
                String senhaBanco = rs.getString("senha");
                String NomeAluno = rs.getString("nome");
                aluno.setNome(NomeAluno);
                aluno.setCurso(rs.getString("curso"));
                aluno.setId(rs.getInt("id"));
                    
              
                
                if(aluno.getMatricula() == loginBanco & aluno.getSenha().equals(senhaBanco)){
                    return "/faces/principal";
                      
                    }
                
                
                }                
            rs.close();
            rs = s.executeQuery("SELECT matricula, senha FROM admin");

            while(rs.next()){
                int loginBanco = rs.getInt("matricula");
                String senhaBanco = rs.getString("senha");
                
                if(aluno.getMatricula() == loginBanco & aluno.getSenha().equals(senhaBanco)){
                    //aluno.setMatricula(loginBanco);
                    return "/faces/principalAdmin";
                      
                    }
                }                
               rs.close();
               s.close();
               conexao.close();
            }catch(SQLException e){
                e.printStackTrace();
                
            }
              
                
            return null;
        }
        
        public Aluno getAluno() {
		return aluno; 
	}
        public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
        
        
        
        
        
        
        
        
        
        
        
     
        
        
        
        
        /*
        public void inserir(){
            Connection conexao = abrir();
            
            try{                                
               PreparedStatement ps;
                ps = conexao.prepareStatement("INSERT INTO bolsa ( descricao, titulo, contato, codprof, comp1) VALUES ( ?, ?, ?, ?, ?)");
               
                if(bolsa.getDescricao().isEmpty() || bolsa.getTitulo().isEmpty() || bolsa.getContato().isEmpty() || bolsa.getComp1().isEmpty()){
                    
                }else{
                
               ps.setString(1, bolsa.getDescricao());
               ps.setString(2, bolsa.getTitulo());
               ps.setString(3, bolsa.getContato());
              ps.setInt(4, user.getId());
               ps.setString(5, bolsa.getComp1().toLowerCase());
               ps.execute();
                }
               ps.close();
               conexao.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
            
            bolsa.setComp1("");
            bolsa.setContato("");
            bolsa.setDescricao("");
            bolsa.setTitulo("");
           
               
        }
        
        public void inserircomp(){
            Connection conexao = abrir();
            
            try{                                
               PreparedStatement ps;
                ps = conexao.prepareStatement("UPDATE aluno SET comp1= ? , comp2= ? , comp3= ? , comp4= ?  WHERE id="+user.getId()+"");
               
               ps.setString(1, alunoo.getComp1().toLowerCase());
               ps.setString(2, alunoo.getComp2().toLowerCase());
               ps.setString(3, alunoo.getComp3().toLowerCase());
               ps.setString(4, alunoo.getComp4().toLowerCase());
               ps.executeUpdate();
               ps.close();
               conexao.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
                       
        }
        
        
        public void mostracomp1(){
            Connection conexao = abrir();
            
            try{                                
                Statement s = conexao.createStatement();
                ResultSet rs = s.executeQuery("SELECT comp1,comp2,comp3,comp4 FROM aluno WHERE id="+user.getId()+"");
                
                
                while(rs.next()){
              
               alunoo.setComp1((rs.getString("comp1").toLowerCase()));
               alunoo.setComp2((rs.getString("comp2").toLowerCase()));
               alunoo.setComp3((rs.getString("comp3").toLowerCase()));
               alunoo.setComp4((rs.getString("comp4").toLowerCase()));
                
               }
               
               rs.close();
              conexao.close();
               
             
                          
               
            }catch(SQLException e){
                e.printStackTrace();
            }
             
                     
        }
        
        public Vaga getBolsa(){
            return bolsa;
        }
        public void setBolsa (Vaga bolsa){
            this.bolsa = bolsa;
        }
        
        
        
        
        public void bolsaprof() throws SQLException{
            Connection conexao = abrir();
            arraybolsa.clear();
            try{
               Statement s = conexao.createStatement();
               ResultSet rs = s.executeQuery("SELECT * FROM bolsa WHERE codprof="+ user.getId() +"");
            while(rs.next()){
                      Vaga vagabusc = new Vaga();
                      vagabusc.setCodbolsa(rs.getInt("id"));
                      vagabusc.setDescricao(rs.getString("descricao"));
                      vagabusc.setTitulo(rs.getString("titulo"));
                      vagabusc.setContato(rs.getString("contato"));
                      arraybolsa.add(vagabusc);
            
                }                
                
                            
            
                     
               rs.close();
               conexao.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
            
        }
        
        
        public void bolsabusca() throws SQLException{
            Connection conexao = abrir();
            arraybolsa.clear();
            getAl().setComp1(competencia.getDescricao());
            try{
               Statement s = conexao.createStatement();
               ResultSet rs = s.executeQuery("SELECT * FROM bolsa WHERE comp1 LIKE '%"+ competencia.getDescricao().toLowerCase()+"%'");
            while(rs.next()){
                      Vaga vagabusc = new Vaga();
                      vagabusc.setCodbolsa(rs.getInt("id"));
                      vagabusc.setDescricao(rs.getString("descricao"));
                      vagabusc.setTitulo(rs.getString("titulo"));
                      vagabusc.setContato(rs.getString("contato"));
                      arraybolsa.add(vagabusc);
                     
                }                
                
                            
            
                     
               rs.close();
               conexao.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
            
        }
        
        */
        
        public void limpar(Bolsa b){
            Connection conexao = abrir();
            
            try{
                PreparedStatement ps = conexao.prepareStatement("DELETE FROM bolsa WHERE idbolsa="+b.getIdbolsa() +"");
                ps.execute();
                ps.close();
                
                conexao.close();
               // buscarBolsa();
            }catch(SQLException e){
                e.printStackTrace();
            }
            
        }  

   

    
    

    

    
    /*public String logout(){
           
        limpa(); 
         
     return "faces/index";   
    }*/
    
   

    
    
    /*
     public void bolsabuscacomp2() throws SQLException{
            Connection conexao = abrir();
            arraybolsa.clear();
            al.setComp1(alunoo.getComp2());
            try{
               Statement s = conexao.createStatement();
               ResultSet rs = s.executeQuery("SELECT * FROM bolsa WHERE comp1 LIKE '%"+ alunoo.getComp2().toLowerCase()+"%'");
            while(rs.next()){
                      Vaga vagabusc = new Vaga();
                      vagabusc.setCodbolsa(rs.getInt("id"));
                      vagabusc.setDescricao(rs.getString("descricao"));
                      vagabusc.setTitulo(rs.getString("titulo"));
                      vagabusc.setContato(rs.getString("contato"));
                      arraybolsa.add(vagabusc);
                     
                }                
                
                            
            
                     
               rs.close();
               conexao.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
     }
    
*/
    
    
}