
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;


public class TaskController {
    
    public void save (Task task ){
    
        String sql = "INSERT INTO tasks (idProject, name, description, completed, notes, deadline, createdAt, updatedAt) VALUES (?,?,?,?,?,?,?,?) " ;
        Connection connection = null ;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7,new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.execute();
            
        } catch (Exception ex) {
           throw new RuntimeException("Erro ao salvar a tarefa"
                   + ex.getMessage(),ex);
        } finally {
        ConnectionFactory.closeConnection(connection, statement);
        
        
        
        }
        
        
    
    }
    
    
    public void update (Task task){
    
    String sql = "UPDATE tasks SET idProject = ?, name = ?,description = ?, completed = ?, notes = ?, deadline = ?, createdAt = ? , updatedAt = ? , WHERE id = ? ";    
        
    Connection connection = null;
    PreparedStatement statement = null ;
    
        try {
            
            //estabelecendo a conex�o com o banco de dados 
            connection = ConnectionFactory.getConnection();
            //preparando a query
            statement = connection.prepareStatement(sql);
            // Setando os valores no statement 
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7,new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId());
            
            
            //executando a query
            statement.execute();
            
        } catch (Exception ex ) {
            throw  new RuntimeException("Erro ao atualizar  a tarefa " + ex.getMessage(), ex);
            
            
        }
    
    
    
    
    }
    
    public void removeById ( int taskId) throws SQLException{
    
    String sql = "DELETE FROM tasks WHERE id = ?" ;
    
    Connection connection = null ;
    PreparedStatement statement = null ;
    
        try {
            //cria��o da conex�o com o banco de dados 
            connection = ConnectionFactory.getConnection();
            //prepara��o da query
            statement = connection.prepareStatement(sql);
            //setando os valores 
            statement.setInt(1, taskId);
            // execu��o da query
            statement.execute();
            
            
        } catch (Exception ex) {
            throw  new RuntimeException("Erro ao deletar a tarefa " + ex.getMessage(), ex);
            
        } finally {
        
        ConnectionFactory.closeConnection(connection,statement);
        
        
        }
    
    }
    
    public List<Task> getAll (int idProject) {
        
        String sql = "SELECT * FROM tasks WHERE idProject = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        
        //lista de tarefas que ser� devolvida quando o metodo acontecer //
        
        
    List<Task> tasks = new ArrayList<Task>();
    
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idProject);
            resultSet = statement.executeQuery();
            
            
            while (resultSet.next()){
            
            Task task = new Task ();
            task.setId(resultSet.getInt("id"));
            task.setIdProject(resultSet.getInt("idProject"));
            task.setName(resultSet.getString("name"));
            task.setDescription(resultSet.getString("description"));
            task.setNotes(resultSet.getString("notes"));
            task.setIsCompleted(resultSet.getBoolean("completed"));
            task.setDeadline(resultSet.getDate("deadline"));
            task.setCreatedAt(resultSet.getDate("CreatedAt"));
            task.setUpdatedAt(resultSet.getDate("updatedAt"));
            
            tasks.add(task);
            
            
            }
            
            
        } catch (Exception ex) {
            throw  new RuntimeException("Erro ao selecionar tarefa " + ex.getMessage(), ex);
        } finally{
          ConnectionFactory.closeConnection(connection , statement,resultSet);
        
        }
  
        
        
      //Lista de tarefas que foram criadas e carregada do banco de dados 
     return  tasks;
    }
    
    
    
}
