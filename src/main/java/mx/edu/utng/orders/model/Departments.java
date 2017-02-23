package mx.edu.utng.orders.model;

/**
 * Created by Husky Siberiano on 23/02/2017.
 */

public class Departments {
    private String deptoNo;
    private String deptoName;
    private String deptoPhone;

    public Departments( String deptoNo, String deptoName, String deptoPhone) {
        this.deptoNo = deptoNo;
        this.deptoName = deptoName;
        this.deptoPhone  = deptoPhone;
    }

    public Departments(){this("","","");}

    public String getDeptoNo() {
        return deptoNo;
    }

    public void setDeptoNo(String deptoNo) {
        this.deptoNo = deptoNo;
    }

    public String getDeptoName() {
        return deptoName;
    }

    public void setDeptoName(String deptoName) {
        this.deptoName = deptoName;
    }

    public String getDeptoPhone() {
        return deptoPhone;
    }

    public void setDeptoPhone(String deptoPhone) {
        this.deptoPhone = deptoPhone;
    }

    @Override
    public String toString() {
        return "Departments{" +
                "deptoNo=" + deptoNo +
                ", deptoName='" + deptoName + '\'' +
                ", deptoPhone='" + deptoPhone + '\'' +
                '}';
    }
}
