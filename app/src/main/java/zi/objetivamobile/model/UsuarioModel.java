package zi.objetivamobile.model;

/**
 * Created by leite on 19/09/17.
 */

public class UsuarioModel {

    private Long id;
    private String usuario;
    private String senha;
    private Long idPerfil;
    private String fotoUsuario;
    private String pathImg;
    private String tipoLogin;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public Long getIdPerfil() {
        return idPerfil;
    }
    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }
    public String getFotoUsuario() {
        return fotoUsuario;
    }
    public void setFotoUsuario(String fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    }
    public String getPathImg() {
        return pathImg;
    }
    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fotoUsuario == null) ? 0 : fotoUsuario.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((idPerfil == null) ? 0 : idPerfil.hashCode());
        result = prime * result + ((pathImg == null) ? 0 : pathImg.hashCode());
        result = prime * result + ((senha == null) ? 0 : senha.hashCode());
        result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UsuarioModel other = (UsuarioModel) obj;
        if (fotoUsuario == null) {
            if (other.fotoUsuario != null)
                return false;
        } else if (!fotoUsuario.equals(other.fotoUsuario))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (idPerfil == null) {
            if (other.idPerfil != null)
                return false;
        } else if (!idPerfil.equals(other.idPerfil))
            return false;
        if (pathImg == null) {
            if (other.pathImg != null)
                return false;
        } else if (!pathImg.equals(other.pathImg))
            return false;
        if (senha == null) {
            if (other.senha != null)
                return false;
        } else if (!senha.equals(other.senha))
            return false;
        if (usuario == null) {
            if (other.usuario != null)
                return false;
        } else if (!usuario.equals(other.usuario))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "usuario [id=" + id + ", usuario=" + usuario + ", senha=" + senha + ", idPerfil=" + idPerfil
                + ", fotoUsuario=" + fotoUsuario + ", pathImg=" + pathImg + "]";
    }
}
