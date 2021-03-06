package onlineassessment.entity;
// Generated Jan 28, 2021 10:41:47 PM by Hibernate Tools 4.3.1



/**
 * Studentmt generated by hbm2java
 */
public class Studentmt  implements java.io.Serializable {


     private Long mtid;
     private Schedulemt schedulemt;
     private String email;
     private String fullName;
     private Boolean mtActive;
     private String SId;

    public Studentmt() {
    }

	
    public Studentmt(Schedulemt schedulemt, String email, String fullName, String SId) {
        this.schedulemt = schedulemt;
        this.email = email;
        this.fullName = fullName;
        this.SId = SId;
    }
    public Studentmt(Schedulemt schedulemt, String email, String fullName, Boolean mtActive, String SId) {
       this.schedulemt = schedulemt;
       this.email = email;
       this.fullName = fullName;
       this.mtActive = mtActive;
       this.SId = SId;
    }
   
    public Long getMtid() {
        return this.mtid;
    }
    
    public void setMtid(Long mtid) {
        this.mtid = mtid;
    }
    public Schedulemt getSchedulemt() {
        return this.schedulemt;
    }
    
    public void setSchedulemt(Schedulemt schedulemt) {
        this.schedulemt = schedulemt;
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
    public Boolean getMtActive() {
        return this.mtActive;
    }
    
    public void setMtActive(Boolean mtActive) {
        this.mtActive = mtActive;
    }
    public String getSId() {
        return this.SId;
    }
    
    public void setSId(String SId) {
        this.SId = SId;
    }




}


