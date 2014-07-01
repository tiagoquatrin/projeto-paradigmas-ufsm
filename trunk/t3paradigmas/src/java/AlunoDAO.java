import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.enterprise.context.SessionScoped;
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

}