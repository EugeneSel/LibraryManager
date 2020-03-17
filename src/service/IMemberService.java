package service;

import model.Member;
import java.util.List;


import exception.ServiceException;

public interface IMemberService {
	public List<Member> getList() throws ServiceException;
	public List<Member> getListMembreEmpruntPossible() throws ServiceException;
	public Member getById(int id) throws ServiceException;
	public int create(Member membre) throws ServiceException;
	public void update(Member membre) throws ServiceException;
	public void delete(int id) throws ServiceException;
	public int count() throws ServiceException;
}
