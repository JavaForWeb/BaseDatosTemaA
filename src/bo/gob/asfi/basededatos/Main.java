package bo.gob.asfi.basededatos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void PostgreSQLJDBC()
    {
        String sql = "";
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JavaForWeb", "postgres", "qwer1234%");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");

        try {

            Statement stmt = c.createStatement();
            sql = "DELETE FROM public.\"city\"";
            stmt.execute(sql);
            //Ciudades
            BufferedReader br = new BufferedReader(new FileReader("./src/resources/fixtures/cities.txt"));
            String s1;
            int lineas = 0;
            while ((s1 = br.readLine())!=null)
            {
                s1 = s1.replace("\"","").replace("'","´");
                if (lineas > 0) {
                    String[] p = s1.split(",");
                    sql = "INSERT INTO public.\"city\" (\"CityId\", \"CountryId\", \"RegionId\", \"City\", \"Latitude\", \"Longitude\", \"TimeZone\", \"DmaId\", \"CODE\") VALUES\n" +
                            "(" + p[0] + "," + p[1] + "," + p[2] + ",'" + p[3] + "','" + p[4] + "','" + p[5] + "','" + p[6] + "'," + p[7] + ",'" + p[8] + "')";
                    stmt.execute(sql);
                }
                lineas +=1;
            }
        }
        catch (Exception e)
        {
            System.err.println(sql);
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        try {

            Statement stmt = c.createStatement();
            sql = "DELETE FROM public.\"region\"";
            stmt.execute(sql);
            //REgiones
            BufferedReader br = new BufferedReader(new FileReader("./src/resources/fixtures/Regions.txt"));
            String s1;
            int lineas = 0;
            while ((s1 = br.readLine())!=null)
            {
                s1 = s1.replace("\"","").replace("'","´");
                if (lineas > 0) {
                    String[] p = s1.split(",");
                    sql = "INSERT INTO public.\"region\" (\"RegionId\", \"CountryId\", \"Region\", \"Code\", \"ADM1Code\") VALUES\n" +
                            "(" + p[0] + "," + p[1] + ",'" + p[2] + "','" + p[3] + "','" + p[4] + "')";
                    stmt.execute(sql);
                }
                lineas +=1;
            }
        }
        catch (Exception e)
        {
            System.err.println(sql);
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        try {

            Statement stmt = c.createStatement();
            sql = "DELETE FROM public.\"country\"";
            stmt.execute(sql);
            //Ciudades
            BufferedReader br = new BufferedReader(new FileReader("./src/resources/fixtures/Countries.txt"));
            String s1;
            int lineas = 0;
            while ((s1 = br.readLine())!=null)
            {
                s1 = s1.replace("\"\"","\"_\"").replace("\"","").replace("'","´");
                if (lineas > 0) {
                    String[] p = s1.split(",");
                    sql = "INSERT INTO public.\"country\" (\"CountryId\", \"Country\", \"FIPS104\", \"ISO2\", \"ISO3\", \"ISON\", \"Internet\", \"Capital\", \"MapReference\", \"NationalitySingular\", \"NationalityPlural\", \"Currency\", \"CurrencyCode\", \"Population\", \"Title\", \"Comment\", \"TimeZone\") VALUES\n" +
                            "(" + p[0] + ",'" + p[1] + "','" + p[2] + "','" + p[3] + "','" + p[4] + "','"
                            + p[5] + "','"  + p[6] + "','" + p[7] + "','" + p[8] + "','" + p[9] + "','"
                            + p[10] + "','" + p[11] + "','" + p[12] + "','" + p[13] + "','" + p[14] + "','"
                            + p[15].replace("_","") + "','')";
                    stmt.execute(sql);
                }
                lineas +=1;
            }
        }
        catch (Exception e)
        {
            System.err.println(sql);
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        /*try {
            System.out.println("Creating statement...");
            String sql;
            sql = "SELECT * FROM city";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int cityId  = rs.getInt("CityId");
                int countryId = rs.getInt("CountryId");
                String city = rs.getString("City");
                String timeZone = rs.getString("TimeZone");

                //Display values
                System.out.print("cityID: " + cityId);
                System.out.print(", countryId: " + countryId);
                System.out.print(", city: " + city);
                System.out.println(", TZ: " + timeZone);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            c.close();


        } catch(SQLException e ) {
            e.printStackTrace();
        }
        */
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");

        PostgreSQLJDBC();
    }
}
