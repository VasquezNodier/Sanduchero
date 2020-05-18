package co.vasquez.nodier.sanduchero.model.entity;


import java.util.Date;


public class Compras{
    private String idCom;
    private int idUsuCom;
    private int idSanCom;
    private int total;
    private Date fechaCom;

    public Compras() {
    }

    public Compras(int idUsuCom, int idSanCom, int total, Date fechaCom) {
        //this.idCom = 0;
        this.idUsuCom = idUsuCom;
        this.idSanCom = idSanCom;
        this.total = total;
        this.fechaCom = fechaCom;
    }

    public String getIdCom() {
        return idCom;
    }

    public void setIdCom(String idCom) {
        this.idCom = idCom;
    }

    public int getIdUsuCom() {
        return idUsuCom;
    }

    public void setIdUsuCom(int idUsuCom) {
        this.idUsuCom = idUsuCom;
    }

    public int getIdSanCom() {
        return idSanCom;
    }

    public void setIdSanCom(int idSanCom) {
        this.idSanCom = idSanCom;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getFechaCom() {
        return fechaCom;
    }

    public void setFechaCom(Date fechaCom) {
        this.fechaCom = fechaCom;
    }
}
