package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class RegistrationDAO {
    
    // INSERT YOUR CODE HERE
    private static final String QUERY_CREATE = "INSERT INTO student (studentid, termid, crn) VALUES (?, ?, ?)";
    private static final String QUERY_FIND = "SELECT * FROM student WHERE studentid = ?, termid = ?, ";
    private static final String QUERY_LIST = "SELECT * FROM studentid, termid";
    private static final String QUERY_DELETE = "DELETE FROM student WHERE studentid = ?, termid = ?, crn = ?";
    private static final String QUERY_DELETE2 = "DELETE FROM student WHERE studentid = ?, termid = ?";
    
    private final DAOFactory daoFactory;
    
    RegistrationDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public boolean create(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                ps = conn.prepareStatement(QUERY_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                ps.setInt(3, crn);
                
                 int updateCount = ps.executeUpdate();
                
                if (updateCount > 0) {
            
                    rs = ps.getGeneratedKeys();

                    if (rs.next()) {
                        result = rs.getInt(1);
                    }

                }
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }

    public boolean delete(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                 ps = conn.prepareStatement(QUERY_DELETE);
                 ps.setInt(1, studentid);
                 ps.setInt(2, termid);
                 ps.setInt(3, crn);
                 
                  int updateCount = ps.executeUpdate();
                
                if (updateCount > 0) {
            
                    result = true;

                }
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
    public boolean delete(int studentid, int termid) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                ps = conn.prepareStatement(QUERY_DELETE2);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                
                int updateCount = ps.executeUpdate();
                
                if (updateCount > 0) {
            
                    result = true;

                }
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }

    public String list(int studentid, int termid) {
        
        String result = null;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                 ps = conn.prepareStatement(QUERY_FIND);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                
                boolean hasresults = ps.execute();
                
                if (hasresults) {

                    rs = ps.getResultSet();

                    while(rs.next()) {

                        studentid = rs.getInt("studentid");
                        termid = rs.getInt("termid");
                        result = new student(studentid, termid);
                        
                    }

                }
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
}
