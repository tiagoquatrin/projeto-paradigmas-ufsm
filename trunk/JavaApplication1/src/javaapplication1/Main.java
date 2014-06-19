/*

Paradigmas de Programação - UFSM.
By: Eduardo Rosso Cargnelutti, Tiago Quatrin da Silva.

*/
package javaapplication1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Main {

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



public void buscarTituloDescricao(){
    
    Connection conexao = abrir();
            
    try{                                
        Statement s = conexao.createStatement();
        ResultSet rs = s.executeQuery("SELECT id FROM vaga WHERE titulo || descricao LIKE '%Legal%'");
        
        System.out.println("Listando todos os tipos de vagas cadastradas que possuem titulo ou descrição contendo a palavra \"Legal\":");
                
        while(rs.next()){
                         System.out.println(rs.getString("id"));
        }

      rs.close();
      
      conexao.close();

    }catch(SQLException e){
        e.printStackTrace();
    }
}



public void buscarTudo(){
    
    Connection conexao = abrir();
            
    try{                                
        Statement s = conexao.createStatement();
        ResultSet rs = s.executeQuery("SELECT id FROM vaga");
        
        System.out.println("Listando todos os tipos de vagas cadastradas:");
                
        while(rs.next()){
                         System.out.println(rs.getString("id"));
        }

      rs.close();
      
      conexao.close();

    }catch(SQLException e){
        e.printStackTrace();
    }
}



public void buscarBolsa(){
    
    Connection conexao = abrir();
            
    try{                                
        Statement s = conexao.createStatement();
        ResultSet rs = s.executeQuery("SELECT idbolsa FROM bolsa");
        
        System.out.println("Listando todas as bolsas:");
                
        while(rs.next()){
                         System.out.println(rs.getString("idbolsa"));
        }

      rs.close();
      
      conexao.close();

    }catch(SQLException e){
        e.printStackTrace();
    }
}



public void buscarEmprego(){
    
    Connection conexao = abrir();
            
    try{                                
        Statement s = conexao.createStatement();
        ResultSet rs = s.executeQuery("SELECT idemprego FROM emprego");
        
        System.out.println("Listando todos os empregos:");
        
        while(rs.next()){
                         System.out.println(rs.getString("idemprego"));
        }

      rs.close();
      
      conexao.close();

    }catch(SQLException e){
        e.printStackTrace();
    }
}



public void buscarEstagio(){
    
    Connection conexao = abrir();
            
    try{                                
        Statement s = conexao.createStatement();
        ResultSet rs = s.executeQuery("SELECT idestagio FROM estagio");

        System.out.println("Listando todos os estágios:");
        
        while(rs.next()){
                         System.out.println(rs.getString("idestagio"));
        }

      rs.close();
      
      conexao.close();

    }catch(SQLException e){
        e.printStackTrace();
    }
}



public void inserirBolsa(){
        
    Connection conexao = abrir();
                  
    try{                                
       PreparedStatement ps;
       ps = conexao.prepareStatement("INSERT INTO vaga (loc, tipo, descricao, titulo, contato, cargahoraria, valor) VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
       ps.setString(1, "Santa Maria");
       ps.setString(2, "Bolsa");
       ps.setString(3, "Desenvolvedor");
       ps.setString(4, "Titulo Legal");
       ps.setString(5, "Contates-me");
       ps.setInt(6, 20);
       ps.setInt(7, 800);
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
       ps.setString(2, "Andrea");
       ps.setString(3, "UFSM");
       ps.setString(4, "1 Semestre");
       ps.execute();
       ps.close();
       
       conexao.close();
       
    }catch(SQLException e){
        e.printStackTrace();
    }
            
}



public void inserirEstagio(){
        
    Connection conexao = abrir();
             
    try{                                
       PreparedStatement ps;
       ps = conexao.prepareStatement("INSERT INTO vaga (loc, tipo, descricao, titulo, contato, cargahoraria, valor) VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
       ps.setString(1, "Faxinal do Soturno");
       ps.setString(2, "Estagio");
       ps.setString(3, "Descricao Legal");
       ps.setString(4, "Titulo Legal");
       ps.setString(5, "Contates-me");
       ps.setInt(6, 50);
       ps.setInt(7, 450);
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
       ps.setString(2, "INSS");
       ps.execute();
       ps.close();
       
       conexao.close();
       
    }catch(SQLException e){
        e.printStackTrace();
    }
            
}



public void inserirEmprego(){
        
    Connection conexao = abrir();        
           
    try{                                
        PreparedStatement ps;
        ps = conexao.prepareStatement("INSERT INTO vaga (loc, tipo, descricao, titulo, contato, cargahoraria, valor) VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, "Nova Palma");
        ps.setString(2, "Emprego");
        ps.setString(3, "Sem Descrição aqui");
        ps.setString(4, "Programador");
        ps.setString(5, "Contate-me");
        ps.setInt(6, 40);
        ps.setInt(7, 1500);
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
        ps.setString(2, "Auxílio Gás");
        ps.execute();
        ps.close();
        
        conexao.close();
        
     }catch(SQLException e){
         e.printStackTrace();
     }
            
}



    public static void main(String[] args) {
    
        
        /*Scanner leitor = new Scanner(System.in);  
        int numero=0;  
          
        System.out.println ("Insira o numero");  
        numero = leitor.nextInt();*/
        
        
        Main emprego = new Main();
        emprego.inserirEmprego();
        
        Main estagio = new Main();
        estagio.inserirEstagio();
        
        Main bolsa = new Main();
        bolsa.inserirBolsa();
        
        // Todas as buscas retornam o ID da oportunidade (vaga).
        Main buscabolsa = new Main();
        buscabolsa.buscarBolsa();
        
        Main buscaemprego = new Main();
        buscaemprego.buscarEmprego();
        
        Main buscaestagio = new Main();
        buscaestagio.buscarEstagio();
        
        Main buscatudo = new Main();
        buscatudo.buscarTudo();
        
        // Faz busca por títulos ou descrições que contenham a palavra "Legal".
        Main buscatitulodescricao = new Main();
        buscatitulodescricao.buscarTituloDescricao();
    
    }
        
}