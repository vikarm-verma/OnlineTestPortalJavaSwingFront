package onlineassessment.entity;
// Generated Jan 28, 2021 10:41:47 PM by Hibernate Tools 4.3.1



/**
 * Studentregistration generated by hbm2java
 */
public class Studentregistration  implements java.io.Serializable {


     private String SId;
     private String date;
     private String email;
     private String fullName;
     private String password;
     private String role;

    public Studentregistration() {
    }

	
    public Studentregistration(String SId) {
        this.SId = SId;
    }
    public Studentregistration(String SId, String date, String email, String fullName, String password, String role) {
       this.SId = SId;
       this.date = date;
       this.email = email;
       this.fullName = fullName;
       this.password = password;
       this.role = role;
    }
   
    public String getSId() {
        return this.SId;
    }
    
    public void setSId(String SId) {
        this.SId = SId;
    }
    public String getDate() {
        return this.date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFullName() {
        return this.fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }




}

