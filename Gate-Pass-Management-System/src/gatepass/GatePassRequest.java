package gatepass;

import java.text.SimpleDateFormat;
import java.util.Date;
class GatePassRequest {
    protected String name;
    protected String rollno;
    protected String year;
    protected String className;
    protected String section;
    protected String reason;
    private String requestTimestamp;
    private String acceptTimestamp;
    private String rejectTimestamp;

    public GatePassRequest(String name, String rollno, String year, String className, String section, String reason) {
        this.name = name;
        if (isValidRollNo(rollno)) {
            this.rollno = rollno;
        } else {
            throw new IllegalArgumentException("Incorrect ID number");
        }
        this.rollno = rollno;
        this.year = year;
        this.className = className;
        this.section = section;
        this.reason = reason;
        this.requestTimestamp = getCurrentTimestamp();
    }

    public String getName() {
        return name;
    }

    public String getRollNo() {
        return rollno;
    }

    public String getYear() {
        return year;
    }

    public String getClassName() {
        return className;
    }

    public String getSection() {
        return section;
    }

    public String getReason() {
        return reason;
    }

    private boolean isValidRollNo(String rollno) {
        return rollno != null && rollno.length() >= 10 && rollno.substring(2).toLowerCase().contains("bd");
    }

    public String getRequestTimestamp() {
        return requestTimestamp;
    }

    public String getAcceptTimestamp() {
        return acceptTimestamp;
    }

    public void setAcceptTimestamp() {
        this.acceptTimestamp = getCurrentTimestamp();
    }

    public String getRejectTimestamp() {
        return rejectTimestamp;
    }

    public void setRejectTimestamp() {
        this.rejectTimestamp = getCurrentTimestamp();
    }

    private String getCurrentTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        return sdf.format(now);
    }


    @Override
    public String toString() {
        return "GatePassRequests{" +
                "Name='" + name + '\'' +
                ", Rollno='" + rollno + '\'' +
                ", Year='" + year + '\'' +
                ", ClassName='" + className + '\'' +
                ", Section='" + section + '\'' +
                ", Reason='" + reason + '\'' +
                '}';
    }
    class User extends GatePassRequest {
        public User(String name, String userid, String year, String className, String section, String reason) {
            super(name, userid, year, className, section, reason);
        }
    }

}