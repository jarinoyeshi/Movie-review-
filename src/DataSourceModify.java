
import java.sql.*;

public class DataSourceModify extends DataSource{

    private static final String DB_Name= "movieDB.db";
    private static final String JD_DB_CONNECTION= "jdbc:sqlite:./"+DB_Name;

    private static int COUNT_RATING= 1;

    private static final String TABLE_MOVIE= "movie";
    private static final String COL_NAME= "name";
    private static final String COL_YEAR= "year";
    private static final String COL_CAST= "cast";
    private static final String COL_DIRECTOR= "director";
    private static final String COL_GENRE= "genre";
    private static final String COL_RATING= "rating";
    private static final String COL_RATING_COUNT= "ratingCount";

    public DataSourceModify() {
        super(DataSourceModify.JD_DB_CONNECTION);
    }


    public boolean updateMovie(String oldVal,String newVal,String colName){

        Statement statement= null;

        StringBuilder newValeBuild= new StringBuilder(newVal);
        newValeBuild.insert(0,"'");
        newValeBuild.insert(newValeBuild.length(),"'");

        StringBuilder oldBuild= new StringBuilder(oldVal);
        oldBuild.insert(0,"'");
        oldBuild.insert(oldBuild.length(),"'");

        try{
            statement= connection.createStatement();

            statement.execute("UPDATE "+TABLE_MOVIE+" SET "+colName+"="+newValeBuild+" WHERE "+colName+"="+oldBuild);

            return true;

        }catch(SQLException e){
            System.out.println("Query Failed "+e.getMessage());

            return false;
        }finally{

            if(statement!=null)
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Couldn't close Statement!");
                    e.printStackTrace();
                }
        }
    }

    public boolean updateRating(String name,double rating){

        Statement statement= null;

        StringBuilder nameBuild= new StringBuilder(name);
        nameBuild.insert(0,"'");
        nameBuild.insert(nameBuild.length(),"'");

        double avgRating= rating/COUNT_RATING;

        StringBuilder ratingBuild= new StringBuilder(Double.toString(avgRating));
        ratingBuild.insert(0,"'");
        ratingBuild.insert(ratingBuild.length(),"'");

        try{
            statement= connection.createStatement();

            statement.execute("UPDATE "+TABLE_MOVIE+" SET "+COL_RATING+"="+ratingBuild+" WHERE "+COL_NAME+"="+nameBuild);

           COUNT_RATING++;

            return true;

        }catch(SQLException e){
            System.out.println("Query Failed "+e.getMessage());

            return false;
        }finally{

            if(statement!=null)
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Couldn't close Statement!");
                    e.printStackTrace();
                }
        }
    }

    public boolean deleteRecord(String val){

        Statement statement= null;

        StringBuilder newValeBuild= new StringBuilder(val);
        newValeBuild.insert(0,"'");
        newValeBuild.insert(newValeBuild.length(),"'");

        try{
            statement= connection.createStatement();

            statement.execute("DELETE FROM "+TABLE_MOVIE+" WHERE "+COL_NAME+"="+newValeBuild);

            return true;

        }catch(SQLException e){
            System.out.println("Query Failed "+e.getMessage());

            return false;

        }finally{

            if(statement!=null)
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Couldn't close Statement!");
                    e.printStackTrace();
                }
        }
    }
}
