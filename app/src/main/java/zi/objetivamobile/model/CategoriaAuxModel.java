package zi.objetivamobile.model;

/**
 * Created by leite on 30/11/17.
 */

public class CategoriaAuxModel {

    private int id;
    private int cd_categoria;
    private int idTitulo;
    private int idSubTitulo;
    private int idResposta;

    private String descricao;

    private String categoria;

    private boolean selected;

    public int getId() {
        return id;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoriaAuxModel that = (CategoriaAuxModel) o;

        if (id != that.id) return false;
        if (cd_categoria != that.cd_categoria) return false;
        if (idTitulo != that.idTitulo) return false;
        if (idSubTitulo != that.idSubTitulo) return false;
        if (idResposta != that.idResposta) return false;
        if (selected != that.selected) return false;
        if (descricao != null ? !descricao.equals(that.descricao) : that.descricao != null)
            return false;
        return categoria != null ? categoria.equals(that.categoria) : that.categoria == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + cd_categoria;
        result = 31 * result + idTitulo;
        result = 31 * result + idSubTitulo;
        result = 31 * result + idResposta;
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (categoria != null ? categoria.hashCode() : 0);
        result = 31 * result + (selected ? 1 : 0);
        return result;
    }

    public void setId(int id) {

        this.id = id;
    }

    public int getIdTitulo() {
        return idTitulo;
    }

    public int getCd_categoria() {
        return cd_categoria;
    }

    public void setCd_categoria(int cd_categoria) {
        this.cd_categoria = cd_categoria;
    }

    public void setIdTitulo(int idTitulo) {
        this.idTitulo = idTitulo;
    }

    public int getIdSubTitulo() {
        return idSubTitulo;
    }

    public void setIdSubTitulo(int idSubTitulo) {
        this.idSubTitulo = idSubTitulo;
    }

    public int getIdResposta() {
        return idResposta;
    }

    public void setIdResposta(int idResposta) {
        this.idResposta = idResposta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}