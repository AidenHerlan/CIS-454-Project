/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis.pkg454.project;
/*
 * @author Aiden
 */
public class Report {
    private int id;
    private int reporteeId;
    private String type; // "USER", "POST", or "OTHER"
    private String status; // "RESOLVED" or "UNRESOLVED"
    private String description;
    private String comment;
    
    // Constructor for making a new report
    public Report(int reporteeID, String type, String description) {
        this.reporteeId = reporteeID;
        this.type = type;
        this.status = "UNRESOLVED";
        this.description = description;
        this.comment = "";
//        this.id = highest id in backend + 1
    }
    
    // Constructor for updating a report
    public Report(Report oldReport, String comment) {
        this.reporteeId = oldReport.getRepoteeId();
        this.type = oldReport.getType();
        this.status = oldReport.getStatus();
        this.description = oldReport.getDescription();
        this.comment = comment;
        this.id = oldReport.getId();
    }
    
    public int getId()
{
    return this.id;
}
    public void setId(int value)
{
     this.id = value;
}
    public int getRepoteeId()
{
    return this.reporteeId;
}
    public void setReporteeId(int value)
{
     this.reporteeId = value;
}
    public String getType()
{
    return this.type;
}
    public void setType(String value)
{
     this.type = value;
}
    public String getStatus()
{
    return this.status;
}
    public void setStatus(String value)
{
     this.status = value;
}
    public String getDescription()
{
    return this.description;
}
    public void setDescription(String value)
{
     this.description = value;
}
    public String getComment()
{
    return this.comment;
}
    public void setComment(String value)
{
     this.comment = value;
}
    
    
}
