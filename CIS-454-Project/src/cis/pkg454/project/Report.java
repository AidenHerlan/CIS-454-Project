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
    private String type;
    private String status;
    private String description;
    private String comment;
    
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
