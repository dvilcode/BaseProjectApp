package $packageName.security;

import java.util.Date;

public class PortalColaborador {
	private String activo;
	private long consecutivo;
	private Date fechaSys;
	private String emplid;
	private String usuario;

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public long getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(long consecutivo) {
		this.consecutivo = consecutivo;
	}

	public Date getFechaSys() {
		return fechaSys;
	}

	public void setFechaSys(Date fechaSys) {
		this.fechaSys = fechaSys;
	}

	public String getEmplid() {
		return emplid;
	}

	public void setEmplid(String emplid) {
		this.emplid = emplid;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
