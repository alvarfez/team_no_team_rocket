package team_no_team_rocket;


/** Esta clase abstracta permitir� crear instancias de tipo usuario_admin, usuario_bar, usuario_final
 * @author Ander
 *
 */
public abstract class Usuario {

	private String categoria;
	private String nombre;
	private String codigo;
	private String contrase�a;
	
	
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}public String getContrase�a() {
		return contrase�a;
	}
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	
	
}
