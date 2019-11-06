package $packageName.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import $packageName.interfaces.services.ISecurityService;
import $packageName.security.PeopleSoftDaoLocal;
import $packageName.security.PortalColaborador;
import $packageName.security.PortalColaboradorDaoLocal;
import $packageName.security.PortalEstudiante;
import $packageName.security.PortalEstudianteDaoLocal;

@Service
public class SecurityService implements ISecurityService {
	@Autowired
	PeopleSoftDaoLocal peopleSoftDao;

	@Autowired
	PortalEstudianteDaoLocal portalEstudianteDao;

	@Autowired
	PortalColaboradorDaoLocal portalColaboradorDao;

	@Override
	public PortalEstudiante procesar(String p_idEntrada) {
		PortalEstudiante portal = portalEstudianteDao.read(p_idEntrada);
		if (portal != null) {
			if (portal.getActivo().equalsIgnoreCase("Si")) {
				portalEstudianteDao.updateTicket(portal.getEmplid());
				return portal;
			}
		}
		return null;
	}

	@Override
	public PortalColaborador procesarPortalColaborador(String p_idEntrada) {
		PortalColaborador portal = portalColaboradorDao.read(p_idEntrada);
		if (portal != null) {
			if (portal.getActivo().equalsIgnoreCase("Si")) {
				portalColaboradorDao.updateTicket(portal.getEmplid());
				return portal;
			}
		}
		return null;
	}

	// Getters and Setters
	public PortalEstudianteDaoLocal getPortalEstudianteDao() {
		return portalEstudianteDao;
	}

	public void setPortalEstudianteDao(PortalEstudianteDaoLocal portalEstudianteDao) {
		this.portalEstudianteDao = portalEstudianteDao;
	}

	@Override
	public String procesar(String usuario, int sec) {
		return peopleSoftDao.read(usuario, sec);
	}

	public PeopleSoftDaoLocal getPeopleSoftDao() {
		return peopleSoftDao;
	}

	public void setPeopleSoftDao(PeopleSoftDaoLocal peopleSoftDao) {
		this.peopleSoftDao = peopleSoftDao;
	}

	@Override
	public List<String> Roles(String usuario) {
		// TODO Auto-generated method stub
		return this.peopleSoftDao.Roles(usuario);
	}

	@Override
	public String findEmplId(String userName, int secuencia) throws Exception {
		// TODO Auto-generated method stub
		return peopleSoftDao.findEmplId(userName, secuencia);
	}
}
