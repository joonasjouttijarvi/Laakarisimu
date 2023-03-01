package entity;

import jakarta.persistence.*;

@Entity
public class Potilaat {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "jonotusaika", nullable = true, precision = 0)
    private Double jonotusaika;
    @Basic
    @Column(name = "hoidontarve", nullable = true, length = 20)
    private String hoidontarve;
    @Basic
    @Column(name = "palveluaika", nullable = true, precision = 0)
    private Double palveluaika;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getJonotusaika() {
        return jonotusaika;
    }

    public void setJonotusaika(Double jonotusaika) {
        this.jonotusaika = jonotusaika;
    }

    public String getHoidontarve() {
        return hoidontarve;
    }

    public void setHoidontarve(String hoidontarve) {
        this.hoidontarve = hoidontarve;
    }

    public Double getPalveluaika() {
        return palveluaika;
    }

    public void setPalveluaika(Double palveluaika) {
        this.palveluaika = palveluaika;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Potilaat potilaat = (Potilaat) o;

        if (id != potilaat.id) return false;
        if (jonotusaika != null ? !jonotusaika.equals(potilaat.jonotusaika) : potilaat.jonotusaika != null)
            return false;
        if (hoidontarve != null ? !hoidontarve.equals(potilaat.hoidontarve) : potilaat.hoidontarve != null)
            return false;
        if (palveluaika != null ? !palveluaika.equals(potilaat.palveluaika) : potilaat.palveluaika != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (jonotusaika != null ? jonotusaika.hashCode() : 0);
        result = 31 * result + (hoidontarve != null ? hoidontarve.hashCode() : 0);
        result = 31 * result + (palveluaika != null ? palveluaika.hashCode() : 0);
        return result;
    }
}
