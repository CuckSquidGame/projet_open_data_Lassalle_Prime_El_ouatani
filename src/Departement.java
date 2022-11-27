import java.util.Objects;

public class Departement {

    String nom;
    String code;

    public Departement(String nom, String code) {
        this.nom = nom;
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departement that = (Departement) o;
        return Objects.equals(nom, that.nom) &&
                Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, code);
    }
}
