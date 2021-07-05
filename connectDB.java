package server.com;

import java.sql.*;

public class connectDB {
    Connection conn=null;

    public  void DbConnect(){
        try {
            String url = "jdbc:postgresql://localhost:5432/postgres";
            conn = DriverManager.getConnection(url, "postgres","");


        }catch (SQLException e){
            System.out.println("Sql connection error:"+e);

        }
    }

    public  String getStudentsInfo(){
        String showResponse = "<p><table><colgroup><col span=\"1\" style=\"outline: 3px solid blue\">" +
                "<col style=\"background-color:white\"><col style=\"border: 3px solid red\">" +
                "<col style=\"background-colorwhite\"><col style=\"background-color:white\">" +
                "</colgroup><p><tr><th>student_id</th><th>name</th><th>grade</th>" +
                "<th>marks</th></tr></p>";
        String query = "select student_id, name, grade, marks from students2";
        try {
            Statement statement= conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){


                showResponse+= " <tr><td>"+ resultSet.getString("student_id")+"</td><td>"+ resultSet.getString("name")+"</td><td>"+ resultSet.getString("grade")+"</td><td>"+ resultSet.getString("marks")+"</td><tr>";


            }
            showResponse+= "</table></p>";

        }catch (SQLException ex){
            System.out.println("Sql connection error"+ ex);

        }
        return showResponse;
    }
    public void close() {
        try {
            if(conn!=null)conn.close();
        }catch (SQLException ex){
            System.out.println("SQL connection error");
        }
    }
}
