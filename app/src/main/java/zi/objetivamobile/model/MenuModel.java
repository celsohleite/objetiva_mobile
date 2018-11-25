package zi.objetivamobile.model;

/**
 * Created by leite on 25/09/17.
 */

public class MenuModel {

    private Long id;
    private String descricao;
    private Integer menu;


    public MenuModel(Long id, String descricao, Integer menu) {
        this.id = id;
        this.descricao = descricao;
        this.menu = menu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getMenu() {
        return menu;
    }

    public void setMenu(Integer menu) {
        this.menu = menu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuModel menuModel = (MenuModel) o;

        if (id != null ? !id.equals(menuModel.id) : menuModel.id != null) return false;
        if (descricao != null ? !descricao.equals(menuModel.descricao) : menuModel.descricao != null)
            return false;
        return menu != null ? menu.equals(menuModel.menu) : menuModel.menu == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (menu != null ? menu.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MenuModel{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", menu=" + menu +
                '}';
    }
}
